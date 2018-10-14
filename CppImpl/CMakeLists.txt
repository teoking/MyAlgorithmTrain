cmake_minimum_required(VERSION 3.12)
project(CppImpl)

set(CMAKE_CXX_STANDARD 98)

include_directories(/usr/local/include)
add_executable(CppImpl main.cpp)

# Number Box
add_executable(number-box number-box/Main.cpp)

# Binary Search Tree
add_executable(binary-search-tree binary-search-tree/Main.cpp)

# Re-Encode
add_executable(re-encode re-encode/Main.cpp)

# Score sort
add_executable(score-sort score-sort/Main.cpp)

# Sorting Algorithm
add_executable(sorting sorting/Main.cpp)

# Card Play
add_executable(card card/Main.cpp)

# Frog
add_executable(frog frog/Main.cpp)

# NearestPP
add_executable(nearest-point-pair nearest-point-pair/Main.cpp)

# Knapsack2
add_executable(knapsack2 knapsack2/Main.cpp)