// OnePass
// Sol 3 是先把所有值放進HashMap再做diff的mapping
// Sol 4 是同步先做diff的mapping再把沒配對成功地放入HashMap
class Solution {
    public int[] twoSum(int[] nums, int target) {
       HashMap<Integer, Integer> prevMap = new HashMap<>();

       for (int i = 0; i < nums.length; i++){
            int num = nums[i];
            int diff = target - num;

            // 若nums[i]前面有值等於diff就輸出結果（因為prevMap.get(diff)是先放進HashMap的值所以順序在前面）
            if (prevMap.containsKey(diff)){
                return new int[]{prevMap.get(diff), i};
            } else {
                // 否則把(num, i)放入HashMap中
                prevMap.put(num, i);
            }
       }
       return new int[0];

    }
}
