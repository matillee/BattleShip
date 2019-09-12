package menu;
import java.util.*;

import highscore.*;
import main.GameEngine;

/**
 * To print the menu of the game
 */
public class Menu implements MenuItem {
	
	private static Scanner scan = new Scanner(System.in);
	
	private String title;
	private ArrayList<MenuItem> items;
	private final static Menu BattleShip = new Menu("BattleShip");
	

	private static HighScore highScoreList = new HighScore();

	public static void main(String[] args) {
		
		AbstractMenuItem quit = new AbstractMenuItem("Quit") {
			public void execute() {
				System.out.println("Quit");
				System.exit(0);
			}
		};
		BattleShip.add(quit);
		
		AbstractMenuItem highScoreMenuItem = new AbstractMenuItem("Highscore") {
			public void execute() {
				
				System.out.println("HighScore - Least amount of shots fired before winning: ");
				highScoreList.printHighScore();
				
				BattleShip.execute();
			}
		};
		BattleShip.add(highScoreMenuItem);
		
		AbstractMenuItem play = new AbstractMenuItem("Play") {
			public void execute() {

				System.out.println("Play: ");
				System.out.println("The squares are marked ");
				System.out.println(" '~' = not shot  \n 'X' = hit \n '*' = miss ");
				
				GameEngine game = new GameEngine();
				game.init(highScoreList);
				
				BattleShip.execute();
			}
		};

		BattleShip.add(play);
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

	

}