/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ua.epam.lab;

/**
 *
 * @author DruidKuma
 */
public class Test {
    public static void main(String[] args) {
        RedBlackTree<Integer, String> rbt = new RedBlackTree<>();
        rbt.put(1, "a");
        rbt.put(2, "b");
        rbt.put(3, "b");
        rbt.put(4, "b");
        rbt.put(5, "b");
        rbt.remove(4);
        rbt.remove(3);
        rbt.remove(2);
        //rbt.remove(1);
        //Integer i = 3;
        //System.out.println();
    }
}
