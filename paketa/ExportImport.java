package paketa;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;

public class ExportImport {
    static final String NL = System.getProperty("line.separator");

    public void exportKey(String type, String user, String filepath) throws Exception {
        String name = null;
        if (type.equals("public")) {
            name = "./keys/" + user + ".pub.xml";
        } else if (type.equals("private")) {
            name = "./keys/" + user + ".xml";
        }
        if (filepath == null) {
            System.out.println(readFile(name));
        } else {
            String text = readFile(name, type);
            writeFile(text, filepath);
            deleteUser(name);
        }
    }

    public void importKey(String user,String filePath) throws FileNotFoundException {
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getElement(String name, String type) throws Exception {
        return String.format("  <%s>%s</%s>%s", name, type, name, NL);
    }

    public String readFile(String fileName, String type) throws FileNotFoundException {
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
                System.out.println("Gabim: Celesi "+ type + " '"+fileName+"' nuk ekziston.");
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
            System.out.println("Gabim tek fshierja: Celesi '"+ user +"' nuk ekziston.");
    }
}