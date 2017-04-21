package stoprepeatingyourself;

import java.util.Objects;

/**
 * Created by: Brittany Safford & Zachary Laborde
 */

public class Heap {

    private int size, lastIndex;
    private Integer[] numbers;
    private String[] words;


    Heap(Integer[] numArr, String[] wordArr) {
        numbers = numArr;
        words = wordArr;

        size = numbers.length;
        lastIndex = numbers.length - 1;

        for (int i = lastIndex / 2; i >= 0; i--)
            minHeap(i);
    }

    protected void sort() {

        for (int i = lastIndex; i > 0; i--) {
            swap(0, i);
            lastIndex--;
            minHeap(0);
        }
    }

    private void minHeap(int i) {
        int left = 2 * i;
        int right = 2 * i + 1;
        int min = i;

        if ((left <= lastIndex) &&
                (numbers[left] < numbers[i] ||
                        (Objects.equals(numbers[left], numbers[i]) && words[left].compareTo(words[i]) > 0)))
            min = left;


        if ((right <= lastIndex) &&
                (numbers[right] < numbers[min] ||
                        (Objects.equals(numbers[right], numbers[min]) && words[right].compareTo(words[min]) > 0)))
            min = right;


        if (min != i) {

            swap(i, min);
            minHeap(min);
        }

    }

    private void swap(int i, int j) {

        int num = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = num;

        String word = words[i];
        words[i] = words[j];
        words[j] = word;
    }

    protected Integer[] getNumbers() {
        return numbers;
    }

    protected String[] getWords() {
        return words;
    }

    protected int size() {
        return size;
    }

}