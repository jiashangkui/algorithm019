# 学习笔记

## 位运算

首先需要明白，为什么我们需要位运算？因为计算机底层运算是01电平传递信息，刚好表示为二进制，所以在底层运算时，位运算更符合计算机的计算逻辑，所以计算速度较快。

- 左移：<<，即二进制数整体向高位移动指定位数，溢出边界的数则直接舍弃。
- 右移：>>，即二进制数整体向低位移动指定位数，溢出边界的数则直接舍弃。
- 或：|，若运算两边有一位是1则输出1，否则输出0。
- 与：&，若运算两边均为1则输出1，否则输出0。
- 取反：~，将二进制数的0变成1，1变成0。
- 异或：^，运算两边数值相同则输出0，否则输出1。

##### 

##### 异或操作的一些实例

x ^ 0 = x

x ^ 1s = ~x  //1s = ~0

x ^ (~x) = 1s

x ^ x = 0

c = a ^ b => a ^ c = b, b ^ c = a    // 两数交换

a ^ b ^ c = a ^ (b ^ c) = (a ^ b) ^ c

##### 

##### 指定位置的位运算

1. 将x最右边的n位清零：x & (~0 << n)
2. 获取x的第n位值（0或者1）：(x >> n) & 1
3. 获取x的第n位的幂值：x & (1 << n)
4. 仅将第n位置为1：x | (1 << n)
5. 仅将第n位置为0：x & (~ (1 << n))
6. 将x最高位至第n位（含）清零：x & ((1 << n) - 1)

##### 

##### 其他一些位运算的技巧

1. 判断奇偶
   - x % 2 == 1  --> (x & 1) == 1
   - x % 2 == 0  --> (x & 1) == 0
2. x >> 1  --> x / 2
   - mid = (x + y) / 2  --> mid = (x + y) >> 1
3. 清零最低位的1：x = x & (x - 1)
4. 得到最低位的1：x & -x
5. x & ~x == 0

## 

## 布隆过滤器和LRU

- 布隆过滤器是什么？

  是一个占用空间很小、效率很高的随机数据结构，它由一个bit数组和一组Hash算法构成。可用于判断一个元素是否在一个集合中，查询效率很高（1-N，最优能逼近于1）。

- 布隆过滤器原理？

  布隆过滤器（Bloom  Filter）的核心实现是一个超大的位数组和几个哈希函数，假设位数组的长度为m，哈希函数的个数为k，假设集合里面有3个元素{x, y,  z}，哈希函数的个数为3。首先将位数组进行初始化，将里面每个位都设置位0。对于集合里面的每一个元素，将元素依次通过3个哈希函数进行映射，每次映射都会产生一个哈希值，这个值对应位数组上面的一个点，然后将位数组对应的位置标记为1。查询W元素是否存在集合中的时候，同样的方法将W通过哈希映射到位数组上的3个点。如果3个点的其中有一个点不为1，则可以判断该元素一定不存在集合中。反之，如果3个点都为1，则该元素可能存在集合中。注意：此处不能判断该元素是否一定存在集合中，可能存在一定的误判率。可以从图中可以看到：假设某个元素通过映射对应下标为4，5，6这3个点。虽然这3个点都为1，但是很明显这3个点是不同元素经过哈希得到的位置，因此这种情况说明元素虽然不在集合中，也可能对应的都是1，这是误判率存在的原因。

  - 布隆过滤器添加元素
    - 将要添加的元素给k个哈希函数
    - 得到对应于位数组上的k个位置
    - 将这k个位置设为1
  - 布隆过滤器查询元素
    - 将要查询的元素给k个哈希函数
    - 得到对应于位数组上的k个位置
    - 如果k个位置有一个为0，则肯定不在集合中
    - 如果k个位置全部为1，则可能在集合中

