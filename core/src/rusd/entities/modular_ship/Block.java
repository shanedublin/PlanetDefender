package rusd.entities.modular_ship;

import rusd.methods.TextureNames.Textures;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;

public class Block {

	public Texture tr;
	public TextureRegion tr1;
	public enum Direction{
		UP,DOWN,RIGHT,LEFT
	}
	Direction direction;
	
	public Vector2 center;
	public Polygon poly;
	public boolean destroyed;
	
	public Block(Vector2 center){
		this.center = center;
		tr = Textures.BLOCK;
		tr1 = new TextureRegion(tr);
		this.direction = Direction.UP;
		poly = new Polygon(new float[]{center.x-16, center.y - 16, center.x-16,center.y + 16,center.x+16,center.y + 16,center.x+16,center.y - 16});
		this.destroyed = false;
	}
	public boolean isWeapon(){
		return false;
	}
	
	/**
	 * TODO set up health system for each block
	 */
	public void hit(){
		this.destroyed = true;
	}
}
