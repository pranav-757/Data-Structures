package com.PG.matrix;
import java.util.Arrays;
import  java.util.Random;
public class MatrixRotation {

    public static int getNextRandomNumber() {
        Random rand = new Random();
        return ( rand.nextInt(200) + 1) ;
    }
    public static void initialiseMatrix(int[][] mat, boolean isRandom) {
        if(isRandom) {
            for(int i=0; i< mat.length; i++) {
                for (int j=0; j<mat[0].length; j++) {
                    mat[i][j] = getNextRandomNumber();
                }
            }
        } else {
            int[][] arr = {
                    {1,2,3,4},
                    {11, 12, 13, 14},
                    {21, 22, 23, 24},
                    {31, 32, 33, 34},
            };
            for(int i=0; i< mat.length; i++) {
                for (int j=0; j<mat[0].length; j++) {
                    mat[i][j] = arr[i][j];
                }
            }

        }
    }

    public static void printMatrix(int[][] mat) {
        for(int i=0; i< mat.length; i++) {
            System.out.print(mat[i][0]);
            for (int j=1; j<mat[0].length; j++) {
                System.out.print(" "+mat[i][j]);
            }
            //System.out.println("mat = " + Arrays.deepToString(mat));
            System.out.println("\n");
        }
    }

    public static void rotateMatrixTranspose(int[][] mat) {
        int i, j, k;

        //transpose;
        for(i=0; i<mat.length; i++) {
            for(j=i; j<mat[0].length; j++) {
                int temp = mat[i][j];
                mat[i][j] = mat[j][i];
                mat[j][i] = temp;
            }
        }

        //reverse columns
        for(i=0; i<mat[0].length; i++) {
            for(j=0, k=mat.length-1; j<k; j++, k--) {
                int temp = mat[j][i];
                mat[j][i] = mat[k][i];
                mat[k][i] = temp;
            }
        }
    }

    public static void rotateMatrix(int[][] mat) {
        int N = mat.length,
                i=0,
                j=0;

        for(i=0; i<N/2; i++) {
            for(j=i; j<N-i-1; j++) {
                int temp = mat[i][j];

                mat[i][j] = mat[j][N-i-1];
                //move from bottom to right
                mat[j][N-i-1] = mat[N-i-1][N-j-1];
                //move values from left to bottom
                mat[N-i-1][N-j-1] = mat[N-1-j][i];

                mat[N-1-j][i] = temp;
            }
        }
        printMatrix(mat);
    }
    public static void main(String[] args) {
            int[][] a = new int[4][4];

            initialiseMatrix(a, true);
            printMatrix(a);
        System.out.println("======================= " );
            rotateMatrix(a);
    }
}
