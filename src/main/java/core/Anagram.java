package core;

import java.util.Arrays;
import java.util.Scanner;

public class Anagram {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String firstString = scanner.nextLine();
        String secondString = scanner.nextLine();
        solution1(firstString, secondString);
    }

    private static void solution1(String firstString, String secondString){
        if(firstString.length() == secondString.length()){
            char[] ch1 = firstString.toCharArray();
            char[] ch2 = secondString.toCharArray();
            Arrays.sort(ch1);
            Arrays.sort(ch2);
            if(Arrays.equals(ch1, ch2)){
                System.out.println("It's an anagram.");
            }else{
                System.out.println("Not an anagram.");
            }
        }else
            System.out.println("Not an anagram.");
    }
}
