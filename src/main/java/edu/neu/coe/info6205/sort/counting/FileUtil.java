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
        MSD msd = new MSD();
        LSD lsd = new LSD();
        QuickDualPivot qdp = new QuickDualPivot();
        HuskySortImplementation hs = new HuskySortImplementation();
        TimSort ts = new TimSort();

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

//        msd.sort(hWords);
//        lsd.sort(hWords);
//        qdp.sort(hWords);
//        hs.mergeSort(hWords);
        ts.sort(hWords);
        for(String s: hWords)
            System.out.println(s + "  ");
        System.out.println("----------------------------------------------");
        return hWords;
    }

    public static String extractName(String[] words){
        String word = words[0];
        return word;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(hindiWordsList("C:\\Users\\gvrtk\\OneDrive\\Documents\\hindiText_200.txt"));

    }
}