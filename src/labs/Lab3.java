package labs;

public class Lab3 {
    public static void main(String[] args) {
        Lab3 lab3 = new Lab3();
        System.out.println("\nTask 1");
        lab3.firstMethod();
        System.out.println("\nTask 2");
        lab3.secondMethod();
    }

    private void firstMethod() {
        int[] arr = new int[17];
        int odd = 0;
        int even = 0;
        for (int e : arr) {
            e = (int) (Math.random() * 10);
            System.out.printf("%d ", e);
            if (e % 2 == 0) {
                even++;
            }
            else {
                odd++;
            }
        }
        System.out.printf("\nodds is %d\tevens is %d\n", odd, even);
    }

    private void secondMethod() {
        int[][] arr = new int[5][6];
        int[] maxIndex = new int[arr.length];
        for (int j = 0; j < arr.length; j++) {
            int max = 0;
            for (int i = 0; i < arr[j].length; i++) {
                arr[j][i] = (int) (Math.random() * 10);
                if (arr[j][i] > max) {
                    maxIndex[j] = i;
                    max = arr[j][i];
                }
                System.out.printf("%d ", arr[j][i]);
            }
            System.out.println();
        }
        System.out.println();
        for (int i = 0; i < arr.length; i++) {
            if (0 == maxIndex[i]) {
                continue;
            }
            arr[i][0] += arr[i][maxIndex[i]];
            arr[i][maxIndex[i]] = arr[i][0] - arr[i][maxIndex[i]];
            arr[i][0] -= arr[i][maxIndex[i]];
        }
        for (int[] line : arr) {
            for (int e : line) {
                System.out.printf("%d ", e);
            }
            System.out.println();
        }
    }
}
