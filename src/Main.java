import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        StringListImpl stringList = new StringListImpl(5);
        IntegerListImpl integerList = new IntegerListImpl(5);
        integerList.add(1);
        integerList.add(3);
        integerList.add(4);
        integerList.add(11);
        System.out.println(integerList.contains(11));
        stringList.add("Карандаш");
        stringList.add("Ручка");
        stringList.add("Ручка");
        stringList.add(2, "Ластик");
        System.out.println(stringList.indexOf("Ручка"));
        stringList.clear();
        System.out.println(stringList);
        System.out.println(stringList.equals(stringList));
        int[] test = {3, 2, 1, 4, 56, 23, 11};
        mergeSort(test);
        //quickSort(test, 0, test.length - 1);
        //sortBubble(test);
        //sortSelection(test);
        System.out.println(Arrays.toString(test));
        Random rand = new Random();
        int[] arr = new int[100000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rand.nextInt(1000);
        }
        int[] arr1 = Arrays.copyOf(arr, arr.length);
        int[] arr2 = Arrays.copyOf(arr, arr.length);
        //int[] arr3 = Arrays.copyOf(arr, arr.length);
        //System.out.println(partition(test, 0, test.length - 1));
        long start1 = System.currentTimeMillis();
        quickSort(arr1, 0, arr1.length - 1);
        long time1 = System.currentTimeMillis() - start1;
        System.out.println("quickSort: " + time1);
        long start2 = System.currentTimeMillis();
        mergeSort(arr2);
        long time2 = System.currentTimeMillis() - start2;
        System.out.println("mergeSort: " + time2);

    }

    public static void swapElements(int[] arr, int indexA, int indexB) {
        int temp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = temp;
    }

    public static void sortBubble(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swapElements(arr, j, j + 1);
                }
            }
        }
    }

    public static void sortSelection(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int minimalEl = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minimalEl]) {
                    minimalEl = j;
                }
            }
            swapElements(arr, i, minimalEl);
        }
    }

    public static void quickSort(int[] arr, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);
            quickSort(arr, begin, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
        }

    }

    public static int partition(int[] arr, int begin, int end) {
        int pivot = arr[end];
        int i = begin - 1;
        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;
                swapElements(arr, i, j);
            }
        }
        swapElements(arr, i + 1, end);
        return i + 1;

    }

    public static void mergeSort(int[] arr) {
        if (arr.length < 2) {
            return;
        }
        int mid = arr.length / 2;
        int[] left = new int[mid];
        int[] right = new int[arr.length - mid];

        for (int i = 0; i < left.length; i++) {
            left[i] = arr[i];
        }

        for (int i = 0; i < right.length; i++) {
            right[i] = arr[mid + i];
        }

        mergeSort(left);
        mergeSort(right);

        merge(arr, left, right);
    }

    public static void merge(int[] arr, int[] left, int[] right) {
        int mainP = 0;
        int leftP = 0;
        int rightP = 0;
        while (leftP < left.length && rightP < right.length) {
            if (left[leftP] <= right[rightP]) {
                arr[mainP++] = left[leftP++];
            } else {
                arr[mainP++] = right[rightP++];
            }
        }
        while (leftP < left.length) {
            arr[mainP++] = left[leftP++];
        }
        while (rightP < right.length) {
            arr[mainP++] = right[rightP++];
        }
    }


}