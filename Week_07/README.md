# 学习笔记

## 

## Trie树

Trie树是一种处理字符串问题的树。主要用于字符串的统计和排序，典型应用是字符串前缀搜索。 Trie树查询效率高的原因通过空间换时间，利用公共字符串前缀来减少了字符串的比较次数，从而提高查询效率。

其结构定义如下：

1. 树的节点不存完整的单词，单词是通过从树的根节点到某一个节点的路径上的字符连接来表示。
2. 每个节点的所有子节点路径代表的字符串都不相同。

Trie数的Java实现如下：

```
class Trie {
    private TreeNode root;

    /** Initialize your data structure here. */
    public Trie() {
        root = new TreeNode();
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        TreeNode p = root;
        for (int i = 0, len = word.length(), ch; i < len; i++) {
            ch = word.charAt(i) - 'a';
            if (p.next[ch] == null) {
                p.next[ch] = new TreeNode();
            }
            p = p.next[ch];
        }
        p.isEnd = true;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TreeNode p = root;
        for (int i = 0, len = word.length(), ch; i < len; i++) {
            ch = word.charAt(i) - 'a';
            if (p.next[ch] == null) {
                return false;
            }
            p = p.next[ch];
        }
        return p.isEnd;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TreeNode p = root;
        for (int i = 0, len = prefix.length(), ch; i < len; i++) {
            ch = prefix.charAt(i) - 'a';
            if (p.next[ch] == null) {
                return false;
            }
            p = p.next[ch];
        }
        return true; 
    }

    private class TreeNode {
        private boolean isEnd;
        private TreeNode[] next;

        public TreeNode() {
            isEnd = false;
            next = new TreeNode[26];
        }
    }
```

## 

## 并查集

并查集是一种特殊的数据结构，专门用于解决集合的查找和合并问题。

并查集Java实现如下，主要包括2个方法：

1. 元素的查找。
2. 元素的合并。两个集合不能有相交。

```
class UnionFind {
    private int count = 0;
    private int[] parent;

    public UnionFind(int n) {
        count = n;
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    public int find(int p) {
        while (p != parent[p]) {
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;
        parent[rootP] = rootQ;
        count--;
    }
}
```

## 

## 高级搜索

### 

### 剪枝

所谓剪枝就是在DFS或者BFS搜索的过程中，通过一定的条件，删掉明显不是最优的搜索路径。在BFS或者DFS向下一层 扩展的时候，通过一定的条件判断来移除某些搜索节点，从而减少搜索的范围，从而提升搜索效率。

### 

### 双向BFS

一般的BFS是自定而下的一层层搜搜，双向BFS是指自顶向下和自底向上同时进行BFS，如果2个方向的BFS有重复 节点，则证明搜搜完毕。

### 

### 启发式搜索

启发式搜索的核心是估价函数。用来评价哪些节点是最有希望得到最优结果。它可以提供搜索的方向。实现中通常 使用优先级队列来实现。

### 

### AVL平衡树

普通的二叉搜索树，在一些特定的情况下，会导致左右子树的高度差很大，降低了搜索的性能。所以引入了平衡的 概念，定义平衡因子，即左子树的高度减去柚子树的高度，且取值在-1，0和1之间。

为了满足平衡因子的取值范围，所以在数的插入以及删除过程中，就需要通过旋转操作来保证平衡因子取值在规定 范围内。同时在树的节点中要记录树的高度，便于计算平衡因子。

旋转操作有：

1. 左旋。右右子树的情况。
2. 右旋。左左子树的情况。
3. 左右旋。左右子树的情况。
4. 右左旋。右左子树的情况。

还有待子树的情况的旋转，共有[![四种情况](https://camo.githubusercontent.com/c134fea00d78c2f80dcee1d2445cb551dea1abc299dd356977515618abfa79dc/68747470733a2f2f656e2e77696b6970656469612e6f72672f77696b692f547265655f726f746174696f6e232f6d656469612f46696c653a547265655f526562616c616e63696e672e676966)](https://camo.githubusercontent.com/c134fea00d78c2f80dcee1d2445cb551dea1abc299dd356977515618abfa79dc/68747470733a2f2f656e2e77696b6970656469612e6f72672f77696b692f547265655f726f746174696f6e232f6d656469612f46696c653a547265655f526562616c616e63696e672e676966)。

### 

### 红黑树

AVL树虽然通过引入平衡因子提高的查询效率，但是由于其需要记录树的高度信息，而且随着树的修改，需要频繁的对树进行调整。 所有有了一种近似的平衡二叉树，即红黑树。红黑树的平衡因子定义是左右子树的高度差小于2倍。

红黑树的定义如下：

1. 树的节点只有2种颜色，黑或者红。
2. 根节点和叶子节点（空节点）是黑色。
3. 红色的节点不能相邻，而且从任意一个节点到子节点的所有路径必须包含相同数目的黑色节点。

在工程实践中，红黑树应用更加广泛，如各种高级语言库。红黑树提供很好的插入和删除性能，比AVL树使用更小的存储（不需要记录树 的高度）。但是没有AVL树查询效率高，是一种权衡折中。