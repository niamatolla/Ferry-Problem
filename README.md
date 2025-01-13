This repository contains the implementation of the Ferry Loading Problem, developed as part of the CSI2510: 
Data Structures and Algorithms course during Fall 2024 at the University of Ottawa.

Big Table Approach:
Uses a 2D array (state) for backtracking with memoization.
Results:
Execution Time: 810ms for large inputs.
Memory Usage: High due to the large table size.

Hash Table Approach:
Replaces the 2D table with an efficient hash table for memoization.
Results:
Execution Time: 350ms (57% faster).
Memory Usage: Optimized by storing only necessary states.
