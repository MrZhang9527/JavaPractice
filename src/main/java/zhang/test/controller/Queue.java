package zhang.test.controller;

/**
 * @Descripthon: 链表练习
 * @author: MrZhang
 * @date: 2021/1/13 15:45
 */
public class Queue {
    private int size;
    private int[] data;
    private int front, rear;

    public Queue(int size) {
        this.size = size;
        data = new int[size];
        front = 0; rear = 0;
    }


    public static void main(String[] args) {
        int MaxSize=10;
        Queue q = new Queue(MaxSize);
        System.out.println(q.front == (q.rear+1) % MaxSize);
        System.out.println(q.front == q.rear);
        System.out.println(q.front + q.rear == MaxSize);
        System.out.println(q.front - q.rear == MaxSize);
    }
}
