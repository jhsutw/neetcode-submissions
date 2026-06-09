public class Twitter {
    
    private int count; // 發文的全域時間戳記（用遞減方式模擬越新越大）
    private Map<Integer, List<int[]>> tweetMap; // 儲存每個使用者發的推文清單，每個推文為 [timestamp, tweetId]
    private Map<Integer, Set<Integer>> followMap; // 儲存每個使用者關注的人列表

    public Twitter() {
        count = 0;
        tweetMap = new HashMap<>();
        followMap = new HashMap<>();
    }

    // 發布一則推文
    public void postTweet(int userId, int tweetId) {
        // 把新推文加入到該使用者的推文列表中（timestamp 用遞減值）
        tweetMap.computeIfAbsent(userId, k -> new ArrayList<>()).add(new int[]{count--, tweetId});
    }

    // 取得最新的 10 則推文（包括自己的與已關注的人的）
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> res = new ArrayList<>();

        // 小頂堆（timestamp 越大（越新）越往後放）
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        // 確保自己也追蹤自己（才能看到自己的推文）
        followMap.computeIfAbsent(userId, k -> new HashSet<>()).add(userId);

        // 對每一個追蹤對象，把他的最新推文放入 heap（每個followee先加入最新的一篇，該篇被加入top10後再把第二新的加入minheap...）
        for (int followeeId : followMap.get(userId)) {
            if (tweetMap.containsKey(followeeId)) {
                List<int[]> tweets = tweetMap.get(followeeId);
                int index = tweets.size() - 1;
                int[] tweet = tweets.get(index);
                // 加入格式：[timestamp, tweetId, userId, index]
                minHeap.offer(new int[]{tweet[0], tweet[1], followeeId, index});
            }
        }

        // 每次取出最上面（最新的）推文，最多取 10 筆
        while (!minHeap.isEmpty() && res.size() < 10) {
            int[] curr = minHeap.poll();  // 取出目前最新的推文
            res.add(curr[1]);             // 只加入 tweetId 到結果中
            int index = curr[3];
            // 如果該使用者還有更舊的推文，就把那篇也加入 heap
            if (index > 0) {
                int[] tweet = tweetMap.get(curr[2]).get(index - 1); // index 是該使用者目前推文list中的索引值（list中第幾個元素），代表目前這個 tweet 是第幾篇
                minHeap.offer(new int[]{tweet[0], tweet[1], curr[2], index - 1});
            }
        }

        return res;
    }

    // 關注某人
    public void follow(int followerId, int followeeId) {
        followMap.computeIfAbsent(followerId, k -> new HashSet<>()).add(followeeId);
    }

    // 取消關注某人
    public void unfollow(int followerId, int followeeId) {
        // 注意：不能取消關注自己
        if (followerId != followeeId) {
            followMap.computeIfPresent(followerId, (k, v) -> {
                v.remove(followeeId);
                return v;
            });
        }
    }
}
