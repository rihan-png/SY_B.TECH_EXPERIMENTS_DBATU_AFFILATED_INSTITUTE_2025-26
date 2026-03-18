/*
 * EXPERIMENT 03: STRING OPERATIONS
 * Goal: Perform various operations on strings:
 *       a. Count vowels and consonants
 *       b. Replace spaces with a character
 *       c. Convert to uppercase and lowercase
 *       d. Reverse the string
 * 
 * Concepts Used:
 * - String Methods
 * - String Manipulation
 * - StringBuilder & StringBuffer
 * - Loops and Conditions
 */

public class StringOperations {
    
    // ============ METHOD: COUNT VOWELS AND CONSONANTS ============
    // Counts the number of vowels and consonants in a string
    public static void countVowelsAndConsonants(String str) {
        System.out.println("\n--- Count Vowels & Consonants ---");
        System.out.println("Input String: " + str);
        
        int vowels = 0;
        int consonants = 0;
        
        // Convert to lowercase for easier comparison
        String lower = str.toLowerCase();
        
        // Loop through each character
        for (int i = 0; i < lower.length(); i++) {
            char ch = lower.charAt(i);
            
            // Check if character is a letter
            if (Character.isLetter(ch)) {
                // Check if it's a vowel (a, e, i, o, u)
                if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                    vowels++;
                } else {
                    // It's a consonant
                    consonants++;
                }
            }
        }
        
        System.out.println("Vowels: " + vowels);
        System.out.println("Consonants: " + consonants);
    }
    
    // ============ METHOD: REPLACE SPACES ============
    // Replaces all spaces with a specific character
    public static void replaceSpaces(String str, char replacement) {
        System.out.println("\n--- Replace Spaces ---");
        System.out.println("Original String: " + str);
        System.out.println("Replacement Character: " + replacement);
        
        // Use replace method to replace all spaces
        String replaced = str.replace(' ', replacement);
        
        System.out.println("Modified String: " + replaced);
    }
    
    // ============ METHOD: CONVERT CASE ============
    // Converts string to uppercase and lowercase
    public static void convertCase(String str) {
        System.out.println("\n--- Convert Case ---");
        System.out.println("Original String: " + str);
        
        // Convert to uppercase
        String upperCase = str.toUpperCase();
        System.out.println("Uppercase: " + upperCase);
        
        // Convert to lowercase
        String lowerCase = str.toLowerCase();
        System.out.println("Lowercase: " + lowerCase);
    }
    
    // ============ METHOD: REVERSE STRING (Using StringBuilder) ============
    // Reverses the string using StringBuilder
    public static void reverseStringBuilder(String str) {
        System.out.println("\n--- Reverse String (Using StringBuilder) ---");
        System.out.println("Original String: " + str);
        
        // Create a StringBuilder object
        StringBuilder sb = new StringBuilder(str);
        
        // Use reverse() method
        String reversed = sb.reverse().toString();
        
        System.out.println("Reversed String: " + reversed);
    }
    
    // ============ METHOD: REVERSE STRING (Using StringBuffer) ============
    // Reverses the string using StringBuffer
    public static void reverseStringBuffer(String str) {
        System.out.println("\n--- Reverse String (Using StringBuffer) ---");
        System.out.println("Original String: " + str);
        
        // Create a StringBuffer object
        StringBuffer sbf = new StringBuffer(str);
        
        // Use reverse() method
        String reversed = sbf.reverse().toString();
        
        System.out.println("Reversed String: " + reversed);
    }
    
    // ============ METHOD: REVERSE STRING (Manual Loop) ============
    // Reverses the string using a manual loop
    public static void reverseStringManual(String str) {
        System.out.println("\n--- Reverse String (Using Manual Loop) ---");
        System.out.println("Original String: " + str);
        
        String reversed = "";
        
        // Loop from end to beginning
        for (int i = str.length() - 1; i >= 0; i--) {
            reversed += str.charAt(i);
        }
        
        System.out.println("Reversed String: " + reversed);
    }
    
    // ============ MAIN METHOD ============
    public static void main(String[] args) {
        System.out.println("========== STRING OPERATIONS PROGRAM ==========");
        
        // Test String
        String testString = "Hello Java Programming";
        
        // 1. Count Vowels and Consonants
        countVowelsAndConsonants(testString);
        
        // 2. Replace Spaces
        replaceSpaces(testString, '_');
        
        // 3. Convert Case
        convertCase(testString);
        
        // 4. Reverse String Methods
        reverseStringBuilder(testString);
        reverseStringBuffer(testString);
        reverseStringManual(testString);
        
        // Additional test cases
        System.out.println("\n========== ADDITIONAL TEST CASES ==========");
        
        String test2 = "Java";
        countVowelsAndConsonants(test2);
        replaceSpaces("Hello World Java", '-');
        convertCase("OoPs CoNcEpT");
        reverseStringBuilder("Rihan");
        
        System.out.println("\n========== END OF PROGRAM ==========");
    }
}