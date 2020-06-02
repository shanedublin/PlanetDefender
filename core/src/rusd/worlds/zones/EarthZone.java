package rusd.worlds.zones;

import com.badlogic.gdx.Gdx;

import rusd.entities.better.Building;
import rusd.entities.enemy.DroneWarpGate;
import rusd.entities.modular_ship.ShipOutline;
import rusd.methods.TextureNames.Textures;
import rusd.windows.ShipUpgradeWindow;

public class EarthZone extends FightZone {
	
	
	public Building earth;
	
	public EarthZone(ShipOutline ship){
		super(ship);
		earth = new Building();
		this.width = 1920*2;
		this.height = 1080*2;
		
		earth = new Building();
		earth.tr = Textures.EARTH;
		earth.bounds.width = 64*4;
		earth.bounds.height = 64*4;
		earth.setBounds(Gdx.graphics.getWidth()/2-earth.bounds.width/2, Gdx.graphics.getHeight()/2-earth.bounds.height/2);
		
		
		buildings.add(earth);
		DroneWarpGate g = new DroneWarpGate();
		g.bounds.width   = 64;
		g.bounds.height  = 64;
		g.setBounds(Gdx.graphics.getWidth()/2- g.bounds.width/2, Gdx.graphics.getHeight()/2-g.bounds.height/2);
		gates.add(g);
	
}

}
