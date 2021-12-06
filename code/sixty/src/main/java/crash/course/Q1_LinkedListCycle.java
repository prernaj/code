package crash.course;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/linked-list-cycle/
 * Question
 * Given head, the head of a linked list, determine if the linked list has a cycle in it.
 * There is a cycle in a linked list if there is some node in the list that can be 
 * reached again by continuously following the next pointer. 
 * Internally, pos is used to denote the index of the node that tail's next pointer is connected to. 
 * Note that pos is not passed as a parameter.
 * Return true if there is a cycle in the linked list. Otherwise, return false.
 */
/**
 * Input Output 
 * head = [3,2,0,-4], pos = 1 => true
 * head = [1,2], pos = 0 => true
 * head = [1], pos = -1 => false
 */
/**
 * Thought process
 * Traverse LinkedList frm beginning.
 * store the references to a hashmap.
 * if node's next is already present in hashmap. return true.
 * return false.
 * 
 * Time: O(n)
 * Space: O(n)
 */
/**
 * Improvements
 * use hashSet instead of hashmap.
 * 
 * Floyd's Cycle Finding Algorithm
 * Imagine two runners running on a track at different speed. What happens when the track is actually a circle?
 * The space complexity can be reduced to O(1)O(1) by considering two pointers at different speed - a slow pointer and a fast pointer. 
 * If there is no cycle in the list, the fast pointer will eventually reach the end and we can return false in this case.
 * 
 * Time: O(n)
 * Space: O(1)
 */
public class Q1_LinkedListCycle {
    public boolean hasCycle(ListNode head) {
        Set<ListNode> listNodeMap = new HashSet<>();
        while(head != null) {
            if (listNodeMap.contains(head)) {
                return true;
            }
            listNodeMap.add(head);
            head = head.next;
        }
        return false;
    }

    public boolean hasCycleFloyd(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}