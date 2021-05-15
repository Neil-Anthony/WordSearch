/*
Neil Miller
Darrel Criss
CS 145
Assignment 1: Word Search
Purpose: Will take in up to 12 words from the user and will hide them in a two dimensional array of characters
diagonally, horizontally, and vertically. The user can display the board, or the solution if desired.
For extra credit:
Word search will overlap words with previously hidden words in any direction (ex line 410)
Try Catch used to sanitize user input (line 219 and 241)
Switch Case line 42.
 */


import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;


public class NMWordSearch {
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args){
        printIntro();
        String[] hiddenWords = getHiddenWords(input);

        int extra = Math.max(0,(hiddenWords.length-8));
        int longest = findLongestString(hiddenWords);
        char[][] board = new char[longest+extra+3][longest+extra+3];

        board = fillBoard(board, hiddenWords);
        char [][] solution = copyTwoDimensionalArray(board);
        board = randomizeBoard(board);
        String userInput = "Placeholder";



        do {
            showOptions();
            userInput = input.next();
            String cmd = userInput.substring(0,1);
            cmd.toLowerCase();

            switch (cmd){
                case "p":   printBoard(board);
                    break;
                case "s":   printBoard(solution);
                    break;
                case "o":   showOptions();
                    break;
                case "q":   break;
                default:    System.out.println("Please enter a valid command. Enter \"o\" to show your options");
                    break;
            }

        } while (!userInput.equalsIgnoreCase("q"));
    }

    public static void printIntro(){
        System.out.println("This is a word search game.");
    }


    public static String reverseString(String reverseThis){
        // This method will reverse a string.
        int length = reverseThis.length();
        char [] tbd = new char [reverseThis.length()];
        for (int i = 0; i < length; i++){
            tbd[length - i - 1] = reverseThis.charAt(i);
        }
        return String.copyValueOf(tbd);
    }

    public static char[] stringToCharArray(String toArray){
        // Converts a string into an array of characters.
        char[] charArray = new char[toArray.length()];
        int arraySize = toArray.length();
        for (int i = 0; i < arraySize; i++){
            charArray[i] = toArray.charAt(i);
        }
        return charArray;
    }

    public static char[][] doHorizontal(int[] position, char[] charArray, char[][] board){
        // Places an array of characters horizontally into the board array.

        int vPos = position[0]; // Vertical Position
        int wordLength = charArray.length;
        int hPos = position[1]; //Horizontal Position

        for (int wordChar = 0; wordChar < wordLength; wordChar++){
            board[vPos][hPos + wordChar] = charArray[wordChar];
        }
        return board;
    }

    public static void printBoard(char[][] board){
        // Displays a two dimensional array.
        for (char[] letter: board) {
            for (char element: letter) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }

    /*public static char[][] fillBoard(char[][] board){
        ***THIS DOES NOT WORK BECAUSE A FOR EACH CAN NOT MODIFY AN ARRAY***
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
        // Places an array of characters vertically into the board array.

        int wordLength = charArray.length;
        int hPos = placement[1]; // Horizontal Position

        int vPos = placement[0]; //Vertical Position

        for (int wordChar = 0; wordChar < wordLength; wordChar++){
            board[vPos + wordChar][hPos] = charArray[wordChar];
        }
        return board;
    }

    public static char[][] doDiagonal(int[] placement, char[] charArray, char[][] board){
        // Places an array of characters diagonally top left to bottom right into the board array.

        int wordLength = charArray.length;
        int hPos = placement[1]; // Horizontal Position
        int vPos = placement[0]; //Vertical Position

        for (int i = 0; i < wordLength; i++){
            board[vPos + i][hPos + i] = charArray[i];
        }
        return board;
    }

    public static int[] checkHorizontal(char[] charArray, char[][] board){
        // Finds a position on the board where a char array will fit horizontally left to right.

        Random rand = new Random();

        int[] placement = new int[2];

        for (int k = 0; k < 100; k++){
            int vPos = rand.nextInt(board.length-charArray.length);
            int hPos = rand.nextInt(board.length-charArray.length);

            for(int i = 0; i < charArray.length; i++){
                if ((board[vPos][hPos +i]!= (charArray[i]) && (board[vPos][hPos +i] != '\u0000'))){
                    break;
                }
                if (i == charArray.length - 1){
                    placement[0] = vPos;
                    placement[1] = hPos;
                    return placement;
                }
            }
        } return new int[]{-1, -1};
    }

    public static int[] checkVertical(char[] charArray, char[][] board){
        // Finds a position on the board where a char array will fit vertically top to bottom.

        Random rand = new Random();

        int[] placement = new int[2];

        for (int k = 0; k < 100; k++){
            int vPos = rand.nextInt(board.length-charArray.length);
            int hPos = rand.nextInt(board.length-charArray.length);

            for(int i = 0; i < charArray.length; i++){
                if ((board[vPos + i][hPos]!= (charArray[i]) && (board[vPos+ i][hPos] != '\u0000'))){
                    break;
                }
                if (i == charArray.length - 1){
                    placement[0] = vPos;
                    placement[1] = hPos;
                    return placement;
                }
            }
        } return new int[]{-1, -1};
    }

    public static int[] checkDiagonal(char[] charArray, char[][] board){
        // Finds a position on the board where a char array will fit diagonally top left to bottom right.

        Random rand = new Random();

        int[] placement = new int[2];

        for (int k = 0; k < 100; k++){
            int vPos = rand.nextInt(board.length-charArray.length);
            int hPos = rand.nextInt(board.length-charArray.length);

            for(int i = 0; i < charArray.length; i++){
                if ((board[vPos+i][hPos +i]!= (charArray[i]) && (board[vPos+i][hPos +i] != '\u0000'))){
                    break;
                }
                if (i == charArray.length - 1){
                    placement[0] = vPos;
                    placement[1] = hPos;
                    return placement;
                }
            }
        } return new int[]{-1, -1};

    }

    public static int getSize(Scanner input) {
        // Gets an integer between 1 and 12 (inclusive) from the user.

        while (true) {
            try {
                int output = input.nextInt();

                if (output > 12 || output < 1) {
                    throw new Exception();
                }

                return output;

            } catch (InputMismatchException e) {
                input.next();
                System.out.println("That is not an integer. Try again: ");
            } catch (Exception e) {
                System.out.println("The number needs to be less than 12 and greater than 0");
            }
        }
    }

    public static String getWord(Scanner input) {
        // Gets a string of characters from the user that contains only letters and is between 1 and characters long

        while (true) {
            try {
                String output = input.next();
                int wordLength = output.length();
                for (int i = 0; i < wordLength; i++){
                    if ((!Character.isLetter(output.charAt(i)))) {
                        throw new Exception("Words only please");
                    }
                    if (wordLength < 1){
                        throw new Exception ();
                    }
                    if (wordLength > 12){
                        throw new Exception();
                    }
                }
                output = output.toUpperCase();
                return output;

            } catch (InputMismatchException e) {
                input.next();
                System.out.println("That is not a word. Try again: ");
            } // End while
            catch (Exception e) {
                System.out.println("The word needs to be less than 12 characters.");
            }
        }
    }

    public static String[] getHiddenWords(Scanner input){
        // Gets between 1 and 12 words no longer than 12 characters long from the user and puts it into an array.

        System.out.println("How many words would you like to find? (up to 12 words)");
        int wordCount = getSize(input);
        String[] hiddenWords = new String[wordCount];
        for (int i = 0; i < wordCount; i++){
            System.out.print("Word number " + (i+1) +": ");
            hiddenWords[i]= getWord(input);
        }

        String test = Arrays.toString(hiddenWords);
        System.out.println(test);
        return hiddenWords;
    }

    public static char[][] fillBoard(char[][] board, String[] hiddenWords){
        /*  Fills the board with words from the string array generated by the user.
            Cycles through the switch case up to 100 times.
            Each case will search 100 times for a placement, if a placement is not found, break
            back to the switch and try a different random case.
         */
        Random rnd = new Random();
        int reverse = rnd.nextInt(2);
        for(int i = 0; i < hiddenWords.length; i++) {

            char[] charArray;
            int [] placement;
            if (reverse == 1){ hiddenWords[i] = reverseString(hiddenWords[i]); }

            for(int k = 0; k<100; k++) {
                int path = rnd.nextInt(4);
                switch (path) {
                    case 0:
                        charArray = stringToCharArray(hiddenWords[i]);
                        placement = checkDiagonal(charArray, board);
                        if (placement[0] == -1) {
                            break;
                        } else {
                            doDiagonal(placement, charArray, board);
                            k = 100;
                            break;
                        }

                    case 1:
                        charArray = stringToCharArray(hiddenWords[i]);
                        placement = checkHorizontal(charArray, board);
                        if (placement[0] == -1) {
                            break;
                        } else {
                            doHorizontal(placement, charArray, board);
                            k = 100;
                            break;
                        }
                    case 2:
                        charArray = stringToCharArray(hiddenWords[i]);
                        placement = checkVertical(charArray, board);
                        if (placement[0] == -1) {
                            break;
                        } else {
                            doVertical(placement, charArray, board);
                            k = 100;
                            break;
                        }
                    case 3:
                        charArray = stringToCharArray(hiddenWords[i]);
                        placement = checkDiagonalOther(charArray, board);
                        if (placement[0] == -1) {
                            break;
                        } else {
                            doDiagonalOther(placement, charArray, board);
                            k = 100;
                            break;
                        }
                }
            }
        }
        return board;
    }

    public static void showOptions(){
        // Menu options.
        System.out.println("Please select an option.");
        System.out.println("Print out your word search: (p)");
        System.out.println("Show the solution to your word search: (s)");
        System.out.println("Show your options: (o)");
        System.out.println("Quit the program: (q)");
    }

    public static char[][] copyTwoDimensionalArray(char[][] board){
        // Makes a copy of a two dimensional array.

        char[][] solution = new char[board.length][board.length];
        for(int i=0; i<board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                solution[i][j] = board[i][j];
            }
        }
        return solution;
    }

    public static char[][] randomizeBoard(char[][] board){
        // Fills all empty spots on the board with random capital letters.

        Random rnd = new Random();
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board.length; j++){
                if (board[i][j] == '\u0000'){
                    board[i][j] = (char) (rnd.nextInt(26) + 'A');
                }
            }
        }
        return board;
    }

    public static int findLongestString(String[] hiddenWords){
        // Returns the length of the longest string in a string array.

        int longest = 0;
        for (int i = 0; i < hiddenWords.length; i++){
            if (hiddenWords[i].length() > longest){
                longest = hiddenWords[i].length();
            }
        }
        return longest;
    }

    public static int[] checkDiagonalOther(char[] charArray, char[][] board){
        //Finds a place in the board array where the character array will fit in diagonally.

        Random rand = new Random();

        int[] placement = new int[2];

        for (int k = 0; k < 100; k++){
            int vPos = rand.nextInt(board.length-charArray.length)+(charArray.length);
            int hPos = rand.nextInt(board.length-charArray.length);

            for(int i = 0; i < charArray.length; i++){
                if ((board[vPos-i][hPos +i]!= (charArray[i]) && (board[vPos-i][hPos +i] != '\u0000'))){
                    break;
                }
                if (i == charArray.length - 1){
                    placement[0] = vPos;
                    placement[1] = hPos;
                    return placement;
                }
            }
        } return new int[]{-1, -1};
    }

    public static char[][] doDiagonalOther(int[] placement, char[] charArray, char[][] board){
        //// Places an array of characters diagonally bottom left to top right into the board array.

        int wordLength = charArray.length;
        int hPos = placement[1]; // Horizontal Position
        int vPos = placement[0]; //Vertical Position

        for (int i = 0; i < wordLength; i++){
            board[vPos - i][hPos + i] = charArray[i];
        }
        return board;
    }
}
