class Solution {
    public int[] twoSum(int[] numbers, int target) {
        // 建立HashMap（元素, index + 1）
        Map<Integer, Integer> mp = new HashMap<>();
        for (int i = 0; i < numbers.length; i++){
            int tmp = target - numbers[i];
            // 若tmp值等之前加入過mp的元素（排在numbers[i]前的元素）return (mp.get(tmp), i + 1)
            if (mp.containsKey(tmp)){
                return new int[]{mp.get(tmp), i + 1};
            } else {
                // 否則把(numbers[i], i + 1)加入mp中
                mp.put(numbers[i], i + 1);
            }
        }
        return new int[0];
    }
}
