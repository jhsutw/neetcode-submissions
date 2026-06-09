class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> count = new HashMap<>();
        // 建立一個freq list長度為nums.length + 1（0~nums.length）
        // List<Integer>[]比int[]更自由，可放不只「單一整數」且「可變長度」
        List<Integer>[] freq = new List[nums.length + 1];

        // 建立一個長度為 freq.length 的陣列，裡面放freq.length個陣列（放出現頻率為i的數字）
        for (int i  = 0; i < freq.length; i++){
            freq[i] = new ArrayList<>();
        }

        // 建立hashmap（數字, 出現頻率）
        for (int n : nums){
            count.put(n, count.getOrDefault(n, 0) + 1);
        }

        // 把 HashMap 轉成 bucket（若轉成list在排序會花 O(M log M) 的時間，轉成bucket則只會花 O(N) 的時間)
        // {1:3, 2:2, 3:1} >> freq[3] = [1]
        //                    freq[2] = [2]
        //                    freq[1] = [3]
        for (Map.Entry<Integer, Integer> entry : count.entrySet()){
            freq[entry.getValue()].add(entry.getKey());
        }

        // 建立res陣列放出現頻率前k高的數字
        int[] res = new int[k];
        int index = 0;
        // freq.length - 1 為nums陣列的長度
        // index < k 若頻率不為前k高就不看了
        for (int i = freq.length - 1 ; i > 0 && index < k; i--){
            // 把出現次數為 i 的所有元素（都存在 freq[i] 中）一個一個取出來放進res
            // 再把 index++，方便往下一個空位
            for (int n : freq[i]){
                res[index++] = n;
                // 已經輸出完頻率前k高的數字就return res
                if (index == k){
                    return res;
                }
            }
        }
        return res;
    }
}
