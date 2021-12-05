package edu.neu.coe.info6205.sort.counting;



import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class HindiWords {

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        generateHindi();
    }

    public static void generateHindi() throws IOException
    {
        FileWriter myWriter = new FileWriter("D:\\hindiText.txt");
        char[] ca = {'\u0914'};  //first letter in Hindi script
        char[] vo = {'\u0904'};
        char[] ma = {'\u093E'};
        ArrayList<Character> hindiConsonants=new ArrayList<>();
        ArrayList<Character> hindiVowels=new ArrayList<>();
        ArrayList<Character> hindiMatras=new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                new String(ma);
                ma[0]++;
                hindiMatras.add(ma[0]);
            }

        }
        //System.out.println(hindiMatras.toString());

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 9; j++) {
                new String(ca);
                ca[0]++;
                hindiConsonants.add(ca[0]);
            }

        }
        //System.out.println(hindiConsonants.size());
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                new String(vo);
                vo[0]++;
                hindiVowels.add(vo[0]);
            }

        }
        //System.out.println(hindiVowels.toString());

        Random rand=new Random();
        int count=1;
        while(count <= 500000)
        {
            StringBuilder sb=new StringBuilder();

            int index=rand.nextInt(15);

            sb.append(hindiConsonants.get(index));

            int index1=rand.nextInt(16);
            sb.append(hindiVowels.get(index1));

            int index2=rand.nextInt(36);

            sb.append(hindiConsonants.get(index2));
            int index3=rand.nextInt(36);

            sb.append(hindiConsonants.get(index3));
            myWriter.write(sb.toString()+"\n");
            count++;

        }
        //System.out.println(sb.toString());
        count=1;
        while(count <= 500000)
        {
            StringBuilder sb=new StringBuilder();

            int index=rand.nextInt(36);

            sb.append(hindiConsonants.get(index));

            int index1=rand.nextInt(15);
            sb.append(hindiMatras.get(index1));

            int index2=rand.nextInt(20);

            sb.append(hindiConsonants.get(index2));
            int index3=rand.nextInt(36);

            sb.append(hindiConsonants.get(index3));
            myWriter.write(sb.toString()+"\n");
            // System.out.println(sb.toString());
            count++;

        }


        myWriter.close();
    }

}
