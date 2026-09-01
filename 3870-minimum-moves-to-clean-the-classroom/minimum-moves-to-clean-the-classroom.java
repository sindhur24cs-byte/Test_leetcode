import java.util.*;

class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();

        int sr = 0, sc = 0;
        List<int[]> litter = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char ch = classroom[i].charAt(j);

                if (ch == 'S') {
                    sr = i;
                    sc = j;
                } else if (ch == 'L') {
                    litter.add(new int[]{i, j});
                }
            }
        }

        int k = litter.size();
        if (k == 0) return 0;

        int full = (1 << k) - 1;

        int[][] litterId = new int[m][n];
        for (int[] row : litterId)
            Arrays.fill(row, -1);

        for (int i = 0; i < k; i++) {
            litterId[litter.get(i)[0]][litter.get(i)[1]] = i;
        }

        int[][] best = new int[m * n][1 << k];
        for (int[] row : best)
            Arrays.fill(row, -1);

        int startMask = 0;
        best[sr * n + sc][startMask] = energy;

        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{sr, sc, startMask, energy, 0});

        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, 1, -1};

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            int r = cur[0];
            int c = cur[1];
            int mask = cur[2];
            int en = cur[3];
            int moves = cur[4];

            if (mask == full)
                return moves;

            if (en == 0)
                continue;

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr < 0 || nr >= m || nc < 0 || nc >= n)
                    continue;

                if (classroom[nr].charAt(nc) == 'X')
                    continue;

                int newEnergy = en - 1;
                int newMask = mask;

                int id = litterId[nr][nc];

                if (id != -1)
                    newMask |= (1 << id);

                if (classroom[nr].charAt(nc) == 'R')
                    newEnergy = energy;

                int index = nr * n + nc;

                if (best[index][newMask] >= newEnergy)
                    continue;

                best[index][newMask] = newEnergy;

                q.offer(new int[]{
                    nr,
                    nc,
                    newMask,
                    newEnergy,
                    moves + 1
                });
            }
        }

        return -1;
    }
}