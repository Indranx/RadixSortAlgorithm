public class RadixSortTest1 {

    public static void main(String[] args) {
       
        int initialNumArray[] = {275, 87, 426, 61, 409, 170, 677, 503}; // Array of numbers to be sorted initialized.

        // Two 2D arrays, assuming 10 rows and 3 columns for each digit.
        // 3 columns are used to store numbers based on the digit value although from assignment assumption of 3 digits.
        int Array_1[][] = new int[10][3]; // Array for the first pass.
        int Array_2[][] = new int[10][3]; // Array for the second pass.
        
        int num; // The chosen number.
        int digit; // Current digit being extracted from a number (units, tens, hundreds).

        //=== Iteration 1: Sort by least significant digit (units place) ===

        for(int i = 0; i< initialNumArray.length; i++){

            num = initialNumArray[i]; // Get the number from the initial array.
            digit = num % 10; // Get the least significant digit(LSD) using modulo 10.
            insertNumber(Array_1, num, digit); // Insert the number into Array_1 based on the digit.
    }

    printArray(Array_1, "After Iteration 1 (Units digit) - Array 1"); // Print the first pass result.

    // === Iteration 2: Sort by tens place into Array_2 ===
    for(int i = 0; i < 10; i++) {
        for(int j = 0; j < 3; j++)
        {
            num = Array_1[i][j]; // Get the number from Array_1.
            if(num!=0) {
                digit = (num / 10) % 10; // Get the tens digit (middle number) using integer division and modulo.
                insertNumber(Array_2, num, digit); // Insert the number into Array_2 based on the digit.
            }
        }
    }

    printArray(Array_2, "After Iteration 2 (Tens digit) - Array 2"); // Print the second pass result.

    // === Iteration 3: Sort by hundreds place back into Array_1 ===
    Array_1 = new int[10][10]; // reset array1 back to 0 before reusing it.
    for(int i = 0; i < 10; i++){
        for(int j = 0; j < 3; j++){
            num = Array_2[i][j]; // Get the number from Array_2.
            if(num!=0){ // Check if the number is not zero.
                digit = ((num / 100) % 10); // Get the MSB(Hundreds) by using division and modulo.
                insertNumber(Array_1, num, digit); // Insert the number into Array_1 based on the digit.
            }
        }
    }

    // Print the final sorted array. With values > 0 only.
    printArray(Array_1, "After Pass 3 (Hundreds digit) - Array 1");
    System.out.println("\nSorted List: ");
    for(int i = 0; i < 10; i++)
    {
        for(int j = 0; j < 3; j++){
            if(Array_1[i][j]!=0)
            System.out.print(Array_1[i][j] + " ");
        }
    }
}

//Printing Function with Index labelled
final private static void printArray(int[][] array, String label) {
    System.out.println("\n" + label);
    for (int i = 0; i < 10; i++) {
        System.out.print("Index " +"[" + i + "]: ");
        for (int j = 0; j < 3; j++) {
            if (array[i][j] != 0) {
                System.out.print(array[i][j] + " ");
            }
        }
        System.out.println();
    }
}

// This function inserts the number into the correct position of arrays based on the digits.
final private static void insertNumber(int arr[][], int num, int digit) {
    for(int k = 0; k < 3; k++){ //Loop through the rows of Array_2.
        if(arr[digit][k] == 0) // Check if the position is empty, if not loop till find a position
        {
            arr[digit][k] = num; // Place the number in the location of Array_2.
            break; // Exit the loop when number placed.
        }
    }
}
}

