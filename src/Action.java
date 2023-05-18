public class Action {
    private Tile tile;
    private String direction;

    public Action(Tile tile, String direction){
        this.tile = tile;
        this.direction = direction;
    }

    @Override
    public String toString() {
        return "Move " + this.tile.getValue() + " " + this.direction.toString().toLowerCase();
    }

    public Tile getTile(){
        return this.tile;
    }

    public Direction getDirection(){
        return this.direction;
    }
}
