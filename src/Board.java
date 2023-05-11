import java.util.Arrays;

public class Board {
    private int width;//n
    private int height;//m
    private Tile[][] tiles;

    public Board(String boardStr){
        String[] rows = boardStr.split("|");
        this.height = rows.length;
        this.width = (rows[0].length()+1)/2;
        this.tiles = new Tile[height][width];
        for(int i = 0; i < height; i++) {
            String[] splitRow = rows[i].split(" ");
            for (int j = 0; j < rows[i].length(); j++) {
                if (splitRow[j] != "_") {
                    this.tiles[i][j] = new Tile(Integer.parseInt(splitRow[j]));
                }
            }
        }
    }
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Board)) {
            return false;
        }
        Board board = (Board) other;
        return Arrays.deepEquals(tiles, board.tiles);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(tiles);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public int[] findEmpty() {
        int[] location = new int[2];
        for(int i = 0; i < this.height; i++){
            for(int j = 0; j < this.width; j++){
                if(tiles[i][j] == null) {
                    location[0] = i;
                    location[1] = j;
                }
            }
        }
        return location;
    }

}
