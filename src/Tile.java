public class Tile {
    private int locationI;
    private int locationJ;
    private int value;

    public Tile(int value, int locationI, int locationJ){
        this.locationI = locationI;
        this.locationJ = locationJ;
        this.value = value;
    }

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

    public int getValue() {
        return value;
    }

    public int getLocationJ() {
        return locationJ;
    }

    public int getLocationI() {
        return locationI;
    }
}