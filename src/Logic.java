import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Logic {
    List<Integer> tilesList = new LinkedList<>();
    int counter = 0;
    //Möjligen outputvariabel?


    //Konstruktor som initierar listan
    public Logic() {
        for (int i = 0; i <= 15; i++) {
            tilesList.add(i);
        }
    }
    //Shuffle metod
    public List shuffleTiles() {
        if (tilesList != null) {
            Collections.shuffle(tilesList);
        }
        return tilesList;
    }

    //Metod för att kolla att draget är möjligt
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

            countMoves();
        }
    }

    //Metod för redovisning (Snabb vinst)


    //Metod för counter av speldrag (Vill vi sära på den från moveTile?
    // Går ju att lägga counter++ i moveTile-metoden. Men separation of concern osv?)
    public void countMoves(){
        counter++;
    }

}