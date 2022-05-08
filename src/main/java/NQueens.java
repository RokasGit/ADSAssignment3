import java.util.Scanner;

public class NQueens {
    public static void main(String[] args) {
        while(true){
            Scanner input = new Scanner(System.in);
            System.out.println("Enter how many queens there will be(Q>=3): ");
            int nQueens = input.nextInt();
            buildBoard(nQueens);
        }
    }
    // recursive function to solve the problem
    public static boolean solveNQueensProblem(ChessPiece[][] board,int col){
            // returns true if the problem was solved.
            if(col >= board.length){
                return true;
            }
            /*
            Try placing the queen in all rows one by one
             */
        for(int i=0;i<board.length;i++){
            // Check if the queen can be placed in the row of that column
            if(OK(board,i,col)){
                // placing the queen.
                    board[i][col] = new Queen();
                    if(solveNQueensProblem(board, col + 1)){
                        return true;
                    }
                    board[i][col] = new ChessPiece();
            }
        }

            //returns false if the solution failed.
            return false;
    }
    /*
    Checking only left side as all queens are starting from left to right, and top column.
     */
    public static boolean OK(ChessPiece[][] board, int row, int col){
            int i,j;
            // checking same row on from the left side, returning false placement is wrong
            for(i=0;i<col;i++){
                if(board[row][i].toString().equals(" Q ")){
                    return false;
                }
            }
            // checking diagonal to left upper side, returning false placement is wrong
        for(i=row,j=col;i>=0 && j>=0;i--,j--){
            if(board[i][j].toString().equals(" Q ")){
                return false;
            }
        }
        // checking diagonal to left lower side, returning false placement is wrong
        for(i=row,j=col;j>=0 && i<board.length;i++,j--){
            if(board[i][j].toString().equals(" Q ")){
                return false;
            }
        }

        // returning true that the placement is ok.
        return true;

    }
    // builds an empty board
    public static ChessPiece[][] buildBoard(int nQueens){
        ChessPiece board[][] = new ChessPiece[nQueens][nQueens];
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board.length;j++){
                    board[i][j]=new ChessPiece();
            }
        }
        if(!solveNQueensProblem(board, 0)){
            System.out.println("No solution");
        }else{
            printSolution(board);
        }
        return board;
    }
    public static void printSolution(ChessPiece[][] board){
        String boardPrintout = "";
        for(int i=0;i<board.length;i++){
            for (int j=0;j<board.length;j++){
                boardPrintout+=board[i][j];
            }
            boardPrintout+="\n";
        }
        System.out.println(boardPrintout);
    }
}
