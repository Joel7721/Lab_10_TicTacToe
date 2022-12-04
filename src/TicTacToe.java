import java.util.Scanner;

public class TicTacToe {


    private static final int ROW = 3;
    private static final int COL = 3;
    private static String[][] board = new String[ROW][COL];
    String player = "X";

    public static void main(String[] args) {
        clearBoard();
        boolean finished = false;
        boolean playing = true;
        String player = "X";
        int playerRowMove = 0;
        int playerColMove = 0;


        board[playerRowMove][playerColMove] = "X";

        System.out.println("Welcome to Joel's TicTacToe Game!");

        do // program loop
        {

            clearBoard();
            display();
            int count = 1;
            do //game loop
            {
                do
                {
                    if (count % 2 != 0) {
                        player = "X";
                    } else {
                        player = "O";
                    }
                    Scanner pipe = new Scanner(System.in);
                    playerRowMove = SafeInput.getRangedInt(pipe, "Player " + player + " please enter a value from 1-3 for the row ", 1, 3);
                    playerColMove = SafeInput.getRangedInt(pipe, "Player " + player + " please enter a value from 1-3 for the column ", 1, 3);

                    playerRowMove--;
                    playerColMove--;

                    boolean isValidMove = isValidMove(playerRowMove, playerColMove);
                    if (!isValidMove) {
                        System.out.println("The space is already taken, try again");

                    }

                }while(!isValidMove(playerRowMove, playerColMove));


                board[playerRowMove][playerColMove] = player;
                count++;
                display();



                if(isColWin(player)){
                    playing = false;
                }
                else if(isRowWin(player)){
                    playing = false;
                }
                else if(isDiagonalWin(player)){
                    playing = false;
                }
                else if(count == 10){
                    System.out.println("It is a tie.");
                    playing = false;
                }
                else{
                    playing = true;
                }



            }while (playing);


            if(count < 10) {
                Scanner in = new Scanner(System.in);
                finished = SafeInput.getYNConfirm(in, "Player " + player + " wins! Would you like to play again?");
            }
            if(count == 10) {
                Scanner in = new Scanner(System.in);
                finished = SafeInput.getYNConfirm(in, "Would you like to play again?");
            }

        }while (finished);
    }


    private static void clearBoard() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                board[row][col] = " ";
            }
        }
    }

    private static boolean isValidMove(int row, int col){
        return board[row][col].equals(" ");
    }


    public static void display(){
        for(int row = 0; row < board.length; row++){
            for(int col = 0; col < board[row].length; col++){
                System.out.printf("%5s | ", board[row][col]);
            }
            System.out.println("");
        }

    }


    private static boolean isRowWin(String player){

        for(int row = 0; row < ROW; row++){
            if(board[row][0].equals(player) && board[row][1].equals(player) && board[row][2].equals(player)){
                return true;
            }
        }
        return false;
    }

    private static boolean isColWin(String player){

        for(int col = 0; col < COL; col++){
            if(board[0][col].equals(player) && board[1][col].equals(player) && board[2][col].equals(player)){
                return true;
            }
        }
        return false;
    }

    private static boolean isDiagonalWin(String player){
        for(int row = 0; row < ROW; row++){
            if(board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)){
                return true;
            }
            if(board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player)){
                return true;
            }

        }
        return false;
    }
}


