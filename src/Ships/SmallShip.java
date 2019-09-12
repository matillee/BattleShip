package Ships;

/**
 * Is 2 squares long
 */
public class SmallShip implements Ship {

	private int life = 2;

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
