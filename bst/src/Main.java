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

    static class Node {
        int left, val, right;
    }

    static class Task {

        // ================= 代码实现开始 =================

        /* 请在这里定义你需要的全局变量 */
        static final int N = 11;
        Node[] tree = new Node[N];

        // 根节点及节点总数
        int root, count;

        // 在以x为根的树中插入一个数字v
        // v: 待插入节点的值
        // x: 待插入节点
        // return x
        int insert(int v, int root) {
            Node rootNode = tree[root];
            if (rootNode == null) {
                Node n = new Node();
                n.val = v;
                tree[root] = n;
                count++;
                return root;
            }

            return insert(v, rootNode, root);
        }

        // 插入节点到tree
        int insert(int v, Node rootNode, int root) {
            if (root == 0) {
                Node node = new Node();
                node.val = v;

            }

            if (v < rootNode.val) {
                insert(v, tree[rootNode.left], rootNode.left);
            } else {
                insert(v, tree[rootNode.right], rootNode.right);
            }

            return root;
        }

        // 求以x为根的二叉树的前序遍历
        // x: 当前节点
        // ans: 结果数组
        void dlr(int x, List<Integer> ans) {

        }

        // 求以x为根的二叉树的后序遍历
        // x: 当前节点
        // ans: 结果数组
        void lrd(int x, List<Integer> ans) {

        }

        // 给定一个1到n的排列，依次插入到二叉树中，返回前序遍历和后序遍历
        // n：如题意
        // sequence：给定的排列，大小为n
        // 返回值：将要输出的元素依次加入到返回值中
        List<Integer> getAnswer(int n, List<Integer> sequence) {
            /* 请在这里设计你的算法 */
            root = count = 0;

            for (int i = 0; i < sequence.size(); ++i) {
                insert(sequence.get(i), root);
            }

            List<Integer> ans = new ArrayList<>();
            dlr(root, ans);
            lrd(root, ans);

            return ans;
        }

        // ================= 代码实现结束 =================

        void solve(InputReader in, PrintWriter out) {
            int n = in.nextInt();
            List<Integer> sequence = new ArrayList<>();
            for (int i = 0; i < n; ++i)
                sequence.add(in.nextInt());
            List<Integer> ans = getAnswer(n, sequence);
            for (int i = 0; i < n; ++i)
                out.print(ans.get(i) + (i == n - 1 ? "\n" : " "));
            for (int i = 0; i < n; ++i)
                out.print(ans.get(n + i) + (i == n - 1 ? "\n" : " "));
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