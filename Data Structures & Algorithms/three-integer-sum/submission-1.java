class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        // Arrays.sort()以確保輸出時順序是升序排列
        Arrays.sort(nums);
        // 建立HashMap來存放（num, 頻率）
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums){
            count.put(num, count.getOrDefault(num, 0) + 1);
        }


        List<List<Integer>> res = new ArrayList<>();
        // 遞迴nums中每個元素
        for (int i = 0; i < nums.length; i++){
            // 輪到nums[i]就把它在hashmap中的頻率-1（代表這一輪不能再用它）
            count.put(nums[i], count.get(nums[i]) - 1);
            // 排除重複值
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            for (int j = i + 1; j < nums.length; j++){
                // 輪到nums[j]就把它在hashmap中的頻率-1（代表這一輪不能再用它）
                count.put(nums[j], count.get(nums[j]) - 1);
                // 排除重複值
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;

                // 在hashmap中找target
                // 為了避免target的頻率已經為0，不能寫count.containsKey(target)
                int target = -(nums[i] + nums[j]);
                if (count.getOrDefault(target, 0) > 0){
                    res.add(Arrays.asList(nums[i], nums[j], target));
                }
            }
            // 還原 j 次數
            for (int j = i + 1; j < nums.length; j++){
                count.put(nums[j], count.get(nums[j]) + 1);
            }
        }
        return res;
    }
}
