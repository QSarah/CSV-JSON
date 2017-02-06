package edu.jsu.mcis;

import java.io.*;
import au.com.bytecode.opencsv.*;
import org.json.simple.*;
import org.json.simple.parser.*;

public class Converter {

   

    @SuppressWarnings("unchecked")
    public static String csvToJson(String csvString) {
        JSONObject json = new JSONObject();
        JSONArray data = new JSONArray();
        JSONArray colHeaders = new JSONArray();
        JSONArray rowHeaders = new JSONArray();
        colHeaders.add("Total");
        colHeaders.add("Assignment 1");
        colHeaders.add("Assignment 2");
        colHeaders.add("Exam 1");

        json.put("colHeaders", colHeaders);
        json.put("rowHeaders", rowHeaders);
        json.put("data", data);

        CSVParser parser = new CSVParser();
        BufferedReader reader = new BufferedReader(new StringReader(csvString));
        
        try {
            String cvsline = reader.readLine();
            while ((cvsline = reader.readLine()) != null) {
                String[] parseString = parser.parseLine(cvsline);
                rowHeaders.add(parseString[0]);
                JSONArray row = new JSONArray();
                row.add(new Long(parseString[1]));
                row.add(new Long(parseString[2]));
                row.add(new Long(parseString[3]));
                row.add(new Long(parseString[4]));
                data.add(row);
            }
            
        } catch (IOException e) {
        }
        
        return json.toString();

    }

    public static String jsonToCsv(String jsonString) {
         JSONObject json = null;

        try {
            JSONParser jParser = new JSONParser();
            json = (JSONObject) jParser.parse(jsonString);
        } 
        catch (Exception e) {}

        String csvString = "\"ID\"," + Converter.<String>joinArray((JSONArray) json.get("colHeaders")) + "\n";

        JSONArray rowHeader = (JSONArray) json.get("rowHeaders");
        JSONArray data = (JSONArray) json.get("data");

        for (int i = 0; i < rowHeader.size(); i++) {
            csvString = (csvString + "\"" + (String)rowHeader.get(i) + "\"," + Converter.<Long>joinArray((JSONArray) data.get(i)) + "\n");
        }
        return csvString;
    }
     @SuppressWarnings("unchecked")
     private static <T> String joinArray(JSONArray jarray) {
        String fileline = "";
        for (int i = 0; i < jarray.size(); i++) {
            fileline = (fileline + "\"" + ((T) jarray.get(i)) + "\"");
            if (i < jarray.size() - 1) {
                fileline = fileline + ",";
            }
        }
        return fileline;
    }
}
