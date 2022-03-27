package org.linuxlsx.algo.leetcode;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeInOrderTraversal {

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();

        if(root == null){
            return list;
        }

        traversal(root, list);

        return list;
    }

    public void traversal(TreeNode root, List<Integer> list){

        if(root.left != null){
            traversal(root.left, list);
        }

        list.add(root.val);

        if(root.right != null){
            traversal(root.right, list);
        }

    }
}
