package rusd.test;

import java.util.Iterator;

import rusd.myGame;
import rusd.entities.Projectile;
import rusd.entities.better.Building;
import rusd.entities.modular_ship.ShipOutline;
import rusd.worlds.zones.FactoryZone;
import rusd.worlds.zones.HomeSystem;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector3;

public class AlphaScreen implements Screen {

	
	final myGame game;
	public HomeSystem hs;
	public ShipOutline ship;
	OrthographicCamera cam;
	
	
	public AlphaScreen(myGame game){
		cam = new OrthographicCamera();
		cam.setToOrtho(false,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		this.game = game;
		this.hs = new HomeSystem();
		this.ship = new ShipOutline();
		ship.center.x = Gdx.graphics.getWidth()/2;
		ship.center.y = Gdx.graphics.getHeight()/2;
				
			
		
	}
	
	
	
	
	
	
	
	@Override
	public void render(float delta) {
		userinput();	
		ship.update();
		camMovement();
		cam.update();
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.batch.setProjectionMatrix(cam.combined);
		game.batch.begin();
		
		hs.render(game.batch,ship);
		ship.render(game.batch);
		Iterator<Projectile> bullets = ship.bullets.iterator();
		while(bullets.hasNext()){
			Projectile b = bullets.next();
			game.batch.draw(b.textureRegion, b.x, b.y, 0, 0, b.bounds.width, b.bounds.height, 1, 1, b.direction-90);
		}
		
		game.batch.end();
		
		game.sr.setProjectionMatrix(cam.combined);
		game.sr.begin(ShapeType.Line);
		hs.debug(game.sr);
		game.sr.end();
		
		
	}
	public void camMovement(){
		this.cam.position.x = ship.center.x;
		this.cam.position.y = ship.center.y;
		
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
	
	public void userinput(){
		// TODO inuput procesor;
		
		
		
		
		
		if(Gdx.input.isKeyPressed(Keys.ENTER)){
			
			Iterator<Building> itor = hs.buildings.iterator();
			while(itor.hasNext()){
				Building b = itor.next();
				// if the ship is in the bounds, and the bounds has a fight zone,
				// and not already in a fight zone
				if(b.bounds.contains(ship.center) && b.hasFightZone && hs.fz == 0){
					System.out.println("Enter");
					hs.fz = b.getFightZone();
					ship.setCenter(Gdx.graphics.getWidth()/2, 0);
					
				}
				
			}
		}
		if(Gdx.input.isKeyPressed(Keys.BACKSPACE)){
			if(hs.fz!= 0){
				hs.fz = 0;
			}
		}
		
		
		if(Gdx.input.isKeyPressed(Keys.F12)){
			game.setScreen(new AlphaScreen(game));
		}
		if(Gdx.input.isKeyPressed(Keys.A)){

			// move ship right			
			ship.move(-1,0);
			
		}
		if(Gdx.input.isKeyPressed(Keys.E)||Gdx.input.isKeyPressed(Keys.D)){
			// move ship left
			ship.move(1,0);
		}
		if(Gdx.input.isKeyPressed(Keys.COMMA)||Gdx.input.isKeyPressed(Keys.W)){
			
			// move ship up
			ship.move(0,1);
		}
		if(Gdx.input.isKeyPressed(Keys.O)||Gdx.input.isKeyPressed(Keys.S)){
			// move ship down
			ship.move(0,-1);
			//System.out.print("bl");
			
		}
		
		Vector3 mousePos = new Vector3();
		mousePos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
		cam.unproject(mousePos);
		
		
		if(Gdx.input.isTouched()){
			ship.fire(mousePos);
		}
		
		if(ship.bullets.size > 0){
			Iterator<Projectile> itor = ship.bullets.iterator();
			while(itor.hasNext()){
				Projectile bullet = itor.next();
				bullet.move();
			}
		}
			
		
		
		
		ship.rotateShip(mousePos);	
		ship.blockCenters();
		
	}

}
