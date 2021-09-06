package com.zys.leetcode.editor.cn.util;

import com.zys.leetcode.editor.cn.entity.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author zys
 * @date 2021/8/3
 * @description
 */
public class TreeNodeUtil {
    public static TreeNode arrayToTreeNode(Integer[] array){
        if(array.length == 0){
            return null;
        }
        TreeNode root = new TreeNode(array[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean isLeft = true;
        for(int i = 1; i < array.length; i++){
            TreeNode node = queue.peek();
            if(isLeft){
                if(array[i] != null){
                    node.left = new TreeNode(array[i]);
                    queue.offer(node.left);
                }
                isLeft = false;
            }else {
                if(array[i] != null){
                    node.right = new TreeNode(array[i]);
                    queue.offer(node.right);
                }
                queue.poll();
                isLeft = true;
            }
        }
        return root;
    }
    public static Integer[] treeNodeToArray(TreeNode root){
        if (root == null) {
            return new Integer[]{};
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        List<Integer> result = new ArrayList<Integer>();
        TreeNode node;
        Integer value;
        while (!queue.isEmpty()) {
            node = queue.poll();
            value = node.val;
            result.add(value);
            if (node.left != null) {
                queue.offer(node.left);
            }
            else {
                result.add(null);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
            else {
                result.add(null);
            }
        }
        result.remove(result.size() -1);
        result.remove(result.size() - 1);
        return result.toArray(new Integer[result.size()]);
    }
}
