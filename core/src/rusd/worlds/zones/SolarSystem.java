package rusd.worlds.zones;

import java.util.Iterator;

import rusd.entities.better.Building;
import rusd.entities.enemy.Enemy;
import rusd.entities.modular_ship.ShipOutline;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class SolarSystem {

	
	public Array<Building> buildings;
	public Array<FightZone> fightZones;

	public int fz;
	public SolarSystem(){
		buildings = new Array<Building>();
		fightZones = new Array<FightZone>();
		fz = 0;
	}
	
	public void render(SpriteBatch batch,ShipOutline ship){
		// change to if statement
		
		if(fz == 0){
			Iterator<Building> eitor = buildings.iterator();
			while(eitor.hasNext()){
				Building e  = eitor.next();
				batch.draw(e.tr, e.getX(), e.getY(), 0, 0, e.bounds.width, e.bounds.height, 1, 1, e.rotation);		
			}
			
		} else if( fz > 0){
			fightZones.get(fz-1).update(ship);
			fightZones.get(fz -1).render(batch);
		}
		else{
			System.err.println("error nub");
		}
		
		
		
	}
	public void debug(ShapeRenderer sr){
		// change to if statement
		
		if(fz == 0){
			
		}
		else if (fz > 0){
			fightZones.get(fz -1).debug(sr);
		}
		else{
			System.err.println("wrong fz code");
		}
	
	}
	
	public void update(){
		
		
		
	}
	
}
