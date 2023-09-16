package main;
import Ships.*;

/**
 * Coordinate is used when printing and shooting the board.
 */
public class Coordinate {

	private char print;
	private String ship;
	private int place;
	private Ship myShip;
	
	/**
	 * The constructor needs to know where the coordinate is placed in the board.
	 */
	public Coordinate(int place) {
		this.place = place;
		this.print = '~';
		this.ship = "~";
	}
	
	public Ship getShip() {
		return myShip;
	}
	
	
	/**
	 * returns the place of the coordinate as an int.
	 */
	public int getPlace() {
		return this.place;
	}
	
	/**
	 * When a ship is placed in the coordinate.
	 */
	public void addShip(Ship _ship) {
		this.ship = "x";
		myShip = _ship;
	}

	/**
	 * Method to print where you have placed the ship.
	 */
	public String placementPrint() {
		
		String print = this.ship;
		return print;
	}
	
	/**
	 * To print the right sign at game-play. 
	 */
	public char gamePrint() {
		return print;
	}
	
	/**
	 * If the coordinate is shot
	 * @return true if coordinate contains a ship, else false
	 */
	public boolean hasShip() {
		if (this.ship.equals("x")) {
			this.print = 'X'; // Hit
			myShip.reduceLife();
			return true;
		
		} else {
			this.print = '*'; // Miss
			return false;
			
		}
	}

}


