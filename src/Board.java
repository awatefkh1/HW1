import java.util.Arrays;

public class Board {
    private final int width;//n
    private final int height;//m
    private Tile[][] tiles;//2d array of the tiles

    /**
     * constructor for board
     * @param tiles array of tiles.
     * @param height height of tiles
     * @param width width of tiles
     */
    public Board(Tile[][] tiles, int height, int width){
        this.height = height;
        this.width = width;
        this.tiles = new Tile[height][width];
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                this.tiles[i][j] = tiles[i][j];
            }
        }
    }

    /**
     * constructor that copies a board.
     * @param board the board to copy.
     */
    public Board(Board board){
        this.width = board.width;
        this.height = board.height;
        this.tiles = new Tile[height][width];
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                this.tiles[i][j] = board.getTiles()[i][j];
            }
        }
    }

    /**
     * constructor
     * @param boardStr a string that represents a board.
     */
    public Board(String boardStr){
        String[] rows = boardStr.split("\\|");
        this.height = rows.length;
        this.width = rows[0].split(" ").length;
        this.tiles = new Tile[height][width];
        for(int i = 0; i < height; i++) {
            String[] splitRow = rows[i].split(" ");
            for (int j = 0; j < splitRow.length; j++) {
                if (!splitRow[j].equals("_")) {
                    this.tiles[i][j] = new Tile(Integer.parseInt(splitRow[j]));
                }
                else{
                    this.tiles[i][j] = new Tile(0);
                }
            }
        }
    }

    /**
     * checks if two boards are the same
     * @param other the second board
     * @return true if equals, false otherwise.
     */
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

    /**
     * returns width of board.
     * @return width
     */
    public int getWidth() {
        return width;
    }

    /**
     * returns height of board.
     * @return height
     */
    public int getHeight() {
        return height;
    }

    /**
     * returns the array of tiles
     * @return array of tiles.
     */
    public Tile[][] getTiles() {
        return tiles;
    }

    /**
     * sets a tile from the board
     * @param tile the tile being moved.
     * @param x the x of the new location
     * @param y the y of the new location
     */
    public void setTile(Tile tile, int x, int y){
        int[] location;
        location = this.findTile(tile.getValue());
        int i = location[0];
        int j = location[1];
        Tile temp = this.tiles[x][y];
        this.tiles[i][j] = temp;
        this.tiles[x][y] = tile;
    }

    /**
     * finds the location of a tile
     * @param value value of the tile
     * @return location of the tile in the array.
     */
    public int[] findTile(int value) {
        int[] location = new int[2];
        for(int i = 0; i < this.height; i++){
            for(int j = 0; j < this.width; j++){
                if(this.tiles[i][j].getValue() == value) {
                    location[0] = i;
                    location[1] = j;
                }
            }
        }
        return location;
    }
}
