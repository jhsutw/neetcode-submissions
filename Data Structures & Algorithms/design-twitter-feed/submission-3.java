public class Twitter {

    private int count;  // 用來產生遞減的時間戳記，數字越小表示時間越新
    private Map<Integer, List<int[]>> tweetMap;  // 使用者 ID -> 該使用者的推文列表，每筆推文為 int[]{時間戳記, tweetId}
    private Map<Integer, Set<Integer>> followMap;  // 使用者 ID -> 他追蹤的使用者集合

    public Twitter() {
        this.count = 0;
        this.tweetMap = new HashMap<>();
        this.followMap = new HashMap<>();
    }

    // 發佈一則 tweet
    public void postTweet(int userId, int tweetId) {
        // 若該使用者尚未有任何推文紀錄，先初始化空列表
        tweetMap.computeIfAbsent(userId, k -> new ArrayList<>());

        // 將這則 tweet 加入該使用者的推文列表（用 count 當作時間戳記）
        tweetMap.get(userId).add(new int[]{count, tweetId});

        // 若超過 10 則，移除最舊的一筆，限制 tweet 數量減少記憶體用量
        if (tweetMap.get(userId).size() > 10) {
            tweetMap.get(userId).remove(0);
        }

        count--;  // 時間戳記往前遞減（越新越小）
    }

    // 取得該使用者的新聞動態（最多 10 則 tweet，按時間排序）
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> res = new ArrayList<>();

        // 建立最小堆（timestamp 越小代表越新，會優先被取出）
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[0], b[0])
        );

        // 確保自己也會追蹤自己
        followMap.computeIfAbsent(userId, k -> new HashSet<>()).add(userId);

        // 如果追蹤超過 10 人，我們先用最大堆預選每個人最新一則 tweet，保留前 10 則
        if (followMap.get(userId).size() >= 10) {
            PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
                (a, b) -> Integer.compare(a[0], b[0])  // 因為我們存負號 timestamp，這其實是最大堆
            );

            for (int followeeId : followMap.get(userId)) {
                if (!tweetMap.containsKey(followeeId)) continue;

                List<int[]> tweets = tweetMap.get(followeeId);
                int index = tweets.size() - 1;
                int[] tweet = tweets.get(index);  // 取得該使用者最新一則 tweet

                // 將負 timestamp 存進 maxHeap（這樣 timestamp 小的 tweet 會排在後面）
                maxHeap.offer(new int[]{-tweet[0], tweet[1], followeeId, index - 1});
                if (maxHeap.size() > 10) {
                    maxHeap.poll();  // 保留最晚的 10 則 tweet
                }
            }

            // 將選出的前 10 則 tweet 倒轉轉進 minHeap（還原 timestamp）
            while (!maxHeap.isEmpty()) {
                int[] top = maxHeap.poll();
                minHeap.offer(new int[]{-top[0], top[1], top[2], top[3]});
            }

        } else {
            // 如果追蹤人數很少，就直接把每個人的最新 tweet 丟進 minHeap
            for (int followeeId : followMap.get(userId)) {
                if (!tweetMap.containsKey(followeeId)) continue;

                List<int[]> tweets = tweetMap.get(followeeId);
                int index = tweets.size() - 1;
                int[] tweet = tweets.get(index);

                minHeap.offer(new int[]{tweet[0], tweet[1], followeeId, index - 1});
            }
        }

        // 一次從 minHeap 拿出最新的一則 tweet，放入結果中，然後將下一筆較舊的 tweet 丟回 heap
        while (!minHeap.isEmpty() && res.size() < 10) {
            int[] top = minHeap.poll();  // 取出時間最新的 tweet
            res.add(top[1]);             // 加入 tweetId 到結果中
            int nextIndex = top[3];      // index 指向該使用者的下一則舊 tweet

            if (nextIndex >= 0) {
                List<int[]> tweets = tweetMap.get(top[2]);  // 取得這個使用者的 tweet 列表
                int[] nextTweet = tweets.get(nextIndex);
                // 將下一則 tweet 加入 heap
                minHeap.offer(new int[]{nextTweet[0], nextTweet[1], top[2], nextIndex - 1});
            }
        }

        return res;
    }

    // 追蹤某人
    public void follow(int followerId, int followeeId) {
        followMap.computeIfAbsent(followerId, k -> new HashSet<>()).add(followeeId);
    }

    // 取消追蹤
    public void unfollow(int followerId, int followeeId) {
        if (followMap.containsKey(followerId)) {
            followMap.get(followerId).remove(followeeId);
        }
    }
}