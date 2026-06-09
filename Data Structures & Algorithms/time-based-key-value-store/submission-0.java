// Brute Force
class TimeMap {
    // 儲存所有 key 對應的 timestamp → 對應的 value list
    private Map<String, Map<Integer, List<String>>> keyStore;

    public TimeMap() {
        // 初始化 keyStore
        keyStore = new HashMap<>();
    }

    // 設定某個 key 在某個 timestamp 對應的 value
    public void set(String key, String value, int timestamp) {
        // 如果這個 key 還沒出現，先建立一個對應的 map
        if (!keyStore.containsKey(key)) {
            keyStore.put(key, new HashMap<>());
        }

        // 如果這個 timestamp 第一次出現，先建立一個 list 來裝 value
        if (!keyStore.get(key).containsKey(timestamp)) {
            keyStore.get(key).put(timestamp, new ArrayList<>());
        }

        // 將 value 加進對應 timestamp 的 list 中（雖然通常每個時間點只會有一個 value）
        keyStore.get(key).get(timestamp).add(value);
    }

    // 根據 key 和 timestamp 取得該時間點或更早的「最新」value
    public String get(String key, int timestamp) {
        // 如果 key 根本沒被設過，回傳空字串
        if (!keyStore.containsKey(key)) {
            return "";
        }

        int seen = 0; // 紀錄目前為止找到 ≤ timestamp 的最大時間點

        // 遍歷所有 timestamp，找到 ≤ timestamp 的最大值
        for (int time : keyStore.get(key).keySet()) {
            if (time <= timestamp) {
                seen = Math.max(seen, time);
            }
        }

        // 如果沒找到任何 timestamp ≤ 輸入 timestamp，就回傳空字串
        if (seen == 0) return "";

        // 取出這個 timestamp 對應的 value list 的最後一個（雖然通常只存一個）
        int back = keyStore.get(key).get(seen).size() - 1;
        return keyStore.get(key).get(seen).get(back);
    }
}