import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.*;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;
import java.util.Random;

public class WriteRead {
    private String name;

    public WriteRead(){}

    public WriteRead(String name) {
        this.name = name;
    }
    public PublicKey getPublicElements() throws Exception {

        File file = new File("./keys/"+ name +".pub.xml");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(file);
        doc.getDocumentElement().normalize();

        NodeList nodeList = doc.getElementsByTagName("RSAKeyValue");
        Node node = nodeList.item(0);

        Element eElement = (Element) node;
        String modulus = eElement.getElementsByTagName("Modulus").item(0).getTextContent();
        String exponent = eElement.getElementsByTagName("Exponent").item(0).getTextContent();
        KeyFactory rsaFactory = KeyFactory.getInstance("RSA");

        byte[] modBytes = modulus.getBytes();
        byte[] expBytes = exponent.getBytes();
        BigInteger modBigInt = new BigInteger(1, Base64.getDecoder().decode(modBytes));
        BigInteger expBigInt = new BigInteger(1,Base64.getDecoder().decode(expBytes));

        RSAPublicKeySpec rsaKeyspec;
        rsaKeyspec = new RSAPublicKeySpec(modBigInt,expBigInt);
        PublicKey key = rsaFactory.generatePublic(rsaKeyspec);

        return key;
    }

    public PrivateKey getPrivateElements(String user) throws Exception {
        File file = new File("../keys/"+user+".xml");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(file);
        doc.getDocumentElement().normalize();

        NodeList nodeList = doc.getElementsByTagName("RSAKeyValue");
        Node node = nodeList.item(0);

        Element eElement = (Element) node;
        String modulus = eElement.getElementsByTagName("Modulus").item(0).getTextContent();
        String d = eElement.getElementsByTagName("D").item(0).getTextContent();
        KeyFactory rsaFactory = KeyFactory.getInstance("RSA");

        byte[] modBytes = modulus.getBytes();
        byte[] dBytes = d.getBytes();
        BigInteger modBigInt = new BigInteger(1, Base64.getDecoder().decode(modBytes));
        BigInteger dBigInt = new BigInteger(1,Base64.getDecoder().decode(dBytes));

        RSAPrivateKeySpec rsaKeyspec = new RSAPrivateKeySpec(modBigInt,dBigInt);
        PrivateKey key = rsaFactory.generatePrivate(rsaKeyspec);

        return key;
    }

    public  String encrypt(String plainText) throws Exception {
        PublicKey publicKey = getPublicElements();
        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] cipherbytes = encryptCipher.doFinal(plainText.getBytes());
        String cipherText = Base64.getEncoder().encodeToString(cipherbytes);
        return cipherText;
    }

    public String decrypt(String data,String user) throws Exception {
        PrivateKey privateKey = getPrivateElements(user);
        Cipher decriptCipher = Cipher.getInstance("RSA");
        decriptCipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] bytes = Base64.getDecoder().decode(data.getBytes());
        return new String(decriptCipher.doFinal(bytes));
    }

    public IvParameterSpec generateIV(){
        byte[] iv = new byte[8];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(iv);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        return ivParameterSpec;
    }

    public SecretKeySpec generateDESKey(){
        Random random = new Random();
        byte[] keyBytes =  new byte[8];
        random.nextBytes(keyBytes);
        SecretKeySpec secretKey = new SecretKeySpec(keyBytes, "DES");
        return secretKey;
    }

    public String encryptTextDES(String plaintext,String IV, String deskey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException {
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        byte[] keyBytes = Base64.getDecoder().decode(deskey.getBytes());
        byte[] ivBytes = Base64.getDecoder().decode(IV.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(keyBytes, "DES"),new IvParameterSpec(ivBytes));
        return Base64.getEncoder().encodeToString(cipher.doFinal(plaintext.getBytes()));
    }

    public String decryptTextDES(String ciphertext,String IV, String deskey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException {
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        byte[] keyBytes = Base64.getDecoder().decode(deskey.getBytes());
        byte[] ivBytes = Base64.getDecoder().decode(IV.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(keyBytes, "DES"),new IvParameterSpec(ivBytes));
        byte[] bytes = Base64.getMimeDecoder().decode(ciphertext.getBytes());
        return new String(cipher.doFinal(bytes));
    }
}