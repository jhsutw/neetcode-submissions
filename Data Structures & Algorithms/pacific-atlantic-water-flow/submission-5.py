class Solution:
    def pacificAtlantic(self, heights: List[List[int]]) -> List[List[int]]:
        rows = len(heights)
        cols = len(heights[0])

        pacific = set()
        atlantic = set()

        result = []

        def dfs (r, c, visited, prev_height):
            if r < 0 or r >= rows or c < 0 or c >= cols:
                return
            
            if (r, c) in visited:
                return

            if heights[r][c] < prev_height:
                return

            visited.add((r, c))

            dfs(r - 1, c, visited, heights[r][c])
            dfs(r + 1, c, visited, heights[r][c])
            dfs(r, c - 1, visited, heights[r][c])
            dfs(r, c + 1, visited, heights[r][c])

        for col in range(cols):
            dfs(0, col, pacific, 0)
            dfs(rows - 1, col, atlantic, 0)

        for row in range(rows):
            dfs(row, 0, pacific, 0)
            dfs(row, cols - 1, atlantic, 0)

        for r in range(rows):
            for c in range(cols):
                if (r, c) in pacific and (r, c) in atlantic:
                    result.append([r, c])

        return result

        



        