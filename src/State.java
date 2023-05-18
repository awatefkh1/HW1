import java.util.concurrent.LinkedBlockingDeque;

public class State {
    private Board board;

    public State(Board board){
        this.board = new Board(board);
    }

    public State(State state){
        this.board = new Board(state.board);
    }

    public boolean isGoal(){
        int count = 1;
        Tile[][] tiles = this.board.getTiles();
        for(int i = 0; i < this.board.getHeight(); i++){
            for(int j = 0; j < this.board.getWidth(); j++){
                if(tiles[i][j].getValue() == 0) {
                    if(i == this.board.getHeight()-1 && j == this.board.getWidth()-1)
                        return true;
                    else
                        return false;
                }
                if(tiles[i][j].getValue() != count){
                    return false;
                }
                count++;
            }
        }
        return true;
    }

    public Action[] actions(){
        int[] location = board.findTile(0);
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
            actions[count] = new Action(board.getTiles()[i - 1][j], Direction.DOWN);
            count++;
        }
        //down
        if (inBounds(i + 1, j)) {
            actions[count] = new Action(board.getTiles()[i + 1][j], Direction.UP);
            count++;
        }
        //right
        if (inBounds(i, j + 1)) {
            actions[count] = new Action(board.getTiles()[i][j + 1], Direction.LEFT);
            count++;
        }
        //left
        if (inBounds(i, j - 1)) {
            actions[count] = new Action(board.getTiles()[i][j - 1], Direction.RIGHT);
            count++;
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
        int[] location = new int[2];
        location = this.board.findTile(action.getTile().getValue());
        int i = location[0];
        int j = location[1];
        Board newBoard = new Board(this.board);
        moveTile(newBoard,newBoard.getTiles()[i][j], action.getDirection(), i, j);
        return new State(newBoard);
    }


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

    public Board getBoard(){
        return this.board;
    }
}
