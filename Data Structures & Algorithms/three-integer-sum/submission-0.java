class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> res = new HashSet<>();
        // 排序原因：set中不會有重複值，但加入set的list順序不同時，會被視爲相異值而重複計算
        Arrays.sort(nums);

        // 避免重複探索，所以j = i + 1、k = j + 1
        for (int i = 0; i < nums.length; i++){
            for (int j = i + 1; j < nums.length; j++){
                for (int k = j + 1; k < nums.length; k++){
                    if (nums[i] + nums[j] + nums[k] == 0){
                        // 左邊的變數型別須為List<Integer>而非int[]
                        // Arrays.asList可以讓括號中的Array轉成Ｌist
                        List<Integer> tmp = Arrays.asList(nums[i], nums[j], nums[k]);
                        res.add(tmp);
                    }
                }
            }
        }
        return new ArrayList<>(res);
    }
}
