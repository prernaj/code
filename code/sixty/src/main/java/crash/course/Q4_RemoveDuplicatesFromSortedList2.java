package crash.course;

import java.util.ArrayList;

/**
 * https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/description/
 * Question
 * Given the head of a sorted linked list, delete all nodes that have duplicate numbers, 
 * leaving only distinct numbers from the original list. 
 * Return the linked list sorted as well.
 */
/**
 * Input Output
 * head = [1,2,3,3,4,4,5] => [1,2,5]
 * head = [1,1,1,2,3] => [2,3]
 */
/**
 * Thought Process
 * Create a countmap.
 * Create a new map only with elements whose value is 1.
 * 
 * Time: O(n)
 * Space: O(n)
 * 
 * first remove all duplicate next nodes. then delete itself.
 * Time: O(n)
 * Space: O(1)
 * 
 * coded. but it became too complex.
 */
/**
 * Improvements.
 * Sentinel head + Predecessor
 * the most challenging situation: the list head is to be removed.
 * Sentinel nodes are widely used for trees and linked lists as pseudo-heads, pseudo-tails, etc. 
 * They are purely functional and usually don't hold any data. 
 * Their primary purpose is to standardize the situation to avoid edge case handling.
 * 
 * Time: O(n)
 * Space: O(1)
 */
public class Q4_RemoveDuplicatesFromSortedList2 {
    public ListNode deleteDuplicatesMy(ListNode head) {
        ListNode prev = null;
        ListNode node = head;
        ArrayList<Integer> duplicateNodes = new ArrayList<>();
        while (node != null) {
            if (prev != null) {
                Boolean flag = false;
                while (prev.val == node.val) {
                    prev.next = node.next;
                    flag = true;
                } 
                if (flag) {
                    // remove prev
                    duplicateNodes.add(prev.val);
                }
            } else {
                prev = head;
            }
            node = node.next;
        }
        node = head;
        prev = null;
        while(node != null) {
            if (duplicateNodes.contains(node.val)) {
                if (prev == null) {
                    head = node.next;
                    prev = head;
                } else {
                    prev.next = prev.next.next;
                    prev = prev.next;
                }
            }
        }
        return head;
    }

    public ListNode deleteDuplicates(ListNode head) {
        ListNode sentinel = new ListNode(0, head);
        ListNode pred = sentinel;
        while(head != null) {
            if (head.next != null && head.val == head.next.val) {
                while (head.next != null && head.val == head.next.val) {
                    head = head.next;    
                }
                pred.next = head.next;
            } else {
                pred = pred.next;    
            }
            head = head.next;  
        }
        return sentinel.next;
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode() {

    }
    ListNode(int val) { 
        this.val = val; 
    }
    ListNode(int val, ListNode next) { 
        this.val = val; 
        this.next = next; 
    }
}
