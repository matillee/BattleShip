import java.util.ArrayList;
import java.util.Scanner;

/**
 * To start the game.
 */
public class GameEngine {

	private static Scanner scan = new Scanner(System.in);

	public void playMultiplayerGame(String[] players, ArrayList<Board> boards) {

		do {

			for (int n = 0; n < boards.size(); n++) {

				System.out.println(players[n] + " bring it on!");
				System.out.println("");
				System.out.println("Enter whose board you want to shot in an Integer:");

				for (int i = 0; i < players.length; i++) {
					System.out.println(i + ": " + players[i]);
				}

				if (!scan.hasNextInt()) {
					System.out.println("Too bad you don't know what an Integer is. " + "Now you missed your turn.");
				} else {
					int input = scan.nextInt();
					playerShot(boards.get(input), players[input], players[n]);

				}
			}
		} while (!boards.isEmpty());

		System.out.println("Results:");
		
		result(players, boards);
	
		// L�gst antal skott innan vinst
	}

	
	/**
	 * So the player can enter where it wants to shoot, and start the game.
	 */
	public void playComputerGame(String[] players, ArrayList<Board> boards) {
		System.out.println("Let the game begin!");
		System.out.println(" ");
		System.out.println("Where do you think the mighty computor's ships are hidding?");
		System.out.println("");

		do { // Starts the game against the computer

			System.out.println(players[0] + " bring it on!");
			System.out.println("");

			System.out.println("The mighty Computer's board:");
			System.out.println("----------------------------------");
			boards.get(1).printBoard();
			playerShot(boards.get(1), players[1], players[0]);
			System.out.println("Watch out! it's the mighty computer's turn.");
			System.out.println("");

			computerShot(boards.get(0), players[0], players[1]);

		} while (boards.get(0).win() == false && boards.get(1).win() == false);

		System.out.println("Results:");
		
		result(players, boards);

		// L�gst antal skott innan vinst

	}

	/**
	 * 
	 * @param board
	 * @param playerBeingShot
	 * @param playerShooting
	 */

	// SKRIVER UT N�N TRUE FALSE SKIT OM MAN TR�FFAR SKEPPET... identifiera var.
	public void playerShot(Board board, String playerBeingShot, String playerShooting) {
		boolean hit = false;

		Integer inputRow = null;
		Integer inputCol = null;

		do {
			System.out.println("Enter what square you want to shoot:");
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
				System.out.println("You can only enter an Integer between 0 and 9, now you missed your turn! ");
				break;
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
				System.out.println("You can only enter an Integer between 0 and 9, now you missed your turn! ");
				break;
			}

			int input = (inputRow * 10) + inputCol;

			hit = shot(input, board, playerBeingShot, playerShooting);

			System.out.println("Your damage percent is: " + board.getDamagePercent() + " %.");
			System.out.println("Your hit percent is: " + board.getHitPercent() + " %.");
			System.out.println("");

		} while (hit);
	}

	/**
	 * 
	 * @param input
	 * @param board
	 * @param playerBeingShot
	 * @param playerShooting
	 * @return boolean if there was a hit or miss
	 */
	private boolean shot(int input, Board board, String playerBeingShot, String playerShooting) {
		boolean hit = board.shot(input);

		if (hit) {
			System.out.println(playerShooting + "'s ship was hit!");
		} else {
			System.out.println(playerShooting + " missed!");
		}

		System.out.println(" ");
		System.out.println(playerBeingShot + " board:");
		System.out.println("----------------------------------");
		board.printBoard();

		if (board.win()) { // if all the ships has been shot down
			System.out.println("The player " + playerBeingShot + " has lost!");

		}

		return hit;
	}

	/**
	 * Method so the computer can shoot randomly.
	 */
	public void computerShot(Board board, String playerBeingShot, String playerShooting) {
		boolean hit;
		do {
			int randomPlacement = board.randomShip();
			hit = shot(randomPlacement, board, playerBeingShot, playerShooting);
		} while (hit);
	}
	
	private void result(String[] players, ArrayList<Board> boards) {
		float bestDamageResult= 0;
		String bestDamagePerson = "";
		float bestHitResult = 0;
		String bestHitPerson = "";
		
		for (int n = 0; n < boards.size(); n++) {
			if (boards.get(n).getDamagePercent() > bestDamageResult) {
				bestDamageResult = boards.get(n).getDamagePercent();
				bestDamagePerson = players[n];
			}
			if (boards.get(n).getHitPercent()> bestHitResult) {
				bestHitResult = boards.get(n).getHitPercent();
				bestHitPerson = players[n];
			}
		}

		System.out.println(bestDamagePerson + "had the best damage percent with" + bestDamageResult);
		System.out.println(bestHitPerson + "had the best hit percent with" + bestHitResult);
		
		//H�r m�ste vi skicka resultatet till Menu klassen och j�mf�ra med all time resultat
	}
}
