#!/usr/bin/env python3

# ================= 代码实现开始 =================

''' 请在这里定义你需要的全局变量 '''

# 这个函数的功能是计算答案（即最少花费的金钱）
# n：表示序列长度
# a：存储整个序列 a
# 返回值：最少花费的金钱（需要注意，返回值的类型为 64 位有符号整数）
def getAnswer(n, a):
    ''' 请在这里设计你的算法 '''

# ================= 代码实现结束 =================

n = int(input())
a = list(map(int, input().split()))
print(getAnswer(n, a))
