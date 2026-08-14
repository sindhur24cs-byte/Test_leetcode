class Solution {
    public boolean isValidSudoku(char[][] board) {

        boolean[][] row = new boolean[9][10];
        boolean[][] col = new boolean[9][10];
        boolean[][] box = new boolean[9][10];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {

                if (board[i][j] == '.') {
                    continue;
                }

                int num = board[i][j] - '0';

                // Find the 3x3 box number
                int boxIndex = (i / 3) * 3 + (j / 3);

                // If already present, Sudoku is invalid
                if (row[i][num] || col[j][num] || box[boxIndex][num]) {
                    return false;
                }

                // Mark the number as present
                row[i][num] = true;
                col[j][num] = true;
                box[boxIndex][num] = true;
            }
        }

        return true;
    }
}