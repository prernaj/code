package crash.course;

/**
 * https://leetcode.com/problems/remove-duplicates-from-sorted-list/
 * Question
 * Given the head of a sorted linked list, delete all duplicates such that each element appears only once. Return the linked list sorted as well.
 */
/**
 * Input Output
 * head = [1,1,2] => [1,2]
 * head = [1,1,2,3,3] => [1,2,3]
 */
/**
 * Thought process
 * traver the list.
 * if node's value is equal to previous node, remove that node.
 * 
 * Time: O(n)
 * Space: O(1)
 */
/**
 * Improvements
 * Because the input list is sorted, we can determine if a node is a duplicate by comparing its value to the node after it in the list. 
 * If it is a duplicate, we change the next pointer of the current node so that it skips the next node and points directly to the one after the next node.
 * 
 * You thought it correct.
 * 
 */
public class Q3_RemoveDuplicatesFromSortedList {
    public ListNode deleteDuplicatesMy(ListNode head) {
        ListNode prev = null;
        ListNode node = head;
        while(node != null) {
            if (prev != null) {
                if (prev.val == node.val) {
                    if (prev == head) {
                        head.next = node.next;
                    }
                    prev.next = node.next;
                } else {
                    prev = prev.next;
                }
            } else {
                prev = head;
            }
            node = node.next;
        }
        return head;
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
