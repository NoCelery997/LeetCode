//给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。 
//
// 进阶： 
//
// 
// 你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？ 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：head = [4,2,1,3]
//输出：[1,2,3,4]
// 
//
// 示例 2： 
//
// 
//输入：head = [-1,5,3,4,0]
//输出：[-1,0,3,4,5]
// 
//
// 示例 3： 
//
// 
//输入：head = []
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点的数目在范围 [0, 5 * 104] 内 
// -105 <= Node.val <= 105 
// 
// Related Topics 链表 双指针 分治 排序 归并排序 
// 👍 1249 👎 0

package com.zys.leetcode.editor.cn;

import com.zys.leetcode.editor.cn.entity.ListNode;
import com.zys.leetcode.editor.cn.util.ListNodeUtil;

import java.util.Arrays;

public class SortList {
    public static void main(String[] args) {
        Solution solution = new SortList().new Solution();
        ListNode head = ListNodeUtil.arrayToLinkList(new Integer[]{1,2,3,4});
        solution.sortList(head);
        Arrays.stream(ListNodeUtil.linkListToArray(head)).forEach(n ->{
            System.out.print(n + " ");
        });

    }
    //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = new ListNode();
        newHead.next = head;
        ListNode p = head;
        ListNode pre = newHead;
        ListNode q = head;
        while (q.next != null) {
            p = p.next;
            pre = pre.next;
            q = q.next;
            if (q.next != null) {
                q = q.next;
            }
        }
        pre.next = null;
        ListNode left = sortList(head);
        ListNode right = sortList(p);
        q = newHead;
        while (left != null && right != null) {
            if (left.val < right.val) {
                newHead.next = left;
                left = left.next;
            }else {
                newHead.next = right;
                right = right.next;
            }
            newHead = newHead.next;
        }
        while (left != null) {
            newHead.next = left;
            newHead = newHead.next;
            left = left.next;
        }
        while (right != null) {
            newHead.next = right;
            newHead = newHead.next;
            right = right.next;
        }
        return q.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}