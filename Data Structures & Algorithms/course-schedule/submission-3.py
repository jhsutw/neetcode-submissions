class Solution:
    def canFinish(self, numCourses: int, prerequisites: List[List[int]]) -> bool:
        adj = []

        for _ in range(numCourses):
            adj.append([])

        for course, preq in prerequisites:
            adj[course].append(preq)

        
        states = [0] * numCourses

        def dfs(course):
            if states[course] == 1:
                return False
            if states[course] == 2:
                return True
            
            states[course] = 1
            for preq in adj[course]:
                if not dfs(preq):
                    return False
            
            states[course] = 2
            return True

        for course in range(numCourses):
            if not dfs(course):
                return False

        return True