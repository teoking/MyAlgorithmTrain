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
        static final int N = 100001;
        Node[] tree = new Node[N];

        // 根节点及节点总数
        int root, count;

        // 将vPos位置的Node v插入到树中
        // v: 待插入节点
        // vPos: 待插入节点在数组中的位置
        // return x
        int insert(Node v, int vPos, Node rootNode) {
            if (v.val < rootNode.val) {
                if (rootNode.left != 0) {
                    insert(v, vPos, tree[rootNode.left]);
                } else {
                    rootNode.left = vPos;
                }
            } else {
                if (rootNode.right != 0) {
                    insert(v, vPos, tree[rootNode.right]);
                } else {
                    rootNode.right = vPos;
                }
            }

            return 0;
        }

        void insertWithSequence(List<Integer> sequence) {
            root = count = 0;

            // insert root node
            Node rootNode = new Node();
            rootNode.val = sequence.get(0);
            tree[count++] = rootNode;

            // insert others, start from root node
            for (int i = 1; i < sequence.size(); ++i) {
                Node n = new Node();
                n.val = sequence.get(i);
                // add node to tree array
                tree[count++] = n;
                insert(n, i, rootNode);
            }
        }

        // 求以x为根的二叉树的前序遍历 (Preorder Tranversal: DLR)
        // 访问顺序：visit root, DLR left, DLR right
        // x: 当前节点
        // ans: 结果数组
        void dlr(int x, List<Integer> ans) {
            Node node = tree[x];
            // visit node
            ans.add(node.val);
            if (node.left != 0) {
                dlr(node.left, ans);
            }
            if (node.right != 0) {
                dlr(node.right, ans);
            }
        }

        // 求以x为根的二叉树的后序遍历 (Postorder Traversal: LRD)
        // 访问顺序：LRD left, LRD right, visit root
        // x: 当前节点
        // ans: 结果数组
        void lrd(int x, List<Integer> ans) {
            Node node = tree[x];
            if (node.left != 0) {
                lrd(node.left, ans);
            }
            if (node.right != 0) {
                lrd(node.right, ans);
            }
            // visit node
            ans.add(node.val);
        }

        // 给定一个1到n的排列，依次插入到二叉树中，返回前序遍历和后序遍历
        // n：如题意
        // sequence：给定的排列，大小为n
        // 返回值：将要输出的元素依次加入到返回值中
        List<Integer> getAnswer(int n, List<Integer> sequence) {
            /* 请在这里设计你的算法 */
            root = count = 0;

            insertWithSequence(sequence);

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