package highscore;
/**
 * To keep track of winnerScore
 */
public class Score {
	
	private int score;
	private String name;
	
	public Score(String _name, int _score) {
		this.score = _score;
		this.name = _name;
	}
	
	public void setScore(int _score) {
		this.score = _score;
	}
	
	public int getScore() {
		return this.score;
	}
	
	public String getName() {
		return this.name;
	}

}
