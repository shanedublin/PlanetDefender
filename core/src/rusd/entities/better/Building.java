package rusd.entities.better;

public class Building extends Entity2 {
	
	public boolean hasFightZone;
	public int fightZone;
	
	
	
	public Building(){
		hasFightZone = false;
		fightZone = 0;
	
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	public int getFightZone(){
		return fightZone;
	}

}
