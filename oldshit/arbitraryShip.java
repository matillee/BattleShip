public class arbitraryShip implements Ship {

	private int life;
	private int place;

	public arbitraryShip(int size, int place) {
		this.life = size;
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
