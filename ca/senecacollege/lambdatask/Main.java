/********************************************************************************
 Workshop # 6
 Course: JAC444 - Semester 4
 Last Name: Kanagasapabathy
 First Name: Nishantha (Nisha)
 ID: 135015162
 Section: NB
 This assignment represents my own work in accordance with Seneca Academic Policy.
 Signature
 Date: July 13th/2020
 *********************************************************************************/


package ca.senecacollege.lambdatask;

import java.util.Scanner;

public class Main {
    private static double [] arr;

    public static final ArrayProcessor findMax = (arr)->{
        double max = arr[0];
        for(int i = 1; i < arr.length; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
        }
        return max;
    };

    public static final ArrayProcessor findMin = (arr)->{
        double min = arr[0];
        for(int i = 1; i < arr.length; i++) {
            if (min > arr[i]) {
                min = arr[i];
            }
        }
        return min;
    };

    public static final ArrayProcessor findSum = (arr)->{
        double sum = 0;
        for(int i = 0; i < arr.length; i++){
            sum = sum + arr[i];
        }
        return sum;
    };

    public static final ArrayProcessor findAvg = (arr)->{
        double avg = 0;
        double sum = 0;
        for(int i = 0; i < arr.length; i++){
            sum = sum + arr[i];
        }
        avg = sum/arr.length;
        return avg;

    };

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the number of elements you want in the array: ");
        int length = in.nextInt();
        arr = new double[length];
        for(int i = 0; i < length; i++){
            System.out.println("Enter a number: ");
            double input = in.nextDouble();
            arr[i] = input;
        }
        System.out.println("-------------------------------------------");
        System.out.println("\t\t\tNumbers in Array ");
        System.out.println("-------------------------------------------");
        for(int i = 0; i < arr.length; i++){
            System.out.println("-> " + arr[i] + " at index " + i);
        }
        System.out.println("-------------------------------------------");
        System.out.println("\t\t\t\tSummary");
        System.out.println("-------------------------------------------");
        System.out.println("The maximum value in the array is " + findMax.apply(arr));
        System.out.println("The minimum value in the array is " + findMin.apply(arr));
        System.out.println("The sum value in the array is " + findSum.apply(arr));
        System.out.println("The average value in the array is " + findAvg.apply(arr));
        System.out.println("----------------------------------------------------");
        System.out.println("Find the number of times a value occurs in an array. ");
        System.out.println("Enter a value: ");
        double value = in.nextDouble();
        System.out.println("----------------------------------------------------");
        System.out.println("Number of occurrences in array: " + counter(value).apply(arr));
    }

    //@Override
    public double apply(double[] array){
        return 0;
    }

    public static ArrayProcessor counter(double value) {
        return array -> {
            int count = 0;
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == value) {
                    count++;
                }
            }
            return count;
        };
    }
}
