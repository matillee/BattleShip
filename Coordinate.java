/**
 * Coordinate is used to print the board.
 * 
 */
public class Coordinate {

	private char print;
	private String ship;
	private int place;
	
	private static char hit = 'X';
	private static char miss = '*';
	
	/**
	 * The constructor needs to know where the coordinate is placed in the board.
	 */
	public Coordinate(int place) {
		this.place = place;
		this.print = '~';
		this.ship = "~";
	}

	/**
	 * Gets the place of the coordinate.
	 */
	public int getPlace() {
		return this.place;
	}

	/**
	 * @return true, if there is a ship hiding at the coordinate.
	 */
	public boolean containsShip() {
		if (this.ship.equals("x")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * When a ship is placed in the coordinate.
	 */
	public void addShip() {
		this.ship = "x";
	}

	/**
	 * Method to print where you have placed the ship.
	 */
	public String myPrint() {
		String print = this.ship;
		return print;
	}
	
	/**
	 * If the coordinate is shot
	 * @return a string indicated if it was a hit or a miss
	 */
	public boolean gotHit() {
		if (containsShip() == true) {
			this.print = 'X'; // hit
			return true;
			//return new RoundResult(true,"'s ship was hit!" );
		} else {
			this.print = '*'; // Miss
			return false;
			//return new RoundResult(false," missed!");
		}
	}
	
	public boolean isHit() {
		return this.print == hit;
	}
	
	public boolean isMiss() {
		return this.print == miss;
	}
	
	
	/**
	 * To print the right sign at game-play. 
	 */
	public char print() {
		return print;
	}
}


