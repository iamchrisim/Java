import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

//Im_Dohoo

public class FileEncryption {

    public static void main (String args []){
    
		mainMenu();
    }
    //test method
    public static void mainMenu (){
        Scanner input = new Scanner(System.in);
		
        //header
        System.out.println("-------------------------------------------");
        System.out.println("This is encryption/decryption program");
        
        //asking user what they want to do
        System.out.print("Please choose what would you like to do: (E)ncrypt / (D)ecrypt /(EX)it: ");
        String answer = input.nextLine();
        
        //checking user answer
        while(!answer.equalsIgnoreCase("E")  ||!answer.equalsIgnoreCase("D") ){
			
                        //encrypting
			if (answer.equalsIgnoreCase("E")) { 
                        //input file name
			System.out.print("File name of the file you would like to encrypt ex)name.extension: ");
                        String inputFileName = input.nextLine();
						
                        //output file name
                        System.out.print("File name of the output file ex)name.extension: ");
                        String outputFileName = input.nextLine();
                         
                        //encryption happens here
                        save(encrypt(read(inputFileName)),outputFileName);
                        break;
                        
                         //decrypting
			}	else if (answer.equalsIgnoreCase("D")){
                        //input file name
                        System.out.print("File name of the file you would like to decrypt ex)name.extension: ");
                        String inputFileName = input.nextLine();
                         
                        //output file name
                        System.out.print("File name of the output file: ex:name.extension: ");
                        String outputFileName = input.next(); 
                         
                        //decryption happens here
                        save(decrypt(read(inputFileName)),outputFileName);
                        System.out.println("-------------------------------------------");
                        System.out.println("File successfully decrypted.\nResults saved in: \""+outputFileName+"\"");
                        //ask user if they would like to go back to menu
                        backToMenu();
                        break;
                        
                        //exit if user enters ex 
                }	else if (answer.equalsIgnoreCase("EX")){
                        System.out.println("OK, bye!");
                        System.exit(0);
                        
                        }	else{                            
								System.out.print("Wrong input! Try E or D or EX:");
								answer = input.nextLine();
							}
        }
    }
    //encrypt method; accepts 1 string, returns 1 string (accepts decrypted string, ouputs encrypted)
    public static String encrypt(String s){

        char[] alphabetU = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();//array with uppercase alphabet
        char[] alphabetL = "abcdefghijklmnopqrstuvwxyz".toCharArray();//array with lowercase alphabet
        char[] encKeyU = "KNGCADSXBVFHJTIUMYLZQROPWE".toCharArray();//array with uppercacase key
        char[] encKeyL = "kngcadsxbvfhjtiumylzqropwe".toCharArray();//array with lowercase key
        String newString = null;    
        char[] charArray = s.toCharArray();

        //looping trough string
        for (int i = 0;i < s.length(); i++){
          //assigning char at inedex of i to ch
          char ch = s.charAt(i);
          //checking if char is a letter
          if (Character.isLetter(ch)){
               //encrypting uppercase leters
               if (Character.isUpperCase(ch)){
					for (int l = 0; l <  alphabetU.length; l++) {
						if ( ch == alphabetU[l]) {
							ch = encKeyU[l];
							charArray[i] = ch;
							break;
                        }
                    }//encrypting lowercase leters
                }else if (Character.isLowerCase(ch)){
					for (int l = 0; l <  alphabetL.length; l++) {
						if ( ch == alphabetL[l]) {
							ch = encKeyL[l];
							charArray[i] = ch;
							break;
						}
					}
					}
			}
        }
        String output  = new String(charArray);
        return output;
    }
    //decrypt method; accepts 1 string, returns 1 string(accepts encrypted string, ouputs decrypted)
    public static String decrypt(String s){

        char[] alphabetU = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();//array with uppercase alphabet
        char[] alphabetL = "abcdefghijklmnopqrstuvwxyz".toCharArray();//array with lowercase alphabet
        char[] encKeyU = "KNGCADSXBVFHJTIUMYLZQROPWE".toCharArray();//array with uppercacase key
        char[] encKeyL = "kngcadsxbvfhjtiumylzqropwe".toCharArray();//array with lowercase key

        String newString = null;    
        char[] charArray = s.toCharArray();
		
        //looping trough string
        for (int i = 0;i < s.length(); i++){
			//assigning char at inedex of i to ch
			char ch = s.charAt(i);
			//checking if char is a letter
			if (Character.isLetter(ch)){
				//if char is uppercase
				if (Character.isUpperCase(ch)){
					//decrypting uppercase leters
					for (int l = 0; l <  alphabetU.length; l++) {
						if ( ch ==encKeyU[l]) {
							ch = alphabetU[l];
							charArray[i] = ch;
							break;
                        }
                    }
					//decrypting lowercase letters
					}else if (Character.isLowerCase(ch)){
						for (int l = 0; l <  alphabetL.length; l++) {
							if ( ch == encKeyL[l]) { //check at what index encrypted char is
								ch = alphabetL[l]; //changig char  from key char to alphabet char at index of encypted char
								charArray[i] = ch;
								break;
							}
						}
					}
            }
        }
        String output  = new String(charArray);
        return output;
    }
    //Read Method(reads text from textfile in one string)
    public static String read(String inputPath){
        byte[] encoded = null;
        try {
           encoded = Files.readAllBytes(Paths.get(inputPath));  
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + inputPath + "'");
            backToMenu();
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + inputPath + "'");
            backToMenu();
        }
         String rString = "";
        if (!(encoded == null)){
          rString = new String(encoded);
        }
        return rString;
    }
    public static void save(String text,String outputPath){
      
        PrintStream out = null;
        try {
            File file = new File(outputPath);
            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(text);
            bw.close();
            System.out.println("-----------------------------------------");
            System.out.println("File successfully encrypted.\nResults saved in: \""+outputPath+"\"");
            //ask user if they would like to go back to menu
            backToMenu();
        }catch(IOException ex){
            System.out.println("Error reading file '" + outputPath + "'");
            backToMenu();
        }finally {
            if (!(out==null)){
                out.close();
            }
        }
    }
    public static void backToMenu (){
        String answer ="";
        Scanner input = new Scanner(System.in);
        System.out.println ("Do you want to go back to menu? (Y)es/(N)o");
        answer = input.nextLine();
        
        while(!answer.equals("y")  ||!answer.equals("n") ){
            if (answer.equalsIgnoreCase("y")) {
                mainMenu();
            }else if (answer.equalsIgnoreCase("n")){
                System.out.println("OK, bye");
                System.exit(0);
            }else{                            
                System.out.println("Wrong input! Try Y/N");
                answer = input.nextLine();
            }                     
        }
    }
}