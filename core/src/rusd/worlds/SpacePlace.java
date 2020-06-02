package rusd.worlds;

import java.util.Iterator;

import rusd.entities.Comet;
import rusd.entities.Entity;
import rusd.entities.Projectile;
import rusd.entities.Satellite;
import rusd.entities.Ship;
import rusd.methods.TextureNames;
import rusd.methods.TextureNames.Textures;
import rusd.quest.Quest;
import rusd.quest.Quest.type;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.sun.xml.internal.bind.v2.model.core.MaybeElement;

/**
 * All objects in space should have a space place.
 * when you get to the spaceplace from the main map you have the option to enter it.
 * This will bring a closer view of the planet , star , space station
 * This object will control all events that happen as well as quests in the space place. 
 *
 * 
 * @author Shane
 *
 */
public class SpacePlace  {
	
	public Ship ship;
	public SolarMap sMap;
	// The size of the space place, leave the boundaries and go back to the world map
	public float width;
	public float height;
	
	public OrthographicCamera spaceCam;
	public Entity entity;
	public Array<Entity> entities;
	public Array<Projectile> projectiles;
	public Rectangle solarBounds;
	public Quest quest;
	public boolean showHealthBars = true;
	public int size = 32;
	
	
	public SpacePlace(float width, float height,SolarMap sMap,Entity E){
		this.sMap = sMap;
		this.entity = new Entity(0- E.bounds.width/2, 0-E.bounds.height/2, E.bounds.width, E.bounds.height, E.direction, E.health, E.armor, E.textureRegion);
		this.solarBounds = new Rectangle(E.bounds.x - E.bounds.width/2,E.bounds.y - E.bounds.height/2,E.bounds.width,E.bounds.height);
		//this.entity = new Entity(Gdx.graphics.getWidth()/2 - 128, Gdx.graphics.getHeight()/2 - 128, 256	,256, 90, 10, 10,TextureNames.Textures.SPACESTATION);
		//this.ship= new Ship(50, 50, 32, 32, 90, 10, 1, TextureNames.Textures.SHIP,0);
		this.ship = sMap.map.ship;
		this.spaceCam = new OrthographicCamera();
		this.spaceCam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		this.spaceCam.position.x =0;
		this.spaceCam.position.y =0;
		
		entities = new Array<Entity>();
		//entities.add(ship);
		entities.add(entity);
		this.width = width + 1920;
		this.height = height + 1920;
		projectiles = new Array<Projectile>();
		
	}
	
	public SpacePlace(float width, float height,SolarMap sMap,Satellite E){
		this.sMap = sMap;
		this.entity = new Entity(0 - E.bounds.width/2, 0-E.bounds.height/2
				, E.bounds.width, E.bounds.height, E.direction, E.health, E.armor, E.textureRegion);
		
		this.solarBounds = new Rectangle(E.bounds);
		//this.entity = new Entity(Gdx.graphics.getWidth()/2 - 128, Gdx.graphics.getHeight()/2 - 128, 256	,256, 90, 10, 10,TextureNames.Textures.SPACESTATION);
		//this.ship= new Ship(50, 50, 32, 32, 90, 10, 1, TextureNames.Textures.SHIP,0);
		this.ship = sMap.map.ship;
		this.spaceCam = new OrthographicCamera();
		this.spaceCam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		this.spaceCam.position.x =0;
		this.spaceCam.position.y =0;
		
		entities = new Array<Entity>();
		//entities.add(ship);
		entities.add(entity);
		this.width = width+960;
		this.height = height + 960;
		projectiles = new Array<Projectile>();
	}
	
	
	

	public void update(){
		if(quest.qtype == type.COMET ){
			quest.update();
			
			Iterator<Projectile> bulletItor = projectiles.iterator();
				while(bulletItor.hasNext()){
					Projectile p = bulletItor.next();
						p.move(800);
					}
				
				
				
			
			Iterator<Projectile> itor = quest.comets.iterator();
			while(itor.hasNext()){
				Comet c = (Comet) itor.next();
				
				Iterator<Projectile> bulletItor2 = projectiles.iterator();
				while(bulletItor2.hasNext()){
					Projectile p = bulletItor2.next();
						if(p.bounds.overlaps(c.bounds)){
							c.health --;
							bulletItor2.remove();
							
						}
					}
			
				
				if(c.bounds.overlaps(entity.bounds)){
					//TODO make a better health system
					entity.health --;
					
					c.health --;
					if(entity.health <= 0)
						quest.failed = true;
				}
				
				if(c.health <= 0){
					itor.remove();
				}
				
			}
		}
		
		
		sMap.map.ship.move();
		updateCam();
		for (int i = 0; i < entities.size; i++) {
			Entity e = entities.get(i);
			
			//this might be bad.
			if(e instanceof Projectile){
				((Projectile) e).move(1600);
			}
			
			
		}
		
		//spaceCam.position.x = sMap.map.ship.x;
		//spaceCam.position.y = sMap.map.ship.y;
		
	} 
	public void fixShip(){
		sMap.map.ship.setThrust(350);
		sMap.map.ship.setX(spaceCam.position.x);
		sMap.map.ship.setY(spaceCam.position.y);
		
	}
	
