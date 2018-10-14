#include <bits/stdc++.h>
using namespace std;

// ================= 代码实现开始 =================

/* 请在这里定义你需要的全局变量 */

// n个物品，每个物品有体积价值，求若扔掉一个物品后装进给定容量的背包的最大价值
// n：如题
// w：长度为n+1的数组，w[i]表示第i个物品的价值（下标从1开始，下标0是一个数字-1，下面同理）
// v：长度为n+1的数组，v[i]表示第i个物品的体积
// q：如题
// qV：长度为q+1的数组，qV[i]表示第i次询问所给出的背包体积
// qx：长度为q+1的数组，qx[i]表示第i次询问所给出的物品编号
// 返回值：返回一个长度为q的数组，依次代表相应询问的答案
vector<int> getAnswer(int n, vector<int> w, vector<int> v, int q, vector<int> qV, vector<int> qx) {
    /* 请在这里设计你的算法 */
}

// ================= 代码实现结束 =================

int main() {
    int n, q;
    vector<int> v, w, qv, qx;
    v.push_back(-1);
    w.push_back(-1);
    qv.push_back(-1);
    qx.push_back(-1);
    scanf("%d", &n);
    for (int i = 0; i < n; ++i) {
        int a, b;
        scanf("%d%d", &a, &b);
        v.push_back(a);
        w.push_back(b);
    }
    scanf("%d", &q);
    for (int i = 0; i < q; ++i) {
        int a, b;
        scanf("%d%d", &a, &b);
        qv.push_back(a);
        qx.push_back(b);
    }
    vector<int> ans = getAnswer(n, w, v, q, qv, qx);
    for (int i = 0; i < q; ++i)
        printf("%d\n", ans[i]);
    return 0;
}

