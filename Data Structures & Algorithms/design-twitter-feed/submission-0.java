// 1. Sorting
public class Twitter {
    private int time;  // 用於紀錄每條推文的時間順序（自增的時間戳）
    private Map<Integer, Set<Integer>> followMap; // followerId -> (set of followeeIds)
    private Map<Integer, List<int[]>> tweetMap;   // userId -> (list of [time, tweetId])

    public Twitter() {
        time = 0;
        followMap = new HashMap<>();
        tweetMap = new HashMap<>();
    }

    // 發布推文
    public void postTweet(int userId, int tweetId) {
        tweetMap.putIfAbsent(userId, new ArrayList<>());
        tweetMap.get(userId).add(new int[]{time++, tweetId}); 
        // 每發一條推文，時間戳 time 自增，確保推文的先後順序
    }

    // 取得該用戶的新聞推送，包含自己與他追蹤的用戶推文
    public List<Integer> getNewsFeed(int userId) {
        // 先把自己發的推文加進 feed
        List<int[]> feed = new ArrayList<>(tweetMap.getOrDefault(userId, new ArrayList<>()));

        // 再把他追蹤的每個 followee 的推文加進 feed
        for (int followeeId : followMap.getOrDefault(userId, new HashSet<>())) {
            feed.addAll(tweetMap.getOrDefault(followeeId, new ArrayList<>()));
        }

        // 按時間戳由大到小排序（最新的在前）
        feed.sort((a, b) -> b[0] - a[0]);

        // 取出最新的 10 條推文的 tweetId
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < Math.min(10, feed.size()); i++) {
            res.add(feed.get(i)[1]);
        }
        return res;
    }

    // 追蹤某位使用者
    public void follow(int followerId, int followeeId) {
        if (followerId != followeeId) {  // 不允許自己追蹤自己
            followMap.putIfAbsent(followerId, new HashSet<>());
            followMap.get(followerId).add(followeeId);
        }
    }

    // 取消追蹤
    public void unfollow(int followerId, int followeeId) {
        followMap.getOrDefault(followerId, new HashSet<>()).remove(followeeId);
    }
}