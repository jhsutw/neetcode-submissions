// two pointers
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        // 定義l(left)、r(right)
        int l = 0, r = numbers.length - 1;

        while (l < r){
            // 計算左右兩值和
            // 若左右兩值和大於targer，則右值左移一格；反之左值右移一格
            int curSum = numbers[l] + numbers[r];

            if (curSum > target){
                r--;
            } else if (curSum < target){
                l++;
            } else {
                return new int[] {l + 1, r + 1};
            }
        }
        return new int[0];
    }
}
