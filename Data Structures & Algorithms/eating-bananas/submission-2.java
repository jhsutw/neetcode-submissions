class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int l = 1; // 預設最小速度為1
        int r = Arrays.stream(piles).max().getAsInt(); // 預設最大速度為r（數量最多的pile的香蕉個數，因為一次只能吃一堆，超過該數量也沒用）
        int res = r; // 預設res為最大值，有找到較小的再更新

        // 用l & r插值得k，以k為速度計算是否能在h（時限）內吃完
        // 可以的話把速度放慢（r = k - 1），讓res可以更小
        // 不行的話提升速度（ l = k + 1）
        while (l <= r){
            int k = (l + r) / 2;

            long totalTime = 0;
            for (int p : piles){
                totalTime += Math.ceil((double) p / k);
            }
            if (totalTime <= h){
                res = k;
                r = k - 1;
            } else{
                l = k + 1;
            }
        }
        return res;
    }
}
