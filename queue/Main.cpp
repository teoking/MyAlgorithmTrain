#include <bits/stdc++.h>
using namespace std;

// ================= 代码实现开始 =================

/* 请在这里定义你需要的全局变量 */
const int N = 100001;
string MyQueue[N] = {};
int head, tail;

// 队尾入队
// name：入队人的姓名
void enqueue(string name) {
    /* 请在这里设计你的算法 */
    MyQueue[tail++] = name;
}

// 队首出队
// 返回值：队首的姓名
string dequeue() {
    /* 请在这里设计你的算法 */
    return MyQueue[head++];
}

// 询问队列中某个位置上的人的姓名（队首位置为1，往后位置依次递增）
// pos：询问的位置
// 返回值：pos位置上人的姓名
string query(int pos) {
    /* 请在这里设计你的算法 */
    return MyQueue[head + pos - 1];
}

// ================= 代码实现结束 =================

int main() {
    int n;
    scanf("%d", &n);
    char name[15];
    for (; n--; ) {
        int op;
        scanf("%d", &op);
        if (op == 1) {
            scanf("%s", name);
            enqueue(name);
        } else if (op == 2) {
            printf("%s\n", dequeue().c_str());
        } else {
            int pos;
            scanf("%d", &pos);
            printf("%s\n", query(pos).c_str());
        }
    }
    return 0;
}
