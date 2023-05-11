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
        for(int l = -1; l < 2; l+=2){
            for(int k = -1; k < 2; k+=2){
                if(inBounds(i+k, j)){

                }
            }
            if(inBounds(i, j+l)){

            }
        }

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
}
