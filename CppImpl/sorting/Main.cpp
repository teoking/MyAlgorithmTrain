#include <bits/stdc++.h>
using namespace std;

// ================= 代码实现开始 =================

/* 请在这里定义你需要的全局变量 */
int iParent(int i) {
    return static_cast<int>(floor((i - 1) >> 1));
}

int iLeftChild(int i) {
    return 2 * i + 1;
}

int iRightChild(int i) {
    return 2 * i + 2;
}

void siftDown(vector<int> &a, int start, int end) {
    int root = start;

    while (iLeftChild(root) <= end) {
        int child = iLeftChild(root);
        int __swap = root;

        if (a[__swap] < a[child]) {
            __swap = child;
        }

        if (child + 1 <= end && a[__swap] < a[child + 1])
            __swap = child + 1;

        if (__swap == root)
            return;
        else {
            swap(a[root], a[__swap]);
            root = __swap;
        }
    }
}

void heapify(int count, vector<int> &a) {
    int start = iParent(count - 1);

    while (start >= 0) {
        siftDown(a, start, count - 1);
        start--;
    }
}

vector<int> heapSort(int n, vector<int> a) {
    heapify(n, a);

    int end = n - 1;
    while (end > 0)  {
        swap(a[end], a[0]);
        end--;
        siftDown(a, 0, end);
    }
    return a;
}

// 将给定数组a升序排序
// n：数组大小
// a：所给数组，大小为n
// 返回值：排序后的数组
vector<int> getAnswer(int n, vector<int> a) {
    /* 请在这里设计你的算法 */
    return heapSort(n, a);
}

// ================= 代码实现结束 =================

int main() {
    int n;
    scanf("%d", &n);
    vector<int> a;
    for (int i = 0; i < n; ++i) {
        int x;
        scanf("%d", &x);
        a.push_back(x);
    }
    a = getAnswer(n, a);
    for (int i = 0; i < n; ++i)
        printf("%d%c", a[i], " \n"[i == n - 1]);
    return 0;
}
