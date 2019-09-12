package main;

/**
 * A ComputerPlayer is everything that a Player is, except that it is autonomous
 */
public class ComputerPlayer extends Player {

	public ComputerPlayer(String name, OriginalBoard board) {
		super(name, board);
	}

	/**
	 * return true if playerBeingShot has lost, false if not.
	 */
	public boolean shoot(Player playerBeingShot) {
		boolean hit = false;

		do {
			
			int place;
			
			do {
				place = super.myBoard.getRandomNum();
				
			} while(playerBeingShot.getPlayerBoard().getShotPlace().contains(new Integer(place)));
			

			OriginalBoard playerBeingShotBoard = playerBeingShot.getPlayerBoard();
			
			hit = playerBeingShotBoard.shootBoard(place,playerBeingShot.name, super.name);
			
			super.hitPercent(hit);

			System.out.println(" ");
			System.out.println(playerBeingShot.name + "'s board after " + super.name + " shot it:");
			System.out.println("----------------------------------");
			playerBeingShotBoard.printGameBoard();
			System.out.println("The damage percent on " + playerBeingShot.name + "'s board is: "
					+ playerBeingShotBoard.getDamagePercent() + " %.");

			if (playerBeingShotBoard.isLoser()) { // if all the ships has been shot down
				System.out.println("The player " + playerBeingShot.name + " has lost!");
				
				return true;
			}

			System.out.println(this.name + "'s hit percent is now " + super.getHitPercent() + " %.");
			System.out.println("");
			
			
		} while (hit);

		return false;
	}

}