class Solution {
    int count = 0;

    public int totalNQueens(int n) {
        int[] board = new int[n];
        solve(0, n, board);
        return count;
    }

    void solve(int row, int n, int[] board) {
        if (row == n) {
            count++;
            return;
        }

        for (int col = 0; col < n; col++) {
            if (isSafe(row, col, board)) {
                board[row] = col;
                solve(row + 1, n, board);
            }
        }
    }

    boolean isSafe(int row, int col, int[] board) {
        for (int i = 0; i < row; i++) {
            if (board[i] == col) {
                return false;
            }

            if (Math.abs(board[i] - col) == Math.abs(i - row)) {
                return false;
            }
        }

        return true;
    }
}