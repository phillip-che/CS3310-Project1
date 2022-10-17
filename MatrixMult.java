import java.util.*;

public class MatrixMult {

    private static long time = 0;
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
        System.out.println("\nClassical: \n" + Arrays.deepToString(classical(size, a, b)));
        System.out.println("Execution Time: " + (time) + "\n");
    }

    public static int[][] classical(int n, int[][] a, int[][] b) {
        int[][] res = new int[n][n];
        long startTime = System.nanoTime();
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                int sum = 0;
                for(int k = 0; k < n; k++) {
                    sum += a[i][k]*b[k][j];
                }
                res[i][j] = sum;
            }
        }
        long endTime = System.nanoTime();
        time = endTime - startTime;
        return res;
    }

    public static int[][] divideAndConquer(int n, int[][] a, int[][] b) {
        int[][] res = new int[n][n];
        if(n == 1) {
            res[0][0] = a[0][0]*b[0][0];
            return res;
        }

        int size = n/2;

        addMatrix(divideAndConquer(size, a, b), b, res, size);

        return res;
    }

    public static void addMatrix(int[][] a, int[][] b, int[][] c, int size, int rowC, int colC) {
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                c[i+rowC][j+colC] = a[i][j] + b[i][j];
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