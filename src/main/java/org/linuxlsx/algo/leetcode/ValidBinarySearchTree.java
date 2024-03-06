package org.linuxlsx.algo.leetcode;

import java.util.PriorityQueue;

public class ValidBinarySearchTree {

    public boolean isValidBST(TreeNode root) {

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);

       return valid(root, Long.MIN_VALUE, Long.MAX_VALUE);

    }

    public boolean valid(TreeNode root, Long min, Long max) {

        if (root == null) {
            return true;
        }

        if (min != null && root.val <= min) {
            return false;
        }

        if (max != null && root.val >= max) {
            return false;
        }

        return valid(root.left, min, (long) root.val) && valid(root.right, (long) root.val, max);

    }
}
