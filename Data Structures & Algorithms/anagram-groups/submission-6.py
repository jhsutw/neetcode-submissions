class Solution:
    def groupAnagrams(self, strs: List[str]) -> List[List[str]]:
        # use a map to group the anagram
        groups = defaultdict(list) # 當key不存在時自動新增一個entry，value先設為list再把對應的value append進去
        # iterate each word and use a 26-bits string to represent the occurence of each char
            # if the 26-bits string is in the map, put the target string to the value list
            # else, build a new entry to represent the new combination
        for word in strs:
            count = [0] * 26
            for c in word:
                count[ord(c) - ord('a')] += 1
            key = tuple(count) # dict的key必須是hashable（可雜湊），list是mutable所以不行 (tuple是immutable的list)
            groups[key].append(word)
        # combine all value lists together and return the two-layer list
        return list(groups.values()) #把所有values整合成一個list

        