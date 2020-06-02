package rusd.test;

import java.util.Iterator;

import rusd.myGame;
import rusd.entities.EnemyShip;
import rusd.entities.Projectile;
import rusd.entities.modular_ship.Blank;
import rusd.entities.modular_ship.Block;
import rusd.entities.modular_ship.Sheild;
import rusd.entities.modular_ship.ShipOutline;
import rusd.entities.modular_ship.Thrust;
import rusd.methods.Intersects;
import rusd.methods.MyMath;
import rusd.methods.TextureNames.Textures;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class ShipTestingScreen implements Screen {
	
	
	
	
	myGame game;
	OrthographicCamera cam;
	
	public boolean boxes;
	public ShipOutline ship;
	/**
	 * number of pixels wide the ship is.
	 */
	public int size;
	public ShapeRenderer sr;
	public Array<EnemyShip> enemyShips;
	public long spawnTime;
	
	public ShipTestingScreen(myGame game, ShipOutline ship){
		this.game = game;
		this.ship = ship;
		this.size = 16;
		cam = new OrthographicCamera();
		cam.setToOrtho(false,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		sr = new ShapeRenderer();
		enemyShips = new Array<EnemyShip>();
		spawnTime = TimeUtils.nanoTime();
		spawnEnemy();
	}
	
	

	@Override
	public void render(float delta) {
		update();
		cam.update();
		boxes = false;
		
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		
		game.batch.setProjectionMatrix(cam.combined);
		game.batch.begin();
		
		// Renders the modular Ship
		for(int i = 0; i < ship.blocks.length;i++)
		for(int j = 0; j <ship.blocks[i].length; j++){

			// this convoluted mess offsets the ship so it renders all the squares in the right spots..
			if(!ship.blocks[i][j].destroyed){
				
			game.batch.draw(ship.blocks[i][j].tr1,
					ship.center.x + j *(size)- ship.size * size /2, 
					ship.center.y - (i+1) *(size) + ship.size * size /2, 
//					16,
					(float)size*ship.size/2-size*j,
//					16,
					//size*i - size*2 + size/2 , 
					size * i - size * (ship.size/2 -.5f),
					size, size, 1f, 1f, ship.direction-90);
			}
		
		}
		
		if(enemyShips.size > 0){
			
		Iterator<EnemyShip> esitor = enemyShips.iterator();
		while(esitor.hasNext()){
			EnemyShip eship = esitor.next();
			game.batch.draw(eship.textureRegion, eship.x, eship.y, eship.bounds.width/2, eship.bounds.height/2
					, eship.bounds.width, eship.bounds.height, 1, 1, eship.direction-90);
		
			Iterator<Projectile> pitor = eship.bullets.iterator();
			
			while(pitor.hasNext()){
				Projectile p = pitor.next();
				game.batch.draw(p.textureRegion,p.x,p.y,p.bounds.width,p.bounds.height);
				//System.out.println("drewed" + p.x);
			}
			
		}
		}
		
		
		if(ship.bullets.size > 0){
			Iterator<Projectile> itor = ship.bullets.iterator();
			while(itor.hasNext()){
				Projectile bullet = itor.next();
				//game.batch.draw(bullet.textureRegion, bullet.bounds.x, bullet.bounds.y,bullet.bounds.width,bullet.bounds.height);
				game.batch.draw(bullet.textureRegion, bullet.x, bullet.y , bullet.bounds.width/2,bullet.bounds.height/2,
						bullet.bounds.width, bullet.bounds.height, 1	, 1, bullet.direction -90);
			}
		}

		
		game.font.draw(game.batch, "ship center X: " + ship.center.x, 0, 20);
		game.font.draw(game.batch, "ship center Y: " + ship.center.y, 0, 40);
		Vector3 mousePos = new Vector3();
		mousePos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
		cam.unproject(mousePos);
		
		game.font.draw(game.batch, "MousePos Y: " + mousePos.y, 0, 60);
		game.font.draw(game.batch, "MousePos X: " + mousePos.x, 0, 80);
		game.font.draw(game.batch, "TopLeft X: " + ship.topRight.x, 0, 100);
		game.font.draw(game.batch, "TopLeft Y: " + ship.topRight.y, 0, 120);
		game.font.draw(game.batch, "Sheilds: " + ship.currentShield + "/" + ship.shieldMax, 0, 140);
		game.font.draw(game.batch, "Energy: " +  (int)  ship.currentCharge  + "/" + ship.maxCharge, 0, 160);
		
		
		
		
		
		game.batch.end();
	
	
			
		sr.setProjectionMatrix(cam.combined);
		sr.begin(ShapeType.Line);
		sr.setColor(Color.RED);
		if(boxes){
		//sr.rect(ship.center.x-size/2, ship.center.y-size/2, size, size);
		//sr.polygon(ship.vertices);a,a
		
		for(int i = 0; i < ship.blocks.length;i++)
			for(int j = 0; j <ship.blocks[i].length; j++){
				//sr.rect(ship.blocks[i][j].center.x-16, ship.blocks[i][j].center.y-16, 32, 32);
				sr.polygon(ship.blocks[i][j].poly.getVertices());
				//System.out.println(ship.blocks[i][j].center.x);
				
				
			}
		
		if(ship.bullets.size > 0){
			Iterator<Projectile> itor = ship.bullets.iterator();
			while(itor.hasNext()){
				Projectile bullet = itor.next();
				sr.rect(bullet.bounds.x,bullet.bounds.y,bullet.bounds.width,bullet.bounds.height);
			}
		}
		
		if(enemyShips.size > 0){
			
			Iterator<EnemyShip> esitor = enemyShips.iterator();
			while(esitor.hasNext()){
				EnemyShip ship = esitor.next();
			
				sr.rect(ship.bounds.x, ship.bounds.y, ship.bounds.width, ship.bounds.height);
				//System.out.println("drewed" + ship.y);
			}
			}
		
		
		
		}
		if(ship.isShieldUp){
			sr.setColor(Color.BLUE);
			// 
			sr.circle(ship.center.x, ship.center.y, ship.shieldRadius);
		}
		sr.end();
		
		
		
	}
	public void spawnEnemy(){
		if(TimeUtils.nanoTime() - spawnTime > 1){
			EnemyShip es = new EnemyShip(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()-100, 32, 32, 0, 5, 1, Textures.ENEMY1);
			enemyShips.add(es);
			//System.out.println("ship spawned");
			spawnTime = TimeUtils.nanoTime();
		}
		
	}
	
	public void checkCollision(){
		
		
		Iterator<Projectile> itorB = ship.bullets.iterator();
		
		
		while(itorB.hasNext()){
			Projectile b = itorB.next();
			float hypo;
//			float x;
//			float y;
//			
//			x = b.center.x - ship.center.x;
//			y = b.center.y - ship.center.y;
//			
//			hypo = (float) Math.sqrt(x * x + y * y);
			hypo = MyMath.hypo(b.center, ship.center);
			//TODO set up weapon range
			if(hypo > 600){
				itorB.remove();
				continue;
			}
			Iterator<EnemyShip> itorE = enemyShips.iterator();
			while(itorE.hasNext()){
				EnemyShip e = itorE.next();
				
				if(e.bounds.overlaps(b.bounds)){
					itorE.remove();
					itorB.remove();
					break;
				}
			}
			
			
		}
		
	}
	// checks all the bullets against the ship
	public void checkShipColision(){
		// check all the bullets to see if they hit any of the boxes.
		Iterator<EnemyShip> eitor = enemyShips.iterator();
		// goes through each ship
		while(eitor.hasNext()){
			EnemyShip e = eitor.next();
			if(e.bullets.size > 0){
				Iterator<Projectile> pitor = e.bullets.iterator();
				// checks each bullet belonging to each ship
				while(pitor.hasNext()){
					
				
				Projectile p = pitor.next();
				Array<Block> blocksHit = new Array<Block>();
				Vector2 p1 = p.center;
				Vector2 p2 = new Vector2(p.center.x - p.getTrajectory().x*p.speed, p.center.y - p.getTrajectory().y*p.speed) ;
				// checks to see if it's even close enough to possibly hit the ship 
				//if(MyMath.hypo(p.center, ship.center) < ship.shipPixel * ship.size){
					// this part isn't ray casting, but it is faster and less acurate.
					if(MyMath.hypo(p.center, ship.center)<= ship.shieldRadius && ship.isShieldUp && ship.currentShield >= 1){
						ship.hitShield();
						pitor.remove();
						// hit the shield go to next bullet.
						continue;
						
					}
					for(int i = 0; i < ship.blocks.length;i++)
						for(int j = 0; j <ship.blocks[i].length; j++){
							// skips all the blank and destroyed blocks
							// continue moves on to the next block no need for all that extra math
							// for the blank spaces.
							if(ship.blocks[i][j] instanceof Blank || ship.blocks[i][j].destroyed){
								
								continue;
							}
							
							
							// point 1 and 2 are from the bullets trajectory
							// poitn 3 and 4 are from the polygon lines
							
							
							Vector2 p3 = new Vector2(ship.blocks[i][j].poly.getVertices()[0],ship.blocks[i][j].poly.getVertices()[1]);
							Vector2 p4 = new Vector2(ship.blocks[i][j].poly.getVertices()[2],ship.blocks[i][j].poly.getVertices()[3]);
							Vector2 p5 = new Vector2(ship.blocks[i][j].poly.getVertices()[4],ship.blocks[i][j].poly.getVertices()[5]);
							Vector2 p6 = new Vector2(ship.blocks[i][j].poly.getVertices()[6],ship.blocks[i][j].poly.getVertices()[7]);
							
						  	if(	Intersects.LineLine(p1,p2,p3,p4)){
						  		blocksHit.add(ship.blocks[i][j]);
						  		
						  	}
						  	else if(Intersects.LineLine(p1,p2,p4,p5)){
						  		blocksHit.add(ship.blocks[i][j]);
						  		
						  	}
						  	else if(Intersects.LineLine(p1,p2,p5,p6)){
						  		blocksHit.add(ship.blocks[i][j]);
						  		
						  	}
						  	else if(Intersects.LineLine(p1,p2,p6,p3)){
						  		blocksHit.add(ship.blocks[i][j]);
						  		
						  	}
						  }
					
					if(blocksHit.size> 0){
						
						Block tempBlock;
						tempBlock = blocksHit.get(0);
						for(int i = 0; i < blocksHit.size; i ++){
							// if the block is closer to the previous location of the projectile
							// set as the 1st block hit.
							if(MyMath.hypo(p2, blocksHit.get(i).center) < MyMath.hypo(p2, tempBlock.center)){
								tempBlock = blocksHit.get(i);
							}	
						}
						// TODO move this up so you dont have to do as much math...
						if(ship.currentShield > 1 && ship.isShieldUp){
							ship.hitShield();
						}
						else{
							
						tempBlock.hit();
						// if the block hit is thrust recalculate the speed
						if(tempBlock instanceof Thrust){
							ship.setSpeed();
							
						}
						else if(tempBlock instanceof Sheild){
							ship.setShield();							
						}
						}
						pitor.remove();
					}
						
						
				}
					
					}
		//	}
			
		}
		
		
		
	}
	
	public void moveEnemyBullets(){
		if(enemyShips.size > 0){
			
			Iterator<EnemyShip> esitor = enemyShips.iterator();
			while(esitor.hasNext()){
				EnemyShip eship = esitor.next();
				//eship.setY(eship.getY() -1);
				eship.AI(ship.center);
				Iterator<Projectile> pitor = eship.bullets.iterator();
				
				while(pitor.hasNext()){
					Projectile p = pitor.next();
					p.move();
					
					float hypo;
//					float x;
//					float y;
//					
//					x = p.center.x - ship.center.x;
//					y = p.center.y - ship.center.y;
//					
//					hypo = (float) Math.sqrt(x * x + y * y);
					
					hypo = MyMath.hypo(p.center, ship.center);
					//TODO set up weapon range
					if(hypo >= eship.range){
						pitor.remove();
						continue;
					}
				}
				
				
			}
			}
	}
	
	
	public void update(){
		
		//spawnEnemy();
		moveEnemyBullets();
		// check my bullets vs the enemy ship
		checkCollision();
		// check enemy bullets vs my ship
		checkShipColision();
		
		ship.rechargShield();
		
		ship.charge();
		
	
		if(Gdx.input.isKeyPressed(Keys.F12)){
			game.setScreen(new ShipBuilder(game, 9));
		}
		if(Gdx.input.isKeyPressed(Keys.A)){

			// move ship right
			//ship.center.x -=4;
			//ship.topRight.x -=4;
			ship.move(-1,0);
			
		}
		if(Gdx.input.isKeyPressed(Keys.E)||Gdx.input.isKeyPressed(Keys.D)){
			// move ship left
			//ship.center.x +=4;
			//ship.topRight.x +=4;
			ship.move(1,0);
		}
		if(Gdx.input.isKeyPressed(Keys.COMMA)||Gdx.input.isKeyPressed(Keys.W)){
			
			// move ship up
			//ship.center.y +=4;
			//ship.topRight.y +=4;
			ship.move(0,1);
		}
		if(Gdx.input.isKeyPressed(Keys.O)||Gdx.input.isKeyPressed(Keys.S)){
			// move ship down
			//ship.center.y -=4;
			//ship.topRight.y -=4;
			ship.move(0,-1);
			
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
