package edu.neu.coe.info6205.sort.counting;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import edu.neu.coe.info6205.sort.huskySort.sort.huskySort.PureHuskySort;

import java.util.*;

public class FileUtil {

    public static String[] hindiWordsList(String fileName) throws IOException {
        List<String> hindiWords = new ArrayList<>();
        FileReader fr = new FileReader(fileName);


        try (BufferedReader br = new BufferedReader(fr)) {

            String line = br.readLine();

            while(line != null) {
                String[] names = line.split(",");
                String word = extractName(names);
                hindiWords.add(line);
                line = br.readLine();
            }


        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        String[] hWords=new String[hindiWords.size()];
        hWords= hindiWords.toArray(hWords);
        return hWords;
    }

    public static String extractName(String[] words){
        String word = words[0];
        return word;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(hindiWordsList("/Users/chandrakanthyadav/Downloads/hindiText.txt"));

    }
}