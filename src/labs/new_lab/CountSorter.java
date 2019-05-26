package labs.new_lab;

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
        sort(queue, minValue, maxValue);
    }

    /**
     * Сложность алгоритма
     */
    public static QueueHeadTail sort(QueueHeadTail<Integer> queue, int minValue, int maxValue) {
        QueueHeadTail<Integer> result = new QueueHeadTail<>();
        int[] counters = new int[maxValue - minValue + 1];
        for (int i = 0; i < queue.size(); i++) {
            counters[get(queue, i) - minValue]++;
        }
        for (int i = 0; i < counters.length; i++) {
            for (int j = 0; j < counters[i]; j++) {
                set(result, queue.size() - 1,  i + minValue);
            }
        }
        return result;
    }

    private static int get(QueueHeadTail<Integer> queue, int index) {
        int result;
        for (int i = 0; i < index; i++) {
            queue.push(queue.put());
        }
        result = queue.put();
        queue.push(result);
        for (int i = index + 1; i < queue.size(); i++) {
            queue.push(queue.put());
        }
        return result;
    }

    private static void set(QueueHeadTail<Integer> queue, int index, int value) {
        for (int i = 0; i < index; i++) {
            queue.push(queue.put());
        }
        queue.push(value);
        for (int i = index + 1; i < queue.size(); i++) {
            queue.push(queue.put());
        }
    }
}
