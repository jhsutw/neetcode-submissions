// 1. Sorting
class KthLargest {
    private int K;
    private List<Integer> arr;

    public KthLargest(int k, int[] nums) {
        K = k;
        arr = new ArrayList<>();
        for (int num : nums) {
            arr.add(num);
        }
    }

    public int add(int val) {
        arr.add(val);
        Collections.sort(arr); // 每次加新元素後排序
        return arr.get(arr.size() - K); // 回傳第 K 大
    }
}