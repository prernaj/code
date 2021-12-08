package low.level.truecaller.model.tries;

import java.util.List;

public class ContactTrie {
    private TrieNode root;
    private int indexOfSingleChild;

    public ContactTrie() {
        this.root = new TrieNode("");
    }

    public void insert(String key) {
    }

    public boolean search(String key) {
        return false;
    }

    public List<String> allWordsWithPrefix(String prefix) {
        return null;
    }
    
    public String longestCommonPrefix() {
        return null;
    }

    private int countNumOfChildren(TrieNode trieTrieNode) {
        return 0;
    }

    private void getSuffixes(TrieNode trieNode, String prefix, List<String> allWords) {
    
    }

    public void delete(String key) {
    }


    private boolean deleteHelper(String key, TrieNode currentNode, int length, int level) {
        return false;
    }

}
