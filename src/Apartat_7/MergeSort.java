package Apartat_7;

import java.util.Arrays;
import java.util.Random;

public class MergeSort extends Thread {
    private final int arraySize = 21;
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
        this.run();
    }

    public MergeSort(MergeSort parent, int[] array, boolean first){
        this.parent = parent;
        this.array = array;
        this.first = first;
        //System.out.println("Starting thread: " + this.getName() + " with array: " + Arrays.toString(array));
        this.run();
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
            //System.out.println("\t" + this.getName() + " - My first son sorted this: " + Arrays.toString(sortedSubArray1));
            //System.out.println("\t" + this.getName() + " - My second son sorted this: " + Arrays.toString(sortedSubArray2));
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
        //Arrays.fill(sortedArray, -1);
        //System.out.println("in. " + Arrays.toString(sortedArray));
        //System.out.println("in. " + Arrays.toString(sortedSubArray1));
        //System.out.println("in. " + Arrays.toString(sortedSubArray2));

        int j, k;
        j = k = 0;
        for (int i = 0; i < sortedArray.length; i++){
            //System.out.println("j" + j);
            //System.out.println("k" + k);
            if (j < sortedSubArray1.length && k < sortedSubArray2.length){
                if (sortedSubArray1[j] <= sortedSubArray2[k]){
                    sortedArray[i] = sortedSubArray1[j];
                    j++;
                    //System.out.println("j plus");
                }else {
                    sortedArray[i] = sortedSubArray2[k];
                    k++;
                    //System.out.println("k plus");
                }
                //System.out.println("mid: " + Arrays.toString(sortedArray));
            }else if (j >= sortedSubArray1.length){
                sortedArray[i] = sortedSubArray2[k];
                k++;
            }else {
                sortedArray[i] = sortedSubArray1[j];
                j++;
            }
        }
        //System.out.println("fin: " + Arrays.toString(sortedArray));
/*
        int i, j, k;
        i = j = k = 0;
        while (i < sortedArray.length){
            while (j < sortedSubArray1.length){
                while (k < sortedSubArray2.length){
                    if (sortedSubArray1[j] <= sortedSubArray2[k]){
                        sortedArray[i] = sortedSubArray1[j];
                        i++;
                        j++;
                        break;
                    }else {
                        sortedArray[i] = sortedSubArray2[k];
                        i++;
                        k++;
                    }
                }
            }
        }
        System.out.println("fin: " + Arrays.toString(sortedArray));

 */
/*
        for (int i = 0; i < sortedSubArray1.length; i++){
            for (int j = 0; j < sortedSubArray2.length; j++){
                if (sortedSubArray2[j] < sortedSubArray1[i]){
                    System.out.println("Checking if " + sortedSubArray2[j] + " (j = " + j + ") < " + sortedSubArray1[i] + " (i = " + i + ")");
                    sortedArray[i + j] = sortedSubArray2[j];
                    System.out.println("sortedArray[" + (i + j) + "] = " + sortedSubArray2[j]);
                }else {
                    System.out.println("Checking if " + sortedSubArray2[j] + " (j = " + j + ") > " + sortedSubArray1[i] + " (i = " + i + ")");
                    sortedArray[i + j] = sortedSubArray1[i];
                    System.out.println("sortedArray[" + (i + j) + "] = " + sortedSubArray1[i]);
                }
            }
        }
  */
        /*
        int j = 0;
        int k = 0;
        for (int i = 0; i < sortedSubArray1.length + sortedSubArray2.length; i++){
            while (j < sortedSubArray1.length){
                while (k < sortedSubArray2.length){
                    if (sortedSubArray2[k] < sortedSubArray1[j]) {
                        sortedArray[i] = sortedSubArray2[k];
                        k++;
                    }else {
                        sortedArray[i] = sortedSubArray1[j];
                        j++;
                    }
                }
            }

         */
            /*
            for (int j = 0; j < sortedSubArray1.length; j++) {
                for (int k = 0; k < sortedSubArray2.length; k++) {
                    if (sortedSubArray2[k] < sortedSubArray1[j]) {
                        sortedArray[i] = sortedSubArray2[k];
                    }else {
                        sortedArray[i] = sortedSubArray1[j];
                    }
                }
            }

             */
        //sortedArray = Arrays.copyOf(sortedSubArray1, sortedArray.length);
        /*
        System.arraycopy(sortedSubArray1, 0, sortedArray, 0, sortedSubArray1.length);
        System.out.println("merged sorted array: " + Arrays.toString(sortedArray));
        for (int i = 0; i < sortedArray.length; i++){
            for (int j = 0; j < sortedSubArray2.length; j++){
                if (sortedSubArray2[j] < sortedArray[i]){
                    System.out.println("PRi");
                    sortedArray = Arrays.copyOfRange(sortedArray, i + 1, sortedArray.length);
                    sortedArray[i] = sortedSubArray2[j];
                    System.out.println("post mid sort: " + Arrays.toString(sortedArray));
                }else {
                    System.out.println("Sec");
                    for (int k = i + 1; k < sortedArray.length; k++){
                        if (k + 1 >= sortedArray.length){
                            sortedArray[k] = 100;
                        }else {
                            sortedArray[k] = sortedArray[k + 1];
                        }
                    }
                    //System.arraycopy(sortedArray, i + 1, sortedArray, i + 2, sortedArray.length - (i + 2));
                    System.out.println("test?: " + Arrays.toString(sortedArray));
                    sortedArray[i + 1] = sortedSubArray2[j];
                    System.out.println("post test?: " + Arrays.toString(sortedArray));
                }
            }
        }

         */

        return sortedArray;
    }

    public static void main(String[] args) {
        int [] a = new int[]{10, 20};
        int [] b = new int[2];
        int index = 0;
        System.out.println(Arrays.toString(b));
        System.arraycopy(a, 0, b, 0, b.length);
        System.out.println("b: " + Arrays.toString(b));
        System.arraycopy(b, index + 1, b, index + 2, b.length - (index + 2));
        System.out.println(Arrays.toString(b));
        b[index + 1] = 0;
        System.out.println(Arrays.toString(b));
    }

    public void setSortedSubArray1(int[] sortedSubArray1) {
        this.sortedSubArray1 = sortedSubArray1;
    }

    public void setSortedSubArray2(int[] sortedSubArray2) {
        this.sortedSubArray2 = sortedSubArray2;
    }
}
