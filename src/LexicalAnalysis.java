import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class LexicalAnalysis
{
    public static void main(String[] args)
    {   Scanner scanner = new Scanner(System.in);
        System.out.println("COMPILER CONSTRUCTION");
        System.out.println("SELECT 1 for LEXICAL ANALYZER");
        System.out.println("SELECT 2 FOR PARSING");
        int Choose = scanner.nextInt();
        scanner.nextLine();
        if(Choose == 1){
        ArrayList<String> SymbolTable = new ArrayList<>(Arrays.asList("int","char","string","if","else","do","while")) ;

        int lineNumber=0;
        System.out.print("Please enter the file name or type quit to exit: ");
        String fileName = scanner.nextLine();
        // check if user wants to quit the program
        if (fileName.equalsIgnoreCase("quit"))
        {
            System.out.println("Program terminated.");
        }
        //setup the file
        File f = new File(fileName);
        if (f.exists())
        {
            //is it a file
            if (f.isFile() && f.canRead())
            {
                Scanner input = null;
                try {
                    input = new Scanner(f);
                    while (input.hasNextLine()) {
                        lineNumber++;
                        String words = input.nextLine();
                        words = words.replaceAll("//.*|/\\*.*\\/", " ");
                        String[] tokens = words.split("\\s+");
                        for (int i = 0; i < tokens.length; i++) {

                            //CHECKS identifier or key word
                            if (tokens[i].matches("^[a-zA-Z_][a-zA-Z0-9_-]*$")) {
                                if (tokens[i].equals("int") || tokens[i].equals("char") || tokens[i].equals("string") || tokens[i].equals("if")
                                        || tokens[i].equals("else") || tokens[i].equals("do") || tokens[i].equals("while")) {
                                    if (SymbolTable.contains(tokens[i])) {

                                    } else {
                                        SymbolTable.add(tokens[i]);
                                    }
                                    System.out.println("Line" + lineNumber + ": " + (words.indexOf(tokens[i]) + 1) + " Keyword: " + tokens[i]);
                                } else {
                                    if (SymbolTable.contains(tokens[i])) {

                                    } else {
                                        SymbolTable.add(tokens[i]);
                                    }
                                    System.out.println("Line" + lineNumber + ": " + (words.indexOf(tokens[i]) + 1) + " Identifier: " + tokens[i]);
                                }
                               // CHECKS Relational Operators
                            } else if (tokens[i].contains(">") || tokens[i].contains("<") || tokens[i].contains(">=") || tokens[i].contains("<=") || tokens[i].contains("==")) {

                                System.out.println("Line" + lineNumber + ": " + (words.indexOf(tokens[i]) + 1) + " Relational Operator: " + tokens[i]);
                            }
                            //CHECKS operators
                            else if (tokens[i].contains("+") || tokens[i].contains("-") || tokens[i].contains("*") || tokens[i].contains("/") || tokens[i].contains("=")) {
                                System.out.println("Line" + lineNumber + ": " + (words.indexOf(tokens[i]) + 1) + " Arithemtic Operator: " + tokens[i]);
                            }
                            //CHECKS int constant
                            else if (tokens[i].matches("-?[0-9]+")) {
                                if (SymbolTable.contains(tokens[i])) {

                                } else {
                                    SymbolTable.add(tokens[i]);
                                }
                                System.out.println("Line" + lineNumber + ": " + (words.indexOf(tokens[i]) + 1) + " Int constant: " + tokens[i]);
                            }
                            // CHECKS double & float constant
                            else if (tokens[i].matches("^[-+]?[0-9]*\\.?[0-9]+$")) {
                                if (SymbolTable.contains(tokens[i])) {

                                } else {
                                    SymbolTable.add(tokens[i]);
                                }
                                System.out.println("Line" + lineNumber + ": " + (words.indexOf(tokens[i]) + 1) + " Double constant: " + tokens[i]);
                            }
                            // CHECKS String constant \"([^\\\"]|\\.)*\"
                            else if (tokens[i].matches("\"((\\\\\")|[^\"(\\\\\")])+\"")) {
                                if (SymbolTable.contains(tokens[i])) {

                                } else {
                                    SymbolTable.add(tokens[i]);
                                }
                                System.out.println("Line" + lineNumber + ": " + (words.indexOf(tokens[i]) + 1) + " String Literal: " + tokens[i]);
                            }
                            // CHECKS Extras
                            else if (tokens[i].contains("(") || tokens[i].contains(")") || tokens[i].contains(";") || tokens[i].contains("{") || tokens[i].contains("}")) {

                                System.out.println("Line" + lineNumber + ": " + (words.indexOf(tokens[i]) + 1) + " Extras: " + tokens[i]);
                            } else {
                                System.err.println("Line" + lineNumber + ": " + (words.indexOf(tokens[i]) + 1) + " Error:" + tokens[i] + " not recognized");
                            }

                        }
                    }
                    System.out.println("*************************************");
                    System.out.println("*******SYMBOL TABLE*******");
                    int index=0;
                    System.out.println("AT_VALUE - TOKEN");
                    for(String s : SymbolTable)
                        System.out.println((index++)+"   :   "+s);
                }

                catch (FileNotFoundException e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    if (input != null)
                    {
                        input.close();
                    }
                }
            }
            else
            {
                System.out.println("Cannot read the file.");
            }

        }
        else
        {
            System.out.println("The file does not exist.");
        }
    }
        else if(Choose == 2){
    System.out.println("SORRY THERE IS NO PARSER IN THIS COMPILER :( ");
}
}}
