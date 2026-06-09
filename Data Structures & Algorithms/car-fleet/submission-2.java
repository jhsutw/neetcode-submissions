// iteration
class Solution {
    public int carFleet(int target, int[] position, int[] speed) {
        // 創造一個長度為position.length且長度為2的int array
        int[][] pair = new int[position.length][2];
        // 把pair的第一個元素指派為position；第二個元素指派為speed
        for (int i = 0; i < position.length; i++){
            pair[i][0] = position[i];
            pair[i][1] = speed[i];
        }

        // 把pair依照position降冪排序
        Arrays.sort(pair, (a, b) -> Integer.compare(b[0], a[0]));

        // 若後車不能追上前車（後車的到達終點所需要時間比前車長），就把fleet++，並且更新prevTime
        // 若後車可以追上前車，prevTime不變仍維持前面較慢的車的到達終點所需要時間
        int fleets = 1;
        double prevTime = (double)(target - pair[0][0]) / pair[0][1];
        for (int i = 1; i < position.length; i++){
            double currTime = (double)(target - pair[i][0]) / pair[i][1];
            if (currTime > prevTime){
                fleets++;
                prevTime = currTime;
            }
        }
        return fleets;
    }
}
