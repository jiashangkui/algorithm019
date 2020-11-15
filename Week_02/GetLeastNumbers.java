class Solution {
    public int[] getLeastNumbers(int[] arr, int k) {
        //默认小顶堆
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for(int i = 0; i < arr.length; i++){
            heap.add(arr[i]);
        }
        int[] ans = new int[k];
        for(int i = 0; i < k; i++){
            ans[i] = heap.poll();
        }
        return ans;
    }
}