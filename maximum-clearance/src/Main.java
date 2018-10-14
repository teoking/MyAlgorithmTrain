import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
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

        // 以下代码不需要解释，你只需要知道这是用于生成数据的就行了

        int nextInt(int x) {
            x ^= x << 13;
            x ^= x >>> 17;
            x ^= x << 5;
            return x;
        }

        void initData(int[] a, int n, int k, int seed) {
            for (int i = 0; i < n; ++i) {
                seed = nextInt(seed);
                a[i] = seed >>> (32 - k);
            }
        }

        // 以上代码不需要解释，你只需要知道这是用于生成数据的就行了

        // int 转无符号的 int，用 long 来存储
        long intToUnsignedInt(int x) {
            if (x >= 0)
                return x;
            return (long)x + (1L << 32);
        }

        /* 请在这里定义你需要的全局变量 */
        final int N = 67108864;

        // a
        // l
        // r
        int[] a = new int[N + 1]; //
        int[] l = new int[N + 1];
        int[] r = new int[N + 1];

        // 这是求解答案的函数，你需要对全局变量中的 a 数组求解 maxGap 问题
        // n, k：意义与题目描述相符
        // 返回值：即为答案（maxGap）
        long maxGap(int n, int k) {
            /* 请在这里设计你的算法 */
            //
            final int m = 1 << 26; //
            Arrays.fill(l, -1);
            Arrays.fill(r, -1);

            final int _k = max(k - 26, 0);
            for (int i = 0; i < n; ++i) {
                int bl = a[i] >>> _k;
                //
                if (l[bl] == -1)
                    l[bl] = r[bl] = a[i];
                else if (intToUnsignedInt(a[i]) < intToUnsignedInt(l[bl]))
                    l[bl] = a[i];
                else if (intToUnsignedInt(a[i]) > intToUnsignedInt(r[bl]))
                    r[bl] = a[i];
            }

            //
            long last = intToUnsignedInt(a[0]);
            long ans = 0;
            for (int i = 0; i < m; ++i) {
                if (l[i] != -1) {
                    if (last > intToUnsignedInt(l[i]))
                        last = intToUnsignedInt(l[i]);
                    if (l[i] - last > ans)
                        ans = l[i] - last;
                    last = intToUnsignedInt(r[i]);
                }
            }
            return ans;
        }

        // ================= 代码实现结束 =================

        void solve(InputReader in, PrintWriter out) {
            int n = in.nextInt(), k = in.nextInt();
            long seed = in.nextLong();

            initData(a, n, k, (int)seed);

            out.print(maxGap(n, k));
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