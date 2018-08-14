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

        // seq: 原序列，为了方便处理，将其设为全局变量
        // seqTemp: 用以辅助计算的临时数组
        // cnt: 统计逆序对个数
        List<Integer> seq = null;
        List<Integer> seqTemp = null;
        long cnt;

        // 归并排序函数
        // l, r: 分别为归并排序排序区间的左、右端点
        void mergeSort(int l, int r) {
            // l == r 直接返回
            if  (l == r)
                return;
            // 取mid
            int mid = (l + r) >> 1;
            // 归并[l, mid)
            mergeSort(l, mid);
            // merge [mid + 1, r)
            mergeSort(mid + 1, r);
            // 用两个指针来指向归并时需要比较的两个元素
            int p = l, q = mid + 1;
            for (int i = l; i <= r; ++i) {
                // 如果左边的元素更小，则将左边的元素插入末尾
                if (q > r || p <= mid && seq.get(p) <= seq.get(q))
                    seqTemp.set(i, seq.get(p++));
                // 如果右边的元素更小，则将左边的元素插入末尾
                else {
                    seqTemp.set(i, seq.get(q++));
                    // 并统计产生的逆序对数目
                    cnt += mid - p + 1;
                }
            }
            // 将排序后的序列复制回原序列的对应位置
            for (int i = l; i <= r; ++i)
                seq.set(i, seqTemp.get(i));
        }

        // 这个函数的功能是计算答案（即最少花费的金钱）
        // n：表示序列长度
        // a：存储整个序列 a
        // 返回值：最少花费的金钱（需要注意，返回值的类型为 64 位有符号整数）
        long getAnswer(int n, List<Integer> a) {
            /* 请在这里设计你的算法 */
            // 复制序列到全局变量
            seq = a;
            // 初始化临时数组的长度
            seqTemp = new ArrayList<>(Collections.nCopies(n, 0));
            // 置零计数器
            cnt = 0;
            // 进行归并排序
            mergeSort(0, n - 1);
            // 返回答案
            return cnt;
        }

        // ================= 代码实现结束 =================

        void solve(InputReader in, PrintWriter out) {
            List<Integer> a = new ArrayList<>();
            int n = in.nextInt();
            for (int i = 1; i <= n; ++i)
                a.add(in.nextInt());
            out.println(getAnswer(n, a));
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