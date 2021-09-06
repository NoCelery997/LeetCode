package com.zys.leetcode.editor.cn.util;

import com.zys.leetcode.editor.cn.entity.ListNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zys
 * @date 2021/8/5
 * @description
 */
public class ListNodeUtil {
    public static void main(String[] args) {
        ListNode node = ListNodeUtil.arrayToCircleLinkList(new Integer[]{1,2},0);
        Arrays.stream(ListNodeUtil.linkListToArray(node)).forEach(val ->{
            System.out.print(val + " ");
        });
    }

    public static ListNode arrayToLinkList(Integer[] array){
        if (array.length <= 0) {
            return null;
        }
        ListNode head = new ListNode(array[0]);
        ListNode p = head;
        for (int i = 1; i < array.length; i++) {
            p.next = new ListNode(array[i]);
            p = p.next;
        }
        return head;
    }

    public static Integer[] linkListToArray(ListNode head){
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        Integer[] result = list.toArray(new Integer[list.size()]);
        return result;
    }

    public static ListNode arrayToCircleLinkList(Integer[] array, int pos){
        if (array.length <= 0) {
            return null;
        }
        ListNode head = new ListNode(array[0]);
        ListNode p = head;
        ListNode q = new ListNode();
        for (int i = 1; i < array.length; i++) {
            p.next = new ListNode(array[i]);
            if (pos == i - 1) {
                q = p;
            }
            p = p.next;
        }
        if (pos != -1) {
            p.next = q;
        }
        return head;

    }
}
