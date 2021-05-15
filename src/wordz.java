import java.util.Random;

public class wordz {
    public static void main(String[] args){
        int SIZE = 15;
        char[][] board = new char[15][15];
        fillBoard(board, 'X');
        //printBoard(board);
        String word = "KITTENS";
        int[] here = new int[]{-1,-1};
        while (here[0] == -1){
            here = findHorizontal(board, word);
        }
        doHorizontal(here, "KITTENS", board);
        printBoard(board);
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

    public static void fillBoard(char[][] board, char to_fill){
        for (int i = 0; i < 15; i++){
            for (int j = 0; j < 15; j++){
                board[i][j] = to_fill;
            }
        }
    }

    public static int[] findHorizontal(char[][] board, String to_insert){
        int[] placement = new int[2];
        Random rand = new Random();

        for (int k = 0; k < 100; k++){
            int vPos = rand.nextInt(board.length);
            int hPos = rand.nextInt(board.length - to_insert.length());

            for(int i = 0; i < to_insert.length(); i++){
                if ((board[vPos][hPos +i]!= (to_insert.charAt(i)) && (board[vPos][hPos +i] != 'X'))){
                    break;
                }
                if (i == to_insert.length() - 1){
                    placement[0] = vPos;
                    placement[1] = hPos;
                    return placement;
                }
            }
        } return new int[]{-1, -1};
    }

    public static char[][] doHorizontal(int[] position, String to_insert, char[][] board){
        // Places an array of characters horizontally into the board array.

        int vPos = position[0]; // Vertical Position
        int wordLength = to_insert.length();
        int hPos = position[1]; //Horizontal Position

        for (int wordChar = 0; wordChar < wordLength; wordChar++){
            board[vPos][hPos + wordChar] = to_insert.charAt(wordChar);
        }
        return board;
    }
}