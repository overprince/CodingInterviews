package chapter2;

import org.junit.Assert;
import org.junit.Test;
import util.Util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Stack;

/**
 * 题目：输入一个链表的头节点，从尾到头反过来打印出每个节点的值。
 * 链表节点定义如下：
 * @author flying
 */
public class Q6_PrintLinkedReverseOrder {

    /**
     * 非递归
     */
    private Integer[] printSingleLinkedListReverseOrder(SingleLinkedNode rootNode) {
        if (rootNode == null) {
            return null;
        }

        Stack<SingleLinkedNode> stack = new Stack<>();

        while (rootNode != null) {
            stack.add(rootNode);
            rootNode = rootNode.next;
        }

        return stack.toArray(new Integer[0]);
    }

    /**
     * 递归方式遍历
     */
    private void printSingleLinkedListReverseOrderRecursive(SingleLinkedNode rootNode, ArrayList<Integer> result) {
        if (rootNode == null) {
            return;
        }

        printSingleLinkedListReverseOrderRecursive(rootNode.next, result);
        result.add(rootNode.value);
    }

    /**
     * 非递归, 双向
     * @param rootNode
     */
    private Integer[] printTowWayLinkedListReverseOrder(TwoWayLinkedNode rootNode) {
        if (rootNode == null) {
            return null;
        }

        while (rootNode.next != null) {
            rootNode = rootNode.next;
        }

        ArrayList<Integer> results = new ArrayList<>();

        while (rootNode != null) {
            results.add(rootNode.value);
            rootNode = rootNode.prev;
        }

        return results.toArray(new Integer[0]);
    }

    @Test
    public void test() {
        SingleLinkedNode root = new SingleLinkedNode();
        root.value = 0;
        SingleLinkedNode singleLinkedNode = root;
        int length = 0;
        int[] expect =  new int[11];
        expect[0] = 0;

        while (length++ < 10) {
            expect[length] = length;
            singleLinkedNode.next = new SingleLinkedNode();
            singleLinkedNode.next.value = length;
            singleLinkedNode = singleLinkedNode.next;
        }


        Util.assertArrayEquals(printSingleLinkedListReverseOrder(root), expect);
        ArrayList<Integer> resultList = new ArrayList();
        printSingleLinkedListReverseOrderRecursive(root, resultList);
        Util.assertArrayEquals(resultList.toArray(new Integer[0]), expect);

        TwoWayLinkedNode twoWayLinkedNode = new TwoWayLinkedNode();
        twoWayLinkedNode.value = --length;

        while (--length >= 0) {
            twoWayLinkedNode.prev = new TwoWayLinkedNode();
            twoWayLinkedNode.prev.value = length;
            twoWayLinkedNode.prev.next = twoWayLinkedNode;
            twoWayLinkedNode = twoWayLinkedNode.prev;
        }

        Util.assertArrayEquals(printTowWayLinkedListReverseOrder(twoWayLinkedNode), expect);
    }

    /**
     * 单向链表
     */
    public static class SingleLinkedNode {
        public int value;
        public SingleLinkedNode next;
    }

    /**
     * 双向链表
     */
    public static class TwoWayLinkedNode extends SingleLinkedNode {
        public TwoWayLinkedNode next;
        public TwoWayLinkedNode prev;
    }
}