package rusd.entities.modular_ship;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;

import rusd.entities.modular_ship.Block.Direction;
import rusd.methods.TextureNames.Textures;

public class Weapon extends Block {

	

	
	public long fireRate;
	public long lastFire;
	public Weapon(Vector2 center){
		super(center);
		this.tr = Textures.GUN;
		this.tr1 = new TextureRegion(tr);
		this.direction = Direction.UP;
		
		fireRate = 500000000;
		lastFire = TimeUtils.nanoTime();
	}
	
	public boolean canFire(){
		if(TimeUtils.nanoTime() - lastFire > fireRate){
			
			return true;
			
		}
		
		return false;
	}
	
	public void fire(){
		lastFire = TimeUtils.nanoTime();
	}
	
	
	@Override
	public boolean isWeapon() {
		return true;
		
	}
}
