package rusd.methods;

import rusd.GameScreen;
import rusd.GameScreen.renderLocation;
import rusd.entities.Ship;
import rusd.test.solarmapTest;
import rusd.windows.ShipUpgradeWindow;
import rusd.worlds.UniverseMap;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class MyInputProcessor  implements InputProcessor  {
	public UniverseMap map;
	//Ship ship;
	OrthographicCamera cam;
	
	public void setCam(OrthographicCamera cam){
		this.cam = cam;
	}
	
	public MyInputProcessor(UniverseMap map){
		this.map = map;
		//this.ship = map.ship;
		this.cam = map.cam;
	}
	
	

	@Override
	public boolean keyDown(int keycode) {
		if(keycode == Input.Keys.F1){
			map.drawBounds = !map.drawBounds;
			
		}
		if(keycode == Input.Keys.F12){
			map.game.setScreen(new solarmapTest(map.game));
		}
		
		if(keycode == Input.Keys.P){
			if(map.SystemToRender >= 0){
			//	map.solarSystems.get(map.SystemToRender).shipUpgradeWindow = new ShipUpgradeWindow();
				map.solarSystems.get(map.SystemToRender).shipUpgradeWindow.window.
				setVisible(!map.solarSystems.get(map.SystemToRender).shipUpgradeWindow.window.isVisible());
				System.out.println("you should be able to see me - window");
				
				map.solarSystems.get(map.SystemToRender).shipUpgradeWindow.stage.getViewport().project(cam.position);
				
				
			}
				
		}
		
		if(Input.Keys.ESCAPE == keycode){
			Gdx.app.exit();
			
		}
		
		if(keycode == Input.Keys.SHIFT_LEFT){
			
			setScreen();
		
		}
		
		if(keycode == Input.Keys.BACKSPACE){
		
			//TODO Fix camera
			if(map.SpacePlacetoRender 	>= 0 ){
				map.SpacePlacetoRender = -1;
				map.solarSystems.get(0).fixShip();
			}
		}
	
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		
		
		
		if(keycode == Input.Keys.SPACE){
		
			//gs.trackShip = !gs.trackShip;
			
		}
		

		return false;
	}

	@Override
	public boolean keyTyped(char character) {

		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
	
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		//Gdx.app.log(this.toString(), "scrolled " + amount);
		if (amount > 0){
		//	gs.zoomAmount *=2;
		//	cam.zoom += gs.zoomAmount;
			cam.zoom += 20;
		//	gs.translateAmount = gs.zoomAmount*5;
			//gs.SpaceStation.spaceCam.zoom += gs.zoomAmount;
			
			//Gdx.app.log(this.toString(), "scrolled " + camera.zoom);
			
		}
		else{
			
			//gs.SpaceStation.spaceCam.zoom -= gs.zoomAmount;
		//	cam.zoom -= gs.zoomAmount;
		//	gs.zoomAmount /=2;
			cam.zoom -= 20;
		//	gs.translateAmount = gs.zoomAmount *5;
			// prevents inverse zooming shiznits
		//	if(gs.camera.zoom < 0){
		//		cam.zoom = 1;
		//	}
				
			//Gdx.app.log(this.toString(), "scrolled " + camera.zoom);
			
			
		}
		// no idea what this return does lolz.
		return false;
		
		
		
	
	}
	/**
	 * Super ghetto but it works for now  Possibly make a class that implements Input
	 */
	public void checkInputs(){

		
		if(Gdx.input.isTouched()){
			//Gdx.app.log(toString(), "down");	
			// makes sure you are in a spaceplace before you can fire
			if(map.SpacePlacetoRender >= 0)
			map.solarSystems.get(map.SystemToRender).spacePlaces.get(map.SpacePlacetoRender).fire();
		}
				
		if (Gdx.input.isKeyPressed(Keys.COMMA)){
			map.ship.modThrust(0);
		}
			
		if (Gdx.input.isKeyPressed(Keys.O)){
			map.ship.modThrust(1);
			
		}	
		
		if (Gdx.input.isKeyPressed(Keys.A)){
		
			map.ship.modThrust(2);
			
		}
			
		if (Gdx.input.isKeyPressed(Keys.E)){
			map.ship.modThrust(3);
		
		}
		if(!Gdx.input.isKeyPressed(Keys.COMMA)){
			map.ship.modThrust(4);
		}
		
		if (!Gdx.input.isKeyPressed(Keys.O)){
			map.ship.modThrust(5);
			
		}	
		
		if (!Gdx.input.isKeyPressed(Keys.A)){
			
			map.ship.modThrust(6);
		
		}
		
		if (!Gdx.input.isKeyPressed(Keys.E)){
			map.ship.modThrust(7);
			
		}
			
	
		if (Gdx.input.isKeyPressed(Keys.ENTER)){
		//	if(TimeUtils.nanoTime() - gs.gameWorld.lastFireTime > 10)
			
			map.ship.fire();
	//	}
		if (Gdx.input.isKeyPressed(Keys.SPACE)){
			map.ship.stop();
			
		}
			
		if (Gdx.input.isKeyPressed(Keys.ESCAPE)){
			Gdx.app.exit();
		}
		
	}

	}
	
	// detects where the ship is and tells where to enter it.
	// 
	public void setScreen(){
		
	if(map.SpacePlacetoRender == -1){
		
		
		for(int j = 0; j < map.solarSystems.get(0).spacePlaces.size; j ++){
			if(map.ship.bounds.overlaps(map.solarSystems.get(0).spacePlaces.get(j).solarBounds)){
				map.SpacePlacetoRender = j;
				map.solarSystems.get(0).spacePlaces.get(j).fixShip();
				Gdx.app.log("","Entered planet: "+j);
				break;
				
				
			}
		}
	}
		
	}
	
	}


