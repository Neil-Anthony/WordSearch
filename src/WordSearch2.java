import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.sql.Array;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.lang.String;


public class WordSearch2 {
    public static void main(String[] args) throws FileNotFoundException {

        System.out.println(reverseString("I like Ike"));
        char[][] board = new char[8][8];
        char[] a;
        int [] position;
        a = stringToCharArray("Lemmons");
        position = checkDiagonal(a, board);
        doDiagonal(position, a, board);
        //fillBoard(board);
        //printBoard(board);

        position = checkHorizontal(a,board);
        doHorizontal(position, a, board);
        position = checkVertical(a, board);
        doVertical(position, a, board);
        printBoard(board);

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

    public static char[][] doHorizontal(int[] position, char[] charArray, char[][] board){
        Random rand = new Random();
        int vPos = position[0]; // Vertical Position
        int wordLength = charArray.length;
        int hPos = position[1]; //Horizontal Position

        for (int wordChar = 0; wordChar < wordLength; wordChar++){
            board[vPos][hPos + wordChar] = charArray[wordChar];
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

    public static char[][] doVertical(int[] placement, char[] charArray, char[][] board){
        Random rand = new Random();
        int wordLength = charArray.length;
        int hPos = placement[1]; // Horizontal Position

        int vPos = placement[0]; //Vertical Position

        for (int wordChar = 0; wordChar < wordLength; wordChar++){
            board[vPos + wordChar][hPos] = charArray[wordChar];
        }
        return board;
    }

    public static char[][] doDiagonal(int[] placement, char[] charArray, char[][] board){

        int wordLength = charArray.length;
        int hPos = placement[1]; // Horizontal Position
        int vPos = placement[0]; //Vertical Position

        for (int i = 0; i < wordLength; i++){
            board[vPos + i][hPos + i] = charArray[i];
        }
        return board;
    }

    public static int[] checkHorizontal(char[] charArray, char[][] board){
        Random rand = new Random();
        Boolean proceed = true;
        int[] placement = new int[2];

        do{
            int hPos = rand.nextInt(8-charArray.length);
            int vPos = rand.nextInt(8);

            for(int i = 0; i < charArray.length; i++){
                if (!((board[vPos][hPos +i]== (charArray[i]) || (board[vPos][hPos +i] == '\u0000')))){
                    break;
                }
                if (i == charArray.length - 1){
                    proceed = false;
                    placement[0] = vPos;
                    placement[1] = hPos;
                }
            }
        }while(proceed);
        return placement;
    }

    public static int[] checkVertical(char[] charArray, char[][] board){
        Random rand = new Random();
        Boolean proceed = true;
        int[] placement = new int[2];

        do{
            int vPos = rand.nextInt(8-charArray.length);
            int hPos = rand.nextInt(8);

            for(int i = 0; i < charArray.length; i++){
                if (!((board[vPos+i][hPos]== (charArray[i]) || (board[vPos+i][hPos] == '\u0000')))){
                    break;
                }
                if (i == charArray.length - 1){
                    proceed = false;
                    placement[0] = vPos;
                    placement[1] = hPos;
                }
            }
        }while(proceed);
        return placement;
    }

    public static int[] checkDiagonal(char[] charArray, char[][] board){
        Random rand = new Random();
        Boolean proceed = true;
        int[] placement = new int[2];

        do{
            int vPos = rand.nextInt(8-charArray.length);
            int hPos = rand.nextInt(8-charArray.length);

            for(int i = 0; i < charArray.length; i++){
                if (!((board[vPos+i][hPos + i]== (charArray[i]) || (board[vPos+i][hPos] == '\u0000')))){
                    break;
                }
                if (i == charArray.length - 1){
                    proceed = false;
                    placement[0] = vPos;
                    placement[1] = hPos;
                }
            }
        }while(proceed);
        return placement;
    }

}
