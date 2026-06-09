// binary search
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; i++){
            // l = i + 1 只往後面（還沒找過）的元素找
            int l = i + 1, r = numbers.length - 1;
            int tmp = target - numbers[i];
            while (l <= r){
                // 使用 l + (r - l) / 2 是為了防止整數溢位（比 mid = (l + r) / 2 更安全）
                int mid = l + (r - l) / 2;
                if (numbers[mid] == tmp){
                    // 題目要的output為list的index + 1
                    return new int[]{i + 1, mid + 1};
                } else if (numbers[mid] < tmp){
                    // numbers[mid] < tmp代表 tmp 在 numbers[mid] 跟 r 中間，所以用l = mid + 1再跟r插值
                    l = mid + 1;
                } else{
                    r = mid - 1;
                }
            }
        }
        return new int[0];
    }
}
