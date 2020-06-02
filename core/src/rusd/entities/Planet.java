package rusd.entities;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

/**
 * This is a planet it holds the important info toward game victory !
 * @author Shane
 *
 */
public class Planet extends Entity {
	public int population;
	public String name;
	public float radius;
	public float orbitTime;
	public float orbitDist;
	private Circle cirleBounds;
	
	//TODO math to set center of Planet
	public Planet(float pRadius, float oRadius, float orbitTime,TextureRegion tr){
		super(oRadius,0,pRadius,pRadius,0,10000,100,tr);
	
		this.population = 0;
		this.radius = pRadius;
		this.orbitDist = oRadius;
		this.orbitTime = orbitTime;
		// oRadius is the distance
		setCenter(oRadius , 0);
		
		
	}

	
	

	/**
	 * implies some default stuff that would be the same for every planet
	 * @param name
	 * @param centerX
	 * @param centerY
	 * @param radius
	 * @param orbitDist
	 * @param orbitTime
	 */
	public Planet(String name, float centerX, float centerY,float radius,float orbitDist, float orbitTime,TextureRegion tr ){
		super(centerX - radius,centerY - radius,radius*2,radius*2,0,100,100,tr);
		this.population = 10;
		this.name = name;
		this.radius = radius;
		this.orbitDist = orbitDist;
		this.orbitTime = orbitTime;
		this.setCenter(centerX, centerY);
		this.cirleBounds = new Circle(x, y, radius);
		this.textureRegion = tr;
		
		
	}
	/**
	 * @return the population
	 */
	public int getPopulation() {
		return population;
	}
	
	public Circle getCircleBounds(){
		
		this.cirleBounds.x = this.center.x;
		this.cirleBounds.y = this.center.y;
		return this.cirleBounds;
	}

	/**
	 * @param population the population to set
	 */
	public void setPopulation(int population) {
		this.population = population;
	}
	
	public void growPopulation(){
		this.population *=1.005;
	}
	
	public void hurtPopulation(int damage){
		this.population *=damage;
	}
	/**
	 * not perfect but works for now.
	 * if the center of the object is within the radius return true
	 * @return
	 */
	public boolean overlaps(Vector2 center){
		float dist; 
		dist = (float)Math.sqrt((center.x - x) * (center.x - x) +
		(center.y - y) *(center.y - y));

		if (dist < this.radius)
			return true;
		else 
			return false;
					
		
		
		
	}

}
