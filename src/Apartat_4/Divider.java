package Apartat_4;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Divider extends Thread{
    private static final int listSize = 100;
    private static final int aBuscar = 56;
    private static final int numThreads = 2;
    private static int[] list;

    public Divider(){
        list = new int[listSize];
        for (int i = 0; i < listSize; i++){
            list[i] = i;
        }

        long startTime = System.currentTimeMillis();
        cercaParallela(aBuscar, list, numThreads);
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        System.out.println("Time elapsed: " + elapsedTime);
    }

    public static int cercaParallela(int aBuscar, int[] Array, int NumThreads){
        int numSubArrays = Array.length/NumThreads;
        int leftOvers = Array.length - numSubArrays*NumThreads;
        int[] subArray;
        int currentIndex = 0;
        ArrayList<Searcher> threadList = new ArrayList<Searcher>();

        for (int i = 0; i < NumThreads; i++){
            if (leftOvers > 0){
                subArray = new int[numSubArrays + 1];
                leftOvers--;
                for (int j = 0; j < numSubArrays + 1; j++){
                    subArray[j] = Array[currentIndex + j];
                }
                currentIndex += numSubArrays + 1;
            }else {
                subArray = new int[numSubArrays];
                for (int j = 0; j < numSubArrays; j++){
                    subArray[j] = Array[currentIndex + j];
                }
                currentIndex += numSubArrays;
            }
            Searcher searcher = new Searcher(subArray, aBuscar);
            searcher.setName("Searcher " + i);
            searcher.start();
            threadList.add(searcher);
        }
        ArrayList<Searcher> copyList = new ArrayList<>(threadList);

        while (!threadList.isEmpty()){
            threadList.removeIf(s -> !s.isAlive());
        }

        for (Searcher s : copyList) {
            if (s.getIndex() != -1) {
                System.out.println("El thread " + s.getName() + " ha trobat el nombre buscat a la casella " + s.getIndex());
                return s.getIndex();
            }
        }
        return -1;
    }
}
