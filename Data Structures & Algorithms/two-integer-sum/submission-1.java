// two pointers method

class Solution {
    public int[] twoSum(int[] nums, int target) {
        // 創建一個二元array，第一個值存nums[i]的值、第二個值存num[i]原本的index（sort以後順序會亂掉，但最後輸出的index要照原順序大小排）
        int[][] A = new int[nums.length][2];
        for (int i = 0; i < nums.length; i++){
            A[i][0] = nums[i];
            A[i][1] = i;
        }

        // Arrays.sort 語法！！！
        // Comparator.comparingInt(a -> a[0])指「拿子陣列的第一個值來比大小」
        // 若是一元陣列 Arrays.sort(A) 即可
        Arrays.sort(A, Comparator.comparingInt(a -> a[0]));

        // 頭尾相加，若值小於target則頭值右移，反之則尾值左移
        int i = 0, j = nums.length - 1;
        while(i < j){
            int cur = A[i][0] + A[j][0];
            if (cur == target){
                // 把原順序小的index放前面、把原順序大的index放後面
                return new int[]{Math.min(A[i][1], A[j][1]), 
                                 Math.max(A[i][1], A[j][1])};
            } else if (cur < target){
                i++;
            } else {
                j--;
            }
        }
        return new int[0];
        
    }
}
