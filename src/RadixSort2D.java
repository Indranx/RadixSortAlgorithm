import java.util.LinkedList;
import java.util.Queue;
//using Queue as initialization(FIFO)

public class RadixSort2D {
    public static void main(String[] args) {
        // Step 1: Initialization
        Queue<Integer> initialQueue = new LinkedList<>();
        initialQueue.add(275);
        initialQueue.add(87);
        initialQueue.add(426);
        initialQueue.add(61);
        initialQueue.add(409);
        initialQueue.add(170);
        initialQueue.add(677);
        initialQueue.add(503);

        // Two 2D arrays, 10 rows (digits 0-9), max 10 numbers per row
        int[][] array1 = new int[10][10];
        int[][] array2 = new int[10][10];
        int[] count1 = new int[10]; // count of items per row in array1
        int[] count2 = new int[10]; // count of items per row in array2

        // === Pass 1: Sort by least significant digit (units place) ===
        while (!initialQueue.isEmpty()) {
            int num = initialQueue.poll();
            int digit = num % 10; // Get unit digit
            array1[digit][count1[digit]] = num;
            count1[digit]++;
        }

        printArray(array1, count1, "After Pass 1 (Unit digit) - Array 1");

        // === Pass 2: Sort by tens place into array2 ===
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < count1[i]; j++) {
                int num = array1[i][j];
                int digit = (num / 10) % 10;
                array2[digit][count2[digit]] = num;
                count2[digit]++;
            }
        }

        printArray(array2, count2, "After Pass 2 (Tens digit) - Array 2");

        // === Pass 3: Sort by hundreds place back into array1 ===
        // Clear count1 for reuse
        for (int i = 0; i < 10; i++) {
            count1[i] = 0;
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < count2[i]; j++) {
                int num = array2[i][j];
                int digit = (num / 100) % 10;
                array1[digit][count1[digit]] = num;
                count1[digit]++;
            }
        }

        printArray(array1, count1, "After Pass 3 (Hundreds digit) - Array 1 (Final Sorted)");

        // Final output
        System.out.println("\nFinal Sorted Order:");
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < count1[i]; j++) {
                System.out.print(array1[i][j] + " ");
            }
        }
    }

    // Helper method to print 2D array
    public static void printArray(int[][] array, int[] count, String label) {
        System.out.println("\n" + label + ":");
        for (int i = 0; i < 10; i++) {
            System.out.print("[" + i + "]: ");
            for (int j = 0; j < count[i]; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }
}

