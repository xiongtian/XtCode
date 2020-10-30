package com.study.day.test201012;

import java.util.Stack;

public class test02 {
    public static void main(String[] args) {
        PostOrder.TreeNode<Integer> root = new PostOrder.TreeNode<Integer>(1);
        PostOrder.TreeNode<Integer> l = new PostOrder.TreeNode<Integer>(2);
        PostOrder.TreeNode<Integer> r = new PostOrder.TreeNode<Integer>(4);
        PostOrder.TreeNode<Integer> ll = new PostOrder.TreeNode<Integer>(5);
        PostOrder.TreeNode<Integer> lr = new PostOrder.TreeNode<Integer>(3);
        root.left = l;
        root.right = r;
        l.left = ll;
        l.right = lr;
        postOrder(root);
    }


    public static void postOrder(PostOrder.TreeNode<Integer> root){
        Stack<PostOrder.TreeNode<Integer>> src = new Stack<PostOrder.TreeNode<Integer>>();
        Stack<PostOrder.TreeNode<Integer>> res = new Stack<PostOrder.TreeNode<Integer>>();

        src.push(root);
        while (!src.isEmpty()){
            PostOrder.TreeNode<Integer> p = src.pop();
            res.push(p);
            if (null!=p.left){
                src.push(p.left);
            }
            if (null!=p.right) {
                src.push(p.right);
            }
        }

        while (!res.isEmpty()) {
            System.out.print(res.pop().val+" ");
        }
    }
}
