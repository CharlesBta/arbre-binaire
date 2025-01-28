package com.cab;

import com.cab.binarytree.BinaryTree;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> datas = List.of(5, 3, 7, 2, 4, 6, 9, 8, 10);

        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        binaryTree.insert(datas);
//        binaryTree.insert(List.of(1));

        System.out.println(binaryTree.toTreeString());
        binaryTree.testrotateLeftLeftChild();
        System.out.println(binaryTree.toTreeString());
        binaryTree.testrotateLeftLeftChild();
        System.out.println(binaryTree.toTreeString());
    }
}