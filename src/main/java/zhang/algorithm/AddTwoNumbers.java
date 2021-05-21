package zhang.algorithm;

import zhang.utils.Utils;


/**
 * 题目描述
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * 示例：
 *      输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 *      输出：7 -> 0 -> 8
 *      原因：342 + 465 = 807
 * @Descripthon: 两数相加
 * @author: MrZhang
 * @date: 2021/4/19 16:46
 */
public class AddTwoNumbers {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(2, new ListNode(4, new ListNode(3)));
        ListNode node2 = new ListNode(5, new ListNode(6, new ListNode(4)));
        do {
            Utils.print(String.valueOf(node1.val));
            node1 = node1.next;
        }while (node1.next != null);

    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2){
        ListNode dummyHead = new ListNode();
        ListNode cur = dummyHead;
        int carry = 0;
        return null;
    }


    public static class ListNode {
       int val;
       ListNode next;
       ListNode() {}
       ListNode(int val) { this.val = val; }
       ListNode(int val, ListNode next) { this.val = val; this.next = next; }
   }
}
