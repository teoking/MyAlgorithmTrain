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

        // 这是进行排序的函数
        // n：题目描述中的 n
        // A：各同学的算法训练营成绩
        // DS：各同学的数据结构训练营成绩
        // 返回值：将要输出的数字依次加入到返回值的数组中
        List<Integer> getAnswer(int n, List<Integer> A, List<Integer> DS) {
            /* 请在这里设计你的算法 */
            List<Integer> sum = new ArrayList<>();
            List<Integer> id = new ArrayList<>();
            List<Integer> ans = new ArrayList<>();
            // 得到各同学的学好、总分
            for (int i = 0; i < n; ++i) {
                id.add(i + 1);
                sum.add(A.get(i) + DS.get(i));
            }
            // 记录你逆序对个数
            int cnt = 0;
            // 冒泡排序
            for (int i = 0; i < n; ++i) {
                for (int j = n - 1; j > 0; --j) {
                    if (sum.get(j) > sum.get(j - 1) || (sum.get(j).equals(sum.get(j - 1)) && A.get(j) > A.get(j - 1))) {
                        Collections.swap(id, j, j - 1);
                        Collections.swap(sum, j, j - 1);
                        Collections.swap(A, j, j - 1);
                        Collections.swap(DS, j, j - 1);
                        // 发生了一次交换就说明有一个逆序对
                        cnt++;
                    }
                }
            }
            // 整理返回值并返回
            for (int i = 0; i < n; ++i) {
                ans.add(id.get(i));
                ans.add(sum.get(i));
                ans.add(A.get(i));
                ans.add(DS.get(i));
            }
            ans.add(cnt);
            return ans;
        }

        // ================= 代码实现结束 =================

        void solve(InputReader in, PrintWriter out) {
            int n = in.nextInt();
            List<Integer> A = new ArrayList<>();
            List<Integer> DS = new ArrayList<>();
            for (int i = 0; i < n; ++i) {
                A.add(in.nextInt());
                DS.add(in.nextInt());
            }
            List<Integer> ans = getAnswer(n, A, DS);
            int cnt = 0;
            for (int i = 0; i < n; ++i) {
                out.println(ans.get(cnt) + " " + ans.get(cnt + 1) + " " + ans.get(cnt + 2) + " " + ans.get(cnt + 3));
                cnt += 4;
            }
            out.println(ans.get(cnt));
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