import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/////////////////////////////////////////////////////////////
////// Metoder kvar: För redovisning (Snabb vinst) /////////
/////                                             /////////
////                 Som meddelar vinst?         /////////
/////////////////////////////////////////////////////////


public class Logic {
    private final List<Integer> tilesList = new LinkedList<>();
    private final List<Integer> solutionList = new LinkedList<>();

    private int counter = 0;
    //Möjligen outputvariabel?


    //Konstruktor initierar tileslistan och facitlistan och shufflar tileslistan
    //16 är tom ruta        Testat! /Y
    public Logic() {
        for (int i = 1; i <= 16; i++) {
            tilesList.add(i);
            solutionList.add(i);
        }
        shuffleTiles();
    }
    //Konstruktor för easy win
    public Logic(boolean easyWin) {
        for (int i = 1; i <= 16; i++) {
            tilesList.add(i);
            solutionList.add(i);
        }
        if (easyWin) {
            Collections.swap(tilesList, 14 , 15 );
        }else
            shuffleTiles();
    }

    //Shuffle metod         Testat! /Y
    public void shuffleTiles() {
        Collections.shuffle(tilesList);
    }

    //Metod för att hitta index av tomma rutan      Testat! /Y
    public int findEmptyTile() {
        return tilesList.indexOf(16);
    }

    //Metod för att kolla att draget är möjlig
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
    public void moveTile(int pushed) {
        int empty = findEmptyTile();
        if (validMove(pushed, empty)) {
            int indexPushed = tilesList.indexOf(pushed);
            int indexEmpty = tilesList.indexOf(empty);
            //Ersatte tidigare rader med metod swap för att jag blev osäker på om det fungerade med de tidigare raderna efter varandra

            Collections.swap(tilesList, indexPushed, indexEmpty);

            countMoves();
        } //Här skulle kod för hantering av felaktigt drag kunna skrivas
    }

    //Metod för att kolla vinst, returnerar boolean     Testat! /Y
    public boolean checkWinning(){
        boolean won = false;

        if (tilesList.equals(solutionList)) {
            won = true;
        }
        return won;
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
    //För testning
    public List<Integer> getSolutionList() {
        return solutionList;
    }
}