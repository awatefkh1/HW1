public class Action {
    private Tile tile;
    private String direction;

    public Action(Tile tile, String direction){
        this.tile = tile;
        this.direction = direction;
    }

    @Override
    public String toString() {
        return "Move " + this.tile.getValue() + " " + this.direction;
    }

    public Tile getTile(){
        return this.tile;
    }

    public String getDirection(){
        return this.direction;
    }
}
