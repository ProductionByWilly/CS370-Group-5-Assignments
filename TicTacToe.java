import java.util.Scanner;

public class TicTacToe {

    // Attributes
    private final static int BOARDSIZE = 3;
    private enum Status {WIN, DRAW, CONTINUE};
    private char[][] board;
    private boolean firstPlayer;
    private boolean gameOver;
    
    // Will
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








    // Ethan















    // Jonathan











    


    // Carmelo

}



