package Apartat_5;

import java.util.ArrayList;

public class Divider extends Thread{
    private static final int listSize = 100;
    private static final int aBuscar = 56;
    private static final int numThreads = 99;
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
        int leftOvers = Array.length - (Array.length/NumThreads)*NumThreads;
        int increase = Array.length/NumThreads;
        int startingIndex = 0;
        int endIndex = 0;
        ArrayList<Searcher> threadList = new ArrayList<Searcher>();

        for (int i = 0; i < NumThreads; i++){

            if (leftOvers > 0){
                leftOvers--;
                endIndex += increase + 1;

                Searcher searcher = new Searcher(startingIndex, endIndex, aBuscar);
                searcher.setName("Searcher " + i);
                searcher.start();
                threadList.add(searcher);
                startingIndex += increase;

                startingIndex++;
            }else {
                endIndex += increase;
                Searcher searcher = new Searcher(startingIndex, endIndex, aBuscar);
                searcher.setName("Searcher " + i);
                searcher.start();
                threadList.add(searcher);
                startingIndex += increase;
            }
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

    public static int[] getList() {
        return list;
    }
}
