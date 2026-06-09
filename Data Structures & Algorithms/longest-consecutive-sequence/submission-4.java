class Solution {
    public int longestConsecutive(int[] nums) {
       // 建立HashMap來存放每個元素的最大連續值數量
       Map<Integer, Integer> mp = new HashMap<>();
       int res = 0;

       for (int num : nums){
        // 避免重複處理相同的數字
        if (!mp.containsKey(num)){
            // num的最大連續值數量 = num - 1的最大連續值數量 + num + 1的最大連續值數量 + 1
            mp.put(num, mp.getOrDefault(num - 1, 0) + mp.getOrDefault(num + 1, 0) + 1);
            // 更新上下界的最大連續值數量（跟num的最大連續值數量相同），中間的數值因為後面用不到，所以不用更新
            mp.put(num - mp.getOrDefault(num - 1, 0), mp.get(num));
            mp.put(num + mp.getOrDefault(num + 1, 0), mp.get(num));
        }
        res = Math.max(res, mp.get(num));
       }
       return res; 
    }
}
