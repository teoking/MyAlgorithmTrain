import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

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

        /* 请在这里定义你需要的全局变量 */
        final int N = 500005;
        //
        //
        char[] s = new char[N * 2];
        int[] len = new int[N * 2];

        // 计算str中有多少个回文子串
        // 返回值：子串的数目
        long getAnswer(String str) {
            /* 请在这里设计你的算法 */
            int n = str.length();

            //
            for (int i = n; i != 0; --i) {
                s[i << 1] = str.charAt(i - 1);
                s[i << 1 | 1] = 0;
            }

            //
            n = n << 1 | 1;
            s[1] = 0;
            s[0] = 1;
            s[n + 1] = 2;

            // Manacher算法
            int cur = 1;
            long ans = 0;
            for (int i = 2; i <= n; ++i) {
                //
                int pos = (cur << 1) - i;
                int now = max(min(len[pos], cur + len[cur] - i), 0);
                while (s[i - now - 1] == s[i + now + 1])
                    ++now;
                if (i + now > cur + len[cur])
                    cur = i;
                ans += (now + 1) >> 1;
                len[i] = now;
            }

            return ans;
        }

        // ================= 代码实现结束 =================

        void solve(InputReader in, PrintWriter out) {
            out.print(getAnswer(in.next()));
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