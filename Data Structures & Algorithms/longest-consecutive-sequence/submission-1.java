class Solution {
    public int longestConsecutive(int[] nums) {
        int res = 0;
        // 把nums轉成store（hashset）是因為搜尋的效率較好！List為O(n)、HashSet為O(1)
        Set<Integer> store = new HashSet<>();
        for (int num : nums){
            store.add(num);
        }

        for (int num : nums){
            // streak計算累積幾個連續數值、curr表示目前的數字
            int streak = 0, curr = num;
            while (store.contains(curr)){
                streak++;
                curr++;
            }
            // 若當前累積的連續數值的數量>當前最大值，則streak取代res
            res = Math.max(res, streak);
        }
        return res;
    }
}
