package main.java.edu.neu.coe.info6205.sorting.sorts;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class FileUtil {

    public static boolean hindiWordsList(String fileName) throws IOException {
        List<String> hindiWords = new ArrayList<>();
        FileReader fr = new FileReader(fileName);
        MSD msd=new MSD();
        QuickDualPivot qdp = new QuickDualPivot();
        LSD lsd = new LSD();


        try (BufferedReader br = new BufferedReader(fr)) {

            String line = br.readLine();

            while(line != null) {
                //System.out.println(line);
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

        //String[]
//        msd.sort(hWords);
//        System.out.println("MSD sorted:");
//        for(String s: hWords)
//            System.out.println(s + "  ");
//        System.out.println("----------------------------------------------");
        System.out.println("Dual pivot sorted");
        qdp.sort(hWords);
        for(String s: hWords)
            System.out.println(s + "  ");
        System.out.println("-----------------------------------------------");
//        System.out.println("LSD sorted");
//        lsd.sort(hWords);
        for(String s: hWords)
            System.out.println(s + "  ");
        return true;
    }

    public static String extractName(String[] words){
        String word = words[0];
        return word;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(hindiWordsList("D:\\hindiText.txt"));

    }
}