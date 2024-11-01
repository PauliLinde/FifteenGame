import java.awt.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Logic {
    //Listorna består av Tiles
    private final List<Tile> tilesList = new LinkedList<>();
    private final List<Tile> solutionList = new LinkedList<>();

    private int counter = 0;

    public Logic(boolean easyWin) {

        for (int i = 1; i <= 16; i++) {
            Tile tile = new Tile(i);
                if (i == 1 || i == 6 || i == 11){
                    tile.setTileColor(Color.RED);
                }
                if (i == 2|| i ==  5 || i == 7 || i == 10 || i == 12 || i == 15){
                    tile.setTileColor(Color.ORANGE);
                }
                if (i == 3|| i ==  8 || i == 9 || i == 14){
                    tile.setTileColor(Color.YELLOW);
                }
                if (i == 4|| i == 13){
                    tile.setTileColor(Color.PINK);
                }
                if (i == 16)
                    tile.setTileColor(Color.DARK_GRAY);

            tilesList.add(tile);
            solutionList.add(tile);

        }
        if (easyWin) {
            Collections.swap(tilesList, 14 , 15 );
        }else
            shuffleTiles();
    }

    public void shuffleTiles() {
        Collections.shuffle(tilesList);
    }

    //Metod för att hitta index av tomma rutan      Testat! /Y
    public int findEmptyTile() {

        for (Tile tile : tilesList) {
            if (tile.getTileNumber() == 16) {

                System.out.println(tilesList.indexOf(tile));
                return tilesList.indexOf(tile);
            }
        }
        /* claude säger men ovan funkar ju!
        for (int i = 0; i < tileList.size(); i++){
            if (tilesList.get(i).getTileNumber() == 16)
                return i;
        } */
            return -1;
    }

    //Obs: Nu tar denna metod enbart in INDEX OF tryckt knapp
    public boolean validMove(int indexPushed) {
        countMoves();
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
    public List<Tile> getTilesList() {
        return tilesList;
    }
    //För testning
    public List<Tile> getSolutionList() {
        return solutionList;
    }

    //För inkapsling
    public int getCounter() {
        return counter;
    }

}