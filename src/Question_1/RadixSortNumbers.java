package Question_1;

import java.util.Arrays;

public class RadixSortNumbers {

    public static void main(String[] args) {

        // Step 1: Initialize input array.
        int initialNumArray[] = {275, 87, 426, 61, 409, 170, 677, 503}; // Array of numbers to be sorted initialized.
        int size = initialNumArray.length; // Size of the initial array.
        
        // Step 2: Initialize 2D Arrays, assuming 10 rows for digits (0-9) and size based on the number of elements in the initial array (columns).
        int[][] Array_1 = new int[10][size]; 
        int[][] Array_2 = new int[10][size];

        // Initialize the arrays with -1 to mark empty slots.
        initializeWithNegativeOne(Array_1);
        initializeWithNegativeOne(Array_2);

        // Get max digit length for the numbers in the array.
        int max = findMaxValue(initialNumArray);
        int maxDigits = countDigits(max);

        int multiplier = 1; // Multiplier for extracting digits (1, 10, 100, etc.). Start with LSD (1s place).

        // Step 2: Perform digit-based sorting
        // Loop to control the number of iterations based on the number of digits in the largest number.
        for (int iteration = 0; iteration < maxDigits; iteration++) {
            if (iteration % 2 == 0) { // Even iteration: sort into Array_1
                for (int i = 0; i < size; i++) {
                    int num = (iteration == 0) ? initialNumArray[i] : getValue(Array_2, i); // Only initial array for first iteration then always is Array_2. 
                    int digit = (num / multiplier) % 10; // Extract the current digit using division and modulo.
                    insertNumber(Array_1, num, digit); // Insert the number into Array_1 based on the digit.
                }
                printArray(Array_1, "After Iteration " + (iteration + 1) + " (Digit " + (iteration + 1) + ") - Array 1");
                
                //Reset and prepare Array_2 for next iteration.
                Array_2 = new int[10][size];
                initializeWithNegativeOne(Array_2);

            } else {
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < size; j++) {
                        int num = Array_1[i][j];
                        if (num != -1) { // Check if slot not empty if yes then skip. If not empty then get the number.
                            int digit = (num / multiplier) % 10;
                            insertNumber(Array_2, num, digit);
                        }
                    }
                }
                printArray(Array_2, "After Iteration " + (iteration + 1) + " (Digit " + (iteration + 1) + ") - Array 2");

                // Reset and prepare Array_1 for next iteration.
                Array_1 = new int[10][size];
                initializeWithNegativeOne(Array_1);
            }

            multiplier *= 10; // Increase multiplier by 10 for next digit.
        }

        // Step 4: Print final sorted result
        System.out.println("\nSorted List:");
        int[][] tempArray = (maxDigits % 2 == 0) ? Array_2 : Array_1; // use last filled array, if even then Array_2 else Array_1.
        int[] sorted = new int[size]; // 1D Array to hold sorted numbers.
        int index = 0; // Index for sorted array

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < size; j++) {
                if (tempArray[i][j] != -1) { // If slot not empty add to sorted array.
                sorted[index++] = tempArray[i][j];
                }
    }
}

        // Print the sorted 1D array
    for (int num : sorted) {
        System.out.print(num + " ");
    }

        }
         
    private static void initializeWithNegativeOne(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            Arrays.fill(arr[i], -1); // Use arrays library and fill the array with -1(indicates empty).
        }
    }

    // Finds max value in array
    public static int findMaxValue(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    // Counts digits in a number
    public static int countDigits(int number) {
        int count = 0;
        for (; number != 0; number /= 10) { // loop till number is 0.
            count++;
        }
        return count;
    }

    // Inserts number into the correct bucket
    private static void insertNumber(int[][] arr, int num, int digit) {
        for (int k = 0; k < arr[0].length; k++) { // arr[0].length is the number of columns.
            if (arr[digit][k] == -1) {
                arr[digit][k] = num;
                break;
            }
        }
    }

    // Prints 2D array
    private static void printArray(int[][] array, String label) {
        System.out.println("\n" + label);
        for (int i = 0; i < 10; i++) {
            System.out.print("Index [" + i + "]: ");
            for (int j = 0; j < array[0].length; j++) {
                if (array[i][j] != -1) {
                    System.out.print(array[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    // Gets the valid number from a 2D Array based on the index.
    private static int getValue(int[][] arr, int index) {
        int count = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j] != -1) {
                    if (count == index) { // Get the number at the index and return it.
                        return arr[i][j];
                    }
                    count++;
                }
            }
        }
        return -1; // Return -1 if not found.
    }
}
