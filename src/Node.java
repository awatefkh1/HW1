public class Node {
    private final State state;//the state of the board
    private Node parent;//the parent node of the current node
    private Action action;//the previous action

    /**
     * constructor to the first node
     * @param currentState the first state.
     */
    public Node(State currentState){
        this.state = currentState;
    }

    /**
     * constructor.
     * @param currentState the new state.
     * @param parent the parent node of the new node.
     * @param lastAction the last action preformed that resulted in the new state.
     */
    public Node(State currentState, Node parent, Action lastAction){
        this.state = currentState;
        this.parent = parent;
        this.action = lastAction;
    }

    /**
     * expands the current node to different nodes.
     * @return array of new possible nodes.
     */
    public Node[] expand(){
        Action[] possibleActions = this.state.actions();
        int length = possibleActions.length;
        Node[] newNodes = new Node[length];
        for(int i = 0; i < length; i++){
            State newState = new State(this.state);
            newNodes[i] = new Node(newState.result(possibleActions[i]),this ,possibleActions[i]);
        }
        return newNodes;
    }


    /**
     * returns the action of the node.
     * @return the action.
     */
    public Action getAction(){
        return this.action;
    }

    /**
     * returns the parent node.
     * @return the parent node.
     */
    public Node getParent(){
        return this.parent;
    }

    /**
     * returns the current state
     * @return the state.
     */
    public State getState(){
        return this.state;
    }


    /**
     * creates the goal board from the current board.
     * @param board the current board
     * @return the goal board.
     */
    private Board goalBoard(Board board){
        int height = board.getHeight();
        int width = board.getWidth();

        Tile[][] tiles = new Tile[height][width];
        int count = 1;
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                tiles[i][j] = new Tile(count++);
            }
        }
        tiles[height-1][width-1] = new Tile(0);
        return new Board(tiles, height, width);
    }

    /**
     * this method checks for linear conflicts in the current node and calculates the number of additional steps
     * @return number of steps added to the heuristic value
     */
    public int linear_conflicts(){
        int height = this.state.getBoard().getHeight();
        int width = this.state.getBoard().getWidth();
        int counter = 0;
        Tile[][] tiles = this.state.getBoard().getTiles();
        for(int i = 0; i < height; i ++){
            int max = 0;
            for(int j = 0; j < width; j++){
                if(tiles[i][j].getValue() < max && tiles[i][j].getValue() > i*width &&
                        tiles[i][j].getValue() <= ((i+1)*width)){
                    counter+=2;
                    break;
                }
                if(tiles[i][j].getValue() > i*width && tiles[i][j].getValue() <= ((i+1)*width))
                    max = tiles[i][j].getValue();
            }
        }
        for(int i = 0; i < width; i ++){
            int max = 0;
            for(int j = 0; j < height; j++){
                if(tiles[j][i].getValue() < max && tiles[j][i].getValue()%width == i){
                    counter+=2;
                    break;
                }
                if(tiles[j][i].getValue()%width == i)
                    max = tiles[j][i].getValue();
            }
        }
        return counter;
    }

    /**
     * calculates the heuristic value of the current node.
     * @return the heuristic value of the node.
     */
    public int heuristicValue(){
        Board currentBoard = this.state.getBoard();
        Board goalBoard = goalBoard(currentBoard);
        int heuristic = 0;
        Tile[][] currentTiles = currentBoard.getTiles();
        Tile[][] goalTiles = goalBoard.getTiles();

        for (int i = 0; i < currentBoard.getHeight(); i++) {
            for (int j = 0; j < currentBoard.getWidth(); j++) {
                if (currentTiles[i][j].getValue() != 0) {
                    int currentValue = currentTiles[i][j].getValue();
                    if (goalTiles[i][j].getValue() == 0 || currentValue != goalTiles[i][j].getValue()) {
                        int[] tileLocation = goalBoard.findTile(currentValue);
                        //manhattan distance
                        heuristic += abs(tileLocation[0] - i) + abs(tileLocation[1] - j);

                    }
                }
            }
        }
        heuristic += linear_conflicts();
        return heuristic;
    }

    /**
     * calculates the absolut value.
     * @param value the value
     * @return absolut value of the value.
     */
    private static int abs(int value){
        if(value < 0){
            return value*(-1);
        }
        return value;
    }

}
