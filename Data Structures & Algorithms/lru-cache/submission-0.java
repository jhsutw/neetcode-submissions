class LRUCache {

    // 使用 ArrayList 存儲 [key, value] 配對，越後面代表越新（最近使用）
    private ArrayList<int[]> cache;
    private int capacity;
    
    // 建構子：初始化快取空間與容量
    public LRUCache(int capacity) {
        this.cache = new ArrayList<>();
        this.capacity = capacity;
    }
    
    // 取得 key 對應的 value，如果存在，將它移到最近使用（移到最後）
    public int get(int key) {
        for (int i = 0; i < cache.size(); i++){
            if (cache.get(i)[0] == key){ // 找到 key
                int[] tmp = cache.remove(i); // 移除舊的位置
                cache.add(tmp); // 加到最後（表示最近使用）
                return tmp[1]; // 回傳 value
            }
        }
        return -1;
    }
    
    // 插入或更新 key-value 配對
    public void put(int key, int value) {
        // 先檢查 key 是否已存在，若是則更新並移至最後
        for (int i = 0; i < cache.size(); i++){
            if (cache.get(i)[0] == key){
                int[] tmp = cache.remove(i); // 移除原位置
                tmp[1] = value; // 更新 value
                cache.add(tmp); // 加到最後（表示最近使用）
                return;
            }
        }

        // 若容量已滿，移除最舊（最前面）的元素
        if (capacity == cache.size()){ 
            cache.remove(0);
        }

        // 加入新的 [key, value]
        cache.add(new int[]{key, value});
    }
}
