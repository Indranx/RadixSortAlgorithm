import java.util.*;

public class RadixSortWorking {
    public static void main(String[] args) {
        Queue<Integer> initQueue = new LinkedList<>(Arrays.asList(
            275, 87, 426, 61, 409, 170, 677, 503, 222, 301
        ));

        int[] array1 = new int[10]; // First destination
        int[] array2 = new int[10]; // Alternating use
        Arrays.fill(array1, -1); // Use -1 to indicate empty slots
        Arrays.fill(array2, -1);

        // ==== Step 1: Initialization – Sort by LSD (unit place) into array1 ====
        while (!initQueue.isEmpty()) {
            int num = initQueue.poll();
            int digit = num % 10;

            // Find next empty spot in array1 starting from index 'digit'
            int pos = findEmptySlot(array1, digit);
            array1[pos] = num;
        }

        printArray(array1, "After Initialization (LSD pass) into Array 1");

        // ==== Step 2: Middle digit sort into array2 ====
        Arrays.fill(array2, -1);
        sortDigit(array1, array2, 10); // middle digit
        printArray(array2, "After Middle Digit Pass into Array 2");

        // ==== Step 3: Most significant digit (hundreds) into array1 again ====
        Arrays.fill(array1, -1);
        sortDigit(array2, array1, 100); // most significant digit
        printArray(array1, "After MSD Pass into Array 1 (Final Sorted Order)");

        // Final Output
        System.out.print("\nFinal Sorted Order: ");
        for (int num : array1) {
            if (num != -1) System.out.print(num + " ");
        }
    }

    // Utility to find the next empty spot starting at index
    private static int findEmptySlot(int[] array, int start) {
        int i = start;
        while (i < array.length && array[i] != -1) i++;
        if (i == array.length) i = 0; // wrap around
        while (i < start && array[i] != -1) i++;
        return i;
    }

    // Performs one digit sort from source → destination
    private static void sortDigit(int[] source, int[] dest, int divisor) {
        Queue<Integer>[] buckets = createBuckets();
        for (int i = 0; i < source.length; i++) {
            if (source[i] != -1) {
                int digit = (source[i] / divisor) % 10;
                buckets[digit].add(source[i]);
            }
        }

        int index = 0;
        for (Queue<Integer> bucket : buckets) {
            while (!bucket.isEmpty()) {
                dest[index++] = bucket.poll();
            }
        }
    }

    // Create 10 buckets for digit 0-9
    @SuppressWarnings("unchecked")
    private static Queue<Integer>[] createBuckets() {
        Queue<Integer>[] buckets = new LinkedList[10];
        for (int i = 0; i < 10; i++) {
            buckets[i] = new LinkedList<>();
        }
        return buckets;
    }

    // Print current state of array
    private static void printArray(int[] array, String label) {
        System.out.println("\n" + label + ":");
        for (int i = 0; i < array.length; i++) {
            System.out.printf("[%d]: %s\n", i, array[i] == -1 ? "" : array[i]);
        }
    }
}
