package Ships;

public class BigShip implements Ship {

	private int life = 5;
	
	public BigShip() {
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

