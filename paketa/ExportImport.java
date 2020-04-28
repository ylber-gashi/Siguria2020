package paketa;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;

public class ExportImport {
    static final String NL = System.getProperty("line.separator");

    public void exportKey(String type, String user, String filepath) throws Exception {
        String name = null;
        if (type.equals("public")) {
            name = "../keys/" + user + ".pub.xml";
        } else if (type.equals("private")) {
            name = "../keys/" + user + ".xml";
        }
        if (filepath == null) {
            System.out.println(readFile(name, user, type));
        } else {
            String text = readFile(name, user, type);
            writeFile(text, filepath);
            deleteUser(name);
        }
    }

    public void importKey(String user,String filePath) throws FileNotFoundException {
        try {
            File file = new File(filePath);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            org.w3c.dom.Document doc = db.parse(file);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("RSAKeyValue");
            Node node = nodeList.item(0);

            Element eElement = (Element) node;
            String modulus = eElement.getElementsByTagName("Modulus").item(0).getTextContent();
            String exponent = eElement.getElementsByTagName("Exponent").item(0).getTextContent();

            if (eElement.getElementsByTagName("P") > 0) {
                StringBuilder sb = new StringBuilder();

                sb.append("<RSAKeyValue>" + NL);
                sb.append(getElement("Modulus", modulus));
                sb.append(getElement("Exponent", exponent));
                sb.append("</RSAKeyValue>");

                String x = "../keys/" + user + ".pub.xml";
                writeFile(sb.toString(), x);
                String text = readFile(filePath);
                writeFile(text, "keys/" + user + ".xml");
            } else {
                String text = readFile(filePath);
                writeFile("../keys/" + user + ".pub.xml", text);
            } catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public String getElement(String name, String type) throws Exception {
        return String.format("  <%s>%s</%s>%s", name, type, name, NL);
    }

    public String readFile(String fileName, String user, String type) throws FileNotFoundException {
        StringBuilder sb = new StringBuilder();
        try {
            File file = new File(fileName);
            if ((file.exists())){
                Scanner reader = new Scanner(file);
                while(reader.hasNextLine()){
                    sb.append(reader.nextLine() + "\n");
                }
                reader.close();
            }
            else
                sb.append("Gabim: Celesi " + type +" '" + user + "' nuk ekziston.");
        }catch (Exception e){
            e.printStackTrace();
        }
        return sb.toString();
    }
    public void writeFile(String text, String filename) throws Exception{
        PrintWriter writer = new PrintWriter(filename);
        try{
            writer.write(text);
            System.out.println("Celesi publik u ruajt ne fajllin '" + filename + "'.");
        }catch (Exception e){
            e.getMessage();
        }finally {
            writer.close();
        }

    }

    public void deleteUser(String user) {
        File file = new File(user);
        if ((file.exists())) {
            file.delete();
        } else
            System.out.println("Gabim tek fshirja: Celesi '"+ user +"' nuk ekziston.");
    }

    //Metoda per ta marre celesin nga url
    public String sendGET(String url) throws IOException {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent","Mozilla/5.0");
        int responseCode = con.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
                response.append("\n");
            }
            in.close();
            return response.toString();
        } else {
            return "GET request not worked";
        }
    }


}