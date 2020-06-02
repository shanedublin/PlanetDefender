package rusd.entities.enemy;

import rusd.entities.better.Entity2;
import rusd.methods.TextureNames.Textures;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;

public abstract class WarpGate  extends Entity2{

	
	protected long spawnTime;
	protected long lastSpawnTime;
	
	public WarpGate(){
		spawnTime = 2000000000;
		lastSpawnTime = TimeUtils.nanoTime();
		this.tr = Textures.WARPGATE1;
	}
	
	public boolean canSpawn(){
		if(TimeUtils.nanoTime() - lastSpawnTime > spawnTime){
			return true;		
		}
		
		return false;
	}
	public abstract Enemy Spawn();
}
