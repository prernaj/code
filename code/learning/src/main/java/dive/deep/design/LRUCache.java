package dive.deep.design;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/lru-cache/
 * Question
 * Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
 * Implement the LRUCache class:
 * LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
 * int get(int key) Return the value of the key if the key exists, otherwise return -1.
 * void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. 
 * If the number of keys exceeds the capacity from this operation, evict the least recently used key.
 * The functions get and put must each run in O(1) average time complexity.
 */
/**
 * Input and Output
 * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"] [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
 * [null, null, null, 1, null, -1, null, -1, 3, 4]
 */
/**
 * Thought process
 * Ordered dictionary.
 * We're asked to implement the structure which provides the following operations in \mathcal{O}(1)O(1) time :
 * Get the key / Check if the key exists
 * Put the key
 * Delete the first added key
 * The first two operations in \mathcal{O}(1)O(1) time are provided by the standard hashmap, and the last one - by linked list.
 * There is a structure called ordered dictionary, it combines behind both hashmap and linked list.
 * 
 * Time: O(1)
 * since all operations with ordered dictionary : get/in/set/move_to_end/popitem (get/containsKey/put/remove) are done in a constant time.
 * Space: O(capacity)
 * since the space is used only for an ordered dictionary with at most capacity + 1 elements.
 * 
 * DLL + HashMap
 * https://leetcode.com/problems/lru-cache/discuss/45911/Java-Hashtable-%2B-Double-linked-list-(with-a-touch-of-pseudo-nodes)
 * One advantage of double linked list is that the node can remove itself without other reference. 
 * In addition, it takes constant time to add and remove nodes from the head or tail.
 * One particularity about the double linked list implemented here is that there are pseudo head and pseudo tail to mark the boundary, 
 * so that we don't need to check the null node during the update.
 * 
 * Time: O(1) for put and get
 * Space: O(capacity)
 */
class LRUCache extends LinkedHashMap<Integer, Integer>{
    private int capacity;
    
    public LRUCache(int capacity) {
        super(capacity, 0.75F, true);
        this.capacity = capacity;
    }

    public int get(int key) {
        return super.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        super.put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > capacity; 
    }
}

class LRUCacheDLL {

    class DLinkedNode {
      int key;
      int value;
      DLinkedNode prev;
      DLinkedNode next;
    }
  
    private void addNode(DLinkedNode node) {
      /**
       * Always add the new node right after head.
       */
      node.prev = head;
      node.next = head.next;
  
      head.next.prev = node;
      head.next = node;
    }
  
    private void removeNode(DLinkedNode node){
      /**
       * Remove an existing node from the linked list.
       */
      DLinkedNode prev = node.prev;
      DLinkedNode next = node.next;
  
      prev.next = next;
      next.prev = prev;
    }
  
    private void moveToHead(DLinkedNode node){
      /**
       * Move certain node in between to the head.
       */
      removeNode(node);
      addNode(node);
    }
  
    private DLinkedNode popTail() {
      /**
       * Pop the current tail.
       */
      DLinkedNode res = tail.prev;
      removeNode(res);
      return res;
    }
  
    private Map<Integer, DLinkedNode> cache = new HashMap<>();
    private int size;
    private int capacity;
    private DLinkedNode head, tail;
  
    public LRUCache(int capacity) {
      this.size = 0;
      this.capacity = capacity;
  
      head = new DLinkedNode();
      // head.prev = null;
  
      tail = new DLinkedNode();
      // tail.next = null;
  
      head.next = tail;
      tail.prev = head;
    }
  
    public int get(int key) {
      DLinkedNode node = cache.get(key);
      if (node == null) return -1;
  
      // move the accessed node to the head;
      moveToHead(node);
  
      return node.value;
    }
  
    public void put(int key, int value) {
      DLinkedNode node = cache.get(key);
  
      if(node == null) {
        DLinkedNode newNode = new DLinkedNode();
        newNode.key = key;
        newNode.value = value;
  
        cache.put(key, newNode);
        addNode(newNode);
  
        ++size;
  
        if(size > capacity) {
          // pop the tail
          DLinkedNode tail = popTail();
          cache.remove(tail.key);
          --size;
        }
      } else {
        // update the value.
        node.value = value;
        moveToHead(node);
      }
    }
  }