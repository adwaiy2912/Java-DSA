import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class backtracking {
    public static void printPerm(String str, String perm) {
        if (str.length() == 0) {
            System.out.println(perm);
            return;
        }
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            String newStr = str.substring(0, i) + str.substring(i+1);
            printPerm(newStr, perm + ch);
        }
    }

    // solveNQueen 
    private static boolean isSafe(char[][] board, int row, int col) {

        // vertical check
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 'Q') {
                return false;
            }
        }

        //horizontal check
        for (int j = 0; j < col; j++) {
            if (board[row][j] == 'Q') {
                return false;
            }
        }

        // upper right check
        for (int i = row+1, j = col-1; i < board.length && j >= 0; i++, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        
        // upper left check
        for (int i = row-1, j = col-1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        // lower right check
        for (int i = row+1, j = col+1; i < board.length && j < board.length; i++, j++) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        // lower left check
        for (int i = row-1, j = col+1; j < board.length && i >= 0; j++, i--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        return true;
    }

    private static void addBoard(List<List<String>> allBoards, char[][] board) {        
        List<String> currBoard = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            String str = "";
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == 'Q') {
                    str += 'Q';
                } else {
                    str += '.';
                }
            }
            currBoard.add(str);
        }
        allBoards.add(currBoard);
    }

    private static void recFnc(List<List<String>> allBoards, char[][] board, int col) {
        if (col == board.length) {
            addBoard(allBoards, board);
            return;
        }
        for (int row = 0; row < board.length; row++) {
            if (isSafe(board, row, col)) {
                board[row][col] = 'Q';
                recFnc(allBoards, board, col+1);
                board[row][col] = '.';
            }
        }

    }

    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> allBoards = new ArrayList<>();
        char[][] board = new char[n][n];

        recFnc(allBoards, board, 0);
        System.out.println(allBoards);
        return allBoards;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // printPerm("abc", "");
        // solveNQueens(n);

        sc.close();
    }
}
