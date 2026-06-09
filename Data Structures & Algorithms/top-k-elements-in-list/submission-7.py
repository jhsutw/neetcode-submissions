class Solution:
    def topKFrequent(self, nums: List[int], k: int) -> List[int]:
        # build a priority queue to accomodate the entry (sort by the occurence of each elem)
        # iterate through the nums array to build a dict (elem, occurence)
        # put the entries of the dist into the priority queue
        # pop the first k elem from the priority queue and put the key into result list
        count = {}
        for n in nums:
            count[n] = count.get(n, 0) + 1
        
        heap = []
        for elem, freq in count.items():
            heapq.heappush(heap, (freq, elem))
            if len(heap) > k:
                heapq.heappop(heap)
        
        result = []
        for freq, elem in heap:
            result.append(elem)
        
        return result