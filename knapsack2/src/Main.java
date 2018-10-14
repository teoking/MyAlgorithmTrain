import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

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
        final int N = 5005;

        // d: 前缀背包，d[i][j]表示物品1到物品i放进容量j的背包中的最大价值
        // f: 后缀背包，f[i][j]表示物品1到物品n放进容量j的背包中的最大价值
        int[][] d = new int[N][N];
        int[][] f = new int[N][N];

        // n个物品，每个物品有体积价值，求若扔掉一个物品后装进给定容量的背包的最大价值
        // n：如题
        // w：长度为n+1的数组，w.get(i)表示第i个物品的价值（下标从1开始，下标0是一个数字-1，下面同理）
        // v：长度为n+1的数组，v.get(i)表示第i个物品的体积
        // q：如题
        // qV：长度为q+1的数组，qV.get(i)表示第i次询问所给出的背包体积
        // qx：长度为q+1的数组，qx.get(i)表示第i次询问所给出的物品编号
        // 返回值：返回一个长度为q的数组，依次代表相应询问的答案
        List<Integer> getAnswer(int n, List<Integer> w, List<Integer> v, int q, List<Integer> qV, List<Integer> qx) {
            /* 请在这里设计你的算法 */
            // 计算前缀背包
            for (int i = 1; i <= n; ++i) {
                for (int V = 0; V < v.get(i); ++V)
                    d[i][V] = d[i - 1][V];
                for (int V = v.get(i); V <= 5000; ++V)
                    d[i][V] = max(d[i - 1][V], d[i - 1][V - v.get(i)] + w.get(i));
            }
            // 计算后缀背包
            for (int i = n; i >= 1; --i) {
                // 提示：和计算前缀背包很像
                for (int V = 0; V < v.get(i); ++V)
                    f[i][V] = f[i - 1][V];
                for (int V = v.get(i); V <= 5000; ++V)
                    f[i][V] = max(f[i - 1][V], f[i - 1][V - v.get(i)] + w.get(i));
            }

            List<Integer> ans = new ArrayList<>();
            for (int k = 1; k <= q; ++k) {
                int x = qx.get(k), V = qV.get(k);
                int mx = 0; // 记录当前访问的最优答案
                // 抽取x，然后用前缀背包与后缀背包计算最优背包
                for (int i = 0; i <= V; ++i) {
                    // 将一个体积为V的背包拆成一个体积为i的和一个体积为V-i的背包
                    mx = max(mx, d[x - 1][i] + f[x + 1][V - i]);
                }
                ans.add(mx);
            }
            return ans;
        }

        // ================= 代码实现结束 =================

        void solve(InputReader in, PrintWriter out) {
            int n, q;
            List<Integer> v = new ArrayList<>();
            List<Integer> w = new ArrayList<>();
            List<Integer> qv = new ArrayList<>();
            List<Integer> qx = new ArrayList<>();
            v.add(-1);
            w.add(-1);
            qv.add(-1);
            qx.add(-1);
            n = in.nextInt();
            for (int i = 0; i < n; ++i) {
                int a = in.nextInt(), b = in.nextInt();
                v.add(a);
                w.add(b);
            }
            q = in.nextInt();
            for (int i = 0; i < q; ++i) {
                int a = in.nextInt(), b = in.nextInt();
                qv.add(a);
                qx.add(b);
            }
            List<Integer> ans = getAnswer(n, w, v, q, qv, qx);
            for (int i = 0; i < q; ++i)
                out.println(ans.get(i));
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