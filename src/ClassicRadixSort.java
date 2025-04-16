import java.util.LinkedList;
import java.util.Queue;

public class ClassicRadixSort {

    public static void main(String[] args) {
        // Step 1: Initialize the numbers using a queue
        Queue<Integer> initialQueue = new LinkedList<>();
        initialQueue.add(275);
        initialQueue.add(87);
        initialQueue.add(426);
        initialQueue.add(61);
        initialQueue.add(409);
        initialQueue.add(170);
        initialQueue.add(677);
        initialQueue.add(503);

        // Two normal arrays with 10 spaces
        int[] array1 = new int[10];
        int[] array2 = new int[10];

        for (int i = 0; i < 10; i++) {
            array1[i] = -1;
            array2[i] = -1;
        }

        // Pass 1: Sort by least significant digit (units place)
        while (!initialQueue.isEmpty()) {
            int number = initialQueue.poll();
            int digit = number % 10;

            // Place in array1[digit] or next available slot
            for (int i = digit; i < 10; i++) {
                if (array1[i] == -1) {
                    array1[i] = number;
                    break;
                }
            }
        }

        // Display after first pass
        System.out.println("After Pass 1 (Unit digit) - Array 1:");
        printArray(array1);

        // Pass 2: Sort by middle digit (tens place), using array2
        for (int i = 0; i < 10; i++) {
            if (array1[i] != -1) {
                int number = array1[i];
                int digit = (number / 10) % 10;

                for (int j = digit; j < 10; j++) {
                    if (array2[j] == -1) {
                        array2[j] = number;
                        break;
                    }
                }
            }
        }

        // Display after second pass
        System.out.println("After Pass 2 (Tens digit) - Array 2:");
        printArray(array2);

        // Pass 3: Sort by most significant digit (hundreds place), back into array1
        for (int i = 0; i < 10; i++) {
            array1[i] = -1; // Clear array1
        }

        for (int i = 0; i < 10; i++) {
            if (array2[i] != -1) {
                int number = array2[i];
                int digit = (number / 100) % 10;

                for (int j = digit; j < 10; j++) {
                    if (array1[j] == -1) {
                        array1[j] = number;
                        break;
                    }
                }
            }
        }

        // Final sorted result
        System.out.println("After Pass 3 (Hundreds digit) - Array 1:");
        printArray(array1);

        System.out.println("Final Sorted Order:");
        for (int i = 0; i < 10; i++) {
            if (array1[i] != -1) {
                System.out.print(array1[i] + " ");
            }
        }
    }

    // Print array neatly
    public static void printArray(int[] array) {
        for (int i = 0; i < 10; i++) {
            if (array[i] == -1) {
                System.out.println("[" + i + "]: ");
            } else {
                System.out.println("[" + i + "]: " + array[i]);
            }
        }
        System.out.println();
    }
}
