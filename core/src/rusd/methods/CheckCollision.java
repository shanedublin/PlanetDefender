package rusd.methods;

import java.util.Iterator;

import rusd.entities.Entity;

import com.badlogic.gdx.utils.Array;

public class CheckCollision {

	
	public static void Collisions(Array<Entity> entities,Array<Entity> entities2){
		Iterator<Entity> itor = entities.iterator();
		
		while(itor.hasNext()){
			Entity e = itor.next();
			
			Iterator<Entity> itor2 = entities.iterator();
			while(itor2.hasNext()){
				Entity e2 = itor2.next();
				
				if(e.bounds.overlaps(e2.bounds)){
					
					
				}
			}
			
			
		}
		
		
	}
	
	public static void Collisions(Array<Entity> entities,Entity entitie){
		Iterator<Entity> itor = entities.iterator();
		
		while(itor.hasNext()){
			Entity e = itor.next();
			
			
			
		}
	}
	

}
