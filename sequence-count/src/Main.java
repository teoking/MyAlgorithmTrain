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
        final int N = 300005;

        // n: 题中的n
        // d: 题中的d
        // max_value: 用于存储solve函数中的前缀最大值
        // min_value: 用于存储solve函数中的前缀最小值
        // a: 题中的a
        int n, d;
        int[] max_value = new int[N];
        int[] min_value = new int[N];
        List<Integer> a;
        // 分治计算区间[l, r]中有多少个连续子序列满足最大值最小值差不大于d
        // l: 区分左边界
        // r: 区分右边界
        // 返回值：满足条件的连续子序列的个数
        long solve(int l, int r) {
            // 边界情况，我们不计算长度等于1的连续子序列，故返回0
            if (l == r)
                return 0;
            // 中点
            int mid = (l + r) >> 1;
            // 分治求出左右两半的值
            long ans = solve(l, mid) + solve(mid + 1, r);
            // 我们计算区间[mid + 1, r]的前缀最小值和前缀最大值，
            // 也就是说min_value[i] = min(a[mid + 1....i]), max_value同理
            for (int i = mid + 1; i <= r; ++i) {
                min_value[i] = (i == mid + 1) ? a.get(i) : Math.min(min_value[i - 1], a.get(i));
                max_value[i] = (i == mid + 1) ? a.get(i) : Math.max(max_value[i - 1], a.get(i));
            }
            // 我们倒序枚举子序列的左端点i，i的取值范围在[1, mid]
            // pos表示若连续子序列的左端点是i，那么子序列的右端点最远能拓展到pos位置，
            // 当然pos取值范围在[mid + 1, r]，一开始初始化为r
            // mn是后缀最小值，mx是后缀最大值，也就是说mn = min(a[i...mid), mx同理
            // 那么以i为左端点的连续子序列(右端点在[mid + 1, r]内)个数应该有pos - mid个
            int mn = 0, mx = 0, pos = r;
            for (int i = mid; i >= l && pos > mid; --i) {
                // 更新mn和mx
                mn = (i == mid) ? a.get(i) : Math.min(mn, a.get(i));
                mx = (i == mid) ? a.get(i) : Math.max(mx, a.get(i));
                // pos随着i的递减也会递减
                for (; pos > mid && Math.max(mx, max_value[pos]) - Math.min(mn, min_value[pos]) > d; --pos);
                // 更新答案
                ans += pos - mid;
            }
            return ans;
        }

        // 求出有多少个a数组中的连续子序列（长度大于1），满足该子序列的最大值最小值之差不大于d
        // n：a数组的长度
        // d：所给d
        // a：数组a，长度为n
        // 返回值：满足条件的连续子序列的个数
        long getAnswer(int n, int d, List<Integer> a) {
            /* 请在这里设计你的算法 */
            this.n = n;
            this.d = d;
            this.a = a;
            return solve(0, n - 1);
        }

        // ================= 代码实现结束 =================

        void solve(InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int d = in.nextInt();
            List<Integer> a = new ArrayList<>();
            for (int i = 0; i < n; ++i)
                a.add(in.nextInt());
            out.println(getAnswer(n, d, a));
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