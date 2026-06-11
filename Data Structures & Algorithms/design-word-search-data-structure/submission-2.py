class WordDictionary:

    def __init__(self):
        self.children = {}
        self.is_End = False

    def addWord(self, word: str) -> None:
        node = self
        for char in word:
            if char not in node.children:
                node.children[char] = WordDictionary()
            node = node.children[char]
        node.is_End = True

    def search(self, word: str) -> bool:
        node = self
        for i, char in enumerate(word):
            if char == '.':
                for child in node.children.values():
                    if child.search(word[i + 1 :]):
                        return True
                return False
            else:
                if char not in node.children:
                    return False
                node = node.children[char]
        return node.is_End
        
