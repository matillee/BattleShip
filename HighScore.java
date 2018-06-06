/**
 * Prints statistic of the game.
 */
public class HighScore { 
		
	private int numberOfBoatCoordinates = 0;
	private int shotsMissed = 0;
	private int shotsHit = 0;

	public HighScore(int numberOfBoatCoordinates) {
		this.numberOfBoatCoordinates = numberOfBoatCoordinates;
	}
	
	public void hit() {
		shotsHit++;
	}
	
	public void miss() {
		shotsMissed++;
	}	 
	
	public float getHitPercent() {
		return shotsHit / (shotsMissed + shotsHit);
	}
	
	public float getDamagePercent() {
		return shotsHit / numberOfBoatCoordinates;
	}
	
	

}
// Funktionella krav

//G�r en highscore f�r varje spelare och j�mf�r vem som fick h�gst. spara in till en annan highscore som visas i highscore delen i menyn?

// Det ska finnas en lista �ver b�sta resultat som
// sparas n�r programmet avslutas. Result kan t.ex.
// l�gst antal skott innan vinst.

// Icke-funktionella krav
// Designen ska finnas dokumenterad, t.ex. i
// diagramform.
