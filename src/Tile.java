import java.awt.*;

public class Tile {
    private int tileNumber;
    private Color tileColor;

    public Tile(int tileNumber) {
        this.tileNumber = tileNumber;
    }

    public Tile(int tileNumber, Color tileColor) {
        this.tileNumber = tileNumber;
    }

    public int getTileNumber() {
        return tileNumber;
    }

    public Color getTileColor() {
        return tileColor;
    }
    public void setTileColor(Color tileColor) { this.tileColor = tileColor; }
}
