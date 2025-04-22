package Question_2;

import java.util.Arrays;

public class RadixSortWords {

    public static void main(String[] args) {

        // Step 1: Initialize word list from the assignment
        String[] initialWordArray = {"apple", "bat", "ant", "zebra", "dog", "cat", "ball", "batman"};
        int size = initialWordArray.length;

        // Step 2: Find the longest word length (for padding)
        int maxLength = findMaxLength(initialWordArray);

        // Step 3: Pad words with space (' ') to equal length
        String[] paddedWords = padWords(initialWordArray, maxLength);

        // Step 4: Create two 2D arrays for sorting passes
        String[][] Array_1 = new String[27][size]; // 26 letters + 1 for padding/space
        String[][] Array_2 = new String[27][size];
        initializeArray(Array_1);
        initializeArray(Array_2);

        // Step 5: Perform radix sort from rightmost character to leftmost
        for (int pos = maxLength - 1; pos >= 0; pos--) {

            if ((maxLength - pos) % 2 != 0) {
                // Odd pass → fill Array_1
                for (int i = 0; i < size; i++) {
                    String word = (maxLength - pos == 1) ? paddedWords[i] : getWordFrom2D(Array_2, i);
                    char ch = word.charAt(pos);
                    int row = getCharIndex(ch);
                    insertWord(Array_1, word, row);
                }
                printArray(Array_1, "After Pass (char at position " + (pos + 1) + ") - Array 1");
                Array_2 = new String[27][size];
                initializeArray(Array_2);
            } else {
                // Even pass → fill Array_2
                for (int i = 0; i < 27; i++) {
                    for (int j = 0; j < size; j++) {
                        String word = Array_1[i][j];
                        if (!word.equals("-1")) {
                            char ch = word.charAt(pos);
                            int row = getCharIndex(ch);
                            insertWord(Array_2, word, row);
                        }
                    }
                }
                printArray(Array_2, "After Pass (char at position " + (pos + 1) + ") - Array 2");
                Array_1 = new String[27][size];
                initializeArray(Array_1);
            }
        }

        // Step 6: Collect and print final sorted list
        String[][] finalArray = (maxLength % 2 == 0) ? Array_2 : Array_1;
        String[] sortedWords = new String[size];
        int index = 0;
        for (int i = 0; i < 27; i++) {
            for (int j = 0; j < size; j++) {
                if (!finalArray[i][j].equals("-1")) {
                    sortedWords[index++] = finalArray[i][j].trim(); // remove padding
                }
            }
        }

        System.out.println("\nSorted List:");
        for (String word : sortedWords) {
            System.out.print(word + " ");
        }
    }

    // Initialize all slots to "-1"
    private static void initializeArray(String[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            Arrays.fill(arr[i], "-1");
        }
    }

    // Find longest word for padding length
    private static int findMaxLength(String[] words) {
        int max = words[0].length();
        for (String word : words) {
            if (word.length() > max) {
                max = word.length();
            }
        }
        return max;
    }

    // Pad all words with space so they are equal in length
    private static String[] padWords(String[] words, int length) {
        String[] padded = new String[words.length];
        for (int i = 0; i < words.length; i++) {
            StringBuilder sb = new StringBuilder(words[i]);
            while (sb.length() < length) {
                sb.insert(0, " "); // pad on the left
            }
            padded[i] = sb.toString();
        }
        return padded;
    }

    // Insert word into correct row and first empty column
    private static void insertWord(String[][] arr, String word, int row) {
        for (int k = 0; k < arr[0].length; k++) {
            if (arr[row][k].equals("-1")) {
                arr[row][k] = word;
                break;
            }
        }
    }

    // Convert character to row index (space → 0, 'a' → 1, ..., 'z' → 26)
    private static int getCharIndex(char ch) {
        if (ch == ' ') return 0;
        return (ch - 'a') + 1;
    }

    // Print the 2D array matrix with labels
    private static void printArray(String[][] arr, String label) {
        System.out.println("\n" + label);
        for (int i = 0; i < 27; i++) {
            System.out.print("Index [" + i + "]: ");
            for (int j = 0; j < arr[0].length; j++) {
                if (!arr[i][j].equals("-1")) {
                    System.out.print(arr[i][j].trim() + " ");
                }
            }
            System.out.println();
        }
    }

    // Retrieve the Nth word from a 2D array (non "-1" only)
    private static String getWordFrom2D(String[][] arr, int index) {
        int count = 0;
        for (int i = 0; i < 27; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (!arr[i][j].equals("-1")) {
                    if (count == index) {
                        return arr[i][j];
                    }
                    count++;
                }
            }
        }
        return "-1";
    }
}
