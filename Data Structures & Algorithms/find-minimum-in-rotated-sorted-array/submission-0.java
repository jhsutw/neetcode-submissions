// brute force
class Solution {
    public int findMin(int[] nums) {
        // Arrays.stream(nums) 會把 int[] 轉成一個 IntStream，才可以用可以用 .min()、.max()、.sum() 等方法
        // .min()：回傳 OptionalInt，代表該串流的最小值
        // .getAsInt()：從 OptionalInt 取出實際值
        return Arrays.stream(nums).min().getAsInt();
    }
}
