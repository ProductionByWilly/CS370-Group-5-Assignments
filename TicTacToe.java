import java.util.Scanner;

public class TicTacToe {

    // Attributes
    private final static int BOARDSIZE = 3;  // Size of the tictactoe board
    private enum Status {WIN, DRAW, CONTINUE};  // Enum to represent game result
    private char[][] board;  // 2D array for the board
    private boolean firstPlayer;   // True if player X's turn
    private boolean gameOver;   // To stop game when finished
    
    // Constructor
    public TicTacToe(){
        board = new char[BOARDSIZE][BOARDSIZE];
        for (int i = 0; i < BOARDSIZE; i++) {
            for (int j = 0; j < BOARDSIZE; j++) {
                    board[i][j] = ' ';
                }    
            }
        firstPlayer = true;    // X goes first
        gameOver = false;      // Game is not over at start
    }



    // Methods
    public void play() {
        Scanner input = new Scanner(System.in); // Used in java.util pack to read user input
        System.out.print("Enter row ( 0, 1 or 2): "); // Get the row input
        int row = input.nextInt(); // Grab the row's int value from the user
        System.out.print("Enter column ( 0, 1 or 2): "); // Get the column input
        int column = input.nextInt(); // Grab the columns int value from the user

        while(!validMove(row,column))
        {
            System.out.print("Enter a new value ( 0, 1 or 2):" );
            row = input.nextInt(); // Grab the columns int value from the user
            System.out.print("Enter column ( 0, 1 or 2): "); // Get the column input
            column = input.nextInt(); // Grab the columns int value from the user
        }


        
    }


    // Print current status (whose turn it is)
    private void printStatus(int player) {
        if (player == 1) {
            System.out.println("Player X's turn.")
        } else {
            System.out.println("Player O's turn.");
        }
    }


    // Check whetrher the game is won, drawn, or still going
    private Status gameStatus() {
        //insert code
    }

    public void printBoard() {
        //insert code
        String line = " ------------------"; // line used for to create the boxes
        System.out.println(line); // print the top
        for(int i = 0;i < BOARDSIZE; i ++) // go through the rows
            {
                System.out.print(" | "); 
                for(int j = 0; j < BOARDSIZE;j++) // go through the columns
                    {
                    System.out.print(board[i][j] + "  |  "); // make each cell and separate by using |
                    }
                System.out.println();
                System.out.println(line); // Print after each board cell is created
            }
    }

    // Place a symbol (X or O) on the board
    private void printSymbol(int row, int column, char value) {
        board[row][column] = value; //place the value on the board
    }

    // Check if a move is valid
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

    //This is the Main function that we can use to test the Methods
    public static void main(String[] args){
        TicTacToe t = new TicTacToe();
        System.out.println("This is the (Method class to test) class");
        t.play(); // replace play with the function you are going to test
    }

}








