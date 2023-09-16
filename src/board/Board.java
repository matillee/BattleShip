package board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

import Ships.Ship;
import main.Coordinate;

public class Board {
    ArrayList<Ship> ships = new ArrayList<Ship>();
    Scanner scan = new Scanner(System.in);
    int[] horisontal = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    int[] vertical = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    HashMap<Integer, Coordinate> board = new HashMap<Integer, Coordinate>();

    /**
	 * The constructor creates a board and the coordinates for the boards are placed
	 * in to two HashMaps.
	 */
	public Board() {

		for (int horisontals : horisontal) {
			for (int verticals : vertical) {
				int key = (horisontals * 10) + verticals;
				Coordinate cor = new Coordinate(key);
				board.put(key, cor);
			}
		}
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
				Coordinate cor = board.get(key);
				System.out.print(cor.gamePrint() + ("  "));
				if (n == 10) {
					System.out.println();
				}
			}
			System.out.println();
		}
		System.out.println();
    }

    /**
	 * Returns a random integer that fits inside the board.
	 */
	public int getRandomNum() {

		int randomPlacement;
		Random ram = new Random();
		randomPlacement = ram.nextInt(100);

		return randomPlacement;
	}
}
