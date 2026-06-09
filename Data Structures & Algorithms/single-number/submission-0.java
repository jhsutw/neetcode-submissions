public class Solution {

    /**
     * 找出陣列中只出現一次的數字（其餘數字皆至少重複一次）
     * 作法：針對每個元素，內層再掃一遍確認是否有相同元素
     */
    public int singleNumber(int[] nums) {
        // 外層：逐一假設 nums[i] 是只出現一次的數字
        for (int i = 0; i < nums.length; i++) {
            boolean flag = true; // 旗標：先假設 nums[i] 沒有重複

            // 內層：檢查是否存在另一個與 nums[i] 相同的元素
            for (int j = 0; j < nums.length; j++) {
                // 排除同一個索引（i != j），若找到相同值則表示 nums[i] 並非唯一
                if (i != j && nums[i] == nums[j]) {
                    flag = false; // 發現重複，將旗標設為 false
                    break;        // 可提前結束內層迴圈
                }
            }

            // 若整個內層掃描都沒找到相同數字，表示 nums[i] 為唯一的數
            if (flag) {
                return nums[i];
            }
        }

        // 若題目不保證一定存在唯一元素，找不到時回傳 -1 作為預設值
        return -1;
    }
}