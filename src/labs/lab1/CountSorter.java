package labs.lab1;

public final class CountSorter {


    public static void sort(QueueHeadTail<Integer> queue) {
        QueueHeadTail<Integer> temp = new QueueHeadTail<>();
        Integer item = queue.put();
        Integer minValue = item;
        Integer maxValue = item;
        int size = 0;
        while (item != null) {
            size++;
            minValue = Math.min(minValue, item);
            maxValue = Math.max(maxValue, item);
            temp.push(item);
            item = queue.put();
        }
        for (int i = 0; i < size; i++) {
            queue.push(temp.put());
        }
        sort(queue, minValue, maxValue, size);
    }

    /**
     * Сложность алгоритма
     */
    public static void sort(QueueHeadTail<Integer> queue, int minValue, int maxValue, int size) {
        int[] counters = new int[maxValue - minValue + 1];
        for (int i = 0; i < size; i++) {
            counters[queue.put() - minValue]++;
        }
        for (int i = 0; i < counters.length; i++) {
            for (int j = 0; j < counters[i]; j++) {
                queue.push(i + minValue);
            }
        }
    }

}
