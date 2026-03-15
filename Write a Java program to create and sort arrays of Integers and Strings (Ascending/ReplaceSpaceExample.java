// Java program to replace all spaces in a string with a specific character

import java.util.Scanner;

public class ReplaceSpaces {

    public static void main(String[] args) {

        // Create Scanner object to take input from user
        Scanner sc = new Scanner(System.in);

        // Ask user to enter a string
        System.out.println("Enter a string:");
        String str = sc.nextLine();

        // Ask user which character should replace spaces
        System.out.println("Enter the character to replace spaces:");
        char ch = sc.next().charAt(0);

        // Replace spaces with the given character
        String result = str.replace(' ', ch);

        // Print the modified string
        System.out.println("Modified String: " + result);

        // Close scanner
        sc.close();
    }
}  
input --
Enter a string:
Java Programming Language
Enter the character to replace spaces:
-
output--
Modified String: Java-Programming-Language
