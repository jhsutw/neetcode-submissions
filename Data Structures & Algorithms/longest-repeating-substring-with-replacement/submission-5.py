class Solution:
    def characterReplacement(self, s: str, k: int) -> int:
        # use a sliding window to record the most frequent word
            # if (the length of a sliding window - the frequency of the most frequent word) > k,
            # then shift the left side forward
            # else, update the max length with the length of a sliding window if longer than the existing max length
        l = 0
        r = 0
        cur_freq = 0
        cur_word = s[0]
        count = [0] * 26
        max_len = 0

        while r < len(s) and l <= r:
            count[ord(s[r]) - ord('A')] += 1
            if count[ord(s[r]) - ord('A')] > cur_freq:
                cur_word = s[r]
                cur_freq =  count[ord(s[r]) - ord('A')]

            if ((r - l + 1) - cur_freq > k):
                if (cur_word == s[l]):
                    cur_freq -= 1
                count[ord(s[l]) - ord('A')] -= 1
                l += 1
            max_len = max(max_len, r - l + 1)
            r += 1
        
        return max_len
            



