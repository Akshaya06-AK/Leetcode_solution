
class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {

        int n = nums1.length;
        long[] diff = new long[n];
        long total = (long) k1 + k2;
        long max = 0;

        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            max = Math.max(max, diff[i]);
        }

        if (total >= max) {
            // Check whether all differences can become zero
            long sum = 0;
            for (long d : diff) {
                sum += d;
            }
            if (total >= sum) {
                return 0;
            }
        }

        long left = 0, right = max;

        while (left < right) {
            long mid = left + (right - left) / 2;
            long needed = 0;

            for (long d : diff) {
                if (d > mid) {
                    needed += d - mid;
                }
            }

            if (needed <= total) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        long answer = 0;
        long used = 0;

        for (long d : diff) {
            if (d > left) {
                used += d - left;
                answer += left * left;
            } else {
                answer += d * d;
            }
        }

        long remaining = total - used;

        // Reduce remaining differences one more step optimally
        for (long d : diff) {
            if (remaining == 0) break;

            if (d >= left && d > 0) {
                answer -= left * left;
                answer += (left - 1) * (left - 1);
                remaining--;
            }
        }

        return answer;
    }
}
