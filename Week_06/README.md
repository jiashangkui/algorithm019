## 学习笔记

### 动态规划

本周的重点内容就是动态规划了，这个知识点算是算法解题里特别重要的一个思想或技巧。在实际的业务代码设计中，其实也经常能使用到动态规划的思想。英文全称Dynamic Programming ，简称DP。其实叫动态递推更能显示这种算法思想的思路。

分治，回溯，递归并没有本质上的区别，其本质都是通过将原问题分解成结构相似的子问题，寻找问题的重复性，只不过在代码的细节处理上有所不同，因此三者的代码模板有共同点，都使用递归来解题。广义上来说，分治和回溯是递归的一种特殊类型问题。

动态规划的核心是要找到重复子问题，这些子问题要具有重复性，这样才能使用用机器思维解决，所谓机器思维就是机器只会判断和循环。 另外，动态规划能够解决的问题必须满足最优子结构，即这些子问题之间是有关系的，及子问题的最优解，能够通过递推关系，就能得到 由这个子问题组成的更大的问题的最优解，而动态规划之所以高效，就是因为利用了子问题之间最优解的递推关系，通过中间变量（单个变量， 一维数组，二维数组等）记录子问题的最优解，再根据递推公式，就可以得到最终问题的最优解。

动态规划的核心是分治算法，一般都是自底向上去解决，一般解题步骤为：

1. 确定最优子结构。就是要找到这个问题的子问题是什么，即分治的思想，如何把一个大问题划分为子问题，子问题之间
2. 确定有没有重复性，子问题之间可以相互转化。
3. 存储中间状态，或者通过自底向上的方式递推。
4. 写成递推公式或状态转移方程，从而得到最终问题的解。

##### 

#### 简要原理

动态规划其实就是将一个大问题拆分成若干具有重复性的子问题，通过求解子问题的解从而获取问题的最终解的过程。动态规划的核心不在于大问题拆分成若干子问题，而是若干子问题具有可重复性，且无后效性，即当前问题拆分成若干子问题后，仅需要知道这些子问题的解，而不需要知道子问题的子问题解是什么。通常拆分之前的问题的最优解往往是由子问题的最优解组成，我们称之为最优子结构。

在DP过程中，其实还有一个重要的核心思想，即尽量缩小可能的解空间。所以在DP过程中，对于我们最重要的就是找到状态转移方程。通常“我从哪里来”与“我到哪里去”这两种出发点，都有利于我们构建状态转移方程。

##### 

#### 分治法与动态规划的区别

共同点 ：二者都要求原问题具有最优子结构性质,都是将原问题分而治之,分解成若干个规模较小(小到很容易解决的程序)的子问题.然后将子问题的解合并,形成原问题的解.

不同点：分治法将分解后的子问题看成相互独立的，通过用递归来做。

动态规划将分解后的子问题理解为相互间有联系,有重叠部分，需要记忆，通常用迭代来做。

##### 递归代码模板

```
public void recur(int level, int param) {
  // terminator
  if (level > MAX_LEVEL) {
    // process result
    return;
  }
  // process current logic
  process(level, param);
  // drill down
  recur( level: level + 1, newParam);
  // restore current status

}
```

##### 分治代码模板

```
private static int divide_conquer(Problem problem, ) {
  
  if (problem == NULL) {
    int res = process_last_result();
    return res;     
  }
  subProblems = split_problem(problem)
  
  res0 = divide_conquer(subProblems[0])
  res1 = divide_conquer(subProblems[1])
  
  result = process_result(res0, res1);
  
  return result;
}
```

##### 

#### 总结

对于动态规划来说，最重要的就是找到状态转移方程，而在解题过程中，尽量找到重复子问题，而不是人肉递归找到通用特性。

1打破自己的思维惯性，形成机器思维
2理解复杂逻辑的关键，找重复性
3职业进阶的要点要领，理清