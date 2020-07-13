package com.PG.matrix;

public class RottenOranges {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] grid = { {2,1,1},
                        {1,1,0},
                        {0,1,1} };
        s.orangesRotting(grid);
    }
}

class Solution {
    public int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] res = new int[m][n];
        boolean[][] visited = new boolean[m][n];
        int i, j, max =0;

        for(i=0; i<m; i++) {
            for(j=0; j<n; j++) {
                visited[i][j] = false;
                if(grid[i][j] == 0) {
                    res[i][j] = Integer.MAX_VALUE;
                }else if(grid[i][j] == 2){
                    res[i][j] =0;
                }else {
                    res[i][j] = -1;
                }
            }
        }


        for(i=0; i<m; i++) {
            for(j=0; j<n; j++) {
                if(grid[i][j] == 1 && res[i][j] == -1){
                    int dis = distance(i, j, res, visited);
                    res[i][j] = dis;
                    System.out.print(res[i][j] + " ");
                    if(dis>max){
                        max = dis;
                    }
                }

            }
        }

        for(i=0; i<m; i++) {
            for(j=0; j<n; j++) {
                //System.out.print(res[i][j] + " ");
                // if(res[i][j] == -1){
                //     return -1;
                // }
            }
            //System.out.println("");
        }
        return max;
    }

    public int distance(int i, int j, int[][] res, boolean[][] visited) {
        int m = res.length;
        int n = res[0].length;

        if(i<0 || i>=m || j<0 || j>= n) {
            return Integer.MAX_VALUE;
        }

        if(res[i][j] == Integer.MAX_VALUE || res[i][j] == 0 || res[i][j] != -1)
            return res[i][j];

        if(visited[i][j] == true) {
            return Integer.MAX_VALUE;
        }
        else{
            visited[i][j] = true;
            int d1 = distance(i-1, j, res, visited);
            int d2 = distance(i+1, j, res, visited);
            int d3 = distance(i, j-1, res, visited);
            int d4 = distance(i, j+1, res, visited);

            visited[i][j] =false;
            return 1+min(d1, d2, d3, d4);
        }

    }

    public int min(int d1, int d2, int d3, int d4) {
        int temp1 = d1<d2?d1:d2;
        int temp2 = d3<d4?d3:d4;

        if(temp1<temp2)
            return temp1;
        return temp2;
    }
}
