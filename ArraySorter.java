import java.util.Arrays;      // Corrected: util.Arrays (for sorting)
import java.util.Collections; // Required for reverseOrder()

public class ArraySort { // Class names should follow PascalCase
    public static void main(String[] args) {
        
        // 1. Handling Integer Arrays
        // Note: Using 'Integer' (Wrapper class) instead of 'int' 
        // because Collections.reverseOrder() requires Objects.
        Integer[] nums = {5, 2, 8, 1};

        // Sort in Ascending order (1, 2, 5, 8)
        Arrays.sort(nums);
        System.out.println("Ascending: " + Arrays.toString(nums));

        // Sort in Descending order (8, 5, 2, 1)
        // reverseOrder() acts as a custom "Comparator"
        Arrays.sort(nums, Collections.reverseOrder());
        System.out.println("Descending: " + Arrays.toString(nums));

        ---

        // 2. Handling String Arrays
        String[] fruits = {"banana", "apple", "cherry"};

        // Strings are sorted alphabetically by default
        Arrays.sort(fruits);
        System.out.println("String Ascending: " + Arrays.toString(fruits));
    }
}
