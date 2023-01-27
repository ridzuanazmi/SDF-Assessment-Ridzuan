package sdf;

import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        boolean stop = false; // flag to stop the calculation
        System.out.println("Welcome.");
        int lastResult = 0;
        int result = 0, num1 = 0, num2 = 0;

        while (!stop) {

            System.out.print("> ");
            String input = scanner.nextLine();

            // split the input into a String Array
            String[] terms = input.trim().split(" ");

            // if exit is input, terms.length is only 1
            if (terms.length < 2) {
                System.out.println("Bye bye");
                stop = true;
                break;
            }

            // assigns the terms array into operator and numbers
            String oper = terms[1];
            String numA = terms[0];
            String numB = terms[2];

            // goes through the numA and numB to see if $last is entered
            if (numA.equals("$last") && numB.equals("$last")) {
                num1 = lastResult;
                num2 = lastResult;
            } else if (numA.equals("$last")) {
                num1 = lastResult;
                num2 = Integer.parseInt(numB);
            } else if (numB.equals("$last")) {
                num1 = Integer.parseInt(numA);
                num2 = lastResult;
            } else {
                num1 = Integer.parseInt(numA);
                num2 = Integer.parseInt(numB);
            }

            // switch case loop to do the appropiate calculations and 
            // stores lastResult as the result when $last is entered as a number
            switch (oper) {
                case "+":
                    result = num1 + num2;
                    lastResult = result;
                    break;
                case "-":
                    result = num1 - num2;
                    lastResult = result;
                    break;
                case "*":
                    result = num1 * num2;
                    lastResult = result;
                    break;
                case "/":
                    result = num1 / num2;
                    lastResult = result;
                    break;
                default:
                    System.out.println("Undefined operator");
                    break;
            } // end of switch

            // prints out result once completed
            System.out.println(result);
            
        } // end of while loop
    } // end of main method
}// end of Main class
