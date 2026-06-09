// brute force
class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int[] res = new int[temperatures.length];

        for (int i = 0; i < temperatures.length; i++){
            int temp = temperatures[i];
            int days = 0;
            for (int j = i + 1; j < temperatures.length; j++){
                if (temperatures[j] > temp){
                    days = j - i;
                    break;
                }
            }
            res[i] = days;
        }
        return res;
    }
}
