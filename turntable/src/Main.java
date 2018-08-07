import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
        // 全1的二进制数，用于二进制"与运算"，allOne = 2^(n - 1) - 1
        int allOne;
        // vis[i].get(u)表示从u出发值为i的边
        List<Boolean>[] vis = new ArrayList[2];
        StringBuilder ans;

        // 计算2^x
        // x: 指数
        // 返回值：2^x
        int twoPow(int x) {
            return 1 << x;
        }

        // 求欧拉回路
        // u: 当前所在节点
        void dfs(int u) {
            for (int i = 0; i < 2; ++i) {
                if (!vis[i].get(u)) {
                    // 将u左移一位，然后将最低位置为i，再将最高位去掉
                    int v = ((u << 1) | i) & allOne;
                    vis[i].set(u, true);
                    // 递归v、加入数字到ans中
                    // TODO
                }
            }

        }

        // 本函数求解大转盘上的数，你需要把大转盘上的数按顺时针顺序返回
        // n：对应转盘大小，意义与题目描述一致，具体见题目描述。
        // 返回值：将大转盘上的数按顺时针顺序放到一个String中并返回
        String getAnswer(int n) {
            /* 请在这里设计你的算法 */
            // init
            allOne = twoPow(n - 1) - 1;
            ans = new StringBuilder();
            for (int i = 0; i < 2; ++i)
                vis[i] = new ArrayList<>(Collections.nCopies(twoPow(n - 1), false));
            dfs(0);
            return ans.toString();
        }

        // ================= 代码实现结束 =================

        void solve(InputReader in, PrintWriter out) {
            int n = in.nextInt();
            out.println(getAnswer(n));
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