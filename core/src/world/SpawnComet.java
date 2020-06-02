package world;

import rusd.entities.Comet;
import rusd.entities.Planet;
import rusd.methods.TextureNames;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;


public class SpawnComet {

	
	@SuppressWarnings("unused")
	/**
	 * 
	 * give @param earth
	 * @return a randomly located comet 1000 away
	 */
	private static Comet spawnComet(Planet earth){
		
			
		float randAngle;
		randAngle= MathUtils.random(0, 360);
	
		Gdx.app.log("spawnComet","Rand angle:" + randAngle);
		randAngle *= MathUtils.degreesToRadians;
		float x,y;
		
		
		x =  (float) (Math.cos(randAngle) * 1000);
		y =  (float) (Math.sin(randAngle) * 1000);
		Vector3 tempVect = new Vector3(-x/1000, -y/1000, 0);
		x += earth.center.x;
		y += earth.center.y;
		
		
		Comet comet = new Comet(x-8, y-8, 16, 16, 0, 10, 1,TextureNames.Textures.COMET, tempVect);
		return comet;
		
		
	}
}
