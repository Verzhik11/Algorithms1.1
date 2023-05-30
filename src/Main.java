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
        int[] test = {3, 2, 1, 4};
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
        int[] arr3 = Arrays.copyOf(arr, arr.length);
        /*long start1 = System.currentTimeMillis();
        sortBubble(arr1);
        long time1 = System.currentTimeMillis() - start1;
        System.out.println("sortBubble: " + time1);
        long start2 = System.currentTimeMillis();
        sortSelection(arr2);
        long time2 = System.currentTimeMillis() - start2;
        System.out.println("sortSelection: " + time2);*/

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


}