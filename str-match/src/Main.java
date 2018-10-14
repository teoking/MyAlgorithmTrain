import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
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

        // 这是匹配函数，将所有匹配位置求出并返回
        // n：串 A 的长度
        // A：题目描述中的串 A
        // m：串 B 的长度
        // B：题目描述中的串 B
        // 返回值：一个 List<Integer>，从小到大依次存放各匹配位置
        List<Integer> match(int n, String A, int m, String B) {
            /* 请在这里设计你的算法 */
            // Next
            int[] Next = new int[m];

            int j = Next[0] = -1; //
            for (int i = 1; i < m; ++i) {
                while (j >= 0 && B.charAt(i) != B.charAt(j + 1))
                    j = Next[j];
                if (B.charAt(i) == B.charAt(j + 1))
                    ++j;
                Next[i] = j;
                if (j == m - 1)
                    j = Next[j];
            }
            j = -1;

            List<Integer> ans = new ArrayList<>();
            for (int i = 0; i < n; ++i) {
                while (j >= 0 && A.charAt(i) != B.charAt(j + 1))
                    j = Next[j];
                if (A.charAt(i) == B.charAt(j + 1))
                    ++j;
                if (j == m - 1)
                    ans.add(i - m + 1);
                if (j == m - 1)
                    j = Next[j];
            }
            return ans;
        }

        // ================= 代码实现结束 =================

        void solve(InputReader in, PrintWriter out) {
            int n = in.nextInt();
            String A = in.next();
            int m = in.nextInt();
            String B = in.next();
            List<Integer> ans = match(n, A, m, B);
            for (Integer i : ans)
                out.println(i);
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