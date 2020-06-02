package rusd.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;

public class Projectile extends Entity {

	private Vector3 trajectory;
	public float speed;
	public Projectile(float x, float y, int width, int height, float dir, int hp,int armor,TextureRegion textureName,Vector3 trajectory) {
		super(x, y, width, height, dir, hp, armor,textureName);
		this.trajectory = trajectory;
		this.speed = 16;
		
	}
	/**
	 * @return the trajectory
	 */
	public Vector3 getTrajectory() {
		return trajectory;
	}
	/**
	 * @param trajectory the trajectory to set
	 */
	public void setTrajectory(Vector3 trajectory) {
		this.trajectory = trajectory;
	}
	/**
	 * move the bullet a bit should be called every update period
	 * @param speed
	 */
	
	//TODO remove gdx.graphicks get delta time...
	public void move(float speed){
	
		//Gdx.app.log("Projectile", "moved");
		this.setX( (this.x += speed  * Gdx.graphics.getDeltaTime()* trajectory.x));
		this.setY( (this.y += speed  * Gdx.graphics.getDeltaTime()* trajectory.y));
		//* Gdx.graphics.getDeltaTime()
		
	}
	
	public void move(){
		this.setX( (this.x += speed * trajectory.x));
		this.setY( (this.y += speed * trajectory.y));
	}

}
