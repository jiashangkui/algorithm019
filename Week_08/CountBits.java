//方法一：Pop count 【通过】
class Solution {
    public int[] countBits(int num) {
        int[] ans = new int[num + 1];
        for (int i = 0; i <= num; ++i) {
            ans[i] = popcount(i);
        }
        return ans;
    }

    private int popcount(int x) {
        int count;
        for (count = 0; x != 0; ++count) {
            x &= x - 1;
        }
        return count;
    }
}

//方法二：动态规划 + 最高有效位 【通过】
class Solution {
    public int[] countBits(int num) {
        int[] ans = new int[num + 1];
        int i = 0, b = 1;
        while (b <= num) {
            while (i < b && i + b <= num) {
                ans[i + b] = ans[i] + 1;
                ++i;
            }
            i = 0;
            b <<= 1;
        }
        return ans;
    }    
}

//方法三：动态规划 + 最低有效位 【通过】
class Solution {
    public int[] countBits(int num) {
        int[] ans = new int[num + 1];
        for (int i = 1; i <= num; ++i) {
            ans[i] = ans[i >> 1] + (i & 1);
        }
        return ans;
    }    
}

//方法四：动态规划 + 最后设置位【通过】
class Solution {
    public int[] countBits(int num) {
        int[] ans = new int[num + 1];
        for (int i = 1; i <= num; ++i) {
            ans[i] = ans[i & (i - 1)] + 1;
        }
        return ans;
    }    
}