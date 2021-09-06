package com.zys.leetcode.editor.cn.entity;

/**
 * @author zys
 * @date 2021/8/5
 * @description
 */
public class ListNode {
    public int val;
    public ListNode next;
    public ListNode(){

    }
    public ListNode(int x) {
        val = x;
        next = null;
    }
    public ListNode(int x ,ListNode next){
        this.val = x;
        this.next = next;
    }
}
