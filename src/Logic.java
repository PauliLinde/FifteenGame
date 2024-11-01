import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Logic {
    private final List<Integer> tilesList = new LinkedList<>();
    private final List<Integer> solutionList = new LinkedList<>();

    protected int indexPushed;
    protected int indexEmpty;
    private int counter = 0;


    //Konstruktor för easy win också         Testat! /Y
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

    //Metod för att kolla att draget är möjlig      Delvis testad /Y
    //Obs: Nu tar denna metod enbart in INDEX OF 16 (tom) och tryckt knapp.
    public boolean validMove(int pushed) {
        countMoves();
        int indexEmpty = findEmptyTile();
        int indexPushed = tilesList.indexOf(pushed);

        if (indexPushed == (indexEmpty + 1) && indexPushed % 4 != 0 ||
                indexPushed == (indexEmpty - 1) && indexPushed % 4 != 3 ||
                indexPushed == (indexEmpty - 4) ||
                indexPushed == (indexEmpty + 4)) {

            moveTile(indexPushed, indexEmpty);
            return true;
        }
        return false;
    }

    //Metod för speldrag (knapptryckning)       Delvis testad /Y
    //pushed är nu talet som står i knappen. indexEmpty och indexPusched är idnex of 16 resp. tryckt knapp.
    public void moveTile(int indexPushed, int indexEmpty) {
        this.indexPushed = indexPushed;
        this.indexEmpty = indexEmpty;

        Collections.swap(tilesList, indexPushed, indexEmpty);
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
    public List<Integer> getTilesList() {
        return tilesList;
    }
    //För testning
    public List<Integer> getSolutionList() {
        return solutionList;
    }

    public int getIndexPushed() {
        return indexPushed;
    }

    public int getIndexEmpty() {
        return indexEmpty;
    }

    //För inkapsling
    public int getCounter() {
        return counter;
    }
}