
public class bigShip implements Ship {

	private int life = 5;
	private int place;
	
	public bigShip(int place) {
		this.place = place;
	}

	public int getPlace() {
		return this.place;
	}
	
	public int life(){
		return this.life;
	}
	
	public boolean shot(){
		this.life --;
		if(life == 0){//check if ship is dead
			return true;
		}else
			return false;
	}

}

