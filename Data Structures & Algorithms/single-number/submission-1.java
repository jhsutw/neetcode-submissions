// 2. Hash Set
public class Solution {
    public int singleNumber(int[] nums) {
        // 使用 Set 追蹤目前出現「奇數次」的數字
        // 規則：第一次遇到就加入；第二次遇到就移除
        // 如此成對出現的數字會被抵消，最後只剩下那個只出現一次的數
        Set<Integer> seen = new HashSet<>();

        for (int num : nums) {
            if (seen.contains(num)) {
                // 第二次（或偶數次）出現，從集合移除
                seen.remove(num);
            } else {
                // 第一次（或奇數次）出現，加入集合
                seen.add(num);
            }
        }

        // 題目（LC136）保證恰有一個只出現一次的數，因此此時 Set 只剩它
        /*
        它的意思是：從 seen 這個 Set 取出其中一個元素並回傳。
        1 seen.iterator()：建立一個迭代器。
        2 .next()：回傳迭代器目前指向的元素（第一個可取的元素）。
        */
        return seen.iterator().next();
    }
}