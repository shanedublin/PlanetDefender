package rusd.entities.enemy;

import com.badlogic.gdx.utils.TimeUtils;

public class CometWarpGate extends WarpGate {
	
	
	
	public CometWarpGate(){
		
		this.health = 5;
	}

	
	@Override
	public Enemy Spawn() {
		this.lastSpawnTime = TimeUtils.nanoTime();
		CometEnemy ce = new CometEnemy();		
		ce.setBounds(this.center.x, this.center.y);
		return  ce;
		
	}


	@Override
	public void update() {
		if(TimeUtils.nanoTime() - lastSpawnTime > spawnTime){
			//Spawn();
			lastSpawnTime = TimeUtils.nanoTime();
		}
		
	}
	
	
	

}
