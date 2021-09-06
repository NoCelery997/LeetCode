//Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例如自动补完和拼
//写检查。 
//
// 请你实现 Trie 类： 
//
// 
// Trie() 初始化前缀树对象。 
// void insert(String word) 向前缀树中插入字符串 word 。 
// boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 
//false 。 
// boolean startsWith(String prefix) 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否
//则，返回 false 。 
// 
//
// 
//
// 示例： 
//
// 
//输入
//["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
//[[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
//输出
//[null, null, true, false, true, null, true]
//
//解释
//Trie trie = new Trie();
//trie.insert("apple");
//trie.search("apple");   // 返回 True
//trie.search("app");     // 返回 False
//trie.startsWith("app"); // 返回 True
//trie.insert("app");
//trie.search("app");     // 返回 True
// 
//
// 
//
// 提示： 
//
// 
// 1 <= word.length, prefix.length <= 2000 
// word 和 prefix 仅由小写英文字母组成 
// insert、search 和 startsWith 调用次数 总计 不超过 3 * 10⁴ 次 
// 
// Related Topics 设计 字典树 哈希表 字符串 👍 847 👎 0

package com.zys.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

/**
* @author zys
* @date 2021-08-20 16:49:27
*/
public class ImplementTriePrefixTree {
    public static void main(String[] args) {
        Trie trie = new ImplementTriePrefixTree().new Trie();
        trie.insert("ab");
        trie.insert("ab");
        trie.insert("abc");
        /*trie.insert("add");
        trie.insert("jam");
        trie.insert("rental");*/
        /*System.out.println(trie.search("apps"));//true
        System.out.println(trie.search("app"));//false
        System.out.println(trie.startsWith("ad"));//true
        System.out.println(trie.search("applepie"));
        System.out.println(trie.search("rest"));
        System.out.println(trie.search("jan"));
        System.out.println(trie.search("rent"));
        System.out.println(trie.search("beer"));
        System.out.println(trie.search("jam"));
        System.out.println(trie.startsWith("apps"));
        System.out.println(trie.startsWith("app"));
        System.out.println(trie.startsWith("ad"));
        System.out.println(trie.startsWith("applepie"));
        System.out.println(trie.startsWith("rest"));
        System.out.println(trie.startsWith("jan"));
        System.out.println(trie.startsWith("rent"));
        System.out.println(trie.startsWith("beer"));
        System.out.println(trie.startsWith("jam"));
*/
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Trie {
    class TreeNode{
        char character;
        TreeNode sibling,child;
        boolean isEnd;
        public TreeNode(){
            isEnd = false;
        }
        public TreeNode(char character){
            this();
            this.character = character;
        }
    }

    private Map<Character, TreeNode> trieMap;

    /** Initialize your data structure here. */
    public Trie() {
        trieMap = new HashMap<>();
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        char[] wordChar = word.toCharArray();
        TreeNode trie = trieMap.get(wordChar[0]);
        if (trie == null) {
            TreeNode newTrieNode = createTrie(wordChar,0);
            trieMap.put(wordChar[0],newTrieNode);
        }else {
            int i = 1;
            TreeNode temp = trie.child;
            //判断是遍历到兄弟节点还是孩子节点
            boolean isChild = true;
            //查找相同的前缀，定位到前缀的最后一个字符
            while (i < wordChar.length && temp != null){
                if (temp.character == wordChar[i]) {
                    ++i;
                    trie = temp;
                    temp = temp.child;
                    isChild = true;
                }else {
                    trie = temp;
                    temp = temp.sibling;
                    isChild = false;
                }
            }
            if (i < wordChar.length){
                TreeNode treeNode = createTrie(wordChar,i);
                if (isChild){
                    trie.child = treeNode;
                }else {
                    trie.sibling = treeNode;
                }
            }
            else {
                trie.isEnd = true;
            }
        }
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        return matches(word.toCharArray(),false);
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        return matches(prefix.toCharArray(),true);
    }

    public boolean matches(char[] target, boolean isPrefixMatch){
        TreeNode treeNode = trieMap.get(target[0]);
        if (treeNode == null) {
            return false;
        }
        int i = 1;
        TreeNode pre = treeNode;
        treeNode = treeNode.child;
        while(i < target.length && treeNode != null){
            pre = treeNode;
            if (treeNode.character == target[i]) {
                ++i;
                treeNode = treeNode.child;
            }else {
                treeNode = treeNode.sibling;
            }
        }
        //i < target.length表示无法匹配
        //isPrefixMatch为true,表示是前缀匹配
        //pre.isEnd表示有单词到这里结束
        return (i >= target.length) && (isPrefixMatch || pre.isEnd);
    }

    public TreeNode createTrie(char[] word, int begin){
        TreeNode head = new TreeNode();
        TreeNode tmp = head;
        while (begin < word.length){
            TreeNode treeNode = new TreeNode(word[begin++]);
            tmp.child = treeNode;
            tmp = tmp.child;
        }
        tmp.isEnd = true;
        return head.child;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
//leetcode submit region end(Prohibit modification and deletion)

}