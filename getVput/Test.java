/**
 * Created By: Jason Wehran
 * Date Created: June 27, 2023
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Comparator<String> s = new StringComparator();
        TreeMap<String, String> treeM = new TreeMap(s);
        HashMapSC<String, String> hashMSC = new HashMapSC(100000);
        HashMapLP<String, String> hashMLP = new HashMapLP(100000, 0.5);

        File file = new File("users.txt");
        try {
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] split = line.split(" ");
                if (split.length >= 2) {
                treeM.add(split[0], split[1]);
                hashMSC.put(split[0], split[1]);
                hashMLP.put(split[0], split[1]);
                }
            }
            scan.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        ArrayList<String> usernames = new ArrayList(); //using an arraylist to list usernames for comparisons

        File file1 = new File("user_list.txt");
        try {
            Scanner scan = new Scanner(file1);
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] split = line.split(" ");
                usernames.add(split[0]);
            }
            scan.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        System.out.println("Testing get()");
        System.out.printf("%-8s\t%-10s\t%-10s\t%-10s\n", "username", "TreeMap", "HashMapSC", "HashMapLP");
        for (int i = 0; i < 20; i++) {
            String word = usernames.get(i);
            treeM.get(word);
            hashMSC.get(word);
            hashMLP.get(word);
            int scIter = HashMapSC.iterations;
            int treeMiter = TreeMap.iterations;
            int lpIter = HashMapLP.iterations;
            System.out.printf("%-8s\t%-10d\t%-10d\t%-10d\n", word, treeMiter, lpIter, lpIter);
        }
        //! ALL FORMAT IS BASED OFF OUPUT PROVIDED IN ASSINGMENT DOCUMENT
        //! CITATION: I USED CHATGPT AND STACK OVERFLOW TO BE ABLE TO UNDERSTAND AND CODE THE FORMATTING FOR THE OUTPUT
        System.out.println("\nTesting put()");
        System.out.printf("%-10s\t%-10s\t%-10s\n", "Size", "HashMapSC", "HashMapLP");
        HashMapSC<String, String> hashMSC1 = new HashMapSC(50000);
        HashMapLP<String, String> hashMLP1 = new HashMapLP(50000, 0.5);
        readData(hashMSC1, hashMLP1, "users.txt");
        System.out.printf("%-10d\t%-10d\t%-10d\n", 50000, HashMapSC.collisions, HashMapLP.collisions);

        HashMapSC<String, String> hashMSC2 = new HashMapSC(100000);
        HashMapLP<String, String> hashMLP2 = new HashMapLP(100000, 0.5);
        readData(hashMSC2, hashMLP2, "users.txt");
        System.out.printf("%-10d\t%-10d\t%-10d\n", 100000, HashMapSC.collisions, HashMapLP.collisions);

        HashMapSC<String, String> hashMSC3 = new HashMapSC(150000);
        HashMapLP<String, String> hashMLP3 = new HashMapLP(150000, 0.5);
        readData(hashMSC3, hashMLP3, "users.txt");
        System.out.printf("%-10d\t%-10d\t%-10d\n", 150000, HashMapSC.collisions, HashMapLP.collisions);

        HashMapSC<String, String> hashMSC4 = new HashMapSC(200000);
        HashMapLP<String, String> hashMLP4 = new HashMapLP(200000, 0.5);
        readData(hashMSC4, hashMLP4, "users.txt");
        System.out.printf("%-10d\t%-10d\t%-10d\n", 200000, HashMapSC.collisions, HashMapLP.collisions);

        HashMapSC<String, String> hashMSC5 = new HashMapSC(250000);
        HashMapLP<String, String> hashMLP5 = new HashMapLP(250000, 0.5);
        readData(hashMSC5, hashMLP5, "users.txt");
        System.out.printf("%-10d\t%-10d\t%-10d\n", 250000, HashMapSC.collisions, HashMapLP.collisions);

        HashMapSC<String, String> hashMSC6 = new HashMapSC(300000);
        HashMapLP<String, String> hashMLP6 = new HashMapLP(300000, 0.5);
        readData(hashMSC6, hashMLP6, "users.txt");
        System.out.printf("%-10d\t%-10d\t%-10d\n", 300000, HashMapSC.collisions, HashMapLP.collisions);

        HashMapSC<String, String> hashMSC7 = new HashMapSC(350000);
        HashMapLP<String, String> hashMLP7 = new HashMapLP(350000, 0.5);
        readData(hashMSC7, hashMLP7, "users.txt");
        System.out.printf("%-10d\t%-10d\t%-10d\n", 350000, HashMapSC.collisions, HashMapLP.collisions);

        HashMapSC<String, String> hashMSC8 = new HashMapSC(400000);
        HashMapLP<String, String> hashMLP8 = new HashMapLP(400000, 0.5);
        readData(hashMSC8, hashMLP8, "users.txt");
        System.out.printf("%-10d\t%-10d\t%-10d\n", 400000, HashMapSC.collisions, HashMapLP.collisions);

        HashMapSC<String, String> hashMSC9 = new HashMapSC(450000);
        HashMapLP<String, String> hashMLP9 = new HashMapLP(450000, 0.5);
        readData(hashMSC9, hashMLP9, "users.txt");
        System.out.printf("%-10d\t%-10d\t%-10d\n", 450000, HashMapSC.collisions, HashMapLP.collisions);

        HashMapSC<String, String> hashMSC10 = new HashMapSC(500000);
        HashMapLP<String, String> hashMLP10 = new HashMapLP(500000, 0.5);
        readData(hashMSC10, hashMLP10, "users.txt");
        System.out.printf("%-10d\t%-10d\t%-10d\n", 500000, HashMapSC.collisions, HashMapLP.collisions);

    }

    /**
     * Reads data in
     * @param sc
     * @param lp
     * @param filename
     */
    private static void readData(HashMapSC<String, String> sc, HashMapLP<String, String> lp, String filename) {
        File file = new File(filename);
        try {
            Scanner read = new Scanner(file);
            while (read.hasNextLine()) {
                String line = read.nextLine();
                String[] split = line.split(" ");
                if (split.length >= 2) {
                sc.put(split[0], split[1]);
                lp.put(split[0], split[1]);
            }
        }
            read.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

}

/* 
 * (9.) The overall message that I'm getting from the results I recieved is that the hashmap was able to iterate 
 * much less when using the get() method, as seen per the output.
 */

 /* 
 * (10.) I don't think my results are correct, however what I was able to notice that regardless of the accuracy of the output, was
 * that the two methods act pretty similarly despite using two different collision methods
 */