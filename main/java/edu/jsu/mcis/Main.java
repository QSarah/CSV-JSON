package edu.jsu.mcis;

import java.io.*;

public class Main {
    public static String JsonString;
    public static String CsvString;
    
    public static void main(String[] args) {
        runConversions();
    }
    
    public static void runConversions(){
         ClassLoader loader = ClassLoader.getSystemClassLoader();
         StringBuilder csvContents = new StringBuilder();
         
         try(BufferedReader reader = new BufferedReader(new InputStreamReader(loader.getResourceAsStream("grades.csv")))) {
             String csvline;
             while((csvline = reader.readLine()) != null) {
                 csvContents.append(csvline).append('\n');
             }
         }
         catch(IOException e) {}
         
         String testCsv = csvContents.toString();
         StringBuilder jsonContents = new StringBuilder();
         
         try(BufferedReader reader = new BufferedReader(new InputStreamReader(loader.getResourceAsStream("grades.json")))) {
            String jsonline;
            while((jsonline = reader.readLine()) != null) {
                jsonContents.append(jsonline).append('\n');
            }
        }
        
         catch(IOException e) {}
         
         String testJson = jsonContents.toString();
         JsonString = Converter.csvToJson(testCsv);
         System.out.println(JsonString);
         System.out.println("\n----------------\n");
         
         CsvString = Converter.jsonToCsv(testJson);
         System.out.println(CsvString);
    }
}