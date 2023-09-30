package board;

import java.util.ArrayList;
import java.util.HashMap;

import Ships.ArbitraryShip;
import Ships.Ship;
import Ships.SmallShip;
import main.Coordinate;

public class PlacementBoard extends Board{

    // Predetermined number of squares that the player fills with ships
    private int numberOfSquares = 20;
    private HashMap<Integer, Coordinate> gameBoard = new HashMap<Integer, Coordinate>();

	public PlacementBoard(HashMap<Integer, Coordinate> _gameBoard){
		super();
        this.gameBoard = _gameBoard;
    }

    public HashMap<Integer, Coordinate> getGameBoard(){
        return this.gameBoard;
    }

	public ArrayList<Ship> getShips(){
		return this.ships;
	};

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

		/* (Funderade p� att g�ra skepp av olika storlekar men blev f�r tidskr�vande)
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

		if (this.board.containsKey(place) != true) {
			return;
		}
        // arbitraryShip ship = new arbitraryShip(length);
        SmallShip ship = new SmallShip();
        ships.add(ship);

        for (int i = 0; i < length; i++) {
            Coordinate cor = gameBoard.get(place + i);
            cor.addShip(ship);

            remove((place + i));

        }
	}

	/**
	 * Method is used to place the players ships.
	 */
	public void placeShip(String playerName) {
		do {
			System.out.println(playerName + ": You have " + this.numberOfSquares + " left to place into your board.");
			placeShipUser();

		} while (numberOfSquares > 0);
	}

	/**
	 * Places the ships for the users.
	 * Comment: most code not needed if graphical interface
	 */
	private void placeShipUser() {

		Integer inputRow = null;
		Integer inputCol = null;
		Integer length = getUserShipSize();

		// if the user enters the wrong number
		if (!(length < 6 && length > 0)) {
			System.out.println("You did not enter an Integer between 2 and 5");
			return;
		} 
        if (length > numberOfSquares) {
			System.out.println("You don't have enough squares left");
			return;

		} 
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

		if (this.board.containsKey(place) == false) {
            System.out.println("This place is already taken. \n");
            return;
        }

        boolean fits = false;
		int factor = 1;
        if (input.equalsIgnoreCase("H")){
            fits = shipFitsInsideBoard(length, inputCol);
        }else if (input.equalsIgnoreCase("V")){
            fits = shipFitsInsideBoard(length, inputRow);
			factor = 10;
        }else {
            System.out.println("You did not write H or V.");
            return;
        }

        if(!fits){
            return;
        }

		// Comment: still needed if graphical interface
        ArbitraryShip ship = new ArbitraryShip(length);
		ships.add(ship);

		for (int i = 0; i < length; i++) {

			Coordinate cor = gameBoard.get(place + factor * i);
			cor.addShip(ship);
			remove((place + factor * i));

		}
        

        numberOfSquares = numberOfSquares - length;
        printBoard(gameBoard);


	}

	// Comment: not needed if graphical interface
    private int getUserShipSize(){
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
        return length;
    }

	// Comment: not needed if graphical interface
    private boolean shipFitsInsideBoard(Integer length, int input){

        if (input + length > 10) {
                System.out.println("The ship does not fit inside the board");
                System.out.println(" ");
                return false;
            }

        return true;

    }

	/**
	 * This method is used to remove nearby coordinates from the shipsPlaced,
     * since ships must have at least one square inbetween them
	 * ArrayList
	 * 
	 * @param place is the coordinate where the ship has been placed
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
	 * Removes the coordinate from the HashMap, if it exists.
	 */
	private void hashRemove(int place) {
		if (this.board.containsKey(place) == true) {
			this.board.remove(place);
		}
	}
    
}