- 布隆过滤器实现

  [示例1](https://github.com/lovasoa/bloomfilter/blob/master/src/main/java/BloomFilter.java)，[示例2](https://github.com/Baqend/Orestes-Bloomfilter)

- 注意：布隆过滤器是允许存在误判的，减小误判的方法是扩大内存与调整hash算法，需要在空间与时间上进行取舍。

##### 

##### LRU

LRU（Least recently used，最近最少使用）算法根据数据的历史访问记录来进行淘汰数据，其核心思想是“如果数据最近被访问过，那么将来被访问的几率也更高”。

1. 新数据插入到链表头部；
2. 每当缓存命中（即缓存数据被访问），则将数据移到链表头部；
3. 当链表满的时候，将链表尾部的数据丢弃。
4. 最开始时，内存空间是空的，因此依次进入A、B、C是没有问题的
5. 当加入D时，就出现了问题，内存空间不够了，因此根据LRU算法，内存空间中A待的时间最为久远，选择A,将其淘汰
6. 当再次引用B时，内存空间中的B又处于活跃状态，而C则变成了内存空间中，近段时间最久未使用的
7. 当再次向内存空间加入E时，这时内存空间又不足了，选择在内存空间中待的最久的C将其淘汰出内存，这时的内存空间存放的对象就是E->B->D

当然，LRU工程上也还是有优化空间的，例如mysql中，查询页的缓存时，取出的页第一次插入是从中部开始插入，当再次访问时才会放入队列头部，尽快淘汰仅使用一次的数据。

## 

## 排序算法

常见有十大经典排序算法：

1. 冒泡排序
2. 快速排序
3. 插入排序
4. 希尔排序
5. 选择排序
6. 堆排序
7. 归并排序
8. 计数排序
9. 桶排序
10. 基数排序

其中，大致可以分为两类

- **比较排序**：通过比较来决定元素间的相对次序，由于其时间复杂度不能突破O(nlogn)，因此也称为非线性时间比较类排序。
- **非比较排序**：不通过比较来决定元素间的相对次序，它可以突破基于比较排序的时间下界，以线性时间运行，因此也称为线性时间非比较类排序。

[![img](https://camo.githubusercontent.com/00ff95660bb6595c0508c13e34ba439cdd8e611b39635164bc82d8dfc0c37459/68747470733a2f2f696d67323031382e636e626c6f67732e636f6d2f626c6f672f3834393538392f3230313930332f3834393538392d32303139303330363136353235383937302d313738393836303534302e706e67)](https://camo.githubusercontent.com/00ff95660bb6595c0508c13e34ba439cdd8e611b39635164bc82d8dfc0c37459/68747470733a2f2f696d67323031382e636e626c6f67732e636f6d2f626c6f672f3834393538392f3230313930332f3834393538392d32303139303330363136353235383937302d313738393836303534302e706e67)

各算法复杂度

[![img](https://camo.githubusercontent.com/5b932d1a011f2e5d1382e69c4520bcb2d0158a07ed5fa09d5011588a0173515a/68747470733a2f2f696d61676573323031382e636e626c6f67732e636f6d2f626c6f672f3834393538392f3230313830342f3834393538392d32303138303430323133333433383231392d313934363133323139322e706e67)](https://camo.githubusercontent.com/5b932d1a011f2e5d1382e69c4520bcb2d0158a07ed5fa09d5011588a0173515a/68747470733a2f2f696d61676573323031382e636e626c6f67732e636f6d2f626c6f672f3834393538392f3230313830342f3834393538392d32303138303430323133333433383231392d313934363133323139322e706e67)

相关概念

- **稳定**：如果a原本在b前面，而a=b，排序之后a仍然在b的前面。
- **不稳定**：如果a原本在b的前面，而a=b，排序之后 a 可能会出现在 b 的后面。
- **时间复杂度**：对排序数据的总的操作次数。反映当n变化时，操作次数呈现什么规律。
- **空间复杂度：**是指算法在计算机

代码模板

快速排序

```
public static void quickSort(int[] array, int begin, int end) {
    if (end <= begin) return;
    int pivot = partition(array, begin, end);
    quickSort(array, begin, pivot - 1);
    quickSort(array, pivot + 1, end);
}
static int partition(int[] a, int begin, int end) {
    // pivot: 标杆位置，counter: 小于pivot的元素的个数
    int pivot = end, counter = begin;
    for (int i = begin; i < end; i++) {
        if (a[i] < a[pivot]) {
            int temp = a[counter]; a[counter] = a[i]; a[i] = temp;
            counter++;
        }
    }
    int temp = a[pivot]; a[pivot] = a[counter]; a[counter] = temp;
    return counter;
}
```

归并排序

```
public static void mergeSort(int[] array, int left, int right) {
    if (right <= left) return;
    int mid = (left + right) >> 1; // (left + right) / 2

    mergeSort(array, left, mid);
    mergeSort(array, mid + 1, right);
    merge(array, left, mid, right);
}

public static void merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1]; // 中间数组
        int i = left, j = mid + 1, k = 0;

        while (i <= mid && j <= right) {
            temp[k++] = arr[i] <= arr[j] ? arr[i++] : arr[j++];
        }

        while (i <= mid)   temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];

        for (int p = 0; p < temp.length; p++) {
            arr[left + p] = temp[p];
        }
        // 也可以用 System.arraycopy(a, start1, b, start2, length)
    }
```

堆排序

```
static void heapify(int[] array, int length, int i) {
    int left = 2 * i + 1, right = 2 * i + 2；
    int largest = i;
    if (left < length && array[left] > array[largest]) {
        largest = left;
    }
    if (right < length && array[right] > array[largest]) {
        largest = right;
    }
    if (largest != i) {
        int temp = array[i]; array[i] = array[largest]; array[largest] = temp;
        heapify(array, length, largest);
    }
}
public static void heapSort(int[] array) {
    if (array.length == 0) return;
    int length = array.length;
    for (int i = length / 2-1; i >= 0; i-) 
        heapify(array, length, i);
    for (int i = length - 1; i >= 0; i--) {
        int temp = array[0]; array[0] = array[i]; array[i] = temp;
        heapify(array, i, 0);
    }
}
```