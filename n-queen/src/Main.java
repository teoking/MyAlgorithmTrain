import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

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
        // ans: 总答案
        // allOne: 用于二进制&的全1数
        int ans, allOne;

        // 搜索（用二进制来优化）
        // pos：其二进制上的某个位置的1表示当前所在行的相应位置（列）已经放了一个皇后
        // left：其二进制上的某个位置的1表示当前所在行的相应的位置（是由于右对角线上已经有皇后）不能放置皇后
        // right：其二进制上的某个位置的1表示当前所在行的相应的位置（是由于左对角线上已经有皇后）不能放置皇后
        void dfs(int pos, int left, int right) {
            if (pos == allOne) {
                ++ans;
                return;
            }
            for (int t = allOne & (~(pos | left | right)); t > 0;) {
                int p = t & -t; // low bit: 二进制中最右边的1
                dfs(pos | p, (left | p) << 1, (right | p) >> 1);
                t ^= p; // 消掉low bit
            }
        }

        // 一个n×n的棋盘，在棋盘上摆n个皇后，求满足任意两个皇后不能在同一行、同一列或同一斜线上的方案数
        // n：上述n
        // 返回值：方案数
        int getAnswer(int n) {
            /* 请在这里设计你的算法 */
            ans = 0;
            allOne = (1 << n) - 1;
            dfs(0, 0, 0);
            return ans;
        }

        // ================= 代码实现结束 =================

        void solve(InputReader in, PrintWriter out) {
            out.println(getAnswer(in.nextInt()));
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