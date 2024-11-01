import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Logic {
    private final List<Integer> tilesList = new LinkedList<>();
    private final List<Integer> solutionList = new LinkedList<>();

    private int counter = 0;


    //Konstruktor initierar tileslistan och facitlistan och shufflar tileslistan
    //16 är tom ruta        Testat! /Y
    public Logic() {
        for (int i = 1; i <= 16; i++) {
            tilesList.add(i);
            solutionList.add(i);
        }
        shuffleTiles();
    }
    //Konstruktor för easy win          Testat! /Y
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

        if (pushed == (indexEmpty + 1) && indexPushed % 4 != 0 ||
                pushed == (indexEmpty - 1) && indexPushed % 4 != 3 ||
                pushed == (indexEmpty - 4) && indexPushed >= 4 ||
                pushed == (indexEmpty + 4) && indexPushed <= 11) {

            moveTile(indexPushed, indexEmpty);

            return true;
        }
        return false;
    }

    //Metod för speldrag (knapptryckning)       Delvis testad /Y
    //pushed är nu talet som står i knappen. indexEmpty och indexPusched är idnex of 16 resp. tryckt knapp.
    public void moveTile(int indexPushed, int indexEmpty) {
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