#THIS IS FIRST CLASS UNDER COM.XYZ PACKAGE BELOW IS OTHER SECOND CLASS CREATED 

package app_cal2;                 // Package name where the class is stored

public class Calculator {        // Calculator class definition

    int a;                       // variable to store first number
    int b;                       // variable to store second number

    // Constructor to initialize values
    Calculator(int x, int y) {   // constructor with two parameters
        a = x;                   // assign value of x to a
        b = y;                   // assign value of y to b
    }

    // Method to perform operation
    void operation(int op) {     // method receives user choice

        switch(op) {             // switch statement to choose operation

            case 1:              // case 1 for addition
                System.out.println("Addition = " + (a + b)); // print sum
                break;           // stop switch execution

            case 2:              // case 2 for subtraction
                System.out.println("Subtraction = " + (a - b)); // print difference
                break;           // stop switch

            case 3:              // case 3 for division
                System.out.println("Division = " + (a / b)); // print division result
                break;           // stop switch

            case 4:              // case 4 for multiplication
                System.out.println("Multiplication = " + (a * b)); // print multiplication result
                break;           // stop switch

            default:             // default case if user enters wrong option
                System.out.println("Invalid Choice"); // print error message
        }
    }
}
#SECOND CLASS SATRTS FROM HERE
package app_cal2;                      // package name

import java.util.Scanner;              // import Scanner class for input

public class Application {             // main class

    public static void main(String[] args) {   // program execution starts here

        Scanner sc = new Scanner(System.in);   // create Scanner object

        int firstvalue;                 // variable for first number
        int secondvalue;                // variable for second number
        int option;                     // variable for operation choice

        System.out.println("Enter first number:"); // ask user for first number
        firstvalue = sc.nextInt();      // read first number from keyboard

        System.out.println("Enter second number:"); // ask user for second number
        secondvalue = sc.nextInt();     // read second number

        System.out.println("1.Add 2.Sub 3.Div 4.Mult"); // show operation menu
        option = sc.nextInt();          // read operation choice

        Calculator c = new Calculator(firstvalue, secondvalue); // create object

        c.operation(option);            // call operation method

    }
}
