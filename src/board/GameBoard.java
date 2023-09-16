package board;

import java.util.ArrayList;
import java.util.HashMap;

import Ships.Ship;
import main.Coordinate;

public class GameBoard extends Board {

    // used to optimize shootboard(), shot coordinates are placed here
    private ArrayList<Integer> shotPlace = new ArrayList<Integer>();

    public GameBoard(HashMap<Integer, Coordinate> gameBoard){
        super();
        this.board = gameBoard;
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
	 * @param place
	 *            that is shot
	 * 
	 * @return true if hit, false if miss
	 */
	public boolean shootBoard(int place, String playerBeingShotName, String shooterName) {

		// Check if coordinate has been shot before.
		if (shotPlace.contains(place) == false) {
			// if not, add to coordinates that have been shot
			shotPlace.add(new Integer(place));

			// Check if place can be converted into a coordinate.
			if (board.containsKey(place) == true) {

				// Convert place into a coordinate
				Coordinate cor = board.get(place);

				// Check if there lies a ship at that coordinate
				if (cor.isShot()) {
					System.out.println(playerBeingShotName + "'s ship was hit!");

					Ship corShip = cor.getShip();

					if (corShip.hasSunk()) {
						ships.remove(corShip);
						System.out.println("The ship has sunk!");
					}

					return true;

				} else {
					System.out.println(shooterName + " missed!");
				}
			}
		}

		return false;
	}


    
}
