class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] output = new int[n - k + 1];
        Deque<Integer> q = new LinkedList<>();

        int l = 0, r = 0;

        while (r < n) {
            while (!q.isEmpty() && (nums[q.getLast()] < nums[r])) {
                q.removeLast(); // remove掉在deque中比nums[r]值小的值的index
            }
            q.addLast(r); // 比nums[r]小的值的index都被removeLast掉了，所以nums[r]一定是sliding window最小值的index

            if (l > q.getFirst()) { // 若deque最大值的index已經不在sliding window內，就要remove掉
                q.removeFirst();
            }

            if ((r - l + 1) >= k) {
                output[l] = nums[q.getFirst()];
                l++;
            }
            r++;
        }
        return output;
    }
}
