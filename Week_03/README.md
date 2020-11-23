## 学习笔记

### 递归，分治和回溯

#### 递归

我们一般把在函数中调用自身的方式叫做递归，通常适用于解决那些处理逻辑重复，且出入参相同的问题。在Java的递归中，一般每进入一层，都会开辟一个新栈来处理，如果使用了尾递归优化，可以重复使用栈，减少栈占用的内存过多。

在编写递归逻辑时，最重要的是***退出条件***。如果退出条件不得当，可能会导致死循环或没有触达目标层。

常见递归模板

```java
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

逻辑用文字描述，则为：

1. 退出条件判断
2. 执行当层逻辑
3. 进入下层并等待结果
4. 对当前层进行必要的状态销毁

递归在树的遍历中比较常见，其实递归不一定比迭代更慢或更消耗资源。

### 分治、回溯

分治和回溯一般场景下可以视作递归的高级使用场景。

分治一般是把大段的复杂大任务，分解成若干的简单小任务，如果分解的小任务仍然复杂，则继续分解成更细小的任务，直到可简单完成后，将结果汇聚合并，即为大任务的最终解。

MapReduce和ForkJoin就是分治思想的工程体现，将大任务拆解成若干个小任务，分别完成后向上汇总得到结果。

而回溯则是在递归中，当前路径如果不能满足条件，则立即返回切换其他路径继续前进的过程，常结合DFS进行所有选择遍历。

常见的分治模板

```java
public int divide_conquer(Problem problem) {
  
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

常见的回溯模板

```java
public void backtrack(int level) {
  if (level > height) {
    deal(currentNode)
    return;
  }
  for (int i = k; i < levelLength; i++) {
    dealLevelDown(..);
    backtrack(level + 1);
    dealLevelUp(...);
  }
}
```