package crash.course;

/**
 * https://leetcode.com/problems/reverse-linked-list/
 * Question
 * Given the head of a singly linked list, reverse the list, and return the reversed list.
 */
/**
 * Input Output
 * head = [1,2,3,4,5] => [5,4,3,2,1]
 * head = [1,2] => [2,1]
 * head = [] => []
 */
/**
 * Thought process
 * traverse the linked from beginning with a prev, curr, next pointers.
 * Time: O(n)
 * Space: O(1)
 * 
 * Details:
 * While you are traversing the list, change the current node's next pointer to point to its previous element. 
 * Since a node does not have reference to its previous node, you must store its previous element beforehand. 
 * You also need another pointer to store the next node before changing the reference. 
 * Do not forget to return the new head reference at the end!
 */
/**
 * Recursion
 * to do later..
 */
public class Q7_ReverseLinkedList {
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }
}
