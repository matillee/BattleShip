package main;

import java.util.Scanner;
import board.GameBoard;

/**
 * To create players taking part of the games
 *
 */
public class Player {

	private static Scanner scan = new Scanner(System.in);

	protected String name;
	protected GameBoard myBoard;
	protected int numOfShotsFired;
	protected int numOfHits;

	public Player(String name, GameBoard board) {
		this.name = name;
		this.myBoard = board;
		this.numOfShotsFired = 0;
		this.numOfHits = 0;
	}

	public String getPlayerName() {
		return this.name;
	}

	public GameBoard getPlayerBoard() {
		return this.myBoard;
	}

	protected void hitPercent(boolean hit) { 
		if (hit) {
			this.numOfHits++;
		}
		this.numOfShotsFired++;
	}

	public float getHitPercent() {
		float hitPercent = ((float) this.numOfHits / (float) this.numOfShotsFired);
		return hitPercent;
	}
	
	public int getShotsFired() {
		return this.numOfShotsFired;
	}

	/**
	 * For player to play it's turn and shoot the other players board
	 * 
	 * @param playerBeingShot
	 *            's board.
	 * @return
	 */
	public boolean shoot(Player playerBeingShot) {

		boolean hit = false;

		GameBoard playerBeingShotBoard = playerBeingShot.getPlayerBoard();

		Integer inputRow = null;
		Integer inputCol = null;
		
		System.out.println(" ");
		System.out.println(playerBeingShot.name + "'s board before shooting:");
		System.out.println("----------------------------------");
		playerBeingShotBoard.printBoard();

		do {
			inputRow = null;
			inputCol = null;
			System.out.println(this.name + " enter what square you want to shoot:");
			System.out.println(" ");
			System.out.println("Row: ");

			while (inputRow == null) {
				try {
					inputRow = Integer.parseInt(scan.next());
				} catch (NumberFormatException e) {
					System.out.println("Please enter an integer");
				}
			}

			if (0 > inputRow || inputRow > 9) {
				System.out.println("You can only enter an Integer between 0 and 9, try again! ");
				shoot(playerBeingShot);
			}

			System.out.println("Column: ");

			while (inputCol == null) {
				try {
					inputCol = Integer.parseInt(scan.next());
				} catch (NumberFormatException e) {
					System.out.println("Please enter an integer");
				}
			}

			if (0 > inputCol || inputCol > 9) {
				System.out.println("You can only enter an Integer between 0 and 9, try again! ");
				shoot(playerBeingShot);
			}

			int place = (inputRow * 10) + inputCol;

			hit = playerBeingShotBoard.shootBoard(place, playerBeingShot.name, this.name);

			hitPercent(hit);
			

			System.out.println(" ");
			System.out.println(playerBeingShot.name + "'s board after " + this.name + " shot it:");
			System.out.println("----------------------------------");
			playerBeingShotBoard.printBoard();
			System.out.println("The damage percent on " + playerBeingShot.name + "'s board is: "
					+ playerBeingShotBoard.getDamagePercent() + " %.");

			if (playerBeingShotBoard.isLoser()) { // if all the ships has been shot down
				System.out.println("The player " + playerBeingShot.name + " has lost!");
				return true;
			}

			System.out.println(this.name + "'s hit percent is now " + this.getHitPercent() + " %.");
			System.out.println("");

		} while (hit);

		return false;

	}

}