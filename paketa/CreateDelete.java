package paketa;

import java.io.Console;
import java.io.File;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.*;
import java.security.spec.RSAPrivateCrtKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateDelete {
    static final String KEY_ALGORITHM = "RSA";
    static final int KEY_LENGTH = 1024;
    static final String NL = System.getProperty("line.separator");

    public void saveKeys(String user) throws Exception {
        KeyPair keyPair = createKeyPair(KEY_LENGTH);
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        String privateKeyAsXml = savePrivateKeyAsXml(privateKey);
        File fajlliPub = new File("keys/" +user + ".pub.xml");
        File fajlliPriv = new File("keys/" +user + ".xml");
        if(!(fajlliPub.exists() && fajlliPriv.exists())) {
            System.out.print("Jepni fjalekalimin: ");
            Console console1 = System.console();
            char[] passChar = console1.readPassword();
            String pass = String.valueOf(passChar);
            if(validatePassword(pass))
            {
                System.out.print("Perserit fjalekalimin: ");
                Console console2 = System.console();
                char[] passChar2 = console2.readPassword();
                String repeatPass = String.valueOf(passChar2);
                if(repeatPass.equals(pass)) {
                    byte[] salt = getSalt();
                    String password = SHA1SecurePassword(pass, salt);
                    String saltedString = Base64.getEncoder().encodeToString(salt);
                    insertUser(user, saltedString, password);
                    writeFile(privateKeyAsXml, "./keys/" + user + ".xml");
                    String publicKeyAsXml = savePublicKeyAsXml(publicKey);
                    writeFile(publicKeyAsXml, "./keys/" + user + ".pub.xml");
                    System.out.println("Eshte krijuar shfrytezuesi '" + user + "'");
                    System.out.println("Eshte krijuar celesi publik: " + user + ".pub.xml");
                    System.out.println("Eshte krijuar celesi privat: " + user + ".xml");
                }else {
                    System.out.println("Fjalekalimet nuk perputhen.");
                }
            }
            else {
                System.out.println("Gabim: Fjalekalimi duhet te permbaje se paku nje numer ose simbol.");
            }
        }
        else{
            System.out.println("Gabim: Shfrytezuesi '"+user+"' ekziston paraprakisht.");
        }
    }

    public KeyPair createKeyPair(int keyLength) throws NoSuchAlgorithmException {
        KeyPairGenerator keygen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keygen.initialize(keyLength, new SecureRandom());
        KeyPair keyPair = keygen.generateKeyPair();
        return keyPair;
    }
    public boolean validatePassword(String password){
        String regex = "^(?=.*[0-9])(?=.*[a-z]).{8,20}$";
        Pattern pattern = Pattern.compile(regex);

        if(password == null){
            return false;
        }

        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
    public byte[] getSalt() throws NoSuchAlgorithmException
    {
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }
    public String SHA1SecurePassword(String passwordToHash, byte[] salt)
    {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(salt);
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return generatedPassword;
    }
    public void insertUser(String user, String salt, String password) throws Exception {
        String query = "INSERT INTO users(username,salt, passwordd) VALUES('"+user+"','"+salt+"','"+password+"');";

        Connection connection = dbConnection.getConnection();
        Statement statement = connection.createStatement();
        statement.execute(query);

    }
     public void deleteUserFromDB(String user) throws Exception {
        String query = "DELETE FROM users WHERE username = '"+user+"';";
        Connection connection = dbConnection.getConnection();
        Statement statement = connection.createStatement();
        int affectedRows = statement.executeUpdate(query,Statement.RETURN_GENERATED_KEYS);
        if(affectedRows > 0 ){
            System.out.println("Shfrytezuesi eshte fshire nga databaza.");
        }
    }
    
    public String savePrivateKeyAsXml(PrivateKey privateKey) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        RSAPrivateCrtKeySpec spec = keyFactory.getKeySpec(privateKey, RSAPrivateCrtKeySpec.class);
        StringBuilder sb = new StringBuilder();

        sb.append("<RSAKeyValue>" + NL);
        sb.append(getElement("Modulus", spec.getModulus()));
        sb.append(getElement("Exponent", spec.getPublicExponent()));
        sb.append(getElement("P", spec.getPrimeP()));
        sb.append(getElement("Q", spec.getPrimeQ()));
        sb.append(getElement("DP", spec.getPrimeExponentP()));
        sb.append(getElement("DQ", spec.getPrimeExponentQ()));
        sb.append(getElement("InverseQ", spec.getCrtCoefficient()));
        sb.append(getElement("D", spec.getPrivateExponent()));
        sb.append("</RSAKeyValue>");

        return sb.toString();
    }

    public String savePublicKeyAsXml(PublicKey publicKey) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        RSAPublicKeySpec spec = keyFactory.getKeySpec(publicKey, RSAPublicKeySpec.class);
        StringBuilder sb = new StringBuilder();

        sb.append("<RSAKeyValue>" + NL);
        sb.append(getElement("Modulus", spec.getModulus()));
        sb.append(getElement("Exponent", spec.getPublicExponent()));
        sb.append("</RSAKeyValue>");

        return sb.toString();
    }

    public String getElement(String parameter, BigInteger bigInt) throws Exception {

        byte[] bytesFromBigInt = bigInt.toByteArray();
        String elementContent = Base64.getEncoder().encodeToString(bytesFromBigInt);
        return String.format("  <%s>%s</%s>%s", parameter, elementContent, parameter, NL);
    }

    public void deleteUser(String user) throws Exception {
        File publik = new File("./keys/"+user+".pub.xml");
        File privat = new File("./keys/"+user+".xml");
        if (user.equals(user) && (publik.exists() || privat.exists())) {
            if (publik.delete()) {
                System.out.println("Eshte larguar celesi publik: '" + "keys/" + user + ".pub.xml" + "' ");
            }
            if (privat.delete()) {
                System.out.println("Eshte larguar celesi privat: '"+"keys/"+ user +".xml"+"' \n");
            }
            deleteUserFromDB(user);
        }
        else
            System.out.println("Shfrytezuesi '"+user+"' nuk ekziston");
    }

    public void writeFile(String text, String filename) throws Exception{
        try(PrintWriter writer = new PrintWriter(filename)){
            writer.write(text);
        }
    }
}
