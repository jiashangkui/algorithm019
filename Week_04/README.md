## 学习笔记

### 深度优先搜索和广度优先搜索

一般情况下，深度优先搜索（DFS）和广度优先搜索（BFS）指的是对树的遍历，可以是具象的树，也可以是将问题转化为树（例如处理下一步的选择这种类型的问题）。

DFS在处理时，会快速到达树的叶子节点，如果记录下访问路径，在当前路径的叶子节点不符合预期的情况下寻找其他的叶子节点，就是回溯。DFS适合解决连通性的问题，不过因为DFS大多是用递归求解，需要关注爆栈的问题，即当树的高度非常大时，用DFS有爆栈的危险。

BFS处理时，会将当前层的节点全部遍历，非常适合处理最短路径问题，但是因为BFS在处理时一般会将节点存储在队列中，所以内存占用相对于DFS来说较高，需要关注内存问题。

**DFS的代码模板**

```java
public List<List<Integer>> levelOrder(TreeNode root) {
  List<List<Integer>> allResults = new ArrayList<>();
  if(root==null){
    return allResults;
  }
  travel(root,0,allResults);
  return allResults;
}


private void travel(TreeNode root,int level,List<List<Integer>> results){
  // 递归的中断条件
  if(results.size()==level){
    results.add(new ArrayList<>());
  }
  // 处理当前层
  results.get(level).add(root.val);
  // 开始下钻
  // 处理左孩子
  if(root.left!=null){
    travel(root.left,level+1,results);
  }
  // 处理右孩子
  if(root.right!=null){
    travel(root.right,level+1,results);
  }
}
```

**BFS的代码模板**

```java
public class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;

  TreeNode(int x) {
    val = x;
  }
}

public List<List<Integer>> levelOrder(TreeNode root) {
  List<List<Integer>> allResults = new ArrayList<>();
  if (root == null) {
    return allResults;
  }
  Queue<TreeNode> nodes = new LinkedList<>();
  // 将根节点放入待处理
  nodes.add(root);
  while (!nodes.isEmpty()) {
    // 在当前层开始之前记录下长度，可以做到仅处理当前层节点
    int size = nodes.size();
    List<Integer> results = new ArrayList<>();
    for (int i = 0; i < size; i++) {
      // 每次从队尾获取元素
      TreeNode node = nodes.poll();
      // 处理当前节点
      results.add(node.val);
      // 如果左孩子不为空，则添加到队首
      if (node.left != null) {
        nodes.add(node.left);
      }
      // 如果右孩子不为空，则添加到队首
      if (node.right != null) {
        nodes.add(node.right);
      }
    }
    allResults.add(results);
  }
  return allResults;
}
```

在实际应用过程中，往往并没有直接的二叉树进行处理，大多数问题都是选择性问题，例如单词接龙、扫雷、括号生成，每一步的选择，都有若干下一步可以选择，有点类似人类面对选择的情况，计算机处理选择的方式就是将所有的选择触达一次，从中选取符合条件的结果。

### 贪心算法

贪心算法是一种在每一步选择中都采取在当前状态下最好和最优的选择，从而希望导致结果是全局最好和最优的算法。

> 贪心算法是一种在每一步选择中都采取在当前状态下最好或最优（即最有利）的选择，从而希望导致结果是全局最好或最优的算法。
>
> 贪心算法与动态规划的不同在于它对每个子问题的解决方案都做出选择，不能回退。动态规划则会保存以前的运算结果，并根据以前的结果对当前进行选择，有回退功能。

### 二分查找算法

二分查找其实是一种高效的查找算法，应用对象必须是已经排序的集合。每次查询时，先获取中间队列的值，然后目标值与中间值进行匹配，如果匹配中则直接返回，如果目标值大于中间值，则在中间值和较大的队尾中间重新进行一次二分查抄，否则就在较小的队首与中间值之间进行一次二分查找，直到查询到目标值。

**代码模板**

```java
public int binarySearch(int[] array, int target) {
    int left = 0, right = array.length - 1, mid;
    while (left <= right) {
        mid = (right - left) / 2 + left;

        if (array[mid] == target) {
            return mid;
        } else if (array[mid] > target) {
            right = mid - 1;
        } else {
            left = mid + 1;
        }
    }

    return -1;
}
```

一般用二分查找快速的寻找集合数组中的某个值，时间复杂度为O(logn)，相对于一般遍历查找的O(n)来说要快很多。

特殊情况下，二分查找也是一种思想，例如取中间数，如果判定需要的结果小于中间数，则在开头到中间数的队列中进行下一次的判断处理，否则到中间数与结尾的队列进行下一次的判断处理。