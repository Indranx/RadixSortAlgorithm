public class RadixSortTest2 {

    public static void main(String[] args) {

        // Initial array of positive integers to be sorted (max 3-digit numbers assumed)
        int initialNumArray[] = {275, 87, 426, 61, 409, 170, 677, 503};

        // Two 2D arrays for digit-wise sorting across passes
        int Array_1[][] = new int[10][10]; // Used in Pass 1 and Pass 3
        int Array_2[][] = new int[10][10]; // Used in Pass 2

        int num;   // Current number being processed
        int digit; // Current digit being extracted (units, tens, hundreds)

        // === PASS 1: Sort by Least Significant Digit (Units place) ===
        // Place numbers into Array_1 based on their unit digit (rightmost digit)
        for (int i = 0; i < initialNumArray.length; i++) {
            num = initialNumArray[i];
            digit = num % 10; // Get unit digit

            // Place number into the first available column in the corresponding row
            for (int j = 0; j < 10; j++) {
                if (Array_1[digit][j] == 0) {
                    Array_1[digit][j] = num;
                    break;
                }
            }
        }

        printArray(Array_1, "After Pass 1 (Units digit) - Array 1");

        // === PASS 2: Sort by Middle Digit (Tens place) into Array_2 ===
        // Read from Array_1 and sort based on the middle digit
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                num = Array_1[i][j];
                if (num != 0) {
                    digit = (num / 10) % 10; // Extract tens digit
                    for (int k = 0; k < 10; k++) {
                        if (Array_2[digit][k] == 0) {
                            Array_2[digit][k] = num;
                            break;
                        }
                    }
                }
            }
        }

        printArray(Array_2, "After Pass 2 (Tens digit) - Array 2");

        // === PASS 3: Sort by Most Significant Digit (Hundreds place) back into Array_1 ===
        // Reset Array_1 before reuse
        Array_1 = new int[10][10];

        // Read from Array_2 and sort based on hundreds digit
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                num = Array_2[i][j];
                if (num != 0) {
                    digit = (num / 100) % 10; // Extract hundreds digit
                    for (int k = 0; k < 10; k++) {
                        if (Array_1[digit][k] == 0) {
                            Array_1[digit][k] = num;
                            break;
                        }
                    }
                }
            }
        }

        printArray(Array_1, "After Pass 3 (Hundreds digit) - Array 1");

        // === Final Output ===
        System.out.print("Sorted List: ");
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (Array_1[i][j] != 0) {
                    System.out.print(Array_1[i][j] + " ");
                }
            }
        }
        System.out.println(); // Add newline at end
    }

    /**
     * Prints the contents of a 2D radix bucket array
     * @param array The 2D array to print
     * @param label Description of which pass this output belongs to
     */
    final private static void printArray(int[][] array, String label) {
        System.out.println("\n" + label);
        for (int i = 0; i < 10; i++) {
            System.out.print("Index [" + i + "]: ");
            for (int j = 0; j < 10; j++) {
                if (array[i][j] != 0) {
                    System.out.print(array[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}
