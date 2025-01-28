package com.cab;

import com.cab.binarytree.BinaryTree;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> datas = List.of(5, 3, 7, 2, 4, 6, 8, 9, 10);

        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        binaryTree.insert(datas);
//        binaryTree.insert(List.of(1));

        System.out.println(binaryTree.prefix());
        System.out.println(binaryTree.infix());
        System.out.println(binaryTree.postfix());
        System.out.println(binaryTree.lateral());

        System.out.println("height " + binaryTree.getHeight());
        System.out.println(binaryTree.getBalanceFactor());
        System.out.println(binaryTree.isAVL());

        System.out.println(binaryTree.toPrettyString());
        System.out.println(binaryTree.toTreeString());
    }
}