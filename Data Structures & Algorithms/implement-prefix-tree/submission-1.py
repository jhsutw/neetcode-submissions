class PrefixTree:

    def __init__(self):
        self.children = {}
        self.isEndOfWord = False
        
    def insert(self, word: str) -> None:
        node = self
        for char in word:
            if char not in node.children:
                node.children[char] = PrefixTree()
            node =  node.children[char]
        node.isEndOfWord = True

    def search(self, word: str) -> bool:
        node = self
        for char in word:
            if char not in node.children:
                return False
            node = node.children[char]
        return node.isEndOfWord
        

    def startsWith(self, prefix: str) -> bool:
        node = self
        for char in prefix:
            if char not in node.children:
                return False
            node = node.children[char]
        return True
        
        