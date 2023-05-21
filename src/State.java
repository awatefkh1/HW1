public class State {
    private final Board board;//the current board

    /**
     * first state constructor.
     * @param board the initial board.
     */
    public State(Board board){
        this.board = new Board(board);
    }

    /**
     * constructor that copies a state.
     * @param state the state to be copied.
     */
    public State(State state){
        this.board = new Board(state.board);
    }

    /**
     * checks if the current state is the goal state of the board.
     * @return true if the state is he goal state, false otherwise.
     */
    public boolean isGoal(){
        int count = 1;
        Tile[][] tiles = this.board.getTiles();
        for(int i = 0; i < this.board.getHeight(); i++){
            for(int j = 0; j < this.board.getWidth(); j++){
                if(tiles[i][j].getValue() == 0) {
                    return i == this.board.getHeight() - 1 && j == this.board.getWidth() - 1;
                }
                if(tiles[i][j].getValue() != count){
                    return false;
                }
                count++;
            }
        }
        return true;
    }

    /**
     * returns the possible actions that can be preformed from the current state.
     * @return array of possible actions.
     */
    public Action[] actions(){
        int[] location = this.board.findTile(0);
        int i = location[0];
        int j = location[1];
        int n = 0;
        if(inBounds(i-1,j)){
            n++;
        }
        if(inBounds(i+1,j)){
            n++;
        }
        if(inBounds(i,j-1)){
            n++;
        }
        if(inBounds(i,j+1)){
            n++;
        }

        Action[] actions = new Action[n];
        int count = 0;
        //up
        if (inBounds(i - 1, j)) {
            actions[count] = new Action(this.board.getTiles()[i - 1][j], Direction.DOWN);
            count++;
        }
        //down
        if (inBounds(i + 1, j)) {
            actions[count] = new Action(this.board.getTiles()[i + 1][j], Direction.UP);
            count++;
        }
        //right
        if (inBounds(i, j + 1)) {
            actions[count] = new Action(this.board.getTiles()[i][j + 1], Direction.LEFT);
            count++;
        }
        //left
        if (inBounds(i, j - 1)) {
            actions[count] = new Action(this.board.getTiles()[i][j - 1], Direction.RIGHT);
        }

        return actions;

    }

    /**
     * checks if a location is in bounds of the board.
     * @param i x of the location
     * @param j y of the location
     * @return true if in bounds, false otherwise.
     */
    public boolean inBounds(int i, int j){
        return (i >= 0 && j >= 0 && i < this.board.getHeight() && j < this.board.getWidth());
    }

    /**
     * checks if two states are equal.
     * @param other the other state
     * @return true if equal, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof State)) {
            return false;
        }
        State otherState = (State) other;
        return board.equals(otherState.board);
    }

    @Override
    public int hashCode() {
        return board.hashCode();
    }

    /**
     * returns a  new state which is the current state after an action was preformed on it.
     * @param action the action to be preformed on the state.
     * @return a new state after preforming the action.
     */
    public State result(Action action){
        int[] location;
        location = this.board.findTile(action.getTile().getValue());
        int i = location[0];
        int j = location[1];
        Board newBoard = new Board(this.board);
        moveTile(newBoard,newBoard.getTiles()[i][j], action.getDirection(), i, j);
        return new State(newBoard);
    }

    /**
     * moving a tile to a new location.
     * @param board the board where the tile is moved.
     * @param tile the tile to be moved.
     * @param direction the direction of the move.
     * @param locationI the current x location of the board
     * @param locationJ the current y location of the board.
     */
    public void moveTile(Board board, Tile tile, Direction direction, int locationI, int locationJ){
        int newI = locationI;
        int newJ = locationJ;
        switch (direction){
            case UP:
                newI--;
                break;
            case DOWN:
                newI++;
                break;
            case RIGHT:
                newJ++;
                break;
            case LEFT:
                newJ--;
                break;
        }
        if(inBounds(newI, newJ)){
            board.setTile(tile, newI, newJ);
        }
    }

    /**
     * returns the board of the state.
     * @return the board.
     */
    public Board getBoard(){
        return this.board;
    }
}
