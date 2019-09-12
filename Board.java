import java.util.*;

/**
 * Creating the board
 */
public class Board {

	private static int[] horisontal = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
	private static int[] vertical = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

	private ArrayList<Ship> ships = new ArrayList<Ship>();

	// used in the game to hide ships that have not been shot
	private HashMap<Integer, Coordinate> hiddenShips = new HashMap<Integer, Coordinate>();

	//Coordinates are removed from HashMap to prevent user from placing ships incorrectly.
	private HashMap<Integer, Coordinate> shipsPlaced = new HashMap<Integer, Coordinate>();

	// keeps track of which coordinates have been shot
	private ArrayList<Coordinate> shotCoordinate = new ArrayList<Coordinate>();

	private Scanner scan = new Scanner(System.in);
	private int numberOfSquares = 20;

	/**
	 * The constructor creates a board and the coordinates for the boards are placed
	 * in to two HashMaps.
	 */
	public Board() {
		for (int verticals : vertical) {
			for (int horisontals : horisontal) {
				int key = horisontals + (verticals * 10);
				Coordinate cor = new Coordinate(key);
				hiddenShips.put(key, cor);
				shipsPlaced.put(key, cor);
			}
		}
	}

	/**
	 * @return the damage percent for every player.
	 */
	public float getDamagePercent() {
		int hit = 0;
		for (int i = 0; i < shotCoordinate.size(); i++) {
			Coordinate coordinate = shotCoordinate.get(i);
			System.out.println(coordinate.isHit());
			if (coordinate.isHit()) {
				hit++;
			}
		}

		return ((float) hit / numberOfSquares) * 100;
	}

	/**
	 * @return hit percent for every player.
	 */
	public float getHitPercent() {
		int hit = 0;
		for (int i = 0; i < shotCoordinate.size(); i++) {
			Coordinate coordinate = shotCoordinate.get(i);
			if (coordinate.isHit()) {
				hit++;
			}
		}
		return ((float) hit / shotCoordinate.size()) * 100;
	}

	/**
	 * Prints the board during the game, used to hide the ships until they've been
	 * shot.
	 */
	public void printBoard() {

		System.out.println("   0  1  2  3  4  5  6  7  8  9");

		for (int l = 0; l < 10; l++) {

			System.out.print(l + ("  "));
			for (int n = 0; n < 10; n++) {
				int key = (horisontal[l] * 10) + (vertical[n]);
				Coordinate cor = hiddenShips.get(key);
				System.out.print(cor.print() + ("  "));
				if (n == 10) {
					System.out.println();
				}
			}
			System.out.println();
		}
		System.out.println();
	}

	/**
	 * Used when you placing the ships, and don't want to hide them.
	 */
	public void printMyBoard() {

		System.out.println("   0  1  2  3  4  5  6  7  8  9");

		for (int l = 0; l < 10; l++) {

			System.out.print(l + ("  "));
			for (int n = 0; n < 10; n++) {
				int key = (horisontal[l] * 10) + (vertical[n]);
				Coordinate cor = shipsPlaced.get(key);
				System.out.print(cor.myPrint() + ("  "));
				if (n == 10) {
					System.out.println();
				}
			}
			System.out.println();
		}
		System.out.println();
	}

	/**
	 * Method that returns number of squares you have left to fill with ships.
	 */
	public int getNumberOfSquares() {
		return numberOfSquares;
	}

	/**
	 * Method that returns number of non-hit squares.
	 */
	public int getNumberOfHits() {
		return (100 - hiddenShips.size());
	}

	/**
	 * Method to hit a coordinate (square).
	 * 
	 * @param place
	 * 
	 * @return a String if you have missed or hit.
	 */
	public boolean shot(int place) {

		// Check if place can be converted into a coordinate.
		if (hiddenShips.containsKey(place) == true) {

			// Convert place into a coordinate
			Coordinate cor = hiddenShips.get(place);

			// Check if coordinate has been shot before.
			if (shotCoordinate.contains(cor) != true) {
				// if not, add to coordinates that have been shot
				shotCoordinate.add(cor);

				// Check if there is a ship at that coordinate
				if (cor.gotHit() == true) {

					// Look for the right ship in the Arraylist.
					for (int i = 0; i < ships.size(); i++) {
						Ship ship = ships.get(i);

						if (ship.getPlace() == place) {
							// Removes "a live" for the ship at the same time as it
							// returns a boolean determining if the ship has died.
							boolean dead = ship.shot();
							if (dead == true) {
								ships.remove(ship);
							}
						}
					}
				}

			}
		}

		return false;
	}

	/**
	 * Methods that returns true if there is no more ships to shoot down.
	 */
	public boolean win() {
		return ships.isEmpty();
	}

	/**
	 * Method that returns the coordinate for the wished place.
	 */
	public Coordinate getCoordinate(int place) {
		return hiddenShips.get(place);
	}

	/**
	 * Used when the computer needs a random coordinate in the board. Returns a
	 * random integer that fits inside the board.
	 */
	public int randomShip() {

		int randomPlacement;

		Random ram = new Random();

		randomPlacement = ram.nextInt(98);

		return randomPlacement;
	}

