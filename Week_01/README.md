## 学习笔记

### 栈和队列

#### 用 add first 或 add last 这套新的 API 改写 Deque 的代码

```java
Deque<String> deque = new LinkedList<String>();

deque.addFirst("a");
deque.addFirst("b");
deque.addFirst("c");
System.out.println(deque);

String str = deque.peekFirst();
System.out.println(str);
System.out.println(deque);

while (deque.size() > 0) {
    System.out.println(deque.removeFirst());
}
System.out.println(deque);
```

#### 分析 Queue 和 PriorityQueue 的源码

##### Queue

Queue interface 继承了 Collection 接口，额外提供了插入，获取和检查方法。每个方法有两种形式：一种是操作失败会抛出异常，另一种是返回一个特殊值 null 或 false。

|      | 抛出异常  | 返回特殊值 |
| ---- | --------- | ---------- |
| 插入 | add(e)    | offer(e)   |
| 删除 | remove(e) | poll(e)    |
| 检查 | element() | peek()     |

##### PriorityQueue

PriorityQueue 继承了 AbstractQueue 并实现了 java.io.Serializable，AbstractQueue 实现了 Queue。PriorityQueue 中的队列是通过**平衡二叉树堆**实现。

##### 如何插入？

PriorityQueue 提供了 add() 和 offer() 方法，实际 add() 还是会调用 offer() 方法。

```java
    public boolean add(E e) {
        return offer(e);
    }

    public boolean offer(E e) {
        if (e == null)
            throw new NullPointerException();
        modCount++;
        int i = size;
      	// 如果 size 大于当前的长度，对队列进行扩容
      	// 如果队列长度小于 64 进行翻倍扩容，否则增加 50%
        if (i >= queue.length)
            grow(i + 1);
        size = i + 1;
        if (i == 0)
            queue[0] = e;
        else
          	// 通过调用创建 PriorityQueue 时提供的 Comparable 接口实现或
          	// 插入元素提供的 Comparable 接口实现来找到在哪个位置插入该元素
            siftUp(i, e);
        return true;
    }
```

##### 如何删除？

PriorityQueue 提供了 clear() 、 remove() 和 poll() 方法。

remove()  方法会遍历队列找到和传入元素相同的值并删除这个值返回 true 和 false。

clear() 清空队列，遍历整个队列，设置每一个位置都为 null，重置 size 为 0。

poll() 取出队列数组下标 0 的元素，如果当前队列的 size 还不是 0，调用 siftDown() 方法确保通过 Comparable 获取最小的值在队列顶部，确保下次取出的也是最小的元素。

##### 如何查看？

PriorityQueue 提供 peek() 方法查看队列顶部的数据。

### 跳表

```
跳表是一种特殊的链表形态，跳表必须是一个有序的链表。
在一维的链表上增加一级缓存，使用升维的方法来加速查询的效率，跳表中搜索的时间复杂度为O(logN)
由于需要维护索引的准确性，所以每次增加和删除时候都需要更新索引，从而时间在复杂度变为O(logN)
跳表的出现用来逐渐取代平衡树二分查找。
Redis和LeveDB等中都使用了跳表。
增加效率通常的手法为升维和空间换时间。
```

### 优先队列

```
时间复杂度 插入O(1) 取出O(logN)
底层具体时间的数据结构多样复杂，如Heap，bst,treap。
```