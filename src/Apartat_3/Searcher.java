package Apartat_3;

public class Searcher extends Thread{
    private final ParallelSearch parent;
    private final int aBuscar;
    private final boolean front;

    public Searcher(ParallelSearch parent, int aBuscar, boolean front){
        this.parent = parent;
        this.aBuscar = aBuscar;
        this.front = front;
    }

    @Override
    public void run() {
        if (front){
            frontSearch();
        }else {
            backSearch();
        }
    }

    private void backSearch() {
        for (int i = ParallelSearch.getList().size() - 1; i > 0; i--){
            if (ParallelSearch.getList().get(i) == aBuscar){
                parent.found(this.getName());
            }
        }
    }

    private void frontSearch() {
        for (int i = 0; i < ParallelSearch.getList().size(); i++){
            if (ParallelSearch.getList().get(i) == aBuscar){
                parent.found(this.getName());
            }
        }
    }
}
