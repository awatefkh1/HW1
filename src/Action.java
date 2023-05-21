public class Action {
    private final Tile tile;//the tile that we want to move
    private final Direction direction;//the direction that we want to move the tile to

    /**
     * constructor
     * @param tile the tile the action is preformed on.
     * @param direction the direction the tile is moved.
     */
    public Action(Tile tile, Direction direction){
        this.tile = tile;
        this.direction = direction;
    }

    /**
     * returns a string of the action.
     * @return string that describes the action.
     */
    @Override
    public String toString() {
        return "Move " + this.tile.getValue() + " " + this.direction.toString().toLowerCase();
    }

    /**
     * returns the action the tile is preformed on.
     * @return the tile of the action.
     */
    public Tile getTile(){
        return this.tile;
    }

    /**
     * returns the direction of the action.
     * @return the direction of the action.
     */
    public Direction getDirection(){
        return this.direction;
    }
}
