package com.example.justlhyutils.datastruct.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * description:
 *
 * @author liuhuayang
 * date: 2024/3/19 09:18
 */
public class DoubleTree {

    public static void main(String[] args) {
        TreeNode head = initTree();
//        printFrontTree(head);
//        printCenterTree(head);
//        printAfterTree(head);
//        printTree(head);
         reverseTree(head);
         printTree(head);
    }

    private static void printTree(TreeNode head) {
        LinkedList<TreeNode> linkedList = new LinkedList<>();
        linkedList.add(head);
        boolean left = true;
        System.out.println(head.value);
        while (linkedList.size() != 0) {
            TreeNode first = linkedList.getFirst();
            if (left) {
                TreeNode leftNode = first.getLeftNode();
                if (leftNode != null) {
                    System.out.println(leftNode.getValue());
                    linkedList.add(leftNode);
                }
            } else {
                TreeNode rightNode = first.getRightNode();
                if (rightNode != null) {
                    System.out.println(rightNode.getValue());
                    linkedList.add(rightNode);
                }
                linkedList.removeFirst();
            }
            left = !left;
        }
        //1 2 3 4 5 6 7 9 10 11 12
    }

    private static void printAfterTree(TreeNode head) {
        if(head==null){
            return;
        }
        printAfterTree(head.leftNode);
        printAfterTree(head.rightNode);
        System.out.println(head.value);
        //9 4 10 11 5 2 12 6 7 3 1
    }

    private static void printCenterTree(TreeNode head) {
        if(head==null){
            return;
        }
        printCenterTree(head.leftNode);
        System.out.println(head.value);
        printCenterTree(head.rightNode);
        // 4 9 2 10 5 11 1 12 6 3 7
    }

    private static void printFrontTree(TreeNode head) {
        if(head==null){
            return;
        }
        System.out.println(head.value);
        printFrontTree(head.leftNode);
        printFrontTree(head.rightNode);
        // 1 2 4 9 5 10 11 3 6 12 7
    }

    private static void reverseTree(TreeNode head) {
        if (head != null) {
            TreeNode left = head.leftNode;
            TreeNode right = head.rightNode;
            head.setRightNode(left);
            head.setLeftNode(right);
            reverseTree(left);
            reverseTree(right);
        }
        //1 3 2 7 6 5 4 12 11 10 9
    }

    /**
     *                  1
     *        2                  3
     *    4        5         6       7
     * null  9  10   11   12
     *
     *
     */

    private static TreeNode initTree() {
        String[] a = new String[]{"1", "2", "3", "4", "5", "6", "7", null, "9", "10", "11", "12"};
        TreeNode head = new TreeNode(a[0]);
        boolean left = true;
        //顺序放了所有的节点
        LinkedList<TreeNode> record = new LinkedList<>();
        record.add(head);
        for (int i = 1; i < a.length; i++) {
            TreeNode first = record.getFirst();
            TreeNode curNode;
            //如果本次是右边，则说明当前节点已经放满了，该去到下一个节点。
            if (!left) {
                record.removeFirst();
            }
            if (a[i] == null) {
                left = !left;
                continue;
            } else {
                curNode = new TreeNode(a[i]);
                record.add(curNode);
            }
            if (left) {
                first.setLeftNode(curNode);
                left = false;
            } else {
                first.setRightNode(curNode);
                left = true;
            }

        }
        return head;
    }


    static class TreeNode {

        private String value;
        private TreeNode leftNode;
        private TreeNode rightNode;

        public TreeNode(String value){
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public TreeNode getLeftNode() {
            return leftNode;
        }

        public void setLeftNode(TreeNode leftNode) {
            this.leftNode = leftNode;
        }

        public TreeNode getRightNode() {
            return rightNode;
        }

        public void setRightNode(TreeNode rightNode) {
            this.rightNode = rightNode;
        }
    }

}
