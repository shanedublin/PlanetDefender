package rusd.entities.enemy;

import com.badlogic.gdx.utils.TimeUtils;

public class DroneWarpGate  extends WarpGate{
	
	
	public DroneWarpGate(){
		this.health = 1000000000;
	}

	@Override
	public Enemy Spawn() {
		this.lastSpawnTime = TimeUtils.nanoTime();
		DroneEnemy e = new DroneEnemy();
		e.bounds.width = 32;
		e.bounds.height = 32;
		e.setBounds(this.center.x - e.bounds.width/2, this.center.y- e.bounds.height/2);
		return e;
	}
	
	
	// change
	@Override
	public void update() {
		if(TimeUtils.nanoTime() - lastSpawnTime > spawnTime){
			Spawn();
			lastSpawnTime = TimeUtils.nanoTime();
		}
		
	}

	

}
