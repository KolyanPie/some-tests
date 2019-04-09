package labs;

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
        CountSorter.sort(queue, 0, 9, 10);
        System.out.println(queue);
        CountSorter.sort(queue1);
        System.out.println(queue1);
    }
}
