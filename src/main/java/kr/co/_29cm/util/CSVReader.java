package kr.co._29cm.util;

import java.io.*;
import java.util.*;

public class CSVReader {
    public static List<List<String>> readCSV(String filePath) {

        List<List<String>> csvList = new ArrayList<List<String>>();
        File csv = new File(filePath);
        BufferedReader br = null;
        String line = "";

        try {
            br = new BufferedReader(new FileReader(csv));
            while ((line = br.readLine()) != null) {
                List<String> aLine = new ArrayList<String>();
                String[] lineArr = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)",-1);;

                aLine = Arrays.asList(lineArr);

                csvList.add(aLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
        return csvList;
    }
}