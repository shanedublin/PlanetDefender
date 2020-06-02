package rusd.entities;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;

/**
 * The comet class tells you cool stuffs.
 * 
 * @author Shane mother fuckin' Dublin
 * 
 */
public class Comet extends Projectile {

	public float planetDamage;

	/**
	 * @return the planetDamage
	 */

	public Comet(float x, float y, int width, int height, float dir, int hp,
			int armor,TextureRegion textureName, Vector3 trajectory) {
		super(x, y, width, height, dir, hp, armor,textureName, trajectory);
		this.planetDamage =  0.8f;
	}

	/**
	 * multiply this number times the current pop when the comet hits
	 * if population drops below 1000 or some CONST then game over.
	 * @return
	 */
	public float getPlanetDamage() {
		return planetDamage;
	}

	/**
	 * @param planetDamage
	 *            the planetDamage to set
	 */
	public void setPlanetDamage(int planetDamage) {
		this.planetDamage = planetDamage;
	}
}
