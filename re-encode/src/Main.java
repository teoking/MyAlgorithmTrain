import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
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
        PriorityQueue<Long> pq = new PriorityQueue<>();

        // 这是求解整个问题的函数
        // w：题目描述中的 w（所有）
        // n：题目描述中的 n
        // 返回值：答案
        long getAnswer(int n, List<Long> w) {
            /* 请在这里设计你的算法 */
            for (int i = 0; i < n; ++i) {
                pq.add(w.get(i));
            }
            // 置零返回值
            long sum = 0;
            // 当pq中仍有超过多少个元素时进行循环
            while (pq.size() != 1) {
                long newEle = 0;
                //从pq中取出最小的2个元素并合并
                for (int k = 0; k < 2; ++k) {
                    newEle += pq.poll();
                }
                // 将本次合并累积到结果
                sum += newEle;
                pq.add(newEle);
            }
            return sum;
        }

        // ================= 代码实现结束 =================

        void solve(InputReader in, PrintWriter out) {
            int n = in.nextInt();
            List<Long> w = new ArrayList<>();
            for (int i = 0; i < n; ++i) {
                long x = in.nextLong();
                w.add(x);
            }
            out.println(getAnswer(n, w));
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