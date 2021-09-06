//给你二叉树的根结点 root ，请你将它展开为一个单链表： 
//
// 
// 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。 
// 展开后的单链表应该与二叉树 先序遍历 顺序相同。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [1,2,5,3,4,null,6]
//输出：[1,null,2,null,3,null,4,null,5,null,6]
// 
//
// 示例 2： 
//
// 
//输入：root = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：root = [0]
//输出：[0]
// 
//
// 
//
// 提示： 
//
// 
// 树中结点数在范围 [0, 2000] 内 
// -100 <= Node.val <= 100 
// 
//
// 
//
// 进阶：你可以使用原地算法（O(1) 额外空间）展开这棵树吗？ 
// Related Topics 栈 树 深度优先搜索 链表 二叉树 
// 👍 871 👎 0

package com.zys.leetcode.editor.cn;

import com.zys.leetcode.editor.cn.entity.TreeNode;
import com.zys.leetcode.editor.cn.util.TreeNodeUtil;

import java.util.Arrays;

public class FlattenBinaryTreeToLinkedList {
    public static void main(String[] args) {
        Solution solution = new FlattenBinaryTreeToLinkedList().new Solution();
        TreeNode root = TreeNodeUtil.arrayToTreeNode(new Integer[]{1, 2, 5, 3, 4, null, 6});
        solution.flatten(root);
        Arrays.stream(TreeNodeUtil.treeNodeToArray(root)).forEach(val ->{
            System.out.print(val + " ");
        });

    }
    //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
*/
class Solution {
    public void flatten(TreeNode root) {

        //添加工具类，由数组生成二叉树，方便调试

        if (root == null) {
            return;
        }
        if (root.left != null) {
            flatten(root.left);
        }
        TreeNode right = root.right;
        TreeNode left = root.left;
        if (left != null) {
            root.right = left;
            root.left = null;
            while (left.right != null) {
                left = left.right;
            }
        }else {
            left = root;
        }
        if (right != null) {
            left.right = right;
            flatten(right);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}