// 1. Sorting
public class Solution {
    public int lastStoneWeight(int[] stones) {
        // 將 int[] 轉為 List<Integer> 方便操作（刪除元素）
        List<Integer> stoneList = new ArrayList<>();
        for (int stone : stones) {
            stoneList.add(stone);
        }

        // 當剩下的石頭超過一個時持續進行碰撞 (要排除為空及只有一顆石頭兩種情況！！！)
        while (stoneList.size() > 1) {
            // 先排序，這樣兩個最大值會在最後面
            Collections.sort(stoneList);

            // 取出最大的兩個石頭
            int cur = stoneList.remove(stoneList.size() - 1) -   // 最大的石頭
                      stoneList.remove(stoneList.size() - 1);    // 第二大的石頭

            // 如果結果不為 0，表示兩塊石頭重量不同，要把差值加入石頭堆中
            if (cur != 0) {
                stoneList.add(cur);
            }
        }

        // 最後如果 list 為空，回傳 0，否則回傳剩下那一顆石頭的重量
        return stoneList.isEmpty() ? 0 : stoneList.get(0);
    }
}