package world;

import java.util.Iterator;

import rusd.entities.Comet;
import rusd.entities.Planet;
import rusd.entities.Projectile;
import rusd.entities.Satellite;
import rusd.entities.Ship;
import rusd.methods.TextureNames;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

/**
 * this class holds all the objects and game logic or it forwards it to another
 * class.
 * 
 * @author Shane
 * 
 */
public class World {

	// Objects
	public Ship ship;
	
	public SolarSystem solarSystem;
	public Satellite spaceStation;

	// Constants
	final int SHIPWIDTH = 32;
	final int SHIPHEIGHT = 32;
	final int ORBWIDTH = 8;
	final int ORBHEIGHT = 8;
	final boolean BOXES = false;

	// Variables
	int dropsGathered;
	int width, height;
	float cometSpawnDeg = 0;
	int cometsDestroyed =0;


	// Timers
	public long lastCometTime;
	public long lastFireTime;
	public long cometFireRate;
	public float Timer;
	// Arrays
	public Array<Projectile> bullets;
	public Array<Rectangle> raindrops;
	public Array<Comet> comets;

	public World() {
		this.solarSystem = new SolarSystem();
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
		this.spaceStation = new Satellite(solarSystem.planets.get(3).x-10000, 0, 256,256,0,10,100, TextureNames.Textures.SPACESTATION, solarSystem.planets.get(3));
		this.ship = new Ship(spaceStation.center.x,spaceStation.center.y, SHIPWIDTH,
				SHIPHEIGHT, 0, 10, 1, TextureNames.Textures.SHIP, 0);
		
		
		this.lastFireTime = TimeUtils.nanoTime();
		this.lastCometTime = TimeUtils.nanoTime();
		this.cometFireRate = 1000000000;
		bullets = new Array<Projectile>();
		comets = new Array<Comet>();
		this.Timer = 60;
		
		spawnComet();
		
	}

	/**
	 * this should be called every cycle. this updates everything in the world
	 * moves objects checks collisions
	 * 
	 */
	
	public void updateWorld() {
		
		
		
	
	
		
		
		
		
		this.Timer -= Gdx.graphics.getDeltaTime();
		ship.move();
		
	
		if (TimeUtils.nanoTime() - lastCometTime > cometFireRate){
			spawnComet();
			lastCometTime = TimeUtils.nanoTime();
			cometFireRate -= 10000;
			//Gdx.app.log(toString(), "kermit spawned");
		}
	
		
		if(comets != null){
			
		Iterator<Comet> iterComet = comets.iterator();
		// moves the comets
		while (iterComet.hasNext()) {
			Comet comet = iterComet.next();
			comet.move(2000);
			// if the commet touches earth hurt earth.
			if (solarSystem.planets.get(3).overlaps(comet.center)) {
			//if (comet.bounds.overlaps(solarSystem.planets.get(3).bounds)) {
				solarSystem.planets.get(3).population *= comet.getPlanetDamage();
				//iterComet.remove();
				solarSystem.planets.get(3).modHealth(-1);
				//Gdx.app.log(toString(), "hit te earth");
			}
			// if the ship hits the comet kill the  ship
			else if(comet.bounds.overlaps(ship.bounds)){
				ship.kill();
				iterComet.remove();
			}
		}
		}


		if (bullets != null) {
			Iterator<Projectile> projectileIter = bullets.iterator();
			
			while (projectileIter.hasNext()) {
				Projectile bullet = projectileIter.next();
				bullet.move(2000);
				// if the bullet is too far away from the ship remove it
				if (Math.abs(ship.center.x -bullet.center.x) > 1000)
					projectileIter.remove();
				else if (Math.abs(ship.center.y -bullet.center.y) > 1000)
					projectileIter.remove();				
				// if the bullet contacts the comet kill the bullet and comet.
				else {

					Iterator<Comet> iterComet1 = comets.iterator();
					while (iterComet1.hasNext()) {
						Comet comet = iterComet1.next();
						if (bullet.bounds.overlaps(comet.bounds)) {
							iterComet1.remove();
							//projectileIter.remove();
							cometsDestroyed ++;
							break;

						}

					}
				}

			}
		}
		
		

	}

	public void fire() {
		Vector3 Trajectory;
		Projectile bullet;

		Trajectory = new Vector3((float) Math.cos(ship.direction
				* MathUtils.degreesToRadians), (float) Math.sin(ship.direction
				* MathUtils.degreesToRadians), 0);
		//TODO CHANGE ORB NAME
		bullet = new Projectile((ship.center.x - ORBWIDTH / 2),
				(ship.center.y - ORBHEIGHT / 2), ORBWIDTH, ORBHEIGHT, 0, 1, 1,TextureNames.Textures.ORB,
				Trajectory);
		
		//Gdx.app.log("gameWorld", "bullet added");
		bullets.add(bullet);
		lastFireTime = TimeUtils.nanoTime();
	}

	public void spawnComet() {
		
		for (int i =0; i < 360; i ++){
			
		float randAngle;
		randAngle= MathUtils.random(0, 360);
		randAngle =  i ;
	
		//Gdx.app.log("spawnComet","Rand angle:" + randAngle);
		randAngle *= MathUtils.degreesToRadians;
		float x,y;
		
		
		x =  (float) (Math.cos(randAngle) * 7500);
		y =  (float) (Math.sin(randAngle) * 7500);
		Vector3 tempVect = new Vector3(-x/7500, -y/7500, 0);
		x += solarSystem.planets.get(3).center.x;
		y += solarSystem.planets.get(3).center.y;
		
		
		
		Comet comet = new Comet(x-8, y-8, 16, 16, 0, 10, 1,TextureNames.Textures.COMET, tempVect);
		comets.add(comet);
		}

	}


	// TODO delete this if the code is working properly.
	public void keyPressHandler(Input input){
		
		
		
		if (Gdx.input.isKeyPressed(Keys.COMMA)){
			ship.modThrust(0);
		}
			
		if (Gdx.input.isKeyPressed(Keys.O)){
			ship.modThrust(1);
			
		}	
		
		if (input.isKeyPressed(Keys.A)){
		
			ship.modThrust(2);
			
		}
			
		if (Gdx.input.isKeyPressed(Keys.E)){
			ship.modThrust(3);
		
		}
		if(!Gdx.input.isKeyPressed(Keys.COMMA)){
			ship.modThrust(4);
		}
		
		if (!Gdx.input.isKeyPressed(Keys.O)){
			ship.modThrust(5);
			
		}	
		
		if (!input.isKeyPressed(Keys.A)){
			
			ship.modThrust(6);
		
		}
		
		if (!Gdx.input.isKeyPressed(Keys.E)){
			ship.modThrust(7);
			
		}
			
	
		if (Gdx.input.isKeyPressed(Keys.ENTER)){
			if(TimeUtils.nanoTime() - lastFireTime > 10)
			this.fire();
		}
		if (Gdx.input.isKeyPressed(Keys.SPACE)){
			ship.stop();
			
		}
			
		if (Gdx.input.isKeyPressed(Keys.ESCAPE)){
			Gdx.app.exit();
		}
			
			
		
	}
	
	public void dispose(){

		
	}
	
	public String toString(){
		return "World";
	}
	public int getCometsDestroyed(){
		return cometsDestroyed;
	}
}
