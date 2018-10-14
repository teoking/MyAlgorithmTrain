#include <bits/stdc++.h>
using namespace std;

// ================= 代码实现开始 =================

/* 请在这里设计你的算法 */

// 本函数求解答案（损失的最小口感和）
// n：青草棵数
// k：奶牛的初始坐标
// x：描述序列 x（这里需要注意的是，由于 x 的下标从 1 开始，因此 x[0] 的值为 -1，你可以忽略它的值，只需知道我们从下标 1 开始存放有效信息即可），意义同题目描述
// 返回值：损失的最小口感和
int getAnswer(int n, int k, vector<int> x) {
    /* 请在这里设计你的算法 */
}

// ================= 代码实现结束 =================

int main() {
    int n, k, tmp;
    vector<int> x;
    scanf("%d%d", &n, &k);
    x.clear();
    x.push_back(-1);
    for (int i = 1; i <= n; ++i) {
        scanf("%d", &tmp);
        x.push_back(tmp);
    }
    int ans = getAnswer(n, k, x);
    printf("%d\n", ans);
    return 0;
}
