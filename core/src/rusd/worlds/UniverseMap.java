package rusd.worlds;

import rusd.myGame;
import rusd.entities.Ship;
import rusd.entities.modular_ship.ShipOutline;
import rusd.methods.MyInputProcessor;
import rusd.methods.TextureNames;
import rusd.methods.TextureNames.Textures;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class UniverseMap implements Screen {
	
	public myGame game;
	public Ship ship;
	public ShipOutline ship2;
	
	public Array<SolarMap> solarSystems;
	public int SystemToRender = 0;
	public int SpacePlacetoRender = -1;
	
	public OrthographicCamera cam;
	public OrthographicCamera hudCam;
	public MyInputProcessor myInputProc;
	public boolean drawBounds = false;
	
	public UniverseMap(myGame game){
		
		this.game = game;
		solarSystems = new Array<SolarMap>();
		SolarMap solarMap = new SolarMap(this);
		solarSystems.add(solarMap);
		ship = new Ship(solarMap.spacePlaces.get(5).solarBounds.x, 0, 32, 32, 0, 10, 1, Textures.SHIP, 0);
		cam = new OrthographicCamera();
		cam.setToOrtho(false,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		myInputProc = new MyInputProcessor(this);
		ship2 = new ShipOutline(5,16);
		
	}
	

	@Override
	public void render(float delta) {
		
		Gdx.input.setInputProcessor(myInputProc);
		//TODO move to lower classes
		myInputProc.checkInputs();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		if(SystemToRender == -1){
			myInputProc.setCam(cam);
			renderUniverse();
		}
		else{
			if(SpacePlacetoRender == -1){
				//TODO render solar System
				myInputProc.setCam(solarSystems.get(SystemToRender).cam);
				solarSystems.get(SystemToRender).render(game.batch);
				//solarSystems.render();
			}
			else{
				//TODO render SpacePlace
				myInputProc.setCam(solarSystems.get(SystemToRender).spacePlaces.get(SpacePlacetoRender).spaceCam);
				solarSystems.get(SystemToRender).spacePlaces.get(SpacePlacetoRender).render(game.batch);;
			}
		}
		game.hudBatch.begin();
		TextureNames.font.draw(game.hudBatch, "Ship X:" + ship.x + " Y: " + ship.y, 0, Gdx.graphics.getHeight());
		game.hudBatch.end();
		
		
		// TODO Auto-generated method stub
		
	}
	public void renderUniverse(){
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
