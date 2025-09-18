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
        Scanner input = new Scanner(System.in);

        while (!gameOver) {
            printBoard(); 
            char symbol = firstPlayer ? 'X' : 'O';
            System.out.println("Player " + symbol + "'s turn.");

            int row, col;
            do {
                System.out.print("Player " + symbol + ": Enter row (0, 1, or 2): ");
                row = input.nextInt();
                System.out.print("Player " + symbol + ": Enter column (0, 1, or 2): ");
                col = input.nextInt();
            } while (!validMove(row, col));

            printSymbol(row, col, symbol);

            Status status = gameStatus();
            if (status == Status.WIN) {
                printBoard();
                System.out.println("Player " + symbol + " wins.");
                gameOver = true;
            } else if (status == Status.DRAW) {
                printBoard();
                System.out.println("It's a draw!");
                gameOver = true;
            } else {
                firstPlayer = !firstPlayer;  // Switch turns
            }
            
        }
    }

    // Print current status (whose turn it is)
    private void printStatus(int player) {
        if (player == 1) {
            System.out.println("Player X's turn.");
        } else {
            System.out.println("Player O's turn.");
        }
    }


    // Check whether the game is won, drawn, or still going
    private Status gameStatus() {
        char symbol = firstPlayer ? 'X' : 'O';  // char options

        // Check rows
        for (int i = 0; i < BOARDSIZE; i++) {
            if (board[i][0] == symbol && board[i][1] == symbol && board[i][2] == symbol) {
                return Status.WIN;
            }
        }

        // Check columns
        for (int j = 0; j < BOARDSIZE; j++) {
            if (board[0][j] == symbol && board[1][j] == symbol && board[2][j] == symbol) {
                return Status.WIN;
            }
        }

        // Check diagonals
        if (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) {
            return Status.WIN;
        }
        if (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol) {
            return Status.WIN;
        }


        // Check if board is full (draw)
        boolean full = true;
        for (int i = 0; i < BOARDSIZE; i++) {
            for (int j = 0; j < BOARDSIZE; j++) {
                if(board[i][j] == ' ') {
                    full = false;
                }
            }
        }
        if (full) {
            return Status.DRAW; // Board is full, game ends in a draw
        }
        return Status.CONTINUE; // Game keeps going
    }

    public void printBoard() {
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








