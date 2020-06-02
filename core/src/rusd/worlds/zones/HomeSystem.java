package rusd.worlds.zones;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import rusd.entities.better.Building;
import rusd.entities.modular_ship.ShipOutline;
import rusd.methods.TextureNames.Textures;


public class HomeSystem extends SolarSystem {
	

	public Building sun;
	public Building earth;
	public Building factory;
	public ShipOutline ship;
	public EarthZone ez;
	public FactoryZone fzone;
	
	public HomeSystem(){
		float dist = 128*8 ;
		
		ship = new ShipOutline();
	
		sun = new Building();
		sun.tr = Textures.SUN;
		sun.bounds.width = 128*4;
		sun.bounds.height = 128*4;
		sun.setBounds(Gdx.graphics.getWidth()/2 -sun.bounds.width/2, Gdx.graphics.getHeight()/2-sun.bounds.width/2);
		
		earth = new Building();
		earth.tr = Textures.EARTH;
		earth.bounds.width = 64*4;
		earth.bounds.height = 64*4;
		earth.setBounds(Gdx.graphics.getWidth()/2+dist, Gdx.graphics.getHeight()/2-earth.bounds.height/2);
		earth.hasFightZone = true;
		ez = new EarthZone(ship);
		earth.fightZone = 1;
		fightZones.add(ez);
		
		
		factory = new Building();
		factory.bounds.width = 128;
		factory.bounds.height = 128;
		factory.setBounds(Gdx.graphics.getWidth()/2+dist+64*4, Gdx.graphics.getHeight()/2- factory.bounds.height/2);
		factory.tr = Textures.SPACESTATION;
		factory.hasFightZone = true;
		fzone = new FactoryZone(ship);
		factory.fightZone = 2;
		fightZones.add(fzone);
		
		
		buildings.add(sun);
		buildings.add(earth);
		buildings.add(factory);
		
		
		
	}
	
	

}
