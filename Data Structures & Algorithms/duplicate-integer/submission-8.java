/**
1. Arrays.stream(nums)
把陣列 nums 轉成 Java 的 Stream，可以用函式式寫法處理元素。

2. .distinct()
會保留唯一元素，相當於自動「去重」。

3. .count()
計算去重後剩下多少元素。

4. < nums.length
如果「去重後」的元素數目 小於 原本陣列長度，就代表陣列裡有重複元素。
*/

public class Solution {
    public boolean hasDuplicate(int[] nums) {
        return Arrays.stream(nums).distinct().count() < nums.length;
    }
}