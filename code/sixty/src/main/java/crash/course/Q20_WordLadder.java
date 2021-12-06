package crash.course;

import java.util.List;

/**
 * https://leetcode.com/problems/word-ladder/
 * Question
 * A transformation sequence from word beginWord to word endWord 
 * using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:
 * Every adjacent pair of words differs by a single letter.
 * Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
 * sk == endWord
 * Given two words, beginWord and endWord, and a dictionary wordList, 
 * return the number of words in the shortest transformation sequence from beginWord to endWord, or 0 if no such sequence exists.
 */
/**
 * Input Output
 * beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"] => 5
 * {"hit", "hot", "dot", "dog", "cog"}
 * beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"] => 0 (since endword is not present in wordList)
 */
/**
 * Thought process
 * !!
 * Hint: Let startWord and endWord be the start and end node of the graph.
 * for each word pair in the word list create a edge with the edge weight as the difference.
 * keep only those edges that have weight = 1
 * start traversing from the startWord. mark nodes as visited. also increase counter.
 * if reaching endNode. return the counter.
 * else return -1
 * 
 * Time: O(N^2) where N is the number of words in wordList. to build the graph
 * Graph traversal: O(E) where E is the number of edges.
 * Space: 
 */
/**
 * Improvements
 * One of the most important step here is to figure out how to find adjacent nodes i.e. words which differ by one letter. 
 * To efficiently find the neighboring nodes for any given word we do some pre-processing on the words of the given wordList.
 * The pre-processing involves replacing the letter of a word by a non-alphabet say, *.
 * hot => *ot, h*t, ho*
 * This pre-processing helps to form generic states to represent a single letter change.
 * Both Dog and Dig map to the same intermediate or generic state D*g.
 * The preprocessing step helps us find out the generic one letter away nodes for any word of the word list and hence making it easier and quicker to get the adjacent nodes. 
 * Otherwise, for every word we will have to iterate over the entire word list and find words that differ by one letter. 
 * That would take a lot of time. 
 * This preprocessing step essentially builds the adjacency list first before beginning the breadth first search algorithm.
 * 
 * BFS.
 * Start from beginWord and search the endWord using BFS.
 * 
 * Algorithm:
 * 1. Do the pre-processing on the given wordList and find all the possible generic/intermediate states. 
 * Save these intermediate states in a dictionary with key as the intermediate word and value as the list of words which have the same intermediate word.
 * 2. Push a tuple containing the beginWord and 1 in a queue. 
 * The 1 represents the level number of a node. 
 * We have to return the level of the endNode as that would represent the shortest sequence/distance from the beginWord.
 * 3. To prevent cycles, use a visited dictionary.
 * 4. While the queue has elements, get the front element of the queue. Let's call this word as current_word.
 * 5. Find all the generic transformations of the current_word and 
 * find out if any of these transformations is also a transformation of other words in the word list. 
 * This is achieved by checking the all_combo_dict.
 * 6. The list of words we get from all_combo_dict are all the words which have a common intermediate state with the current_word. 
 * These new set of words will be the adjacent nodes/words to current_word and hence added to the queue.
 * 7. Hence, for each word in this list of intermediate words, append (word, level + 1) into the queue where level is the level for the current_word.
 * 8. Eventually if you reach the desired word, its level would represent the shortest transformation sequence length.
 * 
 * Yet to visualize this algorithm and code.
 * 
 * Time: O(m^2*n): m: length of word, n: number of words
 * Space: O(m^2*n)
 * 
 * To be continued...
 */
public class Q20_WordLadder {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        return 0;
    }
}
