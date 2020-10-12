package Apartat_5;

public class Searcher extends Thread{
    private int index;
    private final int aBuscar;
    private final int startingIndex;
    private final int endIndex;

    public Searcher(int startingIndex, int endIndex, int aBuscar){
        //System.out.println(this.getName() + "\tStart index: " + startingIndex + "\tendIndex: " + (endIndex - 1));
        this.startingIndex = startingIndex;
        this.endIndex = endIndex;
        this.aBuscar = aBuscar;
        index = -1;
    }

    @Override
    public void run() {
        for (int i = startingIndex; i < endIndex; i++){
            //System.out.println(this.getName() + " esta buscant en el index: [" + i + "]: ");
            if (Divider.getList()[i] == aBuscar){
                index = i;
            }
        }
    }

    public int getIndex() {
        return index;
    }
}
