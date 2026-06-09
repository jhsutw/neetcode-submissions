class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        cur_longest = 0
        total_longest = 0
        map = {}
        l = 0

        for r in range(len(s)):
            cur_longest += 1
            map[s[r]] = map.get(s[r], 0) + 1

            while map[s[r]] > 1:
                map[s[l]] -= 1
                l += 1
                cur_longest -= 1

            total_longest = max(total_longest, cur_longest)
        
        return total_longest


        