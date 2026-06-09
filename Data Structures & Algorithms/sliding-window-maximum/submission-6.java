public class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] output = new int[n - k + 1]; // 用來儲存每個滑動視窗的最大值

        Deque<Integer> q = new LinkedList<>(); // 雙端佇列，用來儲存 index，維持單調遞減的順序（最大值在前）
        int l = 0, r = 0; // l 是滑動視窗的左邊界，r 是右邊界（正在處理的元素）

        // 主迴圈：遍歷整個陣列
        while (r < n) {
            // 移除所有比 nums[r] 小的 index，保持隊列單調遞減
            while (!q.isEmpty() && nums[q.getLast()] < nums[r]) {
                q.removeLast(); // 從尾端移除比目前元素小的 index
            }
            q.addLast(r); // 將當前元素的 index 加入佇列尾端

            // 檢查佇列頭的 index 是否已經不在目前視窗範圍內
            if (l > q.getFirst()) {
                q.removeFirst(); // 移除過期的 index
            }

            // 如果已經形成一個完整視窗，則可以計算最大值
            if ((r + 1) >= k) {
                output[l] = nums[q.getFirst()]; // 佇列頭的 index 對應的值就是目前視窗的最大值
                l++; // 視窗左界往右移動
            }

            r++; // 每一輪都把右界往右移動
        }

        return output; // 回傳每個視窗的最大值結果
    }
}