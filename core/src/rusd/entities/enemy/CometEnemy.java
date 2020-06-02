package rusd.entities.enemy;

import com.badlogic.gdx.math.Vector2;

import rusd.entities.modular_ship.ShipOutline;
import rusd.methods.TextureNames.Textures;

public class CometEnemy extends Enemy {
	
	
	public CometEnemy(){
		this.tr = Textures.COMET;
		this.bounds.width = 16;
		this.bounds.height = 16;
		
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
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



}
