package rusd.entities.ship;

public class Laser extends Weapon{

	
	public Laser(){
		this.requiresEnergy = true;
		this.requiresAmmo = false;
		this.speed = 800;
		this.energyReq = 5;
	}
	
}
