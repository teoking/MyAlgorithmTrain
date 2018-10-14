#include <bits/stdc++.h>
using namespace std;

// ================= 代码实现开始 =================

const int N = 1005;

/* 请在这里定义你需要的全局变量 */

// 为了减少复制开销，我们直接读入信息到全局变量中
// a, b：题目所述数组，a的大小为(n+1)*(m+1)，b的大小为(p+1)*(q+1)，下标均从1开始有意义（下标0无意义，你可以直接无视）
// n, m, p, q：题中所述
int a[N][N], b[N][N], n, m, p, q;

// 求出a中有哪些位置出现了b
// 返回值：一个pair<int, int>的数组，包含所有出现的位置
vector<pair<int, int>> getAnswer() {
    /* 请在这里设计你的算法 */
} 

// ================= 代码实现结束 =================

int main() {
    scanf("%d%d%d%d", &n, &m, &p, &q);
    for (int i = 1; i <= n; ++i)
        for (int j = 1; j <= m; ++j)
            scanf("%d", &a[i][j]);
    for (int i = 1; i <= p; ++i)
        for (int j = 1; j <= q; ++j)
            scanf("%d", &b[i][j]);
    vector<pair<int, int>> ans = getAnswer();
    for (int i = 0; i < int(ans.size()); ++i)
        printf("%d %d\n", ans[i].first, ans[i].second);
    return 0;
}