	public void render(SpriteBatch batch){
		
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		// called every frame.
		update();
		
		spaceCam.update();
		
		//sMap.map.game.bgBatch.setProjectionMatrix(spaceCam.combined);;
		// this is the batch for drawing the background image.
		sMap.map.game.bgBatch.begin();
		sMap.map.game.bgBatch.disableBlending();
		// draws the back ground picture.
		sMap.map.game.bgBatch.draw(Textures.BACKGROUND, 0,0 , Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		sMap.map.game.bgBatch.end();
		
		// Main batch to draw all the objects
		batch.setProjectionMatrix(spaceCam.combined);
		batch.begin();
		Iterator<Entity> itor = entities.iterator();
		while(itor.hasNext()){
			Entity entity1 = itor.next();
			
			batch.draw(entity1.textureRegion, entity1.x, entity1.y, entity1.bounds.width/2, entity1.bounds.height/2,
					entity1.bounds.width	,entity1.bounds.height , 1, 1, entity1.getDirection());
		}
		
		Iterator<Projectile> pItor = projectiles.iterator();
		while(pItor.hasNext()){
			Projectile p = pItor.next();
			batch.draw(p.textureRegion, p.x, p.y, p.bounds.width/2, p.bounds.height/2,
					p.bounds.width	,p.bounds.height , 1, 1, p.getDirection());
		}
		
		
	
		
		batch.draw(sMap.map.ship.textureRegion, sMap.map.ship.x, sMap.map.ship.y, sMap.map.ship.bounds.width/2, 
				sMap.map.ship.bounds.height/2, sMap.map.ship.bounds.width, sMap.map.ship.bounds.height, 1, 1, 
				sMap.map.ship.getDirection()-90);
		
		
		// Renders the modular Ship
			for(int i = 0; i < sMap.map.ship2.blocks.length;i++)
			for(int j = 0; j <sMap.map.ship2.blocks[i].length; j++){

				// this convoluted mess offsets the ship so it renders all the squares in the right spots..
				batch.draw(sMap.map.ship2.blocks[i][j].tr1,
						sMap.map.ship.center.x + j *(size)- (float)sMap.map.ship2.blocks.length / 2*size, 
						sMap.map.ship.center.y-i*(size)+(((float) sMap.map.ship2.blocks.length/2)-1) *size , 
						(float)size*sMap.map.ship2.blocks.length/2-size*j, size/2 +size*i -size*2, 
						size, size, 1f, 1f, sMap.map.ship.getDirection()-90);
			
			}
		
		
		Vector3 mousePosistion;
		mousePosistion = new Vector3(Gdx.input.getX() ,Gdx.input.getY(),0);
		spaceCam.unproject(mousePosistion);
		//TextureNames.font.scale(5);
		TextureNames.font.draw(batch, "Mouse Pos:" + mousePosistion.x, Gdx.graphics.getWidth()-150, Gdx.graphics.getHeight());
		
		
		
		
		
		batch.end();
		quest.render(batch);
		
		//Health bar for spaceStation / what ever else I decide to add.
		if(showHealthBars){
			
		sMap.map.game.sr.begin(ShapeType.Filled);
		sMap.map.game.sr.setProjectionMatrix(spaceCam.combined);
		
		sMap.map.game.sr.setColor(Color.RED);
		sMap.map.game.sr.rect(entity.x, entity.y+entity.bounds.height, entity.bounds.width, 10);
		sMap.map.game.sr.setColor(Color.GREEN);
		// need to cast to float to make it accept fractions...
		sMap.map.game.sr.rect(entity.x, entity.y+entity.bounds.height, (float)(entity.bounds.width * entity.health/entity.maxHealth), 10);
		
		sMap.map.game.sr.end();
		}
		
		
		
		
		

		Vector3 mousePos = new Vector3();
		mousePos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
		spaceCam.unproject(mousePos);
		
		
		sMap.map.ship.rotateShip(mousePos);	
	}
	
	
	private void updateCam(){
		spaceCam.position.x = sMap.map.ship.x;
		spaceCam.position.y = sMap.map.ship.y;
		
		
		// these 4 statments prevent the camera from leaving the area around the spaceplace
		if(spaceCam.position.x > width)
			spaceCam.position.x = width;
		
		if(spaceCam.position.x < -width)
			spaceCam.position.x = - width;
		
		if(spaceCam.position.y > height)
			spaceCam.position.y = height;
		
		if(spaceCam.position.y < -height)
			spaceCam.position.y = - height;
			
	}
	/**
	 * use to set the quest
	 */
	public void setQuest(Quest q){
		this.quest = q;
		
	}
	public void fire(){
		if(sMap.map.ship.canFire())
		projectiles.add(sMap.map.ship.fire());
	}


}
