/**
 * 
 * 
 *
 */
public class smallShip implements Ship {

	private int life = 2;
	private int place;

	public smallShip(int place) {
		this.place = place;
	}

	public int getPlace() {
		return this.place;
	}

	public int life() {
		return this.life;
	}

	public boolean shot() {
		this.life--;
		if (life == 0) {// check if ship is dead
			return true;
		} else
			return false;
	}
}
