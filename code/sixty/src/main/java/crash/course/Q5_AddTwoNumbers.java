package crash.course;

/**
 * https://leetcode.com/problems/add-two-numbers/
 * Question
 * You are given two non-empty linked lists representing two non-negative integers. 
 * The digits are stored in reverse order, and each of their nodes contains a single digit. 
 * Add the two numbers and return the sum as a linked list.
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 */
/**
 * Input Output
 * l1 = [2,4,3], l2 = [5,6,4] => [7,0,8] {342 + 465 = 807.}
 * l1 = [0], l2 = [0] => [0]
 * l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9] => [8,9,9,9,0,0,0,1]
 * 
 * Time: O(max(m,n))
 * Space: O(max(m,n))
 */
public class Q5_AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode sumNodeHead = null;
        ListNode sumNodeTail = null;
        int carry = 0;
        while (l1 != null || l2 != null || carry == 1) {
            int val1 = l1 == null ? 0 : l1.val;
            int val2 = l2 == null ? 0 : l2.val;
            int sum = val1 + val2 + carry;
            if (sum >= 10) {
                sum = sum-10;
                carry = 1;
            } else {
                carry = 0;
            }
            ListNode newNode = new ListNode(sum);
            if (sumNodeHead == null) {
                sumNodeHead = newNode;
                sumNodeTail = newNode;
            } else {
                sumNodeTail.next = newNode;
                sumNodeTail = newNode;
            }
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }
        return sumNodeHead;
    }
}
