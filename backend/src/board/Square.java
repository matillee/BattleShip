package board;

import java.util.Map;

public class Square {

    int coordinate;
    boolean containsShip;
    boolean hasBeenShot;

    private static final Map<String, String> printStrings = Map.of(
        "normal", "~",
        "ship", "X",
        "miss", "*"
    );

    //String[] printStrings = {"~","*", "X"};
    String[] states = {"duringPlacement","unShotSquare", "missedSquare", "hitSquare"};

    public Square(int _coordinate) {
		this.coordinate = _coordinate;
        this.containsShip = false;
        // When the player places their ships, they still need to see them 
        // To solve that, we pretend all Squares have been shot until the game starts for now.
        // TODO: Add player authorization.
        this.hasBeenShot = true;
	}

    /**
	 * returns the place of the coordinate as an int.
	 */
	public int getCoordinate() {
		return this.coordinate;
	}

    protected void addShip(){
        this.containsShip = true;
    }

    // TODO: Remove after authorization to own board added.
    protected void finishedPlacement(){
        this.hasBeenShot = false;
    }
    
    public String print(){
        if(!hasBeenShot){
            return printStrings.get("normal");
        }

        if(containsShip){
            return printStrings.get("ship");
        }

        return printStrings.get("miss");

    }
    
}
