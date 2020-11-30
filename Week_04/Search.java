class Solution {
    public int search(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) {
            return -1;
        }
        if (n == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int l = 0, r = n - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[0] <= nums[mid]) {
                if (nums[0] <= target && target < nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[n - 1]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return -1;
    }
}


class Solution {
    public int search(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length - 1;

        while(lo < hi){
            int mid = (lo + hi) / 2;
            //当[0,mid]有序时，向后规约条件
            if(nums[0] <= nums[mid] && (target > nums[mid] || target < nums[0])){
                lo = mid + 1;
                //当[0,mid]发生旋转时，向后规约条件
            } else if (target > nums[mid] && target < nums[0]){
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo == hi && nums[lo] == target ? lo : -1;
    }
}