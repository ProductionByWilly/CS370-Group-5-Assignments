import java.util.Scanner;

public class TicTacToe {

    // Attributes
    private final static int BOARDSIZE = 3;
    private enum Status {WIN, DRAW, CONTINUE};
    private char[][] board;
    private boolean firstPlayer;
    private boolean gameOver;
    
    // Constructor
    public TicTacToe(){
        board = new char[BOARDSIZE][BOARDSIZE];
        for (int i = 0; i < BOARDSIZE; i++) {
            for (int j = 0; j < BOARDSIZE; j++) {
                    board[i][j] = ' ';
                }    
            }
        firstPlayer = true;
        gameOver = false;
    }



    // Methods
    public void play() {
        Scanner input = new Scanner(System.in);


        
    }
    private void printStatus(int player) {
        //insert code
    }
    private Status gamestatus() {
        //insert code
    }

    public void printBoard() {
        //insert code
    }
    private void printSymbol(int row, int column, char value) {
        board[row][column] = value; //place the value on the board
    }

    private boolean validMove(int row, int column) {
        if (row < 0 || row >= BOARDSIZE || column < 0 || column >= BOARDSIZE){ // Check if in bounds
            System.out.println("Not a valid move, try again.");
            return false;
        }
        if (board[row][column] != ' '){ // Check if cell is occupied already
            System.out.println("Cell is occupied, try again.");
            return false;
        }
        return true;
    }

}





