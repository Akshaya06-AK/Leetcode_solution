class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] ans = new long[k];
        long[] dp = new long[k];

        for (int num : nums) {
            long[] next = new long[k];

            int x = num % k;

            // Single element subarray
            next[x]++;

            // Previous subarrays + current number
            for (int r = 0; r < k; r++) {
                if (dp[r] != 0) {
                    int newRemainder = (int) ((long) r * x % k);
                    next[newRemainder] += dp[r];
                }
            }

            // Add all subarrays ending here
            for (int r = 0; r < k; r++) {
                ans[r] += next[r];
            }

            dp = next;
        }

        return ans;
    }
}