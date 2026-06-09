public class TimeMap {
    // 使用 Map 儲存 key → TreeMap（timestamp → value）
    // TreeMap 可以自動維持時間順序，並提供快速 floorEntry 查找
    private Map<String, TreeMap<Integer, String>> m;

    public TimeMap() {
        // 初始化主儲存空間
        m = new HashMap<>();
    }

    // 設定某個 key 在指定 timestamp 的值
    public void set(String key, String value, int timestamp) {
        // 如果該 key 尚未出現，就建立一個新的 TreeMap 給它
        // TreeMap 會自動按照 timestamp 排序
        m.computeIfAbsent(key, k -> new TreeMap<>()).put(timestamp, value);
    }

    // 取得 key 在 timestamp 時的對應值（找 ≤ timestamp 中最新的一個）
    public String get(String key, int timestamp) {
        // 若找不到 key，直接回傳空字串
        if (!m.containsKey(key)) return "";

        // 拿出該 key 對應的 TreeMap（時間 → 值）
        TreeMap<Integer, String> timestamps = m.get(key);

        // 找出 ≤ timestamp 的最大 key（即時間點）對應的 entry
        // timestamps.floorEntry() : 回傳 小於或等於 timestamp 的最大 key 對應的 entry（鍵值對）。
        Map.Entry<Integer, String> entry = timestamps.floorEntry(timestamp);

        // 如果找不到（即所有 timestamp 都 > 輸入的 timestamp），回傳空字串
        return entry == null ? "" : entry.getValue();
    }
}