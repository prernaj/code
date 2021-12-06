package crash.course;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/linked-list-cycle-ii/
 * Question
 * Given the head of a linked list, return the node where the cycle begins. 
 * If there is no cycle, return null.
 * There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. 
 * Internally, pos is used to denote the index of the node that tail's next pointer is connected to (0-indexed). 
 * It is -1 if there is no cycle. 
 * Note that pos is not passed as a parameter.
 * Do not modify the linked list.
 */
/**
 * Input Output
 * head = [3,2,0,-4], pos = 1 => 1
 * head = [1,2], pos = 0 => 0
 * head = [1], pos = -1 => no cycle
 */
/**
 * Thought process
 * the point where fast and slow pointer meet is the start of the cycle. Wrong.
 */
/**
 * Improvements.
 * If we keep track of the nodes that we've seen already in a Set, we can traverse the list and return the first duplicate node.
 * Time: O(n)
 * Space: O(n)
 * 
 * Floyd's Tortoise and Hare
 * To compute the intersection point, let's note that the hare has traversed twice as many nodes as the tortoise, 
 * i.e. 
 * 2d(tortoise) = d(hare), that means
 * 2(F+a)=F+nC+a, where nn is some integer.
 * 
 * Hence the coordinate of the intersection point is F + a = nCF+a=nC.
 * F+a = nC
 * 
 * To do so, we initialize two more pointers: ptr1, which points to the head of the list, and ptr2, which points to the intersection.
 * Then, we advance each of them by 1 until they meet; the node where they meet is the entrance to the cycle, so we return it.
 * since, F + a = nC
 * and b + a = nC
 * F: distance from start to start of cycle
 * a: distance from start of cycle to meeting point.
 * b: distance from meeting point to start of cycle.
 * 
 * Time: O(n)
 * Space: O(1)
 * 
 */
public class Q2_LinkedListCycle2 {
    public ListNode detectCycle(ListNode head) {
        Set<ListNode> visited = new HashSet<>();
        while(head != null) {
            if (visited.contains(head)) {
                return head;
            }
            visited.add(head);
            head = head.next;
        }
        return null;
    }

    public ListNode detectCycle2(ListNode head) {
        if (head == null) {
            return null;
        }

        // If there is a cycle, the fast/slow pointers will intersect at some
        // node. Otherwise, there is no cycle, so we cannot find an entrance to
        // a cycle.
        ListNode intersect = getIntersect(head);
        if (intersect == null) {
            return null;
        }

        // To find the entrance to the cycle, we have two pointers traverse at
        // the same speed -- one from the front of the list, and the other from
        // the point of intersection.
        ListNode ptr1 = head;
        ListNode ptr2 = intersect;
        while (ptr1 != ptr2) {
            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
        }

        return ptr1;
    }

    private ListNode getIntersect(ListNode head) {
        ListNode tortoise = head;
        ListNode hare = head;

        // A fast pointer will either loop around a cycle and meet the slow
        // pointer or reach the `null` at the end of a non-cyclic list.
        while (hare != null && hare.next != null) {
            tortoise = tortoise.next;
            hare = hare.next.next;
            if (tortoise == hare) {
                return tortoise;
            }
        }
        return null;
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


