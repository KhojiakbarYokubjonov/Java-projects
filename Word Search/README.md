# Word Search
- This program implements a word search on the given grid that contains 
individual letters represented as Strings.
- A word can occur as any path from the first
letter to the last where a step in the path can go up, right, down, or left. (No diagonals).
- it is possible for the same letter to be used more than once in the same word.
- Uses a BFS algorithm to find the first character in the word. Then, it uses a DFS
to search for the word itself.
- The output for this search will be a String representation of the locations in the path
as shown below.
<br>
Examples: <br/><br/>

```
ARIZONA: (0, 0)(1, 0)(2, 0)(2, 1)(1, 1)(0, 1)(0, 0)
UNIVERSITY: (6, 3)(5, 3)(5, 4)(5, 5)(6, 5)(7, 5)(7, 4)(7, 3)(8, 3)(8, 4)
WILDCATS: (9, 0)(8, 0)(8, 1)(7, 1)(7, 2)(8, 2)(9, 2)(9, 1)
```
