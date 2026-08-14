class Solution:
    def isNStraightHand(self, hand: List[int], groupSize: int) -> bool:
        if len(hand) % groupSize != 0:
            return False
        
        count = Counter(hand)
        minHeap = list(count.keys())
        heapq.heapify(minHeap)

        while minHeap:
            first = minHeap[0]

            for card in range(first, first + groupSize):
                if card not in minHeap:
                    return False

                count[card] -= 1

                if count[card] == 0:
                    if card != minHeap[0]:
                        return False
                    
                    heapq.heappop(minHeap)

                    del count[card]
        
        return True
                
