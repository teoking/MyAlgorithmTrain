#include <bits/stdc++.h>

using namespace std;

// ================= 代码实现开始 =================

/* 请在这里定义你需要的全局变量 */
const int N = 15;

struct node {
    int val, l, r;
} t[N];

int root, cnt;

void insert(node &v, int vPos, node &rootNode) {
    if (v.val < rootNode.val) {
        if (rootNode.l != 0) {
            insert(v, vPos, t[rootNode.l]);
        } else {
            rootNode.l = vPos;
        }
    } else {
        if (rootNode.r != 0) {
            insert(v, vPos, t[rootNode.r]);
        } else {
            rootNode.r = vPos;
        }
    }
}

void printTree() {
    for (int n = 0; n < N; n++) {
        node v = t[n];
        printf("(%d, %d, %d) -- ", v.l, v.val, v.r);
    }
    printf("\n");
}

void insertWithSequence(vector<int> &sequence) {
    root = cnt = 0;

    // insert root node
    node rootNode;
    rootNode.val = sequence[0];
    rootNode.l = rootNode.r = 0;
    t[cnt++] = rootNode;

    // insert others, start from root node
    for (int i = 1; i < sequence.size(); ++i) {
        node n;
        n.val = sequence[i];
        n.l = n.r =  0;
        t[cnt++] = n;
        insert(n, i, rootNode);
    }

    printTree();
}

void dlr(int x, vector<int> &ans) {
    node n = t[x];

    ans.push_back(n.val);
    if (n.l != 0) {
        dlr(n.l, ans);
    }
    if (n.r != 0) {
        dlr(n.r, ans);
    }
}

void lrd(int x, vector<int> &ans) {
    node n = t[x];

    if (n.l != 0) {
        lrd(n.l, ans);
    }
    if (n.r != 0) {
        lrd(n.r, ans);
    }
    ans.push_back(n.val);
}

// 给定一个1到n的排列，依次插入到二叉树中，返回前序遍历和后序遍历
// n：如题意
// sequence：给定的排列，大小为n
// 返回值：将要输出的元素依次加入到返回值中
vector<int> getAnswer(int n, vector<int> sequence) {
    /* 请在这里设计你的算法 */
    root = cnt = 0;

    insertWithSequence(sequence);
    vector<int> ans;
    dlr(root, ans);
    lrd(root, ans);

    return ans;
}

// ================= 代码实现结束 =================

int main() {
    int n;
    scanf("%d", &n);
    vector<int> sequence;
    for (int i = 0; i < n; ++i) {
        int x;
        scanf("%d", &x);
        sequence.push_back(x);
    }
    vector<int> ans = getAnswer(n, sequence);
    for (int i = 0; i < n; ++i)
        printf("%d%c", ans[i], " \n"[i == n - 1]);
    for (int i = 0; i < n; ++i)
        printf("%d%c", ans[n + i], " \n"[i == n - 1]);
    return 0;
}
