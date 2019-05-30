package labs.lab1;

import labs.lab1.new_lab.QueueHeadTail;
import labs.lab1.new_lab.CountSorter;

public class Test {
    public static void main(String[] args) {
        QueueHeadTail<Integer> queue = new QueueHeadTail<>();
        QueueHeadTail<Integer> queue1 = new QueueHeadTail<>();
        for (int i = 0; i < 10; i++) {
            int temp = (int) (Math.random() * 10);
            queue.push(temp);
            queue1.push(temp);
            System.out.print(temp + " ");
        }
        System.out.println();
        System.out.println(queue);
        System.out.println(queue.size());
        System.out.println(CountSorter.sort(queue, 0, 9));
        System.out.println(queue.size());
        System.out.println(queue);
        CountSorter.sort(queue1);
        System.out.println(queue1);
    }
}
