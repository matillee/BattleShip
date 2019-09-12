import java.util.*;

public class Menu implements MenuItem {
	
	
	//Innan midsommar
	//Innan 10 juni för CSN
	
	
	// Skapa spelarklass
	
	// 
	
	// Dator mot dator
	// flera spelare.
	
	// array av spelare i gameengine, åsidosätta shot.
	// abstrakt klass
	
	// skicka mail med förklarningar på var vi har gjort. innan sommmaren.
	
	// få bort true, false
	
	//fixa när man placerar skeppen så den inte är för lång för boarden.
	// efter h eller v gör if...
	

	private static AllTimeHigh allTimeHigh = new AllTimeHigh(0, "", 0, "");
	
	private String title;
	private ArrayList<MenuItem> items;
	private final static Menu BattleShip = new Menu("BattleShip");
	private static Scanner scan = new Scanner(System.in);

	protected static String[] name;
	protected static ArrayList<Board> boards = new ArrayList<Board>();

	public static void main(String[] args) {

		AbstractMenuItem quit = new AbstractMenuItem("Quit") {
			public void execute() {
				System.exit(0);
			}
		};
		BattleShip.add(quit);

		final Menu play = new Menu("Play");
		BattleShip.add(play);
		
		AbstractMenuItem highScore = new AbstractMenuItem("Highscore") {
			public void execute() {
			
				System.out.println("All time Highscore");
				allTimeHigh.damagePercent();
				allTimeHigh.hitPercent();
				
				BattleShip.execute();
			}
		};
		BattleShip.add(highScore);

		final Menu autoPlacement = new Menu("Autoplacement of ships");
		play.add(autoPlacement);

		final Menu selfPlacement = new Menu("Selfplacement of ships");
		play.add(selfPlacement);
		
		AbstractMenuItem goBackToMain = new AbstractMenuItem("Go back") {
			public void execute() {
				BattleShip.execute();
			}
		};
		play.add(goBackToMain);
		
		AbstractMenuItem selfFriends = new AbstractMenuItem("Play against a friend(s)") {
			public void execute() {

				GameEngine game = new GameEngine();

				System.out.println("How many players dare to play? " + "Please answer in an integer.");
				int numberOfPlayers = scan.nextInt();
				name = new String[numberOfPlayers];

				for (int i = 0; i < numberOfPlayers; i++) {

					System.out.println("Player " + i + ": ");
					name[i] = name();
					boards.add(new Board());
					placeShip(name[i], boards.get(i));
					
				}
				
				game.playMultiplayerGame(name, boards);

				BattleShip.execute();
				
			}
		};
		selfPlacement.add(selfFriends);

		AbstractMenuItem selfComputer = new AbstractMenuItem("Play against the mighty computer") {
			public void execute() {

				GameEngine game = new GameEngine();

				name = new String[2];
				name[0] = name();
				name[1] = "The mighty Computer";

				Board boardPlayer0 = new Board();
				boards.add(boardPlayer0);
				placeShip(name[0], boardPlayer0);

				Board boardComputer = new Board();
				boards.add(boardComputer);
				autoPlacementShip(boardComputer);

				game.playComputerGame(name, boards);
				
				BattleShip.execute();
			}
		};
		selfPlacement.add(selfComputer);

		AbstractMenuItem autoFriends = new AbstractMenuItem("Play against a friend(s)") {
			public void execute() {

				GameEngine game = new GameEngine();

				System.out.println("How many players dare to play? " + "Please answer in an integer.");
				int numberOfPlayers = scan.nextInt();
				name = new String[numberOfPlayers];

				for (int i = 0; i < numberOfPlayers; i++) {

					System.out.println("Player " + i + ": ");
					name[i] = name();
					Board board = new Board();
					boards.add(board);
					autoPlacementShip(board);

				}

				game.playMultiplayerGame(name, boards);

				BattleShip.execute();
			}
		};
		autoPlacement.add(autoFriends);
		
		AbstractMenuItem autoComputer = new AbstractMenuItem("Play against the mighty computer") {
			public void execute() {

				GameEngine game = new GameEngine();

				name = new String[2];
				name[0] = name();
				name[1] = "The mighty Computer";

				Board boardPlayer0 = new Board();
				boards.add(boardPlayer0);
				autoPlacementShip(boardPlayer0);
				
				Board boardComputer = new Board();
				boards.add(boardComputer);
				autoPlacementShip(boardComputer);


				game.playComputerGame(name, boards);
				
				BattleShip.execute();
			}
		};
		autoPlacement.add(autoComputer);

		AbstractMenuItem goBackToPlay = new AbstractMenuItem("Go back") {
			public void execute() {
				play.execute();
			}
		};
		selfPlacement.add(goBackToPlay);
		autoPlacement.add(goBackToPlay);

		BattleShip.execute();

	}

	/**
	 * Creates an empty menu with the given title
	 */
	public Menu(String title) {
		this.title = title;
		items = new ArrayList<MenuItem>();
	}

	/**
	 * Adds menu choice to menu
	 */
	public void add(MenuItem item) {
		items.add(item);
	}

	public String getTitle() {
		return title;
	}

	/**
	 * Writes out the menu
	 */
	public void execute() {

		System.out.println("\n" + title);
		System.out.println("======================");

		for (int i = 0; i < items.size(); i++) {
			System.out.println(i + ". " + items.get(i).getTitle());
		}

		System.out.println("\nEnter 0-" + (items.size() - 1) + ":");

		String input = scan.next();
		System.out.println(input);
		if (input.equals("0") || input.equals("1") || input.equals("2") || input.equals("3") || input.equals("4")) {
			if (input.equals("0")) {
				items.get(0).execute();
			} else if (input.equals("1")) {
				items.get(1).execute();
			} else if (input.equals("2")) {
				items.get(2).execute();
			} else if (input.equals("3")) {
				items.get(3).execute();
			} else if (input.equals("4")) {
				items.get(4).execute();
			}
		} else {
			System.out.println("Try again! You need to enter an integer");
			System.out.println("\n");
			BattleShip.execute();
		}

	}

	/**
	 * Method is used so you can play with more players.
	 */
	public static String name() {
		System.out.println("Please enter your name: ");
		return scan.next();
	}

	/**
	 * Method is used to place the players ships.
	 */
	private static void placeShip(String player, Board board) {
		do {
			System.out.println(player + ": You have " + board.getNumberOfSquares() + " left to place into your board.");

			board.putShipUser();

			// Predetermined number of squares that the player has to decide where
			// to put them.
		} while (board.getNumberOfSquares() > 0);
	}

	//Fixa s� vi anv�nder sm� och stora ships
	private static void autoPlacementShip(Board board) {

		for (int i = 0; i < 10; i++) {

			int randomPlacement = board.randomShip();
			board.putShip(randomPlacement);

		}

	}
}
