import java.awt.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


/*
    OBS: Denna klass innehåller metoden public boolean checkSolvable() innehåller kod som bygger på kod och logik
    hämtad från Geeks for Geeks: How to check if an instance puzzle is solvable
    https://www.geeksforgeeks.org/check-instance-15-puzzle-solvable/
    och Claude, en AI-assistant från Anthropic (conversation date: November 2, 2024)
    Datum: 2024-11-02
*/
public class Logic {

    private final List<Tile> tilesList = new LinkedList<>();
    private final List<Tile> solutionList = new LinkedList<>();

    private int counter = 0;

    public Logic(boolean easyWin) {

        for (int i = 1; i <= 16; i++) {
            Tile tile = new Tile(i);
                if (i == 1 || i == 6 || i == 11){
                    tile.setTileColor(Color.RED.darker());
                }
                if (i == 2|| i ==  5 || i == 7 || i == 10 || i == 12 || i == 15){
                    tile.setTileColor(Color.RED);
                }
                if (i == 3|| i ==  8 || i == 9 || i == 14){
                    tile.setTileColor(Color.ORANGE);
                }
                if (i == 4|| i == 13){
                    tile.setTileColor(Color.YELLOW);
                }
                if (i == 16)
                    tile.setTileColor(Color.GRAY);

            tilesList.add(tile);
            solutionList.add(tile);

        }
        if (easyWin) {
            Collections.swap(tilesList, 14 , 15 );
        }else
            shuffleTiles();
    }

    public void shuffleTiles() {
        do {
            Collections.shuffle(tilesList);
        }while (!checkSolvable());
    }

    public int findEmptyTile() {

        for (Tile tile : tilesList) {
            if (tile.getTileNumber() == 16) {

                return tilesList.indexOf(tile);
            }
        }
            return -1;
    }

    public boolean validMove(int indexPushed) {

        int indexEmpty = findEmptyTile();

        if (indexPushed == (indexEmpty + 1) && indexPushed % 4 != 0 ||
                indexPushed == (indexEmpty - 1) && indexPushed % 4 != 3 ||
                indexPushed == (indexEmpty - 4) ||
                indexPushed == (indexEmpty + 4)) {

            moveTile(indexPushed, indexEmpty);

            return true;
        }
        return false;
    }

    public void moveTile(int indexPushed, int indexEmpty) {
        Collections.swap(tilesList, indexPushed, indexEmpty);
    }

    public boolean checkWinning(){
        boolean won = false;

        if (tilesList.equals(solutionList)) {
            won = true;
        }
        return won;
    }

    /*
        OBS: Denna metod innehåller kod som bygger på kod och logik
        hämtad från Geeks for Geeks: How to check if an instance puzzle is solvable
        https://www.geeksforgeeks.org/check-instance-15-puzzle-solvable/
        och Claude, en AI-assistant från Anthropic (conversation date: November 2, 2024)
        Datum: 2024-11-02
    */
    public boolean checkSolvable(){
        boolean isSolvable = false;
        int[] solvable = new int[16];
        int indexEmpty = findEmptyTile();
        int inversions = 0;

        for (int i = 0; i < solvable.length; i++) {
            Tile tile = tilesList.get(i);
            solvable[i] = tile.getTileNumber();
        }

        for (int i = 0; i < 15; i++){
            for (int j = i + 1; j < 16; j++){

                if (solvable[j] !=16 && solvable[i] != 16
                && solvable[i] > solvable[j])
                    inversions++;
            }
        }

        int rowFromBottom = 3 - (indexEmpty/4);
        boolean even = (rowFromBottom % 2 == 0);

        //even row from bottom, inversions odd = solvable
        if (even && inversions % 2 != 0 )
                    isSolvable = true;

        //odd row from bottom, inversions even = solvable
        if (!even && inversions % 2 == 0){
                    isSolvable = true;
        }
        System.out.println(isSolvable);
        return isSolvable;
    }

    public void countMoves(){
        counter++;
    }

    public List<Tile> getTilesList() {
        return tilesList;
    }

    public int getCounter() {
        return counter;
    }
}