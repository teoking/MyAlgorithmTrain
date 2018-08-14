import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
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
        final int N = 505 * 2;
        final int M = N * N;

        // 邻接表的结构
        class E {
            // next: 下一个邻接边
            // to: 本条边所指向的终点
            int next, to;
        }

        E[] e = new E[M];

        // ihead: 邻接表的头
        // cnt: 邻接表大小
        // mc: 表示每个点所匹配到的另一个点 (match)
        // vis: Y集元素是否被访问过
        int cnt;
        int[] ihead = new int[N];
        int[] mc = new int[N];
        boolean[] vis = new boolean[N];

        // 邻接表连边，表示连一条x到y的有向边
        // x: 起点
        // y: 终点
        void add(int x, int y) {
            ++cnt;
            if (e[cnt] == null)
                e[cnt] = new E();
            e[cnt].next = ihead[x];
            e[cnt].to = y;
            ihead[x] = cnt;
            // 更新match
            mc[x] = 1;
        }

        // 匈牙利算法
        // x: x集上的点，从当前点出发找增广路
        // 返回值：若找到增广路则返回true；否则返回false
        boolean dfs(int x) {
            for (int i = ihead[x]; i != 0; i = e[i].next) {
                int y = e[i].to;
                // 如果找到一个Y集上的点没有标记
                if (!vis[y]) {
                    // 标记该点
                    vis[y] = true;
                    if (mc[y] == 0 || dfs(mc[y])) {
                        // 如果y是没有匹配点的，说明找到了一条增广路；
                        // 或者说递归查找y的匹配点，得到了一条增广路
                        // 找到了增广路，我们要怎样更新mc数组？
                        mc[x] = y;
                        mc[y] = x;
                        return true;
                    }
                }
            }
            return false;
        }

        // 求解棋盘上最多能放多少个“车”
        // n：棋盘的大小为n×n的
        // board：所给棋盘，对于某个位置上的数：若值为1表示可以放“车”；若值为0表示不能放“车”
        // 返回值：能放“车”的最大个数
        int getAnswer(int n, List<List<Integer>> board) {
            /* 请在这里设计你的算法 */
            // 我们将行看做n个点，将列看做另外n个点，标号分别为1到n和n+1到2n
            // 初始化
            cnt = 0;
            for (int i = 1; i <= n * 2; ++i) {
                ihead[i] = 0;
                mc[i] = 0;
            }

            // 连边
            for (int i = 1; i <= n; ++i)
                for (int j = 1; j <= n; ++j)
                    if (board.get(i - 1).get(j - 1).equals(1))
                        add(i, j + n);

            int ans = 0;
            for (int i = 1; i <= n; ++i) {
                // 如果x集中的第i个点没有匹配到Y集上的点，则从这个点出发寻找增广路
                if (mc[i] == 1) {
                    Arrays.fill(vis, false);
                    // 如果找到，答案直接+1
                    if (dfs(i))
                        ++ans;
                }
            }

            return ans;
        }

        // ================= 代码实现结束 =================

        void solve(InputReader in, PrintWriter out) {
            int n = in.nextInt();
            List<List<Integer>> e = new ArrayList<>();
            for (int i = 0; i < n; ++i) {
                List<Integer> t = new ArrayList<>();
                for (int j = 0; j < n; ++j)
                    t.add(in.nextInt());
                e.add(t);
            }
            out.println(getAnswer(n, e));
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