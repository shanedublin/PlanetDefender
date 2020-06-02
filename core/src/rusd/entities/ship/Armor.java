package rusd.entities.ship;

public class Armor {

	
	public float maxHp;
	public float currentHp;
	public float maxSheild;
	public float currentSheild;
	public float sheildChargeRate;
	// I might not have the sheilds charging from the same source that the 
	// lasers charge from
	public float sheildChargeCost;
	public float hullArmor;
	public float sheildArmor;
	
	
	public Armor(){
		this.maxHp = 10;
		this.currentHp = 10;
		this.maxSheild = 2;
		this.currentSheild = 2;
		this.hullArmor = 1;
		this.sheildArmor = 0;
		this.sheildChargeCost = 1;
		this.sheildChargeRate = .1f;
		
	}
	
	
	public boolean alive(){
		if (currentHp >= 0){
			return true;
		}
		else
			return false;
	}
	
	public void chargeSheilds(){
		currentSheild += sheildChargeRate;
		if(currentSheild > maxSheild){
			currentSheild = maxSheild;
		}
		
	}
	
	public float damageSheilds(float dmg){
		if(currentSheild >= dmg){
			currentSheild -= dmg;
			return 0;
		}
		else{
			dmg -= currentSheild;
			currentSheild = 0;
			return dmg;
		}
		
		
	}
	
	/**
	 * give the amount of damage done to the ship.
	 * @param dmg
	 */
	public void damage(float dmg){
		dmg -= sheildArmor;
		
		dmg = damageSheilds(dmg);
		
		dmg -= hullArmor;
		
		if(dmg > 0){
			currentHp -= dmg;
			
		}
		
	}


	/**
	 * @return the maxHp
	 */
	public float getMaxHp() {
		return maxHp;
	}


	/**
	 * @param maxHp the maxHp to set
	 */
	public void setMaxHp(float maxHp) {
		this.maxHp = maxHp;
	}


	/**
	 * @return the currentHp
	 */
	public float getCurrentHp() {
		return currentHp;
	}


	/**
	 * @param currentHp the currentHp to set
	 */
	public void setCurrentHp(float currentHp) {
		this.currentHp = currentHp;
	}


	/**
	 * @return the maxSheild
	 */
	public float getMaxSheild() {
		return maxSheild;
	}


	/**
	 * @param maxSheild the maxSheild to set
	 */
	public void setMaxSheild(float maxSheild) {
		this.maxSheild = maxSheild;
	}


	/**
	 * @return the currentSheild
	 */
	public float getCurrentSheild() {
		return currentSheild;
	}


	/**
	 * @param currentSheild the currentSheild to set
	 */
	public void setCurrentSheild(float currentSheild) {
		this.currentSheild = currentSheild;
	}


	/**
	 * @return the hullArmor
	 */
	public float getHullArmor() {
		return hullArmor;
	}


	/**
	 * @param hullArmor the hullArmor to set
	 */
	public void setHullArmor(float hullArmor) {
		this.hullArmor = hullArmor;
	}


	/**
	 * @return the sheildArmor
	 */
	public float getSheildArmor() {
		return sheildArmor;
	}


	/**
	 * @param sheildArmor the sheildArmor to set
	 */
	public void setSheildArmor(float sheildArmor) {
		this.sheildArmor = sheildArmor;
	}
	
	
	
	
	
}
