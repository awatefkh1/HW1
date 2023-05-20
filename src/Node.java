public class Node {
    private State state;
    private Node parent;
    private Action action;
    private int heuristicValue;

    //constructor to the first node
    public Node(State currentState){
        this.state = currentState;
        this.heuristicValue = heuristicValue();
    }
    public Node(State currentState, Node parent, Action lastAction){
        this.state = currentState;
        this.parent = parent;
        this.action = lastAction;
        this.heuristicValue = heuristicValue();
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

    public int getHeuristicValue() { return  this.heuristicValue;}

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

    public int linear_conflicts(){
        int inversions = 0;
        int counter = 0;
        int height = this.state.getBoard().getHeight();
        int width = this.state.getBoard().getWidth();
        int[] to_1d = new int[width * height];
        //from 2d to 1d
        for(int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                to_1d[counter] = this.state.getBoard().getTiles()[i][j].getValue();
                counter++;
            }
        }
        //rows
        for(int i = 0; i < height; i++){
            for(int j = i*width; j <(i+1)*width ; j++){
                for(int k = j+1; k < (i+1)*width; k++) {
                    if (to_1d[j] > to_1d[k]) {
                        inversions++;
                    }
                }
            }
        }
        //columns
        for(int i = 0 ; i < width; i++){
            for(int j = i; j < to_1d.length; j+=width){
                for(int k = j+1; k < to_1d.length; k+= width) {
                    if (to_1d[j] > to_1d[k]) {
                        inversions++;
                    }
                }
            }
        }
        return inversions*2;
    }

    public int heuristicValue(){
        Board currentBoard = this.state.getBoard();
        Board goalBoard = goalBoard(currentBoard);
        int heuristic = 0;
//        if(this.action != null){
//            int currentValue = this.action.getTile().getValue();
//            int previousHueristic = this.parent.getHeuristicValue();
//            int[] tileLocationGoal = goalBoard.findTile(currentValue);
//            int[] tileLocationCurrent = currentBoard.findTile(currentValue);
//            int[] tileLocationPrevious = currentBoard.findTile(currentValue);
//            //manhattan distance
//            previousHueristic -= abs(tileLocationGoal[0] - tileLocationPrevious[0]) + abs(tileLocationGoal[1] - tileLocationPrevious[1]);
//            heuristic += previousHueristic + abs(tileLocationGoal[0] - tileLocationCurrent[0]) + abs(tileLocationGoal[1] - tileLocationCurrent[1]);
//        }
//        else {
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
        //}
        //heuristicValue += linear_conflicts();
        return heuristic;
    }


    private static int abs(int value){
        if(value < 0){
            return value*(-1);
        }
        return value;
    }

}
