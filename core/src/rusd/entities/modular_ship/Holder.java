package rusd.entities.modular_ship;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Holder {
	
	public Block block;
	public Vector2 center;
	public Rectangle bounds;
	public Polygon polybounds;
	
	
	
	
	public Holder(Vector2 center){
		
		this.block = new Blank(center);
		this.center = center;
		
	}
	public Holder(float x, float y){
		
		this.block = new Blank(new Vector2(x, y));
		this.center = new Vector2(x,y);
		
	}
	
	public void SetBlock(Block block){
		this.block = block;
	}

}
