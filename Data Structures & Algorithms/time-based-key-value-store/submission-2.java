public class TimeMap {

    // 每個 key 對應一個 List，裡面是按照時間排序的 Pair(timestamp, value)
    private Map<String, List<Pair<Integer, String>>> keyStore;

    public TimeMap() {
        // 初始化 key-value 儲存結構
        keyStore = new HashMap<>();
    }

    // 設定某個 key 在指定 timestamp 的值
    public void set(String key, String value, int timestamp) {
        // 如果 key 還不存在，先建立空的 list
        keyStore.computeIfAbsent(key, k -> new ArrayList<>())
                // 將新的 Pair(timestamp, value) 加到 list 末尾（保證時間順序）
                .add(new Pair<>(timestamp, value));
    }

    // 取得 key 在 timestamp 時對應的值（找 ≤ timestamp 的最大時間點）
    public String get(String key, int timestamp) {
        // 取得這個 key 對應的所有 (timestamp, value) 列表；若沒找到則給空 list
        List<Pair<Integer, String>> values = keyStore.getOrDefault(key, new ArrayList<>());

        int left = 0, right = values.size() - 1;
        String result = "";  // 預設為空字串，表示找不到符合條件的值

        // 二分搜尋找 <= timestamp 的最大時間點
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int midTime = values.get(mid).getKey();

            if (midTime <= timestamp) {
                // 當前 timestamp 合法，先記錄這個值，繼續往右邊找更大的合法時間點
                result = values.get(mid).getValue();
                left = mid + 1;
            } else {
                // 太大，往左邊找
                right = mid - 1;
            }
        }

        return result;
    }

    // 自定義的 Pair 類別（因為 Java 沒有內建泛型 Pair）
    private static class Pair<K, V> {
        private final K key;
        private final V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }
}