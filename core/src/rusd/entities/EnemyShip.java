package rusd.entities;

import rusd.methods.TextureNames;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class EnemyShip extends Entity {
	
	public Array<Projectile> bullets;
	public float range;
	public enum Behavior{
		MOVECLOSE,FIRE,
	}
	Behavior behavior;
	public long fireTime;

	public EnemyShip(float x, float y, float width, float height, float dir,
			int hp, int armor, TextureRegion tr) {
		super(x, y, width, height, dir, hp, armor, tr);
		
		bullets = new Array<Projectile>();
		this.range = 300;
		fireTime = TimeUtils.nanoTime();
	}

	
	
	
	/**
	 * Act based on player location
	 * @param shipPos
	 */
	public void AI(Vector2 shipPos){
		
		// within 180 stop moving
		// within range, fire
		// out of range move closer.
		float x;
		float y;
		float hypo;
		
		x = shipPos.x - this.x;
		y = shipPos.y - this.y;
		
		hypo = (float) Math.sqrt(x *x + y * y);
	
		if(hypo >= range-16){

			if(this.center.x > shipPos.x){			
				move(-1,0);
				//System.out.println("moved");
				
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
		
		
		
		if(hypo <= range){
			if(TimeUtils.nanoTime() - fireTime > 1000000000){
				fire();
				fireTime = TimeUtils.nanoTime();
			}
			
		}
		
		
		
		
		
		rotate(shipPos);
		
		
		
	}
	public void fire(){
	
					
		Vector3 Trajectory;
		Projectile bullet;

		Trajectory = new Vector3((float) Math.cos(direction
				* MathUtils.degreesToRadians), (float) Math.sin(direction
				* MathUtils.degreesToRadians), 0);

		bullet = new Projectile((center.x- 2 ),
				center.y - 4 , 2, 4, 0, 1, 1, TextureNames.Textures.ORB,
				Trajectory);
		//bullet.setDirection(direction);
	
		bullets.add(bullet);
		
			
	}
	
	public void move(float x, float y){
		
		setX(this.x += x);
		setY(this.y += y);
		
	}
	
	
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
			direction = angle ;
		}
		//the remaining two cases are covered here.
		else {
			direction =((angle) + 180);
		}

	}
	
	
	
}
