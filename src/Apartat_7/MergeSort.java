package Apartat_7;

import java.util.Arrays;
import java.util.Random;

public class MergeSort extends Thread {
    private final int arraySize = 1000;
    private final int [] array;
    private MergeSort parent;
    private int [] sortedSubArray1;
    private int [] sortedSubArray2;
    private boolean first;

    public MergeSort(){
        array = new int[arraySize];
        Random random = new Random();
        for (int i = 0; i < array.length; i++){
            array[i] = random.nextInt(10);
        }
        System.out.println("Array:\t\t\t\t\t\t\t" + Arrays.toString(array));
        this.start();
    }

    public MergeSort(MergeSort parent, int[] array, boolean first){
        this.parent = parent;
        this.array = array;
        this.first = first;
        this.start();
    }

    @Override
    public void run() {
        if (array.length > 1){
            int [] subArray1 = Arrays.copyOfRange(array, 0, array.length/2);
            int [] subArray2 = Arrays.copyOfRange(array, array.length/2, array.length);
            MergeSort mergeSort1 = new MergeSort(this, subArray1, true);
            MergeSort mergeSort2 = new MergeSort(this, subArray2, false);
            try {
                mergeSort1.join();
                mergeSort2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int[] sortedArray = sortSubArrays(sortedSubArray1, sortedSubArray2);
            if (parent == null){
                System.out.println(this.getName() + "'s final sorted array:\t" + Arrays.toString(sortedArray));
                System.exit(0);
            }
            if (first){
                parent.sortedSubArray1 = sortedArray;
            }else {
                parent.sortedSubArray2 = sortedArray;
            }
            Thread.currentThread().interrupt();
        }else if (array.length == 1){
            if (first){
                parent.sortedSubArray1 = array;
            }else {
                parent.sortedSubArray2 = array;
            }
            Thread.currentThread().interrupt();
        }
    }

    private int[] sortSubArrays(int[] sortedSubArray1, int[] sortedSubArray2) {
        int [] sortedArray = new int[sortedSubArray1.length + sortedSubArray2.length];

        int j, k;
        j = k = 0;
        for (int i = 0; i < sortedArray.length; i++){
            if (j < sortedSubArray1.length && k < sortedSubArray2.length){
                if (sortedSubArray1[j] <= sortedSubArray2[k]){
                    sortedArray[i] = sortedSubArray1[j];
                    j++;
                }else {
                    sortedArray[i] = sortedSubArray2[k];
                    k++;
                }
            }else if (j >= sortedSubArray1.length){
                sortedArray[i] = sortedSubArray2[k];
                k++;
            }else {
                sortedArray[i] = sortedSubArray1[j];
                j++;
            }
        }
        return sortedArray;
    }
}
