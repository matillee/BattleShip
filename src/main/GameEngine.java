package main;

import java.util.ArrayList;
import java.util.Scanner;

import highscore.*;
import board.*;

/**
 * To play the game.
 */
public class GameEngine {

	private static Scanner scan = new Scanner(System.in);
	protected static ArrayList<Player> playerList = new ArrayList<Player>();

	/**
	 * Method initializes game
	 * 
	 * @param _highScoreList
	 */
	public void init(HighScore _highScoreList) {

		Integer numberOfAutoPlayers = null;
		Integer numberOfPlayers = null;

		System.out.println("How many human players are to play? " + "Please answer in an integer.");
		while (numberOfPlayers == null) {
			try {
				numberOfPlayers = Integer.parseInt(scan.next());
			} catch (NumberFormatException e) {
				System.out.println("Please enter an integer");
			}
		}

		for (int i = 0; i < numberOfPlayers; i++) {

			String playerName = enterName();
			
			GameBoard gameBoard = new GameBoard();
			PlacementBoard placementBoard = new PlacementBoard(gameBoard.getGameBoard());
			

			System.out.println(playerName +": Enter 'y' if you want to place your ships manually");
			String input = scan.next();
			if (input.equalsIgnoreCase("y")) {
				placementBoard.placeShip(playerName);
			} else {
				placementBoard.autoPlacementShip();
			}

			gameBoard.setGameBoard(placementBoard.getGameBoard());
			gameBoard.setShips(placementBoard.getShips());
			playerList.add(new Player(playerName, gameBoard));

		}

		System.out.println("How many computer players are to play? " + "Please answer in an integer.");
		while (numberOfAutoPlayers == null) {
			try {
				numberOfAutoPlayers = Integer.parseInt(scan.next());
			} catch (NumberFormatException e) {
				System.out.println("Please enter an integer");
			}
		}

		for (int i = 0; i < numberOfAutoPlayers; i++) {

			String name = "Computer " + i;

			GameBoard gameBoard = new GameBoard();
			PlacementBoard placementBoard = new PlacementBoard(gameBoard.getGameBoard());
			placementBoard.autoPlacementShip();
			gameBoard.setGameBoard(placementBoard.getGameBoard());
			gameBoard.setShips(placementBoard.getShips());
			playerList.add(new ComputerPlayer(name, gameBoard));

		}

		playGame(playerList, _highScoreList);

	}

	/**
	 * Method is used so you can play with more players.
	 */
	public static String enterName() {
		System.out.println("Please enter your name: ");
		return scan.next();
	}

	/**
	 * Method used when players are playing the game
	 * 
	 * @param players
	 * @param highScore
	 */
	private void playGame(ArrayList<Player> players, HighScore highScore) {

		ArrayList<Player> inGamePlayer = players;

		Player playerBeingShot;

		if (players.size() > 1) {
			playerBeingShot = inGamePlayer.get(1);
		} else {
			System.out.println("There needs to be at least 2 players to play this game, try again");
			return;
		}
		
		do {

			for (int n = 0; n < inGamePlayer.size(); n++) {

				boolean lost = false;

				if ((n + 1) >= inGamePlayer.size()) {
					playerBeingShot = inGamePlayer.get(0);
				} else {
					playerBeingShot = inGamePlayer.get(n + 1);
				}
				
				System.out.println("It's " + inGamePlayer.get(n).name + "'s turn to shoot " + playerBeingShot.name + "'s board");

				lost = inGamePlayer.get(n).shoot(playerBeingShot);

				if (lost == true) {
					inGamePlayer.remove(playerBeingShot);
				}

			}

		} while (inGamePlayer.size() != 1);

		System.out.println("Congratulations " + inGamePlayer.get(0).name + ", you are the winner!\n");

		highScore.gameResult(players);
		highScore.setHighScore(inGamePlayer.get(0));

	}

}