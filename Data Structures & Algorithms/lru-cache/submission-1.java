// 定義雙向鏈結串列的節點類別
public class Node {
    int key;
    int val;
    Node prev;
    Node next;

    public Node(int key, int val) {
        this.key = key;
        this.val = val;
        this.prev = null;
        this.next = null;
    }
}

public class LRUCache {
    
    private int cap;  // 快取容量
    private HashMap<Integer, Node> cache;  // key -> Node 的對應表
    private Node left;  // 雙向鏈表的左邊（最不常用）
    private Node right; // 雙向鏈表的右邊（最近使用）

    public LRUCache(int capacity) {
        this.cap = capacity;
        this.cache = new HashMap<>();

        // 初始化虛擬頭尾節點
        this.left = new Node(0, 0); // 開頭的dummay node
        this.right = new Node(0, 0); // 尾端的dummy node

        // 初始化一個Doubly Linked List的虛擬頭尾節點，讓鏈結串列一開始是空的但結構完整
        this.left.next = this.right; // 連接左邊的dummy node與右邊的dummy node
        this.right.prev = this.left; // 連接右邊的dummy node與左邊的dummy node
    }

    // 從鏈表中移除某個節點
    private void remove(Node node) {
        Node prev = node.prev;
        Node nxt = node.next;
        prev.next = nxt; // 略過node節點
        nxt.prev = prev; // 略過node節點
    }

    // 將節點插入到右邊（最尾端，表示最近使用）
    private void insert(Node node) {
        Node prev = this.right.prev; // this.right為尾端dummy node；this.right.prev指當下最後一個節點
        prev.next = node; // 原本的最後一個節點連接向後與node連接
        node.prev = prev; // 把node向前與原本的最後一個節點連接
        node.next = this.right; // 把node向後與尾端dummy node連接
        this.right.prev = node; // 把尾端dummy node向前與node連接
    }

    // 取得 key 對應的值
    public int get(int key) {
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            remove(node); // 先從舊位置移除
            insert(node); // 移到尾端（表示最近使用）
            return node.val;
        }
        return -1; // 找不到則回傳 -1
    }

    // 新增或更新 key-value
    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            // 若存在，先移除舊節點
            remove(cache.get(key));
        }

        // 新建節點並加入尾端
        Node newNode = new Node(key, value);
        cache.put(key, newNode);
        insert(newNode);

        // 若超出容量，移除最左邊的節點（最久未使用）
        if (cache.size() > cap) {
            Node lru = this.left.next;
            remove(lru);
            cache.remove(lru.key);
        }
    }
}