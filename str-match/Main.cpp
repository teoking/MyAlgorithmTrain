#include <bits/stdc++.h>
using namespace std;

// ================= 代码实现开始 =================

/* 请在这里定义你需要的全局变量 */

// 这是匹配函数，将所有匹配位置求出并返回
// n：串 A 的长度
// A：题目描述中的串 A
// m：串 B 的长度
// B：题目描述中的串 B
// 返回值：一个 vector<int>，从小到大依次存放各匹配位置
vector<int> match(int n, string A, int m, string B) {
    /* 请在这里设计你的算法 */
}

// ================= 代码实现结束 =================

int main() {
    ios::sync_with_stdio(false);
    int n, m;
    string A, B;
    cin >> n >> A;
    cin >> m >> B;
    vector<int> ans = match(n, A, m, B);
    for (vector<int>::iterator it = ans.begin(); it != ans.end(); ++it)
        cout << *it << '\n';
    return 0;
}
