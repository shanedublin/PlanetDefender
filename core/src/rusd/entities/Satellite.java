package rusd.entities;

import com.badlogic.gdx.graphics.g2d.TextureRegion;


/**
 * used for moons or other objects that orbit planets
 * 
 * 
 * @author Shane
 *
 */
public class Satellite extends Entity {

	
	
	public Satellite(float x, float y, float width, float height, float dir,int hp, int armor, TextureRegion textureName, Planet planet) {
		super(x, y, width, height, dir, hp, armor,textureName);
		// TODO Auto-generated constructor stub
	}
	
	
	

}
