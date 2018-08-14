import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
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
        final int N = 100005;

        // 类似于c++里的pair
        class pii {
            public int first;
            public int second;

            public pii() {
                first = second = 0;
            }

            public pii(int first, int second) {
                this.first = first;
                this.second = second;
            }
        }

        class cmp implements Comparator<pii> {

            @Override
            public int compare(pii t1, pii t2) {
                if (t1.first == t2.first) {
                    if (t1.second == t2.second)
                        return 0;
                    return t1.second < t2.second ? -1 : 1;
                }
                return t1.first < t2.first ? -1 : 1;
            }
        }

        // graph: 存放图，graph.get(i)表示的是节点i的出边，其中first存储到达的节点，second存储边权
        // pq: 辅助Dijkstra的优先队列
        // flag: 记录每个节点是否进行过松弛，1表示进行过，0表示未进行过
        // mind: 存储起点s到每个节点的最短路径长度
        List<pii>[] graph = new ArrayList[N];
        PriorityQueue<pii> pq;
        boolean[] flag = new boolean[N];
        int[] mind = new int[N];

        // 这个函数用于计算答案（最短路）
        // n：节点数目
        // m：双向边数目
        // U,V,W：分别存放各边的两端点、边权
        // s,t：分别表示起点、重点
        // 返回值：答案（即从 s 到 t 的最短路径长度）
        int shortestPath(int n, int m, List<Integer> U, List<Integer> V, List<Integer> W, int s, int t) {
            /* 请在这里设计你的算法 */
            // 初始化，清空pq,graph, mind, flag
            pq = new PriorityQueue<>(3, new cmp());
            for (int i = 1; i <= n; ++i)
                graph[i] = new ArrayList<>();
            Arrays.fill(mind, 0x7f7f7f7f);
            Arrays.fill(flag, false);

            // 建图，连接图中各边
            for (int i = 0; i < m; ++i) {
                graph[U.get(i)].add(new pii(V.get(i), W.get(i)));
                graph[V.get(i)].add(new pii(U.get(i), W.get(i)));
            }

            // 设置起点的最短路为0，并将起点加入优先队列中
            mind[s] = 0;
            pq.add(new pii(s, s));

            // 执行Dijkstra算法
            while (pq.peek() != null) {
                // 取出堆顶元素
                int u = pq.poll().second;
                // 每个节点最多做一次松弛
                if (!flag[u]) {
                    // 标记u
                    flag[u] = true;
                    // 枚举所有u出发的边
                    for (pii it : graph[u]) {
                        int v = it.first, w = it.second;
                        if (mind[v] <= mind[u] + w)
                            continue;
                        //进行答案的更新
                        mind[v] = mind[u] + w;
                        // 将v伴随其最新的最短路长度加入优先队列
                        pq.add(new pii(mind[v], v));
                    }
                }
            }
            return mind[t];
        }

        // ================= 代码实现结束 =================

        void solve(InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int m = in.nextInt();
            int s = in.nextInt();
            int t = in.nextInt();
            List<Integer> U = new ArrayList<>();
            List<Integer> V = new ArrayList<>();
            List<Integer> W = new ArrayList<>();
            for (int i = 0; i < m; ++i) {
                U.add(in.nextInt());
                V.add(in.nextInt());
                W.add(in.nextInt());
            }

            out.println(shortestPath(n, m, U, V, W, s, t));
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