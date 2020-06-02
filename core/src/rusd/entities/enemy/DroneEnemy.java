package rusd.entities.enemy;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;

import rusd.entities.modular_ship.ShipOutline;
import rusd.methods.TextureNames.Textures;

public class DroneEnemy extends Enemy {

	
	public float range;
	
	public DroneEnemy(){
		this.tr  = Textures.ENEMY1;
		range = 300;
	}
	
	
	
	@Override
	public void update(Vector2 shipPos) {
		
		
				
				// out of range move closer.
				float x;
				float y;
				float hypo;
				
				
				x = shipPos.x - this.center.x;
				y = shipPos.y - this.center.y;
				
				hypo = (float) Math.sqrt(x *x + y * y);
			
				//System.out.println(shipPos.x);
				if(hypo >= range-16){
					if(this.center.x > shipPos.x){			
						move(-1,0);
					}
					if(this.center.x < shipPos.x){
						move(1,0);
					}						
					if (this.center.y > shipPos.y){
						move(0,-1);
					}
					if (this.center.y < shipPos.y){
						move(0,1);						
					}
				}
				
				rotate(shipPos);
				
	}
			
				
				
			
			



	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
