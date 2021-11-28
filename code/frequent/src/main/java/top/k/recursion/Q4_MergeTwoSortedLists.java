package top.k.recursion;

/**
 * Question
 * You are given the heads of two sorted linked lists list1 and list2.
 * Merge the two lists in a one sorted list. 
 * The list should be made by splicing together the nodes of the first two lists.
 * Return the head of the merged linked list.
 */

/**
 * Input Qoutput
 * [1,2,4] and [1,3,4] => [1,1,2,3,4,4]
 * [] and [] => []
 * [] and [0] => [0]
 */

/**
 * Thought process
 * Recursion
 * list1[0] + merge(list[1:], list2) if list1[0] <list2[0]
 * list2[0] + merge(list1, list2[1:]) otherwise
 * 
 * The smaller of the two heads plus the result of a merge on the rest of the elements.
 * 
 * Time: O(m+n). Because each recursive call increments the pointer to l1 or l2 by one.
 * Space: O(m+n). The first call to mergTwoLists will not end until the ends of both l1 and l2 have been reached
 * 
 * Iteration
 * Assuming l1 is smaller than l2. Processing the elements one by one, inserting elements of l2 in necessary positions of l1.
 * Algo:
 * - setup a prehead that allows us to return the head of the merged list later.
 * - maintain a prev pointer, which points to the current node for which we are considering adjusting its next pointer.
 * 
 * Time: O(m+n)
 * Space: O(1)
 * 
 */
public class Q4_MergeTwoSortedLists { 
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }

    public ListNode mergeTwoListsIterative(ListNode l1, ListNode l2) {
        ListNode prehead = new ListNode(-1);
        ListNode prev = prehead;
        while(l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }
        prev.next = l1 == null : l2 : l1;
        return prehead.next;
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
