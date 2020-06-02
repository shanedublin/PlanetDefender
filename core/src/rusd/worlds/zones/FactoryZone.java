package rusd.worlds.zones;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

import rusd.entities.better.Building;
import rusd.entities.better.Entity2;
import rusd.entities.enemy.CometWarpGate;
import rusd.entities.enemy.WarpGate;
import rusd.entities.modular_ship.ShipOutline;
import rusd.methods.TextureNames.Textures;
import rusd.windows.ShipBuilderWindow;

public class FactoryZone extends FightZone {

	
	
	
	public Building factory;
	
	
	public FactoryZone(ShipOutline ship){
		
		super(ship);
 
		this.width = 1920;
		this.height = 1080;
		
		factory = new Building();
		factory.setCenter(width/2, height/2);
		factory.tr = Textures.SPACESTATION;
		factory.bounds.width  = 128*2;
		factory.bounds.height = 128*2;
		factory.setBounds(Gdx.graphics.getWidth()/2 +128, Gdx.graphics.getHeight()/2 + 128);
		buildings.add(factory);
		
		// sets up 4 gates that you'll have to kill 
		CometWarpGate cg = new CometWarpGate();
		cg.setBounds(0, height/2);
		
		gates.add(cg);
		
		CometWarpGate cg1 = new CometWarpGate();				
		cg1.setBounds(width, height/2);
		gates.add(cg1);
		
		CometWarpGate cg2 = new CometWarpGate();		
		cg2.setBounds(width/2, height);
		gates.add(cg2);
		
		CometWarpGate cg3 = new CometWarpGate();		
		cg3.setBounds(width/2, 0);
		gates.add(cg3);
		
		
	}
	
	
	@Override
	public void userInput(){
		if (Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)){
			//open ship building screen.  change ship to the right size. 
			
			
		}
	}
	
	
	
	
}
