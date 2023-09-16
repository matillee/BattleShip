package board;

import java.util.ArrayList;
import java.util.HashMap;

import Ships.Ship;
import main.Coordinate;

public class GameBoard extends Board {

    // used to optimize shootboard(), shot coordinates are placed here
    private ArrayList<Integer> shotPlace = new ArrayList<Integer>();

    public void setGameBoard(HashMap<Integer, Coordinate> gameBoard){
        this.board = gameBoard;
    }

    public HashMap<Integer, Coordinate> getGameBoard(){
        return this.board;
    }

	public void setShips(ArrayList<Ship> _ships){
		this.ships = _ships;
	}

	/**
	 * 
	 * @return this board's damage percent (percentage of shot squares)
	 */
	public float getDamagePercent() {
		float damagePercent = ((float) this.shotPlace.size() / (float) 100);
		return damagePercent;
	}

	/**
	 * used when computer shoots board, to prevent shooting the same place twice
	 * 
	 * @return ArrayList containing Integers that have been shot
	 */
	public ArrayList<Integer> getShotPlace() {
		return this.shotPlace;
	}

	/**
	 * Methods that returns true if there are no more ships to shoot down.
	 */
	public boolean isLoser() {
		return ships.isEmpty();
	}

	/**
	 * Method to used to shoot a coordinate (square).
	 * 
	 * @param place that is shot
	 * 
	 * @return true if hit, false if miss
	 */
	public boolean shootBoard(int place, String playerBeingShotName, String shooterName) {

		// Check if coordinate has been shot before.
        if (shotPlace.contains(place) == true) {
            return false;
        }
        // if not, add to coordinates that have been shot
        shotPlace.add(new Integer(place));

        // Check if place can be converted into a coordinate.
        if (this.board.containsKey(place) == false) {
            System.out.println(shooterName + " missed!");
            return false;
        }

        // Convert place into a coordinate
        Coordinate cor = this.board.get(place);

        // Check if there lies a ship at that coordinate
        if (cor.hasShip()) {
            System.out.println(playerBeingShotName + "'s ship was hit!");

            Ship corShip = cor.getShip();

            if (corShip.hasSunk()) {
                ships.remove(corShip);
                System.out.println("The ship has sunk!");
            }

            return true;

        }

		return false;
	}


    
}
