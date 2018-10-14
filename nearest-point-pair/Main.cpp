#include <bits/stdc++.h>
using namespace std;

// ================= 代码实现开始 =================

typedef double lf;
typedef long long ll;

const int N = 300005;

// 用于存储一个二维平面上的点
struct ip {
    int x, y;
    
    // 构造函数
    ip(int x = 0, int y = 0) : x(x), y(y) { }
    
     // 先比较x轴，再比较y轴
    bool operator < (const ip &a) const {
        return x == a.x ? y < a.y : x < a.x;
    }
} a[N], b[N];

// 计算x的平方
ll sqr(const ll &x) {
    return x * x;
}

// 计算点a和点b的距离
lf dis(const ip &a, const ip &b) {
    return sqrt(sqr(a.x - b.x) + sqr(a.y - b.y));
}

/* 请在这里定义你需要的全局变量 */

// 计算最近点对的距离
// n：n个点
// X, Y：分别表示x轴坐标和y轴坐标，下标从0开始
// 返回值：最近的距离
double getAnswer(int n, vector<int> X, vector<int> Y) {
    /* 请在这里设计你的算法 */
}

// ================= 代码实现结束 =================

int main() {
    int n;
    scanf("%d", &n);
    vector<int> X, Y;
    for (int i = 1; i <= n; ++i) {
        int x, y;
        scanf("%d%d", &x, &y);
        X.push_back(x);
        Y.push_back(y);
    }
    printf("%.2f\n", getAnswer(n, X, Y));
    return 0;
}

