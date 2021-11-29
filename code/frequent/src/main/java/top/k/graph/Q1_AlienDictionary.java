package top.k.graph;

/**
 * There is a new alien language that uses the English alphabet. 
 * However, the order among the letters is unknown to you.
 * You are given a list of strings words from the alien language's dictionary, 
 * where the strings in words are sorted lexicographically by the rules of this new language.
 * Return a string of the unique letters in the new alien language sorted in lexicographically increasing order by the new language's rules. 
 * If there is no solution, return "". 
 * If there are multiple solutions, return any of them.
 * A string s is lexicographically smaller than a string t if at the first letter where they differ, 
 * the letter in s comes before the letter in t in the alien language. 
 * If the first min(s.length, t.length) letters are the same, then s is smaller if and only if s.length < t.length.
 */

/**
 * Input Output
 * words = ["wrt","wrf","er","ett","rftt"]
 * w < r < t
 * w < r < f
 * e < r
 * e < t < t
 * r < f < t < t
 * 
 * w < e < r < f < t
 * e < w < r < f < t
 * 
 * ["z","x"]
 * "zx"
 * 
 * words = ["z","x","z"]
 * ""
 */

/**
 * Thought process
 * - extracting dependency rules from the input.
 * - putting the dependency rules into a graph with letters as nodes and dependency as edges.
 * - topologically sorting the graph nodes.
 */

public class Q1_AlienDictionary {
    
}
