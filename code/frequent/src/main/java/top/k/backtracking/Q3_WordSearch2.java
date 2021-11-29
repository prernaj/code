package top.k.backtracking;

/**
 * Question
 * Given an m x n board of characters and a list of strings words, return all words on the board.
 * Each word must be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. 
 * The same letter cell may not be used more than once in a word.
 */

/**
 * Input Output
 * board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]], words = ["oath","pea","eat","rain"]
 * ["eat","oath"]
 * 
 * board = [["a","b"],["c","d"]], words = ["abcb"]
 * []
 */

/**
 * Thought Process
 * Iterate the cell one by one. From each cell walk along the four directions. 
 * While moving, we would stop the exploration when we know it would not lead to the discovery of new words.
 * 
 * During the backtracking process, need to tell if there exists any word that contains certain prefix.
 * Use Trie DS to find matching prefix.
 * 
 */
public class Q3_WordSearch2 {
    
}
