package core;

import java.util.Arrays;
import java.util.Stack;

public class Recurssion {
    public static void main(String[] args) {
        Stack<Character> stack = new Stack();
        String test = "testing";
        for(Character c : test.toCharArray()){
            stack.push(c);
        }
        stack.stream().forEach(System.out::println);
        reverse(stack);
        System.out.println("Out:-->"+String.valueOf(stack));
    }

    private static void reverse(Stack stack){
        if(stack.isEmpty()){
            System.out.println("Empty stack");
            return;
        }
        char c = (Character) stack.pop();
        System.out.println("Peek-->"+c);
        reverse(stack);
        insertAtBottom(stack, c);
    }

    private static void insertAtBottom(Stack stack, Character val){
        if(stack.isEmpty()){
            System.out.println("Ins Empty-->"+val);
            stack.push(val);
            return;
        }
        Character val1 = (Character) stack.pop();
        insertAtBottom(stack, val);
        // After the recursion unfolds, push each item in the call stack
        // at the top of the stack
        System.out.println("Push-->"+val1);
        stack.push(val1);
    }
}
