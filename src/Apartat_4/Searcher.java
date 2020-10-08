package Apartat_4;

public class Searcher extends Thread{
    private int[] list;
    private int aBuscar;
    private int index;

    public Searcher(int[] list, int aBuscar){
        this.list = list;
        this.aBuscar = aBuscar;
        index = -1;
    }

    @Override
    public void run() {
        for (int i = 0; i < list.length; i++){
//            System.out.println(this.getName() + ": [" + i + "]: " + list[i]);
            if (list[i] == aBuscar){
                index = i;
            }
        }
    }

    public int getIndex() {
        return index;
    }
}
