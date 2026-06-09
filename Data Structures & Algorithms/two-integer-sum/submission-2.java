class Solution {
    public int[] twoSum(int[] nums, int target) {

        // 建立hashmap放(values, index)
        Map<Integer, Integer> indices = new HashMap<>();

        for (int i = 0; i < nums.length; i++){
            indices.put(nums[i], i);
        }

        // 計算target與其一value的差值，然後mapping該差值是否等於其他vlaue
        for (int i = 0; i < nums.length; i++){
            int diff = target - nums[i];
            // hashmap.containsKey用於找某值是否包含於keys中；hashmap.get(key)用於找value
            if (indices.containsKey(diff) && indices.get(diff) != i){
                return new int[]{i, indices.get(diff)};
            }
        }
        return new int[0];
    }
}
