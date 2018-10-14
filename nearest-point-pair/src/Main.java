import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

import static java.lang.Math.min;
import static java.lang.Math.sqrt;

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

        final int N = 300005;

        // 用于存储一个二维平面上的点
        class ip {
            int x, y;

            // 构造函数
            ip(int x, int y) {
                this.x = x;
                this.y = y;
            }

            @Override
            public boolean equals(Object obj) {
                return obj instanceof ip
                        && ((ip) obj).x == x
                        && ((ip) obj).y == y;
            }
        }
        ip[] a = new ip[N];
        ip[] b = new ip[N];

        // 计算x的平方
        long sqr(long x) {
            return x * x;
        }

        // 计算点a和点b的距离
        double dis(ip a, ip b) {
            return sqrt(sqr(a.x - b.x) + sqr(a.y - b.y));
        }

        /* 请在这里定义你需要的全局变量 */
        // 最终答案
        double ans;

        void swap(ip[] src, int p1, int p2) {
            ip tmp = src[p1];
            src[p1] = src[p2];
            src[p2] = tmp;
        }

        // 分治求最近点对
        // l, r: 表示闭区间[l, r]
        void solve(int l, int r) {
            // 边界情况
            if (r - l <= 1) {
                // 只有1个点或者只有2个点时更新答案
                if (a[l].y > a[r].y)
                    swap(a, l, r);
                if (l != r)
                    ans = min(ans, dis(a[l], a[r]));
                return;
            }

            // 分治计算两遍
            int mid = (l + r) >> 1;
            // 中间值
            int md = a[mid].x;

            solve(l, mid);
            solve(mid + 1, r);

            // 对y轴进行归并排序，并且去掉，与中间点mid的距离（x轴），比答案要大的点
            int cnt = 0;
            for (int i = l, j = mid + 1; i <= mid || j <= r; ) {
                for (; j <= mid && md - a[i].x >= ans; ++i);
                for (; j <= r && a[j].x - md >= ans; ++j);
                if (i <= mid && (j > r || a[i].y < a[j].y))
                    b[cnt++] = a[i++];
                else
                    b[cnt++] = a[j++];
            }

            // 现在b数组里的点是按y轴升序的，根据结论，一个点周围不会超过8个点，因此j不会循环很多次，复杂度得到保证
            for (int i = 0; i < cnt; ++i) {
                for (int j = i + 1; j < cnt && b[j].y - b[i].y < ans; ++j) {
                    ans = min(ans, dis(b[i], b[j]));
                }
            }

            // 对y轴进行归并排序，不去掉任何点
            cnt = 0;
            for (int i = l, j = mid + 1; i <= mid || j <= r; ) {
                if (i <= mid && (j > r || a[i].y < a[j].y)) {
                    b[cnt++] = a[i++];
                } else {
                    b[cnt++] = a[j++];
                }
            }

            // 将排好序的点放回a中
            for (int i = 0; i < cnt; ++i)
                a[l + i] = b[i];
        }

        // 计算最近点对的距离
        // n：n个点
        // X, Y：分别表示x轴坐标和y轴坐标，下标从0开始
        // 返回值：最近的距离
        double getAnswer(int n, int[] X, int[] Y) {
            /* 请在这里设计你的算法 */
            for (int i = 0; i < n; ++i)
                a[i + 1] = new ip(X[i], Y[i]);
            ans = 1e100;
            Arrays.sort(a, 1, n + 1, new Comparator<ip>() {
                // 先比较x轴，再比较y轴
                @Override
                public int compare(ip a, ip b) {
                    if (a.x < b.x)
                        return -1;
                    else if (a.x > b.x)
                        return 1;
                    else {
                        if (a.y < b.y)
                            return -1;
                        else if (a.y > b.y)
                            return 1;
                        return 0;
                    }
                }
            }); // 必须先排序
            solve(1, n);
            return ans;
        }

        // ================= 代码实现结束 =================

        void solve(InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int[] X = new int[n];
            int[] Y = new int[n];
            for (int i = 0; i < n; ++i) {
                X[i] = in.nextInt();
                Y[i] = in.nextInt();
            }
            out.printf("%.2f\n", getAnswer(n, X, Y));
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