import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import static java.lang.Math.PI;
import static java.lang.Math.max;

public class Main {

    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Task solver = new Task();
        solver.solve(in, out);
        out.close();
    }

    static class Task {

        // ================= 代码实现开始 =================
        
        /* 请在这里定义你需要的全局变量 */

        // 本函数计算答案（最大经过位置数字总和）
        // n：描述数字三角形大小，意义同题目描述
        // a：描述整个数字三角形，第 i 行的第 j 个数存放在 a[i][j]
        // 中（你需要特别注意的是，所有下标均从 1 开始，也就是说下标为 0 的位置将存放无效信息）
        // 返回值：最大经过位置数字总和
        int getAnswer(int n, int[][] a) {
            /* 请在这里设计你的算法 */
            // dp: 用于动态规划的数组，d[i][j]表示走到第i行第j列能得到的最大数字总和
            int[][] dp = new int[n + 1][n + 2];

            dp[1][0] = dp[1][2] = 0;
            dp[1][1] = a[1][1];
            for (int i = 2; i <= n; ++i) {
                dp[i][0] = dp[i][i + 1] = 0;
                for (int j = 1; j <= i; ++j) {
                    //
                    dp[i][j] = max(dp[i - 1][j - 1], dp[i - 1][j]) + a[i][j];
                }
            }
            int ans = 0;
            for (int i = 1; i <= n; ++i)
                ans = max(ans, dp[n][i]);
            return ans;
        }

        // ================= 代码实现结束 =================

        void solve(InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int[][] a = new int[n + 1][n + 1];
            for (int i = 1; i <= n; ++i)
                for (int j = 1; j <= i; ++j)
                    a[i][j] = in.nextInt();
            out.println(getAnswer(n, a));
        }

    }

    static class InputReader {
        BufferedReader reader;
        StringTokenizer tokenizer;

        private InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        private String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        private short nextShort() {
            return Short.parseShort(next());
        }

        private int nextInt() {
            return Integer.parseInt(next());
        }

        private long nextLong() {
            return Long.parseLong(next());
        }

        private float nextFloat() {
            return Float.parseFloat(next());
        }

        private double nextDouble() {
            return Double.parseDouble(next());
        }
    }

}