	/**
	 * Puts the ships in the board for the computer.
	 */
	public void putShip(int place) {

		int length = 2;
		int fel;

		if (length > (10 - (place % 10))) {

			return;
		}

		if (shipsPlaced.containsKey(place) != true) {
			fel = emptySpace(place, length);
			if (fel > 0) {
				return;
			}
		} else {

			if (length > (10 - (place % 10))) {
				return;
			}

			if (shipsPlaced.containsKey(place) != true) {
				fel = emptySpace(place, length);
				if (fel > 0) {
					return;
				}
			} else {

				for (int i = 0; i < length; i++) {
					Coordinate cor = hiddenShips.get(place + i);
					cor.addShip();
					smallShip ship = new smallShip(place);
					ships.add(ship);

					remove((place + i));

				}
			}
		}

	}

	/**
	 * Places the ships for the users.
	 */
	public void putShipUser() { // KOlla så den inte är utanför boarden!!

		Integer inputRow = null;
		Integer inputCol = null;
		Integer length = null;

		if (getNumberOfSquares() == 1) {
			System.out.println("You only have one square left");
			length = 1;
		} else {

			System.out.println("How long will your ship be? " + "Please enter an Integer between 2 and 5.");

			while (length == null) {
				try {
					length = Integer.parseInt(scan.next());
				} catch (NumberFormatException e) {
					System.out.println("Please enter an integer");
				}
			}
		}
		// if the user enters the wrong number
		if (!(length < 6 && length > 0)) {
			System.out.println("You did not enter an Integer between 2 and 5");
			return;
		} else if (length > this.numberOfSquares) {
			System.out.println("You don't have enough squares left");
			return;

			// if the user enters correct numbers
		} else {
			System.out.println("Enter where you'd like to place your ship. Please enter row:");

			while (inputRow == null) {
				try {
					inputRow = Integer.parseInt(scan.next());
				} catch (NumberFormatException e) {
					System.out.println("Please enter an integer");
					System.out.println("Row:");
				}
			}

			System.out.println("Please enter column:");

			while (inputCol == null) {
				try {
					inputCol = Integer.parseInt(scan.next());
				} catch (NumberFormatException e) {
					System.out.println("Please enter an integer");
					System.out.println("Column:");
				}
			}

			if ((inputRow > 10 || inputRow < 0) || (inputCol > 10 || inputCol < 0)) {
				System.out.println("You can only choose between an Integer between 0 and 9");
				return;
			}

			Integer place = inputRow + (inputCol * 10);

			int fel = 0;

			System.out.println("Write H if you'd like to place the ship horisontally, else write V");

			String input = scan.next();

			if (input.equalsIgnoreCase("H")) {

				if (inputRow + length> 9) { //Funkar inte pga Integer??
					System.out.println("The ship does not fit inside the board");
					System.out.println(" ");
					return;
				}

				if (shipsPlaced.containsKey(place) != true) {// Dubbelkolla om den
					// funkar
					fel = emptySpace(place, length);
					if (fel > 0) {
						System.out.println("This place is already taken");
						return;
					}
				} else {

					for (int i = 0; i < length; i++) {
						Coordinate cor = shipsPlaced.get(place + i);
						cor.addShip();
						arbitraryShip ship = new arbitraryShip(length, place);
						ships.add(ship);
						remove((place + i));

					}
				}

			} else if (input.equalsIgnoreCase("V")) {

				if (inputCol + length > 9) {
					System.out.println("The ship does not fit inside the board");
					System.out.println(" ");
					return;
				}

				if (shipsPlaced.containsKey(place) != true) {// kolla om den
					// funkar
					fel = emptySpace(place, (length * 10));

					if (fel > 0) {
						System.out.println("This place is already taken.");
						System.out.println(" ");
						return;
					}
				} else {
					for (int i = 0; i < length; i++) {

						Coordinate cor = shipsPlaced.get(place + (i * 10));
						cor.addShip();
						arbitraryShip ship = new arbitraryShip(length, place);
						ships.add(ship);

						remove((place + i));
					}
				}
			} else {
				System.out.println("You did not write H or V.");
				return;
			}

			this.numberOfSquares = numberOfSquares - length;
			printMyBoard();
		}

	}

	/**
	 * This method is used to remove nearby coordinates from the shipsPlaced ArrayList
	 * @param place is the coordinate where the ship has been placed
	 */
	private void remove(int place) {
		hashRemove(place);
		hashRemove((place + 1));
		hashRemove((place - 1));
		hashRemove((place + 9));
		hashRemove((place + 10));
		hashRemove((place + 11));
		hashRemove((place - 9));
		hashRemove((place - 10));
		hashRemove((place - 11));
	}

	/**
	 * Removes the coordinate from the HashMap if it exists.
	 */
	private void hashRemove(int place) {
		if (shipsPlaced.containsKey(place) == true) {

			shipsPlaced.remove(place);
		}
	}

	/**
	 * Methods examines if you can put a ship on the coordinate or not.
	 * 
	 * @param place
	 *            is the ships coordinate.
	 * 
	 * @param length
	 *            is the ships length.
	 * 
	 * @return amount of squares if there is already a ship there.
	 */
	private int emptySpace(int place, int length) {
		int fel = 0;
		int modifiedLegnth = length % 10;
		for (int i = 0; i <= modifiedLegnth; i++) {

			if (shipsPlaced.containsKey(place) != true) {
				fel++;
			}

			if (shipsPlaced.containsKey(place) != true) {
				fel++;
			}

			if (place > 1) {

				if (shipsPlaced.containsKey(place) != true) {
					fel++;
				}
			}
			if (place > 10) {

				if (shipsPlaced.containsKey(place) != true) {
					fel++;
				}
			}
			if (place < 90) {

				if (shipsPlaced.containsKey(place) != true) {
					fel++;
				}
			}

		}
		return fel;
	}

}
