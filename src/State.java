import java.util.concurrent.LinkedBlockingDeque;

public class State {
    private Board board;

    public boolean isGoal(){
        int count = 1;
        Tile[][] tiles = this.board.getTiles();
        for(int i = 0; i < this.board.getHeight(); i++){
            for(int j = 0; j < this.board.getWidth(); j++){
                if(tiles[i][j].getValue() != count){
                    return false;
                }
                count++;
            }
        }
        return true;
    }

    public Action[] actions(){
        int[] location = board.findEmpty();
        int i = location[0];
        int j = location[1];
        int n;

        //corner
        if((i == board.getHeight()-1 || i == 0) && (j == board.getWidth()-1 || j == 0)){
            n = 2;
        }
        //side
        else if(((i == board.getHeight()-1 || i == 0) || (j == board.getWidth()-1 || j == 0))){
            n = 3;
        }
        else{
            n = 4;
        }

        Action[] actions = new Action[n];

        //up
        if(inBounds(i-1, j)){
            actions[0] = new Action(board.getTiles()[i-1][j], Direction.UP);
        }
        //down
        if(inBounds(i+1, j)){
            actions[1] = new Action(board.getTiles()[i+1][j], Direction.DOWN);
        }
        //right
        if(inBounds(i, j+1)) {
            actions[2] = new Action(board.getTiles()[i][j-1], Direction.RIGHT);
        }
        //left
        if(inBounds(i, j-1)){
            actions[3] = new Action(board.getTiles()[i][j+1], Direction.LEFT);
        }

        return actions;

    }

    public boolean inBounds(int i, int j){
        return (i >= 0 && j >= 0 && i < board.getHeight() && j < board.getWidth());
    }

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

    public State result(Action action){
        int i = action.getTile().getLocationI();
        int j = action.getTile().getLocationJ();
        moveTile(this.board.getTiles()[i][j], action.getDirection(), i, j);
        return new State(this.board);
    }


    public void moveTile(Tile tile, Direction direction, int locationI, int locationJ){
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
        this.board.setTile(tile, newI, newJ);
    }

    public Board getBoard(){
        return this.board;
    }
}
