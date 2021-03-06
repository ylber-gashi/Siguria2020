package paketa;

import javax.sound.sampled.LineUnavailableException;
import java.io.IOException;

public class ds {
    public static void main(String[] args)throws Exception,ArrayIndexOutOfBoundsException, IOException, LineUnavailableException, InterruptedException
    {
        Caesar caesar = new Caesar();
        MorseCode morseCode=new MorseCode();
        Count count=new Count();
        CreateDelete cd = new CreateDelete();
        ExportImport ex =  new ExportImport();
        WriteRead wr = new WriteRead();
        LoginStatus lg = new LoginStatus();
        if(args.length < 2 || args.length >6){
           System.out.println("Numri i argumenteve nuk eshte i mjaftueshem.");
           System.exit(1);
        }
        
        else if ("morse-code".equals(args[0])){
            if("encode".equals(args[1]))
                System.out.println(morseCode.encode(args[2]));
            
            else if("decode".equals(args[1]))
                System.out.println(morseCode.decode(args[2]));
            
            else if("beep".equals(args[1]))
                morseCode.morseAudio(args[2]);
            
            else{
                System.out.println("\nInvalid arguments. \nPlease make sure to write the arguments as follows: \n\n" +
                        "morse-code encode/decode <text>/<morse code> \n" + "To use the beep function:\n" +
                        "morse-code beep <morse code>\n");
                System.exit(1);
            }
        }
        
        else if("caesar".equals(args[0])){   
            
            if ("encrypt".equals(args[1])) {
                String cipherText = caesar.encrypt(Integer.parseInt(args[2]), args[3]);
                System.out.println("Teksti origjinal:    " + args[3]);
                System.out.println("Teksti i enkriptuar: " + cipherText);
            } 
            else if("decrypt".equals(args[1]))
            {
                String plainText = caesar.decrypt(Integer.parseInt(args[2]), args[3]);
                System.out.println("Teksti i enkriptuar: "+args[3]);
                System.out.println("Teksti origjinal:    "+plainText);
            }
            else if("brute-force".equals(args[1])){
                caesar.bruteForce(args[2]);
            }
            else{
                System.out.println("\nInvalid arguments. \nPlease make sure to write the arguments as follows: \n\n" +
                        "caesar encrypt/decrypt <key> <plainText>/<cipherText> \n" +
                        "To use the brute-force function:"+
                        "caesar brute-force <cipherText>\n\n");
                System.exit(1);
            }
        }
        
        else if("count".equals(args[0])){
            
            if ("lines".equals(args[1])) {
                System.out.println("Numri i rreshtave eshte: " + count.countLines(args[2]));
            } 
            else if ("words".equals(args[1])) {
                System.out.println("Numri i fjaleve eshte: " + count.countWords(args[2]));
            } 
            else if ("letters".equals(args[1])) {
                System.out.println("Numri i shkronjave eshte: " + count.countLetters(args[2]));
            } 
            else if ("characters".equals(args[1])) {
                System.out.println("Numri i karaktereve eshte: " + count.countCharacters(args[2]));
            } 
            else if ("symbols".equals(args[1])) {
                System.out.println("Numri i simboleve eshte: " + count.countSymbols(args[2]));
            } 
            else if ("vowels".equals(args[1])) {
                System.out.println("Numri i zanoreve eshte: " + count.countVowels(args[2]));
            } 
            else if ("consonants".equals(args[1])) {
                System.out.println("Numri i bashtingelloreve eshte: " + count.countConsonants(args[2]));
            } 
            else if ("spaces".equals(args[1])) {
                System.out.println("Numri i hapesirave eshte: " + count.countSpaces(args[2]));
            }
            else {
                System.out.println("\nInvalid arguments. \nPlease make sure to write the arguments as follows:  \n"+
                        "count lines <text>      : to count the number of lines\n" +
                        "count words <text>      : to count the number of words\n" +
                        "count letters <text>      : to count the number of letters\n" +
                        "count characters <text> : to count the number of characters\n"+
                        "count symbols <text>    : to count the number of symbols\n"+
                        "count consonants <text> : to count the number of consonants\n" +
                        "count spaces <text>     : to count the number of consonants\n\n");
                System.exit(1);
            }
        }
        else if("export-key".equals(args[0])){
            if(args.length == 3 && (args[1].equals("public") || args[1].equals("private"))){
                ex.exportKey(args[1],args[2],null);
            }else if (args.length == 4 && (args[1].equals("public") || args[1].equals("private")))
                ex.exportKey(args[1],args[2],args[3]);
            else {
                System.out.println("Nuk jane dhene komanda valide.\n");
                System.out.println("Komanda export-key duhet te jepet sipas kesaj skeme: \n" +
                        "Nese duam vetem ta lexojme celesin:                      export-key <public|private> <name>\n" +
                        "Nese duam ta eksportojme celesin ne ndonje fajll tjeter: export-key <public|private> <name> [file]");
            }
        }
        else if("import-key".equals(args[0])){
            if(args.length == 3){
                ex.importKey(args[1],args[2]);
            }
            else {
                System.out.println("Nuk jane dhene komanda valide.\n");
                System.out.println("Komanda import-key duhet te jepet sipas kesaj skeme: import-key <name> <path>\n");
           }
        }
        else if("create-user".equals(args[0])){
            if(args.length == 2){
                cd.saveKeys(args[1]);
            }else{
                System.out.println("Nuk jane dhene komanda valide.\n");
                System.out.println("Komanda create-user duhet te jepet sipas kesaj skeme: \n" +
                        "create-user <name> \n");
            }
        }
        else if("delete-user".equals(args[0])){
            if(args.length == 2){
                cd.deleteUser(args[1]);
            }else{
                System.out.println("Nuk jane dhene komanda valide.\n");
                System.out.println("Komanda delete-user duhet te jepet sipas kesaj skeme: \n" +
                        "delete-user <name> \n");
            }
        }
        else if("write-message".equals(args[0])){
            if(args.length == 4){
                wr.Write(args[1],args[2],args[3],null);
            }else if(args.length == 3){
                wr.Write(args[1],args[2],null,null);
            }else if(args.length == 6){
                if("--sender".equals(args[4])){
                    wr.Write(args[1],args[2],args[3],args[5]);
                }else
                    System.out.println("Nuk jane dhene komanda valide.");
            }else if(args.length == 5){
                if("--sender".equals(args[3])){
                    wr.Write(args[1],args[2],null,args[4]);
                }else
                    System.out.println("LOPO");
            }else{
                System.out.println("Nuk jane dhene komanda valide.\n");
                System.out.println("Komanda write-message nese deshironi vetem t'a enkriptoni mesazhin tuaj duhet te jepet sipas kesaj skeme: \n" +
                        "'write-message <name> <message>' \n");
                System.out.println("Komanda write-message nese deshironi vetem t'a ruani mesazhin e enkriptuar ne nje file duhet te jepet sipas kesaj skeme: \n" +
                        "write-message <name> <message> [file]' \n");
                System.out.println("Komanda write-message nese deshironi vetem t'a ruani mesazhin e enkriptuar ne nje file duhet te jepet sipas kesaj skeme: \n" +
                        "write-message <name> <message> [file]' --sender <token>\n");
            }
        } else if("read-message".equals(args[0])){
            if(args.length == 2) {
                wr.Read(args[1]);
            }else{
                System.out.println("Nuk jane dhene komanda valide.\n");
                System.out.println("Komanda read-message duhet te jepet sipas kesaj skeme: \n" +
                        "' read-message <encrypted-message>' \n");
            }
        }
        else if("login".equals(args[0])){
            if(args.length == 2){
                lg.login(args[1]);
            }else{
                System.out.println("Nuk jane dhene komanda valide.\n");
                System.out.println("Komanda login duhet te jepet sipas kesaj skeme: \\n\" +\n\"' login <name>' \\n");
            }
        }else if("status".equals(args[0])){
            if(args.length == 2){
                lg.status(args[1]);
            }else {
                System.out.println("Nuk jane dhene komanda valide.\n");
                System.out.println("Komanda status duhet te jepet sipas kesaj skeme: \\n\" +\n\"' status <token>' \\n");
            }
        }

        else {
            System.out.println("\nInvalid arguments. Please use one of the program's functions as the first argument: \n" +
                    " morse-code\n" +
                    " caesar\n" +
                    " count\n" + 
                    " create-user\n" + 
                    " delete-user\n" +
                    " export-key\n" + 
                    " import-key\n" + 
                    " write-message\n" +
                    " read-message\n\n");
            System.exit(1);
        }
    }
}

