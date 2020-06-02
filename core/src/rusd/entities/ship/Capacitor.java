package rusd.entities.ship;

public class Capacitor {

	public float maxCapacity;
	public float currentCapacity;
	public float chargePercentage;
	public float chargeRate;
	
	public Capacitor(){
		
		this.currentCapacity = 100;
		this.maxCapacity = 100;
		this.chargePercentage = 100;
		this.chargeRate = .1f;
		
	}
	
	
	public Capacitor(float capacity, float chargeRate){
		this.currentCapacity = 100;
		this.maxCapacity = 100;

		this.chargePercentage = 100;
		this.chargeRate = chargeRate;
		
	}
	
	/**
	 * Should be Called every update.
	 * charges the ship.
	 */
	public void charge(){
		this.currentCapacity+= chargeRate;
		if(currentCapacity > maxCapacity){
			currentCapacity = maxCapacity;
		}
	}

	/**
	 *  Drains the amount the capacitor
	 */
	public void drain(float amnt){
		this.currentCapacity -= amnt;
	}
	

	/**
	 * @return the maxCapacity
	 */
	public float getMaxCapacity() {
		return maxCapacity;
	}


	/**
	 * @param maxCapacity the maxCapacity to set
	 */
	public void setMaxCapacity(float maxCapacity) {
		this.maxCapacity = maxCapacity;
	}


	/**
	 * @return the currentCapacity
	 */
	public float getCurrentCapacity() {
		return currentCapacity;
	}


	/**
	 * @param currentCapacity the currentCapacity to set
	 */
	public void setCurrentCapacity(float currentCapacity) {
		this.currentCapacity = currentCapacity;
	}


	/**
	 * @return the chargePercentage
	 */
	public float getChargePercentage() {
		return chargePercentage;
	}


	/**
	 * @param chargePercentage the chargePercentage to set
	 */
	public void setChargePercentage(float chargePercentage) {
		this.chargePercentage = chargePercentage;
	}


	/**
	 * @return the chargeRate
	 */
	public float getChargeRate() {
		return chargeRate;
	}


	/**
	 * @param chargeRate the chargeRate to set
	 */
	public void setChargeRate(float chargeRate) {
		this.chargeRate = chargeRate;
	}
	
}
