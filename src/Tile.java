public class Tile {
    private final int value;//the value of the tile

    /**
     * constructor
     * @param value - the value of the tile.
     */
    public Tile(int value){
        this.value = value;
    }

    /**
     * checks if two tiles are equal.
     * @param other second tile
     * @return true if tiles equal, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Tile)) {
            return false;
        }
        Tile tile = (Tile) other;
        return value == tile.value;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(value);
    }

    /**
     * returns value of tile.
     * @return value of tile.
     */
    public int getValue() {
        return value;
    }

}