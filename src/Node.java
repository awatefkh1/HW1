public class Node {
    private State state;
    private Node parent;
    private Action action;

    //constructor to the first node
    public Node(State currentState){
        this.state = currentState;
    }
    public Node(State currentState, Node parent, Action lastAction){
        this.state = currentState;
        this.parent = parent;
        this.action = lastAction;
    }

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


    public Action getAction(){
        return this.action;
    }

    public Node getParent(){
        return this.parent;
    }

    public State getState(){
        return this.state;
    }

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

        Board goalBoard = new Board(tiles, height, width);
        return goalBoard;
    }

    public int heuristicValue(){
        Board currentBoard = this.state.getBoard();
        Tile[][] currentTiles = currentBoard.getTiles();
        Board goalBoard = goalBoard(currentBoard);
        Tile[][] goalTiles = goalBoard.getTiles();
        int heuristicValue = 0;
        for(int i = 0; i < currentBoard.getHeight(); i++){
            for(int j = 0; j < currentBoard.getWidth(); j++){
                if(currentTiles[i][j].getValue() != 0) {
                    int currentValue = currentTiles[i][j].getValue();
                    if (goalTiles[i][j].getValue() == 0 || currentValue != goalTiles[i][j].getValue()) {
                        int[] tileLocation = goalBoard.findTile(currentValue);
                        //manhattan distance
                        heuristicValue += abs(tileLocation[0] - i) + abs(tileLocation[1] - j);

                    }
                }
            }
        }
        return heuristicValue;
    }


    private static int abs(int value){
        if(value < 0){
            return value*(-1);
        }
        return value;
    }

}
