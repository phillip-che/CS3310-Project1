import java.util.Arrays;

public class MatrixMult {
    public static void main(String args[]) {
        int[][] a = {{3,-1,2},{0,1,-2},{1,0,2}};
        int[][] b = {{1,0,-1},{2,0,3},{-1,4,1}};
        int[][] ans = classical(3, a, b);
        System.out.println(Arrays.deepToString(ans));
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

    public static int[][] divideAndConquer(int n, int[][] a, int[][] b) {
        int[][] res = new int[n][n];

        

        return res;
    }

    public static int[][] strassen(int n, int[][] a, int[][] b) {
        int[][] res = new int[n][n];

        return res;
    }
}