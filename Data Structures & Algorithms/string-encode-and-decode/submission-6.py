class Solution:

    def encode(self, strs: List[str]) -> str:
        # create a result string
        # iterate through the strs
            # append the (len of string) + '#' + str to the result string
        result = ""
        for s in strs:
            result += f"{len(s)}#{s}"
        return result

    def decode(self, s: str) -> List[str]:
        # create a result list to accomodate the seperated chunks
        # set start = 0
        # iterate the s
            # if '#' is found (end = #iteration), put the substring(start, end) to the result list and set start to be end + 1
            # else, keep iterating...
        # return result list
        result = []
        i = 0
        while i < len(s):
            j = i
            while s[j] != "#":
                j += 1
            length = int(s[i:j])
            result.append(s[j + 1 : j + 1 + length])
            i = j + 1 + length
        return result
