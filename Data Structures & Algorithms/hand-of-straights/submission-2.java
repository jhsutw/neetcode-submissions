public class Solution {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        // 如果牌數不能整除 groupSize，必定無法分組
        if (hand.length % groupSize != 0) return false;

        // 用 HashMap 統計每張牌的出現次數
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : hand) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }

        // 遍歷每張牌，試著把它當作「可能的起始牌」
        for (int num : hand) {
            int start = num;

            // 如果前一張 (start-1) 也存在，就往前移，找到這個牌能延伸到最小的起點
            while (count.getOrDefault(start - 1, 0) > 0) start--;

            // 從起點開始檢查並組牌
            while (start <= num) {
                // 當前起始牌還有剩，嘗試組一組 groupSize 的連續牌
                while (count.getOrDefault(start, 0) > 0) {
                    for (int i = start; i < start + groupSize; i++) {
                        // 如果缺牌就失敗
                        if (count.getOrDefault(i, 0) == 0) return false;
                        // 使用一張牌
                        count.put(i, count.get(i) - 1);
                    }
                }
                // 移到下一個可能的起點
                start++;
            }
        }
        return true;
    }
}
