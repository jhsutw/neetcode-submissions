// Negative Marking
// 把標註數字是否出現過的int[]整併進原本的nums list
public class Solution {
    public int findDuplicate(int[] nums) {
        // 遍歷陣列中的每個數字
        for (int num : nums) {
            // 取得絕對值後的索引（因為可能被標記為負數）
            int idx = Math.abs(num) - 1;

            // 若該位置的數值為負數，表示這個數字已經出現過一次，現在是第二次出現
            if (nums[idx] < 0) {
                return Math.abs(num);  // 回傳這個重複出現的數字
            }

            // 將對應位置的數值變為負數，表示這個數字已經看過
            nums[idx] *= -1;
        }

        // 若沒找到重複（理論上不會進到這行，因為根據題目至少有一個重複）
        return -1;
    }
}