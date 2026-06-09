class Solution {
    public int longestConsecutive(int[] nums) {
        // 若nums為空回傳0
        if (nums.length == 0){
            return 0;
        }
         
        // 排序nums
        Arrays.sort(nums);

        //初始化：res為結果、curr為當前預期數值、streak為累積連續數值數量、i為迴圈index
        int res = 0, curr = nums[0], streak = 0, i = 0;

        // 當數列不連續，將當前遞迴之數值設為當前預期值（重新開始）、累積數量設為0
        while (i < nums.length){
            if (curr != nums[i]){
                curr = nums[i];
                streak = 0;
            }

            // 推進迴圈並跳過重複值（注意要先寫i < nums.length，因為當此條件不滿足就不會檢查nums[i] == curr，否則會產生out of index問題）
            while (i < nums.length && nums[i] == curr){
                i++;
            }

            // 將預期數值更新成當前數值+1，並增加累積數量
            streak++;
            curr++;

            // 比較當前最大累積數量與當前累積數量，若當前累積數量大於當前最大累積數量，則取代之
            res = Math.max(res, streak);
        }
        return res;
    }
}
