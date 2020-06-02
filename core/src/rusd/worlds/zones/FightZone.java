package rusd.worlds.zones;

import java.util.Iterator;

import rusd.myGame;
import rusd.entities.Entity;
import rusd.entities.Projectile;
import rusd.entities.better.Building;
import rusd.entities.better.Entity2;
import rusd.entities.enemy.Enemy;
import rusd.entities.enemy.WarpGate;
import rusd.entities.modular_ship.ShipOutline;
import rusd.methods.TextureNames.Textures;
import rusd.windows.ShipUpgradeWindow;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

/**
 * contains all the objects 
 * does all the math and updates
 * renders the crap as well
 * @author Shane
 *
 */
public class FightZone {

	public Texture background;
	public float width;
	public float height;
	public int fznumber;
	
	
	
	
	public Array<Entity2> entites;

	public Array<Enemy> enemies;
	
	public Array<WarpGate> gates;
	
	public Array<Building> buildings;
	
	/**
	 * default Constructor
	 */
	public FightZone(ShipOutline ship){
		
		entites = new Array<Entity2>();
		enemies = new Array<Enemy>();
		gates = new Array <WarpGate>();
		buildings = new Array<Building>();
		this.width = 1920;
		this.height = 1080;
		this.background = Textures.BACKGROUND;
		
		
	}
	
	
	public void update(ShipOutline ship){
		
		
		
		
		Iterator<Enemy> eitor = enemies.iterator();
		while(eitor.hasNext()){
			Enemy e  = eitor.next();
			e.update(ship.center);
			//System.out.println("blerg");
			
			Iterator<Projectile> bitor = ship.bullets.iterator();
			while(bitor.hasNext()){
				Projectile b = bitor.next();
				// change to raycasting...
				if(e.bounds.contains(b.center)){
					bitor.remove();
					eitor.remove();
					break;
				}
					
			}
		}
		
		Iterator<WarpGate> witor = gates.iterator();
		while(witor.hasNext()){
			WarpGate w = witor.next();
			//w.update();
			if(w.health <=0){
				witor.remove();
				continue;
			}
			if(w.canSpawn()){
				enemies.add(w.Spawn());
			}
			
		}
		Iterator<Entity2> itor = entites.iterator();
		while(itor.hasNext()){
			Entity2 e = itor.next();
			e.update();
		}
		
		
		Iterator<Projectile> pitor = ship.bullets.iterator();
		while(pitor.hasNext()){
			Projectile p = pitor.next();
			Iterator<WarpGate> wgitor = gates.iterator();
			while(wgitor.hasNext()){
				WarpGate g = wgitor.next();
				
				if(g.bounds.contains(p.center)){
					//TODO change to hit later;
					g.health --;
					pitor.remove();
					break;
					
				}
			}
			
			
		}
				
		
	}
	
	protected void setWidthHeight(float x, float y){
		
	}
	
	
	
	/**
	 * Call this function every render cycle
	 * updates the world then renders the screen.
	 */
	public void render(SpriteBatch batch){
		
		
		Iterator<Building> bitor = buildings.iterator();
		while(bitor.hasNext()){
			Building e  = bitor.next();
			batch.draw(e.tr, e.getX(), e.getY(), 0, 0, e.bounds.width, e.bounds.height, 1, 1, e.rotation);		
		}
		
		Iterator<WarpGate> witor = gates.iterator();
		while(witor.hasNext()){
			WarpGate e = witor.next();
			batch.draw(e.tr, e.getX(), e.getY(), 0, 0, e.bounds.width, e.bounds.height, 1, 1, e.rotation);		
		}
		
		Iterator<Enemy> eitor = enemies.iterator();
		while(eitor.hasNext()){
			Enemy e  = eitor.next();
			batch.draw(e.tr, e.getX(), e.getY(), e.bounds.width/2, e.bounds.width/2, e.bounds.width, e.bounds.height, 1, 1, e.rotation-90);		
		}
		
		
		
		
		
//		Iterator<Entity2> itor = entites.iterator();
//		while(itor.hasNext()){
//			Entity2 e = itor.next();
//			batch.draw(e.tr, e.getX(), e.getY(), 0, 0, e.bounds.width, e.bounds.height, 1, 1, e.rotation);		
//		}
		
		
	}
	
	public void debug(ShapeRenderer sr){
	
		Iterator<Building> bitor = buildings.iterator();
		while(bitor.hasNext()){
			Building e  = bitor.next();
			sr.rect(e.getX(), e.getY(),  e.bounds.width, e.bounds.height);		
		}
		
		Iterator<WarpGate> witor = gates.iterator();
		while(witor.hasNext()){
			WarpGate e = witor.next();
			sr.rect(e.getX(), e.getY(),  e.bounds.width, e.bounds.height);		
			
		}
		
		Iterator<Enemy> eitor = enemies.iterator();
		while(eitor.hasNext()){
			Enemy e  = eitor.next();		
			sr.rect(e.getX(), e.getY(),  e.bounds.width, e.bounds.height);		
		}
		
	
		
	}
	
	public void userInput(){
		
	}
	
}
