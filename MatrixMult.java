import java.util.*;

public class MatrixMult {

    public static void main(String args[]) {

        // sanity check 
        // int[][] a = {{-7, -2, -5, -6}, {-2, -7, 2, 10}, {7, 2, 5, -1}, {-7, 6, -8, 0}};
        // int[][] b = {{4, -9, -5, 7}, {2, -1, -7, -4}, {7, -1, 9, -2}, {3, -7, -2, 6}};
        // int[][] ans1 = classical(4, a, b);
        // int[][] ans2 = divideAndConquer(a, b, 0, 0, 0, 0, 4);
        // int[][] ans3 = strassen(4, a, b);
        // System.out.println("Classical: \n" + Arrays.deepToString(ans1) + "\n");
        // System.out.println("Divide and Conquer: \n" + Arrays.deepToString(ans2) + "\n");
        // System.out.println("Strassen: \n" + Arrays.deepToString(ans3));

        Scanner input = new Scanner(System.in);
        System.out.print("Enter matrix size that is a power of 2: ");
        int size = input.nextInt();
        int[][] a = generateMatrix(size);
        int[][] b = generateMatrix(size);
        // System.out.println("\nMatrix A = \n" + Arrays.deepToString(a));
        // System.out.println("\nMatrix B = \n" + Arrays.deepToString(b));

        long startTime = System.nanoTime();
        // System.out.println("\nClassical: \n" + Arrays.deepToString(classical(size, a, b)));
        long endTime = System.nanoTime();
        long time = endTime - startTime;
        System.out.println("Classical Execution Time: " + (time) + "\n");

        startTime = System.nanoTime();
        // System.out.println("\nDivide and Conquer: \n" + Arrays.deepToString(divideAndConquer(a, b, 0, 0, 0, 0, size)));
        endTime = System.nanoTime();
        time = endTime - startTime;
        System.out.println("Divide and Conquer Execution Time: " + (time) + "\n");

        startTime = System.nanoTime();
        // System.out.println("\nStrassen: \n" + Arrays.deepToString(strassen(size, a, b)));
        endTime = System.nanoTime();
        time = endTime - startTime;
        System.out.println("Strassen Execution Time: " + (time) + "\n");
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

    public static int[][] strassen(int n, int[][] x, int[][] y) {
        int[][] res = new int[n][n];
        if(n == 1) {
            res[0][0] = x[0][0]*y[0][0];
            return res;
        }

        n = n/2;

        int[][] a = new int[n][n];
        int[][] b = new int[n][n];
        int[][] c = new int[n][n];
        int[][] d = new int[n][n];

        int[][] e = new int[n][n];
        int[][] f = new int[n][n];
        int[][] g = new int[n][n];
        int[][] h = new int[n][n];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                a[i][j] = x[i][j];
                b[i][j] = x[i][j+n];
                c[i][j] = x[i+n][j];
                d[i][j] = x[i+n][j+n];

                e[i][j] = y[i][j];
                f[i][j] = y[i][j+n];
                g[i][j] = y[i+n][j];
                h[i][j] = y[i+n][j+n];

            } 
        }

        int[][] p1 = strassen(n, a, sub(f, h));
        int[][] p2 = strassen(n, add(a, b), h);
        int[][] p3 = strassen(n, add(c, d), e);
        int[][] p4 = strassen(n, d, sub(g, e));
        int[][] p5 = strassen(n, add(a, d), add(e, h));
        int[][] p6 = strassen(n, sub(b, d), add(g, h));
        int[][] p7 = strassen(n, sub(a, c), add(e, f));

        int[][] c1 = add(p6, sub(add(p5, p4), p2));
        int[][] c2 = add(p1, p2);
        int[][] c3 = add(p3, p4);
        int[][] c4 = sub(sub(add(p1, p5), p3), p7);

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                res[i][j] = c1[i][j];
                res[i][j+n] = c2[i][j];
                res[i+n][j] = c3[i][j];
                res[i+n][j+n] = c4[i][j];
            }
        }

        return res;
    }

    public static int[][] add(int[][] x, int[][] y) {
        int[][] res = new int[x.length][x.length];
        for(int i = 0; i < x.length; i++) {
            for(int j = 0; j < x.length; j++) {
                res[i][j] = x[i][j] + y[i][j];
            }
        }
        return res;
    }

    public static int[][] sub(int[][] x, int[][] y) {
        int[][] res = new int[x.length][x.length];
        for(int i = 0; i < x.length; i++) {
            for(int j = 0; j < x.length; j++) {
                res[i][j] = x[i][j] - y[i][j];
            }
        }
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