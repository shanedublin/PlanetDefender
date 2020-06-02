package rusd.entities;

import rusd.methods.TextureNames;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.TimeUtils;
//TODO make ship hold its projectiles?
public class Ship extends Entity {

	private float maxSpeed;
	private long lastFireTime;
	private Vector3 trajectory;
	private boolean alive;
	private float thrust;
	
	
	
	private boolean up, down, left, right;
	private enum shipType{
		LASTHOPE, DELEVERANCE,RUSD
	}
	
	shipType shipMod;
	public Ship(float x, float y,float width, float height, float dir, int hp, int armor,TextureRegion textureName, int shipNum) {
		super(x, y, width, height, dir, hp, armor,textureName);
		this.alive = true;
		
		// sets the  ship model based off the number
		switch (shipNum){
		case 0: this.shipMod = shipType.LASTHOPE;
		break;
		case 1: this.shipMod = shipType.DELEVERANCE;
		break;
		}
		
		this.maxSpeed = 500;
		this.trajectory = new Vector3(0, 0, 0);
		this.thrust = 350;
		this.lastFireTime = TimeUtils.nanoTime();
	}
	
	public void setThrust(float thrust){
		this.thrust = thrust;
	}

	public void move(){
		
		
		this.setX( (this.x +=  Gdx.graphics.getDeltaTime()* trajectory.x * thrust));
		this.setY( (this.y +=  Gdx.graphics.getDeltaTime()* trajectory.y * thrust));
		
		
		
	}
	public Projectile fire(){
		
		
		Vector3 Trajectory;
		Projectile bullet;

		Trajectory = new Vector3((float) Math.cos(direction
				* MathUtils.degreesToRadians), (float) Math.sin(direction
				* MathUtils.degreesToRadians), 0);

		bullet = new Projectile((center.x - 8 / 2),
				(center.y - 8 / 2), 8, 8, 0, 1, 1, TextureNames.Textures.ORB,
				Trajectory);
	
		
		//Gdx.app.log("gameWorld", "bullet added");
		return bullet;
		
	}
	
	public void modThrust(int  arrow){
		
		
		switch (arrow) {
		// up w ,
		case 0:
			up = true;
		//trajectory.x =(float) Math.cos(direction * MathUtils.degreesToRadians);
		//trajectory.y =(float) Math.sin(direction * MathUtils.degreesToRadians);
			break;
		// down s o	
		case 1:
			down = true;
			
		//trajectory.x =(float) Math.cos(direction * MathUtils.degreesToRadians)*-1;
		//trajectory.y =(float) Math.sin(direction * MathUtils.degreesToRadians)*-1;	
			break;
		// left a a
		case 2:
			left = true;
			//trajectory.x =(float) Math.cos((direction+90) * MathUtils.degreesToRadians)*-1;
		//	trajectory.y =(float) Math.sin((direction+90) * MathUtils.degreesToRadians)*-1;	
			
			break;
		// right e d
		case 3:
			right = true;
			//trajectory.x =(float) Math.cos((direction-90) * MathUtils.degreesToRadians)*-1;
			//trajectory.y =(float) Math.sin((direction -90)* MathUtils.degreesToRadians)*-1;	
		
			
			break;
		case 4:
			//trajectory.x = 0;
			//trajectory.y = 0;
			up = false;
			break;
		case 5:
			//trajectory.x = 0;
		//	trajectory.y = 0;
			down = false;
			break;
		case 6:
			//trajectory.x = 0;
			//trajectory.y = 0;
			left = false;
			break;
		case 7:
			//trajectory.x = 0;
			//trajectory.y = 0;
			right = false;
			break;

		default:
			break;
		}
		trajectory.x = 0;
		trajectory.y = 0;
		if(up){
			trajectory.x +=(float) Math.cos((direction )* MathUtils.degreesToRadians);
			trajectory.y +=(float) Math.sin((direction )* MathUtils.degreesToRadians);
		}
		if(down){
			trajectory.x +=(float) Math.cos((direction+180) * MathUtils.degreesToRadians);
			trajectory.y +=(float) Math.sin((direction+180) * MathUtils.degreesToRadians);
		}
		if (left){
			trajectory.x +=(float) Math.cos((direction+90) * MathUtils.degreesToRadians);
			trajectory.y +=(float) Math.sin((direction+90) * MathUtils.degreesToRadians);
		}
		if(right){
			trajectory.x +=(float) Math.cos((direction-90) * MathUtils.degreesToRadians);
			trajectory.y +=(float) Math.sin((direction-90) * MathUtils.degreesToRadians);
		}
			
		
			
			
		
		
		// these set a max speed that the ship can go.
		
		
		if (trajectory.x > 5){
			trajectory.x = 5;
		}
		if (trajectory.x < -5){
			trajectory.x = -5;
		}
		if (trajectory.y > 5){
			trajectory.y = 5;
		}
		if (trajectory.y < -5){
			trajectory.y = -5;
		}
			
			
			
		
			
			
		
		
	}
	
	public void kill(){
		this.alive = false;
	}
	
	public boolean alive(){
		return alive;
	}
	
	public void stop(){
		trajectory.x = 0;
		trajectory.y = 0;
	}
	public Vector3 getTrajectory(){
		return trajectory;
	}
	

	/**
	 * point the front end of the ship towards the mouse TODO learn vector math
	 * to simplify
	 */
	public void rotateShip(Vector3 mousePos) {
		float x1, x2, x3;
		float y1, y2, y3;
		float angle;

		x1 = mousePos.x;
		y1 = mousePos.y;		
		x2 = center.x;	
		y2 = center.y;
		x3 = x1 - x2;
		y3 = y1 - y2;		
		angle = (float) Math.atan(y3 / x3);
		angle *= MathUtils.radiansToDegrees;

		// takes care of angle sector stuffs
		// if the mouse is in the north east or south east no correction is
		// needed 
		//TODO uncomment the code for teh mouse angle
		if (x3 >= 0 && y3 >= 0 || x3 >= 0 && y3 <= 0) {
			setDirection(angle);
		}
		//the remaining two cases are covered here.
		else {
			setDirection((angle) + 180);
		}

	

	}
	public boolean canFire(){
		if(TimeUtils.nanoTime() - lastFireTime > 100000000){
			lastFireTime = TimeUtils.nanoTime();
			return true;
		}
		return false;
	}
	
	
	
}
