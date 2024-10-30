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
    //Scramble metod

    //Metod för speldrag (knapptryckning)
    public void moveTile(int pushed, int empty) {
            if (pushed == empty - 1 && pushed % 4 != 0||
                    pushed == empty + 1 && pushed % 4 != 3 ||
                    pushed == empty - 4 && pushed >= 4 ||
                    pushed == empty + 4 && pushed <= 11) {
                //Move tile!
            }
    }

    //Metod för redovisning (Snabb vinst)

    //Metod för counter av speldrag(knapptryckningar)

}