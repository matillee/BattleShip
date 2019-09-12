package main;

import java.util.*;

import Ships.*;

/**
 * Creating the board. One board used when placing ships, and one when shooting
 * them in the game
 */
public class OriginalBoard {

	/****** MUTAL *****/

	private ArrayList<Ship> ships;

	private Scanner scan = new Scanner(System.in);

	private int[] horisontal = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
	private int[] vertical = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

	/**
	 * The constructor creates a board and the coordinates for the boards are placed
	 * in to two HashMaps.
	 */
	public OriginalBoard() {
		ships = new ArrayList<Ship>();
		gameBoard = new HashMap<Integer, Coordinate>();
		shotPlace = new ArrayList<Integer>();
		placeShipsBoard = new HashMap<Integer, Coordinate>();

		for (int horisontals : horisontal) {
			for (int verticals : vertical) {
				int key = (horisontals * 10) + verticals;
				Coordinate cor = new Coordinate(key);
				gameBoard.put(key, cor);
				placeShipsBoard.put(key, cor);
			}
		}
	}

	/**
	 * Used when the computer needs a random coordinate in the board. Returns a
	 * random integer that fits inside the board.
	 */
	public int getRandomNum() {

		int randomPlacement;
		Random ram = new Random();
		randomPlacement = ram.nextInt(100);

		return randomPlacement;
	}

	/****** GAMEBOARD *******/

	// used in the game to print game board (((((to hide ships that have not been
	// shot)))))
	private HashMap<Integer, Coordinate> gameBoard;

	// used to optimize shootboard(), shot coordinates are placed here
	private ArrayList<Integer> shotPlace;

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
		return this.ships.isEmpty();
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
			if (gameBoard.containsKey(place) == true) {

				// Convert place into a coordinate
				Coordinate cor = gameBoard.get(place);

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

	/**
	 * Prints the board during the game, used to hide the ships until they've been
	 * shot.
	 */
	public void printGameBoard() {

		System.out.println("   0  1  2  3  4  5  6  7  8  9");

		for (int l = 0; l < 10; l++) {

			System.out.print(l + ("  "));
			for (int n = 0; n < 10; n++) {
				int key = (horisontal[l] * 10) + (vertical[n]);
				Coordinate cor = gameBoard.get(key);
				System.out.print(cor.gamePrint() + ("  "));
				if (n == 10) {
					System.out.println();
				}
			}
			System.out.println();
		}
		System.out.println();
	}

	/***** PLACEBOARD ******/

	// when user places ship, nearby coordinates are removed from shipsPlaced to
	// prevent user from placing ships incorrectly.
	private HashMap<Integer, Coordinate> placeShipsBoard;

	private int numberOfSquares = 20;

	/**
	 * Used when user is placing the ships, and you want ships to be visible
	 */
	public void printPlacingBoard() {

		System.out.println("   0  1  2  3  4  5  6  7  8  9");

		for (int l = 0; l < 10; l++) {

			System.out.print(l + ("  "));
			for (int n = 0; n < 10; n++) {
				int key = (horisontal[l] * 10) + (vertical[n]);
				Coordinate cor = this.gameBoard.get(key);
				System.out.print(cor.placementPrint() + ("  "));
				if (n == 10) {
					System.out.println();
				}
			}
			System.out.println();
		}
		System.out.println();
	}

	public void autoPlacementShip() {

		for (int i = 0; i < 10; i++) {

			int randomPlacement = getRandomNum();
			placeShipComputer(randomPlacement);
			if (ships.size() != (i + 1)) {
				i++;
			}

		}

	}

	/**
	 * Places ships in the board for the computer.
	 */
	private void placeShipComputer(int place) {

		/* (Funderade på att göra skepp av olika storlekar men blev för tidskrävande)
		 * int length; Random ram = new Random();
		 * 
		 * do { length = ram.nextInt(6);
		 * 
		 * } while(length > 1);
		 */

		int length = 2;

		if (length > (10 - (place % 10))) {
			return;
		}

		if (placeShipsBoard.containsKey(place) != true) {

			return;
		} else {
			// arbitraryShip ship = new arbitraryShip(length);
			SmallShip ship = new SmallShip();
			ships.add(ship);

			for (int i = 0; i < length; i++) {
				Coordinate cor = gameBoard.get(place + i);
				cor.addShip(ship);

				remove((place + i));

			}
		}
	}

	/**
	 * Method is used to place the players ships.
	 */
	public void placeShip() {
		do {
			System.out.println(": You have " + this.numberOfSquares + " left to place into your board.");

			placeShipUser();

			// Predetermined number of squares that the player has to decide where
			// to put them.
		} while (numberOfSquares > 0);
	}

	/**
	 * Places the ships for the users.
	 */
	private void placeShipUser() {

		Integer inputRow = null;
		Integer inputCol = null;
		Integer length = null;

		if (numberOfSquares == 1) {
			System.out.println("You only have one square left");
			length = 1;
		} else {

			if (numberOfSquares > 5) {
				System.out.println("How long will your ship be? " + "Please enter an Integer between 2 and 5.");
			} else {
				System.out.println("How long will your ship be? " + "Please enter an Integer between 2 and "
						+ numberOfSquares + ".");
			}

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
		} else if (length > numberOfSquares) {
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

			Integer placeInteger = (inputRow * 10) + inputCol;

			int place = placeInteger.intValue();

			System.out.println("Write H if you'd like to place the ship horisontally, else write V");

			String input = scan.next();

			if (input.equalsIgnoreCase("H")) {

				if (inputCol + length > 10) {
					System.out.println("The ship does not fit inside the board");
					System.out.println(" ");
					return;
				}

				if (placeShipsBoard.containsKey(place) == false) {

					System.out.println("This place is already taken. \n");
					return;

				} else {

					ArbitraryShip ship = new ArbitraryShip(length);
					ships.add(ship);

					for (int i = 0; i < length; i++) {

						Coordinate cor = gameBoard.get(place + i);
						cor.addShip(ship);
						remove((place + i));

					}
				}

			} else if (input.equalsIgnoreCase("V")) {

				if (inputRow + length > 10) {
					System.out.println("The ship does not fit inside the board");
					System.out.println(" ");
					return;
				}

				if (placeShipsBoard.containsKey(place) != true) {

					System.out.println("This place is already taken. \n");
					return;

				} else {

					ArbitraryShip ship = new ArbitraryShip(length);
					ships.add(ship);

					for (int i = 0; i < length; i++) {
						Coordinate cor = gameBoard.get(place + (i * 10));
						cor.addShip(ship);
						remove((place + i));
					}
				}

			} else {
				System.out.println("You did not write H or V.");
				return;
			}

			numberOfSquares = numberOfSquares - length;
			printPlacingBoard();
		}

	}

	/**
	 * This method is used to remove nearby coordinates from the shipsPlaced
	 * ArrayList
	 * 
	 * @param place
	 *            is the coordinate where the ship has been placed
	 */
	private void remove(int place) {
		hashRemove(place);
		hashRemove((place + 1));
		hashRemove((place - 1));
		hashRemove((place + 9));
		hashRemove((place - 9));
		hashRemove((place + 10));
		hashRemove((place - 10));
		hashRemove((place + 11));
		hashRemove((place - 11));
	}

	/**
	 * Removes the coordinate from the HashMap if it exists.
	 */
	private void hashRemove(int place) {
		if (placeShipsBoard.containsKey(place) == true) {
			placeShipsBoard.remove(place);
		}
	}

}
