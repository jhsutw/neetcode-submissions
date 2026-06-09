class Solution {
    public int longestConsecutive(int[] nums) {
        // 建立HashSet存放nums值，因為運算效率較好！
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums){
            numSet.add(num);
        }

        // 初始化最大累積連續數值數量
        int longest = 0;
        for (int num : nums){
            // 若num為連續值的首值，其length設為1
            if (!numSet.contains(num - 1)){
                int length = 1;
                // 檢查後續值是否連續，並累計長度
                while (numSet.contains(num + length)){
                    length++;
                }
                longest = Math.max(longest, length);
            }
        }
        return longest;
    }
}
