# 学习笔记

## 高级动态规划

动态规划作为一个常见的算法题解题思路，往往占比是最重要的。

动态规划的关键是状态转移方程，同时要区分简单的递归和分治的区别。

在动态规划中，递归和分治可以看做是两种不同的逻辑处理方式，其实还有回溯。而动态规划的状态转移方程，其实更多时候是将子问题利用递归的方式进行求解。而分治，回溯，动态规划，其实基础都是递归，而递归的基础模板其实可以总结为：判出条件，处理逻辑，下钻逻辑，和重置逻辑。

而分治其实就是将一个大问题分拆为若干个小问题，当小问题处理完成后，向上合并直到最终合并完成。

其实动态规划类题目求解的一个重要思想是，将问题转化成最近子问题的递推，如果能够找到重复子问题，那么当前问题就能表示成最近子问题的递推。

而分治、回溯则在处理重复子问题后，对于子问题的结果有更进一步的处理，例如剪枝、合并处理等。

对于动态规划方程的递推公式，主要是要找到特征值并建立维度模型，很多问题其实就是多维度特征值的改变导致的结果走向不同的问题，建立合理的特征值维度模型，就能够建立逻辑简单的地推公式。

## 

## 字符串搜索

首先要明确一点，Java中的字符串其实是不可变的，所谓的字符串操作类，例如StringBuilder等，是利用char数组拼装后生成一个字符串，对于中间使用到的字符串，本质上并没有进行改变。

字符串一些基础的算法本身更偏向于对字符串转换的处理，例如数字转换等。

而字符串的高级算法，一般是对字符串进行时间更快且空间更小的搜索，例如正则匹配等，一般更多基于动态规划进行处理。

而高级的字符串搜索算法，需要了解Rabin-Karp及KMP。

Rabin-Karp代码示例

```
public final static int D = 256;
public final static int Q = 9997;
static int RabinKarpSerach(String txt, String pat) {
  int M = pat.length();
  int N = txt.length();
  int i, j;
  int patHash = 0, txtHash = 0;
  for (i = 0; i < M; i++) {
    patHash = (D * patHash + pat.charAt(i)) % Q;
    txtHash = (D * txtHash + txt.charAt(i)) % Q;
  }
  int highestPow = 1;  // pow(256, M-1)
  for (i = 0; i < M - 1; i++)
    highestPow = (highestPow * D) % Q;
  for (i = 0; i <= N - M; i++) { // 枚举起点
    if (patHash == txtHash) {
      for (j = 0; j < M; j++) {
        if (txt.charAt(i + j) != pat.charAt(j))
          break;
      }
      if (j == M)
        return i;
    }
    if (i < N - M) {
      txtHash = (D * (txtHash - txt.charAt(i) * highestPow) + txt.charAt(i + M)) % Q;
      if (txtHash < 0)
        txtHash += Q;
    }
  }
  return -1;
}
```

[KMP算法介绍](https://www.ruanyifeng.com/blog/2013/05/Knuth–Morris–Pratt_algorithm.html)