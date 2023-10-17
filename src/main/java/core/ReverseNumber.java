package core;

public class ReverseNumber {
    public static void main(String[] args) {
        int input = 789;
        int temp = input;
        int no,reverse=0;
        int total=0;
        while(input>0){
            no = input%10;
            System.out.println("Mod-"+no);
            reverse = reverse * 10 + no;
            total = total + no;
            System.out.println("Reverse-"+reverse);
            input = input/10;
            System.out.println("Input-"+input);
        }
        System.out.println("Total-"+total);
        if(temp == reverse){
            System.out.println("Palindrome");
        }
    }
}
