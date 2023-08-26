# Travelling Salesman Problem
This program implements some basic solutions to the traveling salesman problem
and analyzes their performance. it uses a weighted graph (DGraph) to represent 
cities and their distances.

Implented solutions:
```
    HEURISTIC algorithm
    BACKTRACKING algorithm
    a variation of the two
```

The graph is stored in a file and the filename is given to the program as a
command line argument. The input files are in the matrix market (.mtx) format. 
An example input file is:
```
%%MatrixMarket matrix coordinate real general
3 3 6
1 2 1.0
2 1 2.0
1 3 3.0
3 1 4.0
2 3 5.0
3 2 6.0
```
Also provided on the command line is a directive to indicate which algorithm should be used to
solve the TSP and when timing should be performed.

The output for the TIME command will look as follows:
```
heuristic: cost = 935.3299999999999, 0 milliseconds
mine: cost = 935.3299999999999, 0 milliseconds
backtrack: cost = 835.8799999999999, 5 milliseconds
```
