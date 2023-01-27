package sdf;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        FileReader fr = null;
        BufferedReader br = null;
        double wordCount = 0;
        double specificWordCount = 0;

        // Create a HashMap to store the word and it's appearance
        HashMap<String, Integer> wordCounts = new HashMap<>();

        String file = "";
        if (args.length > 0) {
            file = args[0];
        }
        System.out.println("The file name entered is: " + file);

        try {

            fr = new FileReader(file);
            br = new BufferedReader(fr);

            String line;
            System.out.print("Enter word to count in file: ");
            String wordFind = scanner.nextLine();
            System.out.println("word selected: " + wordFind);

            while ((line = br.readLine()) != null) {

                // Split the line into words and replace punctuations with white spaces
                line = line.replaceAll("[^\\p{L}\\p{Z}]", " ");
                String[] words = line.split("[\\s@&.?$+-]+");

                for (String word : words) {
                    // Print out the word and increment wordCount
                    // System.out.println(word);
                    if (!word.isEmpty()) {
                        // check if the word is not empty
                        wordCount++;
                    }

                    if (word.toLowerCase().equals(wordFind.toLowerCase())) {
                        specificWordCount++;
                    } 
                    
                    if (word.length()>1) {
                        word = word.toLowerCase();
                        if (wordCounts.containsKey(word)) {
                            wordCounts.put(word, wordCounts.get(word)+ 1);
                        } else {
                            wordCounts.put(word, 1);
                        }
                    }                    

                }
            }
            // print out wordCount, specific word and it's counter, and word frequency
            System.out.println("Word count = " + wordCount);
            System.out.println("The word " + wordFind + ", appears " + specificWordCount + " times");
            double wordFrequecy = specificWordCount / wordCount;
            System.out.printf("The frequency of the word %s is %.3f%n", wordFind, wordFrequecy);

            // sort the wordCounts in descending order
            System.out.println("The top 10 words by order of appearance");
            wordCounts = sortByValue(wordCounts);
            int count = 0;
            for (Map.Entry<String, Integer> entry : wordCounts.entrySet()) {
                if (count < 10) {
                    System.out.println(entry.getKey() + ": " + entry.getValue());
                    count++;
                }
            }

        } finally {
            if (fr != null) {
                fr.close();
                br.close();
            }
        }

    } // end of main method

    // sortByValue sorts the top 10 words in order using the HashMap
    public static HashMap<String, Integer> sortByValue(HashMap<String, Integer> wordCounts) {

        List<Map.Entry<String, Integer>> list = new LinkedList<>(wordCounts.entrySet());
        Collections.sort(list, (o1, o2) -> o2.getValue().compareTo(o1.getValue()));

        HashMap<String, Integer> temp = new LinkedHashMap<>();

        for (Map.Entry<String, Integer> entry : list) {
            temp.put(entry.getKey(), entry.getValue());
        }

        return temp;

    }
}