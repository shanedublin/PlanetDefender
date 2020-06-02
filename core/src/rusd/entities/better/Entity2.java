package rusd.entities.better;

import rusd.entities.enemy.Enemy;
import rusd.entities.modular_ship.ShipOutline;
import rusd.methods.TextureNames.Textures;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity2 {
	
	public Rectangle bounds;
	public Vector2 center;
	public TextureRegion tr;
	public float  rotation;
	public float health;
	
	public Entity2(){
		this.bounds = new Rectangle(0, 0, 64, 64);
		this.center = new Vector2(bounds.x - bounds.width/2, bounds.y - bounds.height/2);
		this.tr = Textures.BLANK1;
		this.health =1;
		
	}
	
	
	
	
	public void setCenter(float x, float y){
			this.center.x = x;
			this.center.y = y;
			
	}
	
	public void setBounds(float x, float y){
		this.bounds.x = x;
		this.bounds.y = y;
		setCenter(bounds.x + bounds.width/2, bounds.y + bounds.height/2);
		
	}
	
	public float getX(){
		return bounds.x;
	}
	public float getY(){
		return bounds.y;
	}
	
	public abstract void update();

	
	

}
