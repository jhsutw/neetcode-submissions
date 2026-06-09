public class LRUCache {
    // 使用 LinkedHashMap 作為底層快取容器
    private final Map<Integer, Integer> cache;
    private final int capacity;

    // 建構子，初始化快取容量
    public LRUCache(int capacity) {
        this.capacity = capacity;

        // 建立一個 LinkedHashMap（第三個參數 true 表示啟用「access-order」）
        /*  1. capacity: 預設容量（例如：LRU 最多只能放 2 個東西）
            2. 0.75f: 負載因子（不用管，照抄就好）
            3. true: 重點！ 表示這個 Map 會依照「最近使用順序（access-order）」排序 → 只要有 get 或 put 某個 key，它就會被排到後面
        */
        this.cache = new LinkedHashMap<>(capacity, 0.75f, true) {
            // 當加入新元素時，檢查是否超出容量，若是則移除最舊的元素（LRU）
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return size() > LRUCache.this.capacity;
            }
        };
    }

    // 取得 key 對應的值；若不存在則回傳 -1
    public int get(int key) {
        return cache.getOrDefault(key, -1);
    }

    // 新增或更新 key-value 配對，同時根據「使用順序」調整順序
    public void put(int key, int value) {
        cache.put(key, value);
    }
}