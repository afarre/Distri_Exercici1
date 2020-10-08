package Apartat_3;

import java.util.LinkedList;

public class ParallelSearch {
    private static final int listSize = 100000;
    private static final int aBuscar = 50000;
    private static LinkedList<Integer> list;

    public ParallelSearch(){
        list = new LinkedList<Integer>();
        createList();
        startSearch();
    }

    private void startSearch() {
        Searcher frontSearcher = new Searcher(this, aBuscar, true);
        frontSearcher.setName("FRONT");
        Searcher backSearcher = new Searcher(this, aBuscar, false);
        backSearcher.setName("BACK");
        frontSearcher.start();
        backSearcher.start();
    }

    private void createList() {
        for (int i = 0; i < listSize; i++){
            list.add(i);
        }
    }

    public synchronized void found(String name){
        System.out.println("Thread " + name + " found the searched number first.");
        System.exit(0);
    }

    public static LinkedList<Integer> getList() {
        return list;
    }
}
