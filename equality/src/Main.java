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
        final int N = 3000005;

        // Father：每个节点的父亲节点
        int[] Father = new int[N];
        // Rank: 节点的秩
        int[] Rank = new int[N];

        // 查找节点x所在集合的根
        // x：节点x
        // 返回值：根
        int find(int x) {
            // 路径压缩
            if (Father[x] != x) {
                Father[x] = find(Father[x]);
            }
            return Father[x];
        }

        // 给定n个变量以及m个约束，判定是否能找出一种赋值方案满足这m个约束条件
        // n：如题意
        // m：如题意
        // A：大小为m的数组，表示m条约束中的a
        // B：大小为m的数组，表示m条约束中的b
        // E：大小为m的数组，表示m条约束中的e
        // 返回值：若能找出一种方案，返回"Yes"；否则返回"No"（不包括引号）。
        String getAnswer(int n, int m, List<Integer> A, List<Integer> B, List<Integer> E) {
            /* 请在这里设计你的算法 */
            // 初始化
            for (int i = 1; i <= n; ++i) {
                Father[i] = i;
                Rank[i] = 0;
            }

            // swap?
            int last = 0;
            for (int i = 0; i < m; ++i) {
//                if (E.get(i) == 0) {
//                    Collections.swap(E, i, m - 1);
//                    Collections.swap(A, i, m - 1);
//                    Collections.swap(B, i, m - 1);
//                }
                // 提前操作1表达式到0之前
                if (E.get(i) == 1) {
                    Collections.swap(E, i, last);
                    Collections.swap(A, i, last);
                    Collections.swap(B, i, last);
                    last++;
                }
            }

            for (int i = 0; i < m; ++i) {
                int setA = find(A.get(i));  // 找到A[i]所在集合的根
                int setB = find(B.get(i));  // 找到B[i]所在集合的根
                if (E.get(i) == 0) {
                    if (setA == setB)
                        return "No";
                } else {
                    // Merge
                    // 不在同一集合时合并
                    // 启发式合并
                    if (setA != setB) {
                        if (Rank[setA] < Rank[setB]) {
                            Father[setA] = setB;
                        } else if (Rank[setA] > Rank[setB]) {
                            Father[setB] = setA;
                        } else {
                            Father[setB] = setA;
                            Rank[setA] = Rank[setA] + 1;
                        }
                    }
                }
            }
            return "Yes";
        }

        // ================= 代码实现结束 =================

        void solve(InputReader in, PrintWriter out) {
            int T = in.nextInt();
            for (int t = 0; t < T; ++t) {
                int n = in.nextInt(), m = in.nextInt();
                List<Integer> A = new ArrayList<>();
                List<Integer> B = new ArrayList<>();
                List<Integer> E = new ArrayList<>();
                for (int i = 0; i < m; ++i) {
                    A.add(in.nextInt());
                    B.add(in.nextInt());
                    E.add(in.nextInt());
                }
                out.println(getAnswer(n, m, A, B, E));
            }
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