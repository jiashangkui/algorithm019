class Solution {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int[] tmp = new int[1001];
        int n = arr1.length;
        //第一个for循环累加arr1数组中每个数字的个数
        for (int num : arr1) {
            tmp[num]++;
        }
        int pos = 0; //arr1中索引的位置，也是arr2中含有的数字在arr1中的最大长度
        //第二个for循环直接在arr1上从0开始顺序添加arr中的值，并且将该值累计数-1
        for (int num : arr2) {
            while (tmp[num] > 0) {
                arr1[pos++] = num;
                tmp[num]--;
            }
        }
        //第三个for循环添加arr2中没有的值
        for (int i = 0; i < 1001; ++i) {
            while (tmp[i] > 0) {
                arr1[pos++] = i;
                tmp[i]--;
            }
        }
        return arr1;
    }
}