import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/////////////////////////////////////////////////////////////
////// Metoder kvar: För redovisning (Snabb vinst) /////////
/////                För att hitta tomma rutan?   /////////
////                 För att kolla vinst?        /////////
/////////////////////////////////////////////////////////


public class Logic {
    private final List<Integer> tilesList = new LinkedList<>();
    private int counter = 0;
    //Möjligen outputvariabel?


    //Konstruktor initierar listan och shufflar
    public Logic() {
        for (int i = 0; i <= 15; i++) {
            tilesList.add(i);
        }
        shuffleTiles();
    }

    //Shuffle metod
    public void shuffleTiles() {
        Collections.shuffle(tilesList);
    }

    //Metod för att kolla att draget är möjligt
    //Ska empty vara en inparameter eller ska findEmpty anropas?
    public boolean validMove(int pushed, int empty) {
        if (pushed == empty - 1 && pushed % 4 != 0||
                pushed == empty + 1 && pushed % 4 != 3 ||
                pushed == empty - 4 && pushed >= 4 ||
                pushed == empty + 4 && pushed <= 11) {
            return true;
        }
        return false;
    }

    //Metod för speldrag (knapptryckning)
    public void moveTile(int pushed, int empty) {
        if (validMove(pushed, empty)) {
            int indexPushed = tilesList.indexOf(pushed);
            int indexEmpty = tilesList.indexOf(empty);

            tilesList.set(indexPushed, empty);
            tilesList.set(indexEmpty, pushed);
            countMoves();
        }
    }


    //Metod för counter av speldrag
    public void countMoves(){
        counter++;
    }

    //För inkapsling
    public int getCounter() {
        return counter;
    }

    //För inkapsling
    public List<Integer> getTilesList() {
        return tilesList;
    }


}