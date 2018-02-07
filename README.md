#FVS_BF

This implementation of Feedback vertex Set problem with Brute force approach is to understand the improvement done by parameterized version (https://github.com/feldsherov/pace2016/) implemented by Svyatoslav Feldsherov (Moscow State University) in the PACE2016 challange (https://pacechallenge.wordpress.com/pace-2016/track-b-feedback-vertex-set/).

#Input:
    list of edges
#Output:
    list of vertices in minimum feedback vertex set

#Algorithm:
    Store all the edges in edgestore
    For each combination of vertex-set (in increasing order of size):
        reduce the graph by removing all edges adjecent to the selected combination subset
        check acyclicity of that reduced graph with help of union-find algorithm
        if reduced graph is acyclic
            the current combination of vertex set is the minimum vertex set.
            return that subset and terminate program

#Running Time:
    Let say input has E edges and V vertices,
    Graph reading takes O(E) time.
    For subset creation O(2^N) time. O(V^k) is time for k-sub algorithm and V is for all possibility of length of set
        In isAcyclic() function:
            copy making of edgelist takes O(E) time
            cyclicity checking takes O(E*log(V)) time. (for each edge in edgelist copy, verify by union find algorithm)

So total time complexity will be O(2^N*E*log(V))

Here we used union-find algorithm by path compression and by rank, so it gives the better time then original O(V) time logic.

#put your all testcases in 'testcases' folder with '*.graph' format.

#give permission to buil.sh and run.sh on your computer by following command.
    chmod +x build.sh
    chmod +x run.sh

#now build program with,
    ./build.sh

#to run you have two options
    if you want to run only sigle testcase, comment second script block in run.sh as shown there, and on terminal type,
    ./run.sh filename.graph

    if you want to run multiple testcases automatically, comment first option there, uncomment second script block and on terminal type,
    ./run.sh# Feedback-Vertex-Set
