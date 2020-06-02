package rusd.entities.enemy;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import rusd.entities.better.Entity2;
import rusd.entities.modular_ship.ShipOutline;




public abstract class  Enemy extends Entity2 {

	
	
			
	public Enemy(){
		
	}
	
	
	public abstract void update(Vector2 shipPos);
	
	
	public void rotate(Vector2 shipPos){
		float x1, x2, x3;
		float y1, y2, y3;
		float angle;

		x1 = shipPos.x;
		y1 = shipPos.y;		
		x2 = center.x;	
		y2 = center.y;
		x3 = x1 - x2;
		y3 = y1 - y2;		
		angle = (float) Math.atan(y3 / x3);
		angle *= MathUtils.radiansToDegrees;

		// takes care of angle sector stuffs
		// if the mouse is in the north east or south east no correction is
		// needed 
		//
		if (x3 >= 0 && y3 >= 0 || x3 >= 0 && y3 <= 0) {
			rotation = angle ;
		}
		//the remaining two cases are covered here.
		else {
			rotation =((angle) + 180);
		}

	}
	
	
	
	public void move(float x, float y){
	//	System.out.println("moved");
		setBounds(this.bounds.x += x,this.bounds.y += y);
		
	}
	
}
