public class Solution {
    public List<Integer> partitionLabels(String s) {

        // 建立一個 Map，用來記錄每個字元最後一次出現的位置
        Map<Character, Integer> lastIndex = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            lastIndex.put(s.charAt(i), i);
        }

        List<Integer> res = new ArrayList<>();

        // size：目前這段 partition 的長度
        // end：目前這段 partition 最遠應該延伸到哪一個索引位置
        int size = 0, end = 0;

        // 遍歷字串，逐步確認 partition 邊界
        for (int i = 0; i < s.length(); i++) {
            size++;  // 當前區段的長度 +1

            // 更新目前區段應該到達的最遠邊界
            // 若某個字元最後一次出現的位置比 end 更右邊，就要延伸
            end = Math.max(end, lastIndex.get(s.charAt(i)));

            // 若目前位置 i 剛好等於這段區段應該到達的最遠位置 end
            // 表示這段已完整，不會再被未來字符牽連
            if (i == end) {
                res.add(size);  // 把這段長度加入結果
                size = 0;        // 重設 size，準備開始下一段
            }
        }

        return res;
    }
}