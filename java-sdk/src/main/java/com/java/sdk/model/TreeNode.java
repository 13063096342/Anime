package com.java.sdk.model;

import lombok.Data;

/**
 * @author chenfh
 * @date 2021-07-20 11:20
 */
@Data
public class TreeNode {
    TreeNode left;
    TreeNode right;
    int value;

    TreeNode() {
    }

    TreeNode(int value) {
        this.value = value;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.value = val;
        this.left = left;
        this.right = right;
    }
}
