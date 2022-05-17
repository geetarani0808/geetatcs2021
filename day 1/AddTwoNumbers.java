package com.demo;
 class AddTwoNumbes {
    // method
    void SumOfTwoNumbers() {
        int number1 = 10;
        int number2 = 20;
        int result = number1 + number2;
        System.out.print("Sum of two numbers " + result);
    }
}
public class AddTwoNumbers {
    public static void main(String arg[]) {
        AddTwoNumbes object = new AddTwoNumbes();// object of this class -> AddTwoNumber
        object.SumOfTwoNumbers();// calling from here
    }
}
