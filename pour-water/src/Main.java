import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import static java.lang.Math.abs;
import static java.lang.Math.max;
import static java.lang.Math.min;

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

        // 类似于c++里的pair
        class pii {
            public int fi;
            public int se;

            public pii() {
                fi = se = 0;
            }

            public pii(int first, int second) {
                this.fi = first;
                this.se = second;
            }
        }

        /* 请在这里定义你需要的全局变量 */
        final int N = 2003;

        // mind: mind[i][j]表示从初始状态到达状态(i, j)需要的最小步数
        // q: 用数组模拟的队列
        // qh: 队头下标
        // qt：队列下标
        int[][] mind = new int[N][N];
        pii[] q = new pii[N * N];
        int qh, qt;

        // 倒水函数，表示状态p经过第k种策略倒水后的状态
        // p：初始状态
        // k：策略（如下阐释）
        // n，m：两辈子容量
        // 返回值：最终状态
        pii to(pii p, int k, int n, int m) {
            if (k == 0) // 倒空杯子一
                return new pii(0, p.se);
            else if (k == 1) // 倒空杯子二
                return new pii(p.fi, 0);
            else if (k == 2) // 倒满杯子一
                return new pii(n, p.se);
            else if (k == 3) // 倒满杯子二
                return new pii(p.fi, m);
            else if (k == 4) // 将杯子二的水向杯子一倒
                return new pii(min(p.fi + p.se, n), max(p.fi + p.se - n, 0));
            else if (k == 5) // 将杯子一的水向杯子二倒
                return new pii(max(p.fi + p.se - m, 0), min(p.fi + p.se, m));
            else // 否则什么也不做
                return p;
        }

        // 计算答案的函数
        // n, m, t, d：意义均与题目描述一致
        // 返回值：即为答案
        int getAnswer(int n, int m, int t, int d) {
            /* 请在这里设计你的算法 */
            // 初始化，清空队列，将mind所有位置置为-1表示未访问
            for (int i = 0; i < N; ++i)
                for (int j = 0; j < N; ++j)
                    mind[i][j] = -1;
            qh = qt = 0;
            q[++qt] = new pii(0, 0);
            mind[0][0] = 0;

            // 进行BFS
            while (qh < qt) {
                pii u = q[++qh]; // 取出队头元素
                if (mind[u.fi][u.se] == t)
                    break; // 如果已经进行了t步，那么没必要继续搜索了，退出循环即可
                for (int k = 0; k < 6; ++k) { // 枚举6种策略
                    pii v = to(u, k, n, m);
                    if (mind[v.fi][v.se] != -1) // 判断目标状态是否未曾到达过
                        continue;;
                    q[++qt] = v; // 加入队列
                    mind[v.fi][v.se] = mind[u.fi][u.se] + 1; // 记录mind
                }
            }

            // 统计答案，找到最优的状态
            int ans = d;
            for (int i = 0; i <= n; ++i)
                for (int j = 0; j <= m; ++j)
                    if (mind[i][j] != -1)
                        ans = min(ans, abs(i + j - d));
            return ans;
        }

        // ================= 代码实现结束 =================

        void solve(InputReader in, PrintWriter out) {
            int n = in.nextInt(), m = in.nextInt(), t = in.nextInt(), d = in.nextInt();
            out.println(getAnswer(n, m, t, d));
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