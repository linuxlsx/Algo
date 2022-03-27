package org.linuxlsx.algo.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeLevelOrderTraversal {

    public List<List<Integer>> levelOrderV2(TreeNode root) {

        List<List<Integer>> lists = new ArrayList<>();
        if (root == null) {
            return lists;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode treeNode = queue.poll();
                level.add(treeNode.val);
                if (treeNode.left != null) {
                    queue.offer(treeNode.left);
                }
                if (treeNode.right != null) {
                    queue.offer(treeNode.right);
                }
            }
            lists.add(level);
        }

        return lists;
    }

    public List<List<Integer>> levelOrder(TreeNode root) {

        List<List<Integer>> lists = new ArrayList<>();
        if (root == null) {
            return null;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        order(queue, lists);

        return lists;
    }

    public void order(Queue<TreeNode> queue, List<List<Integer>> lists) {

        Queue<TreeNode> newQueue = new LinkedList<>();
        List<Integer> list = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode treeNode = queue.poll();
            list.add(treeNode.val);

            if (treeNode.left != null) {
                newQueue.add(treeNode.left);
            }

            if (treeNode.right != null) {
                newQueue.add(treeNode.right);
            }
        }

        lists.add(list);

        if (!newQueue.isEmpty()) {
            order(newQueue, lists);
        }

    }
}
