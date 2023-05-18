import java.util.Arrays;

public class Board {
    private int width;//n
    private int height;//m
    private Tile[][] tiles;

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

    public void setTile(Tile tile, int x, int y){
        int[] location = new int[2];
        location = this.findTile(tile.getValue());
        int i = location[0];
        int j = location[1];
        Tile temp = tiles[x][y];
        this.tiles[i][j] = temp;
        this.tiles[x][y] = tile;
    }

    public int[] findTile(int value) {
        int[] location = new int[2];
        for(int i = 0; i < this.height; i++){
            for(int j = 0; j < this.width; j++){
                if(tiles[i][j].getValue() == value) {
                    location[0] = i;
                    location[1] = j;
                }
            }
        }
        return location;
    }
}
