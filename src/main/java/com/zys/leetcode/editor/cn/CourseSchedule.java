//你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。 
//
// 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表
//示如果要学习课程 ai 则 必须 先学习课程 bi 。 
//
// 
// 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。 
// 
//
// 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。 
//
// 
//
// 示例 1： 
//
// 
//输入：numCourses = 2, prerequisites = [[1,0]]
//输出：true
//解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。 
//
// 示例 2： 
//
// 
//输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
//输出：false
//解释：总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。 
//
// 
//
// 提示： 
//
// 
// 1 <= numCourses <= 105 
// 0 <= prerequisites.length <= 5000 
// prerequisites[i].length == 2 
// 0 <= ai, bi < numCourses 
// prerequisites[i] 中的所有课程对 互不相同 
// 
// Related Topics 深度优先搜索 广度优先搜索 图 拓扑排序 
// 👍 908 👎 0

package com.zys.leetcode.editor.cn;

import com.zys.leetcode.editor.cn.util.ArrayUtil;

/**
* @author zys
* @date 2021-08-18 15:32:53
*/
public class CourseSchedule {
    public static void main(String[] args) {
        Solution solution = new CourseSchedule().new Solution();
        int[][] array = ArrayUtil.stringToTwoDimenArray("[1,0]");
        System.out.println(solution.canFinish(2, array));
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[][] graph = new int[numCourses][numCourses];
        int[] degree = new int[numCourses];
        for (int i = 0; i < prerequisites.length; i++) {
            graph[prerequisites[i][0]][prerequisites[i][1]] = 1;
            degree[prerequisites[i][1]]++;
        }
        topological(graph,degree);
        for (int i = 0; i < numCourses; i++) {
            if (degree[i] > 0) {
                return false;
            }
        }
        return true;
    }

    public void topological(int[][] graph, int[] degree){
        int len = degree.length;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (degree[j] == 0) {
                    degree[j]--;
                    for (int k = 0; k < degree.length; k++) {
                        if (graph[j][k] == 1) {
                            degree[k]--;
                        }
                    }
                    break;
                }
            }
        }
    }

    }
//leetcode submit region end(Prohibit modification and deletion)

}