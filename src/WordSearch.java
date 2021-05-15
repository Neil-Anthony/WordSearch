import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.sql.Array;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.lang.String;


public class WordSearch {
    public static void main(String[] args) throws FileNotFoundException {

        System.out.println(reverseString("I like Ike"));
        char[][] board = new char[14][14];
        char[] a;
        a = stringToCharArray("Lemmons");
        doDiagonal(a, board);
        //fillBoard(board);
        printBoard(board);
        int [] position;
        position = checkHorizontal(a,board);

    }

    public static void printIntro(){
        System.out.println("HEYO THIS IS AN INTRODUCTION TO A LAB");
    }

    public static void menu(){
        Scanner response = new Scanner(System.in);
        String userInput = response.next();

        System.out.println("Please select an option.");
        System.out.println("Generate a new word search (g)");
        System.out.println("Print out your word search (p)");
        System.out.println("Show the solution to your word search (s)");
        System.out.println("Quit the program (q)");


        do {

        } while (userInput.equalsIgnoreCase("q"));
    }

    public static String reverseString(String reverseThis){
        int length = reverseThis.length();
        char [] tbd = new char [reverseThis.length()];
        for (int i = 0; i < length; i++){
            tbd[length - i - 1] = reverseThis.charAt(i);
        }
        return String.copyValueOf(tbd);
    }

    public static char[] stringToCharArray(String toArray){
        char[] charArray = new char[toArray.length()-1];
        int arraySize = toArray.length() - 1;
        for (int i = 0; i < arraySize; i++){
            charArray[i] = toArray.charAt(i);
        }
        return charArray;
    }

    public static char[][] doHorizontal(char[] charArray, char[][] board){
        Random rand = new Random();
        int vPos = rand.nextInt(14); // Vertical Position
        int k = charArray.length;
        int hPos = rand.nextInt(14-k); //Horizontal Position

        for (int l = hPos; l < k + hPos; l++){
            board[vPos][l] = charArray[l-hPos];
        }
        return board;
    }

    public static void printBoard(char[][] board){
        for (char[] letter: board) {
            for (char element: letter) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }

    /*public static char[][] fillBoard(char[][] board){
        Random rnd = new Random();
        for (char[] letter: board) {
            for (char element: letter) {
                if ((int)element == 0){
                    element = (char) (rnd.nextInt(26) + 'A');
                }
            }
        }
        return board;
    }*/

    public static char[][] doVertical(char[] charArray, char[][] board){
        Random rand = new Random();
        int hPos = rand.nextInt(14); // Horizontal Position
        int k = charArray.length;
        int vPos = rand.nextInt(14-k); //Vertical Position

        for (int i = vPos; i < k + vPos; i++){
            board[i][hPos] = charArray[i-vPos];
        }
        return board;
    }

    public static char[][] doDiagonal(char[] charArray, char[][] board){
        Random rand = new Random();
        int k = charArray.length;
        int hPos = rand.nextInt(14-k); // Horizontal Position
        int vPos = rand.nextInt(14-k); //Vertical Position

        for (int i = 0; i < k; i++){
            board[vPos + i][hPos + i] = charArray[i];
        }
        return board;
    }

    public static int[] checkHorizontal(char[] charArray, char[][] board){
        Random rand = new Random();

        while(true){
            int hPos = rand.nextInt(14-charArray.length);
            int vPos = rand.nextInt(14);
            for(int i = 0; i < charArray.length; i++){
                if (!(board[vPos][hPos +i]== (charArray[i]) && (!(board[vPos][hPos +i] == '\u0000')))){
                    break;
                }
            }
            int[] hPlacement = new int[]{vPos, hPos};
            return hPlacement;
        }
    }
}
