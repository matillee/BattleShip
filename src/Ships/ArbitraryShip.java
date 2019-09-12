package Ships;
/**
 * Can be any size
 */
public class ArbitraryShip implements Ship {

	private int life;

	public ArbitraryShip(int size) {
		this.life = size;
	}

	public int getLife() {
		return this.life;
	}

	@Override
	public void reduceLife() {
		this.life --;
		
	}

	@Override
	public boolean hasSunk() {
		if (life == 0)
			return true;
		else
			return false;
	}
}
