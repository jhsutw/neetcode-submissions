// 4. Breadth First Search (不是用扣的，用加的！)
public class Solution {
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) return 0; // 若金額為 0，則不需要任何硬幣

        Queue<Integer> q = new LinkedList<>(); // 用 BFS 探索每個可能的金額
        q.add(0); // 初始狀態：金額為 0
        boolean[] seen = new boolean[amount + 1]; // 避免重複計算某個金額
        seen[0] = true;
        int res = 0; // res 表示目前使用的硬幣數量（即 BFS 的層數）

        while (!q.isEmpty()) {
            res++; // 每次進入下一層，代表多使用一枚硬幣
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int cur = q.poll(); // 當前金額
                for (int coin : coins) {
                    int nxt = cur + coin; // 嘗試加入一枚硬幣
                    if (nxt == amount) return res; // 若剛好湊到目標金額，回傳當前使用的硬幣數量
                    if (nxt > amount || seen[nxt]) continue; // 若超過目標金額或已經計算過，則略過
                    seen[nxt] = true; // 標記該金額為已探索（次數只會比前面層數加入的時候多，所以不用算！）
                    q.add(nxt); // 加入 queue 等待後續擴展
                }
            }
        }

        return -1; // 無法湊到目標金額，回傳 -1
    }
}