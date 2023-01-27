package sdf;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        FileReader fr = null;
        BufferedReader br = null;
        double wordCount = 0;
        double specificWordCount = 0;

        // Create a HashMap to store the word and it's occurrence
        HashMap<String, Integer> wordCounts = new HashMap<>();

        // Read the file name that is entered in the console
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
            String wordFind = scanner.nextLine().trim().toLowerCase();
            System.out.println("word selected: " + wordFind + "\n");

            while ((line = br.readLine()) != null) {

                // Split the line into words and replace punctuations with white spaces
                // wash the line again with split()
                line = line.replaceAll("[^\\p{L}\\p{Z}]", " ");
                String[] words = line.split("[\\s@&.?$+-]+");

                // Go through each word
                for (String word : words) {
                    // Print out the word and increment wordCount
                    // System.out.println(word);
                    if (!word.isEmpty()) {
                        wordCount++; // check if the word is not empty
                    }
                    // converts the word into lowercase and the wordfind to lowercase in the
                    // condition
                    // to make things simpler since word is free from punctuation
                    if (word.toLowerCase().equals(wordFind.toLowerCase())) {
                        specificWordCount++;
                    }

                    // add each word into the HashMap wordCounts
                    if (word.length() >= 1) {
                        word = word.toLowerCase(); // convert the word into lowercase
                        if (wordCounts.containsKey(word)) { // checks if there is a repeated word if there is, incrementthe count in HashMap
                            wordCounts.put(word, wordCounts.get(word) + 1);
                        } else {
                            wordCounts.put(word, 1); // initializes new word into the HashMap
                        }
                    }

                } // end of for loop

            } // end of while loop

            // print out wordCount, specific word and it's counter, and word frequency
            System.out.println("Word count = " + wordCount);
            System.out.println("The word '" + wordFind + "' appears " + specificWordCount + " times");
            double wordFrequecy = specificWordCount / wordCount;
            System.out.printf("The frequency of the word %s is %.3f%n", wordFind, wordFrequecy);

            // use Comparator to help sort out wordCounts HashMap
            Comparator<Map.Entry<String, Integer>> comparator = (o1, o2) -> o2.getValue().compareTo(o1.getValue());
            List<Map.Entry<String, Integer>> sortedWordCounts = wordCounts.entrySet().stream().sorted(comparator).collect(Collectors.toList());

            // List out the top 10 appearance
            System.out.println("Top 10 words in appearance:");
            for (int i = 0; i < 10; i++) {
                System.out.println(sortedWordCounts.get(i).getKey() + ": " + sortedWordCounts.get(i).getValue());
            }

        } finally {
            if (fr != null) {
                fr.close();
                br.close();
            }

        } // end of try-finally block

    } // end of main method

}// end of Main class