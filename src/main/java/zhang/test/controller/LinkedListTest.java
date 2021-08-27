package zhang.test.controller;

import com.alibaba.fastjson.util.IOUtils;
import org.apache.poi.ss.formula.functions.T;
import org.apache.xmlbeans.impl.common.IOUtil;

import java.nio.charset.StandardCharsets;


/**
 * @Descripthon: LinkedList 练习
 * @author: MrZhang
 * @date: 2021/3/18 17:24
 */
public class LinkedListTest {
    transient Node last;
    transient Node first;
    private int length;

    // node结构
    public static class Node<T> {
        // 数据
        private T data;
        // 上一个指针
        private Node prev;
        // 下一个指针
        private Node next;
        Node(Node prev, T data, Node next) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
    }
    // 链表添加操作
    public void add(T data) {
        final Node l = last;
        final Node newNode = new Node(l, data, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        length++;
    }

    public Node<T> getMiddleData(Node head) {
        Node<T> fastNode = head;
        while (fastNode.next != null) {
            if (fastNode.next.next != null) {
                head = head.next;
                fastNode = fastNode.next.next;
            } else {
                head = head.next;
                return head;
            }
        }
        return head;
    }
    public static void main(String[] args) {
        System.out.println(String.valueOf(StandardCharsets.UTF_8));
    }

}
