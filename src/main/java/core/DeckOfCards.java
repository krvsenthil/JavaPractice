package core;

import antlr.StringUtils;

import java.util.*;

public class DeckOfCards {

    public static void main(String[] args) {
        //deckOfCards();
        //nonRepeatedChar();
        String s = "Senthil";
        StringBuilder sf = new StringBuilder(s.toString());
        System.out.println("Reverse:-->"+sf.reverse());
    }

    private static void nonRepeatedChar(){
        String s = "LoveYourself";
        char[] c = s.toCharArray();
        //Arrays.sort(c);
        List<Character> list = new ArrayList<>();
        for(int i=0;i<c.length;i++){
            if(!s.substring(i+1).toLowerCase(Locale.ROOT).contains(String.valueOf(c[i]).toLowerCase(Locale.ROOT))){
                System.out.println("First Non Repeated Char-->"+c[i]);
                break;
            }
        }
        //System.out.println(String.valueOf(c));
    }

    private static void deckOfCards() {
        String[] suit = {"S", "D", "C", "H"};
        String[] rank = {"2","3","4","5","6","7","8","9","10","J","Q","K","A"};

        String[] deck = new String[rank.length * suit.length];
        //deck of cards
        for(int i = 0; i<deck.length; i++){
            deck[i] = rank[i%13] + suit[i/13];
        }

        //shuffle
        for(int k=0; k< deck.length; k++){
            Random random = new Random();
            //int index = k + random.nextInt(deck.length - k);
            int index = (int) (Math.random() * deck.length);
            System.out.println(index);
            String temp = deck[k];
            deck[k] = deck[index];
            deck[index] = temp;
        }
        Arrays.stream(deck).forEach(System.out::println);
    }
}
