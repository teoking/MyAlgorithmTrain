#!/usr/bin/env python3

# ================= 代码实现开始 =================

N = 500005
mo = 23333

''' 请在这里定义你需要的全局变量 '''

# 为了减少复制开销，我们直接读入信息到全局变量中
# s：题目所给字符串，下标从1开始
# n：字符串长度
n = 0
s = str()

# 求出字符串s有多少不同的子序列
# 返回值：s不同子序列的数量，返回值对mo取模
def getAnswer():
    ''' 请在这里设计你的算法 '''

# ================= 代码实现结束 =================

s = '\0' + input()
n = len(s) - 1
print(getAnswer())