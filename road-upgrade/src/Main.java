import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.*;

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

        static final int N = 500005;

        // 并查集
        static class DisjointSet {
            // parent: 每个节点的父亲节点
            int[] f = new int[N];

            // 初始化
            // n: 结合数量
            void init(int n) {
                for (int i = 1; i <= n; ++i) {
                    f[i] = i;
                }
            }

            // 查找节点x所在集合的根
            // x: 节点x
            // 返回值： 根
            int find(int i) {
                // Path compression way
                if (f[i] != i) {
                    f[i] = find(f[i]);
                }
                return f[i];
            }

            // 将x节点和y节点所在集合合并
            // x：一个节点
            // y：一个节点
            // 返回值：返回true表示成功合并，返回false表示已经在一个集合里了
            boolean merge(int x, int y) {
                int xRoot = find(x);
                int yRoot = find(y);

                // 已经在一个集合里
                if (xRoot == yRoot)
                    return false;

                // merge
                f[xRoot] = yRoot;
                return true;
            }
        }

        /* 请在这里定义你需要的全局变量 */
        DisjointSet ds = new DisjointSet();

        // 给定一个n个点m条边的无向图，第i条边边权为i，求所有需要升级的边
        // n：如题意
        // m：如题意
        // U：大小为m的数组，表示m条边的其中一个端点
        // V：大小为m的数组，表示m条边的另一个端点
        // 返回值：所有需要升级的边，从小到大排列；第一小问的答案自然即为返回值的size，所以你不必考虑如何返回size
        List<Integer> getAnswer(int n, int m, List<Integer> U, List<Integer> V) {
            /* 请在这里设计你的算法 */
            List<Integer> ans = new ArrayList<>();
            ds.init(n);
            // 根据道路载重定义，从后往前遍历
            // 如果从低位往高位遍历，则不能直接添加到结果集中
            for (int i = m - 1; i >= 0; --i) {
                int u = U.get(i);
                int v = V.get(i);
                // 隐含的权重（载重）
                int w = i + 1;
                if (ds.merge(u, v)) {
                    // 添加最大边
                    ans.add(w);
                }
            }
            // 按题目要求asc排序
            Collections.sort(ans);
            return ans;
        }

        // ================= 代码实现结束 =================

        void solve(InputReader in, PrintWriter out) {
            int n = in.nextInt(), m = in.nextInt();
            List<Integer> U = new ArrayList<>();
            List<Integer> V = new ArrayList<>();
            for (int i = 0; i < m; ++i) {
                U.add(in.nextInt());
                V.add(in.nextInt());
            }
            List<Integer> ans = getAnswer(n, m, U, V);
            out.println(ans.size());
            for (Integer item : ans)
                out.println(item);
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