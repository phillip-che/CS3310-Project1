import java.util.*;

public class MatrixMult {

    public static void main(String args[]) {
        // int[][] a = {{3,-1,2},{0,1,-2},{1,0,2}};
        // int[][] b = {{1,0,-1},{2,0,3},{-1,4,1}};
        // int[][] ans = classical(3, a, b);
        // System.out.println(Arrays.deepToString(ans));
        Scanner input = new Scanner(System.in);
        System.out.print("Enter matrix size: ");
        int size = input.nextInt();
        int[][] a = generateMatrix(size);
        int[][] b = generateMatrix(size);
        System.out.println("\nMatrix A = \n" + Arrays.deepToString(a));
        System.out.println("\nMatrix B = \n" + Arrays.deepToString(b));

        long startTime = System.nanoTime();
        System.out.println("\nClassical: \n" + Arrays.deepToString(classical(size, a, b)));
        long endTime = System.nanoTime();
        long time = endTime - startTime;
        System.out.println("Execution Time: " + (time) + "\n");

        startTime = System.nanoTime();
        System.out.println("\nDivide and Conquer: \n" + Arrays.deepToString(divideAndConquer(a, b, 0, 0, 0, 0, a.length)));
        endTime = System.nanoTime();
        time = endTime - startTime;
        System.out.println("Execution Time: " + (time) + "\n");
    }

    public static int[][] classical(int n, int[][] a, int[][] b) {
        int[][] res = new int[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                int sum = 0;
                for(int k = 0; k < n; k++) {
                    sum += a[i][k]*b[k][j];
                }
                res[i][j] = sum;
            }
        }
        return res;
    }

    public static int[][] divideAndConquer(int[][] a, int[][] b, int rowA, int colA, int rowB, int colB, int n) {
        int[][] res = new int[n][n];
        if(n == 1) {
            res[0][0] = a[rowA][colA]*b[rowB][colB];
            return res;
        }

        n = n/2;

        addMatrix(divideAndConquer(a, b, rowA, colA, rowB, colB, n), divideAndConquer(a, b, rowA, colA+n, rowB+n, colB, n), res, 0, 0);
        addMatrix(divideAndConquer(a, b, rowA, colA, rowB, colB+n, n), divideAndConquer(a, b, rowA, colA+n, rowB+n, colB+n, n), res, 0, n);
        addMatrix(divideAndConquer(a, b, rowA+n, colA, rowB, colB, n), divideAndConquer(a, b, rowA+n, colA+n, rowB+n, colB, n), res, n, 0);
        addMatrix(divideAndConquer(a, b, rowA+n, colA, rowB, colB+n, n), divideAndConquer(a, b, rowA+n, colA+n, rowB+n, colB+n, n), res, n, n);

        return res;
    }

    public static void addMatrix(int[][] a, int[][] b, int[][] c, int row, int col) {
        for(int i = 0; i < a.length; i++) {
            for(int j = 0; j < a.length; j++) {
                c[i+row][j+col] = a[i][j] + b[i][j];
            }
        }
    } 

    public static int[][] strassen(int n, int[][] a, int[][] b) {
        int[][] res = new int[n][n];

        return res;
    }

    public static int[][] generateMatrix(int size) {
        int[][] matrix = new int[size][size];
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                matrix[i][j] = (int) (Math.random() * 20) - 9;
            }
        }
        return matrix;
    }
}