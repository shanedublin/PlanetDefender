package rusd;






import rusd.entities.Projectile;
import rusd.entities.Comet;
import rusd.methods.MyInputProcessor;
import rusd.methods.TextureNames;
import rusd.worlds.SpacePlace;
import world.SolarSystem;
import world.World;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class GameScreen implements Screen  {
	// Objects
	public final myGame game;
	ShapeRenderer shapeRenderer;
	public World gameWorld;
	public InputProcessor inputProcessor;
	public MyInputProcessor myInputProcessor;
	public SpacePlace SpaceStation;

	// Constants
	final int SHIPWIDTH = 32;
	final int SHIPHEIGHT = 32;
	final int ORBWIDTH = 8;
	final int ORBHEIGHT = 8;
	

	// Variables
	public int width, height;
	public float zoomAmount = 2;
	public float translateAmount = 2;
	public float x;
	public boolean init = false;
	public boolean initSpaceStation = false;
	public boolean trackShip = true;
	public boolean BOXES = true;
	
	public enum renderLocation{
		SUN, SPACESTATION,EARTH, MARS, VENUS, MAP
	}
	public renderLocation location;
	// Images
	// TODO stitch all the Images together
	Texture dropImage;
	Texture bucketImage;
	Texture spaceShip;
	Texture earthImage;
	Texture commetImage;
	Texture orbImage;
	Texture sunImage;
	Texture marsImage;
	Texture mercuryImage;
	Texture venusImage;
	Texture spaceStationImage;
	TextureRegion textures;

	// Sounds and music
	Sound dropSound;
	Music rainMusic;

	// Camera
	public OrthographicCamera camera;
	public OrthographicCamera hudCamera;
	// Rectangle bucket;

	// Timers
	long lastDropTime;
	long lastFireTime;
	long lastZoomTime;
	// Arrays
	public Array<SpacePlace> spacePlaces;
	

	
	
	public GameScreen(final myGame gam) {
		
		this.game = gam;
		this.width = Gdx.graphics.getWidth();
		this.height = Gdx.graphics.getHeight();
		
		gameWorld = new World();
		
		// load the images TODO set up texture atlas		
		spaceShip = new Texture(Gdx.files.internal("SpaceShip.png"));
		earthImage = new Texture(Gdx.files.internal("Earth.png"));
		textures = new TextureRegion(spaceShip);
		commetImage = new Texture(Gdx.files.internal("commet.png"));
		orbImage = new Texture(Gdx.files.internal("orb.png"));
		sunImage = new Texture(Gdx.files.internal("Sun.png"));
		marsImage = new Texture(Gdx.files.internal("Mars.png"));
		mercuryImage = new Texture(Gdx.files.internal("mercury.png"));
		venusImage = new Texture(Gdx.files.internal("Venus.png"));
		spaceStationImage = new Texture(Gdx.files.internal("SpaceStation.png"));
		//TODO error here fix if you go back to using this
		SpaceStation = new SpacePlace(2048, 2048,null,null);

		lastZoomTime = TimeUtils.nanoTime();

		// create the camera and the SpriteBatch
		camera = new OrthographicCamera();
		//hudCamera = new OrthographicCamera();
		camera.setToOrtho(false, width, height);
		//camera.lookAt(0, 0, 0);
		shapeRenderer = new ShapeRenderer();
		
		//myInputProcessor = new MyInputProcessor(this);

		location = renderLocation.MAP;
		
		
	}

	
	
	/**
	 * takes care of all the rendering and logic. 
	 * TODO move the logic to another
	 * class to clear up some of the clutter.
	 */
	@Override
	public void render(float delta) {
	
		Gdx.input.setInputProcessor(myInputProcessor);
		switch(location){
		case MAP:
		renderMap();
		break;
		
		case EARTH:
			break;
		case SUN:
			break;
		case VENUS:
			break;
		case MARS:
			break;
		case SPACESTATION:
			renderSpaceStation();
			break;
		
		
		}
		
		renderHUD();
		

	}
	
	public void initS(){
		camera.position.x = width/2;
		camera.position.y = height/2;
	}
	private void renderSpaceStation(){
		
		if(!initSpaceStation){
			initS();
			initSpaceStation = true;
		}
			
		SpaceStation.render(game.batch);
		myInputProcessor.checkInputs();
		
		
	}
	
	private void renderMap(){
	
		
		// sets the viewpoint to earth
		if(!init){
			init();
			
		}
		if(trackShip){
			camera.position.x = gameWorld.ship.center.x;
			camera.position.y = gameWorld.ship.center.y;
		}
		
		cameraInput();
		
		// clear the screen with a dark blue color. The
		// arguments to glClearColor are the red, green
		// blue and alpha component in the range [0,1]
		// of the color to be used to clear the screen.
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		

		
		
		camera.update();
		
		renderWorld();
				
		if(BOXES){			
			renderHitBoxes();
		}		
		
		
		// TODO figure out how to  get mouse pouse in the world Class.
			if (TimeUtils.nanoTime() - gameWorld.lastFireTime > 100000000)
		if (Gdx.input.isTouched()) {
			gameWorld.fire();
		}
		

			Vector3 mousePos = new Vector3();
		mousePos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
		camera.unproject(mousePos);
		
		gameWorld.ship.rotateShip(mousePos);		
		//IDK if this will work
		//gameWorld.keyPressHandler(Gdx.input);
		
		
		
		myInputProcessor.checkInputs();
		gameWorld.updateWorld();
			
		

	
	}

	// try to get this to do it during start up idk why it wont do it during start up.
	private void init(){
		camera.position.x = gameWorld.solarSystem.planets.get(3).center.x;
		camera.position.y = gameWorld.solarSystem.planets.get(3).center.y;
		init = true;

	}
	
	
	private void renderHUD(){
		// cool stuff use a separate batch that doesn't get updated based on the camera, 
		// you can also use a 2nd camera if you need to mess with the location of the hud.
		game.hudBatch.begin();
		game.font.draw(game.hudBatch, "This is the hud: Fuck yeah "  , 0, height);
		game.font.draw(game.hudBatch, "Ship location:  x- " + gameWorld.ship.center.x + " y- " + gameWorld.ship.center.y, 0, height-20);
		game.font.draw(game.hudBatch, "Sun Center:  x- " + gameWorld.solarSystem.planets.get(0).center.x 
				+ " y- " + gameWorld.solarSystem.planets.get(0).center.y, 0, height-40);
		game.font.draw(game.hudBatch, "Earth Center:  x- " + gameWorld.solarSystem.planets.get(3).center.x 
				+ " y- " + gameWorld.solarSystem.planets.get(3).center.y, 0, height-60);
		
		game.font.draw(game.hudBatch, "Zoom Amount: " + zoomAmount 
				+ " Translate: " + translateAmount, 0, height-100);
		
	
		Vector3 mousePosistion;
		mousePosistion = new Vector3(Gdx.input.getX() ,Gdx.input.getY(),0);
		camera.unproject(mousePosistion);
		game.font.draw(game.hudBatch,
				"Mouse Pos x: " + mousePosistion.x + " y: " + mousePosistion.y , 0, height - 80);
		game.hudBatch.end();
			

	
	}
	
	private void renderWorld(){
		
	game.batch.setProjectionMatrix(camera.combined);
		
		
		// begin a new batch and draw the bucket and
		game.batch.begin();		
		game.batch.draw(sunImage, gameWorld.solarSystem.planets.get(0).x,gameWorld.solarSystem.planets.get(0).y
				,gameWorld.solarSystem.planets.get(0).radius *2,gameWorld.solarSystem.planets.get(0).radius *2);
		
		game.batch.draw(mercuryImage, gameWorld.solarSystem.planets.get(1).x,gameWorld.solarSystem.planets.get(1).y
				,gameWorld.solarSystem.planets.get(1).radius *2,gameWorld.solarSystem.planets.get(1).radius *2);
		
		game.batch.draw(venusImage, gameWorld.solarSystem.planets.get(2).x,gameWorld.solarSystem.planets.get(2).y
				,gameWorld.solarSystem.planets.get(2).radius *2,gameWorld.solarSystem.planets.get(2).radius *2);
		
		game.batch.draw(earthImage, gameWorld.solarSystem.planets.get(3).x,gameWorld.solarSystem.planets.get(3).y
				,gameWorld.solarSystem.planets.get(3).radius *2,gameWorld.solarSystem.planets.get(3).radius*2);
		
		game.batch.draw(marsImage, gameWorld.solarSystem.planets.get(4).x,gameWorld.solarSystem.planets.get(4).y
				,gameWorld.solarSystem.planets.get(4).radius *2,gameWorld.solarSystem.planets.get(4).radius *2);
		
		game.batch.draw(spaceStationImage, gameWorld.spaceStation.x,gameWorld.spaceStation.y
				,gameWorld.spaceStation.bounds.width,gameWorld.spaceStation.bounds.height);
		
		
		//game.batch.draw(earthImage, gameWorld.earth.x, gameWorld.earth.y);
		game.batch.draw(textures, gameWorld.ship.getX(), gameWorld.ship.getY(), 16, 16, 32, 32, 1,
				1, gameWorld.ship.getDirection() - 90);
		// draws all the commets
		for (Comet comet: gameWorld.comets) {
			game.batch.draw(commetImage, comet.x, comet.y,comet.bounds.width,comet.bounds.height);
			//Gdx.app.log("tag", "cometdrew");
		}
		// draws all the bullets
		for (Projectile bullet : gameWorld.bullets) {
			//Gdx.app.log(this.toString(), "render bullet");
			game.batch.draw(orbImage, bullet.x, bullet.y, bullet.bounds.width, bullet.bounds.height);
		}
		game.batch.end();
	}
	// move this to the input processor.
	private void cameraInput(){
		
		if(Gdx.input.isKeyPressed(Keys.NUM_3)){
			camera.position.x = gameWorld.solarSystem.planets.get(3).center.x;
			camera.position.y = gameWorld.solarSystem.planets.get(3).center.y;
		}
		
		if(Gdx.input.isKeyPressed(Keys.NUM_5)){
			camera.position.x = gameWorld.spaceStation.center.x;
			camera.position.y = gameWorld.spaceStation.center.y;
		}
		
		if (TimeUtils.nanoTime() - lastZoomTime > 1000000000)
		if(Gdx.input.isKeyPressed(Keys.Z)){
			
			Vector3 mousePosistion;
			mousePosistion = new Vector3(Gdx.input.getX() ,Gdx.input.getY(),0);
			camera.unproject(mousePosistion);
			camera.position.x  = mousePosistion.x + camera.viewportWidth/2;
			camera.position.y  = mousePosistion.y + camera.viewportHeight/2;
			//camera.zoom = 50;
			lastZoomTime = TimeUtils.nanoTime();
		}
		
		if(Gdx.input.isKeyPressed(Keys.UP)){

			camera.translate(0, translateAmount, 0);
		}
		if(Gdx.input.isKeyPressed(Keys.DOWN)){
			
			camera.translate(0, -translateAmount, 0);
			
		}
		if(Gdx.input.isKeyPressed(Keys.LEFT)){
			camera.translate(-translateAmount, 0, 0);
		}
		if(Gdx.input.isKeyPressed(Keys.RIGHT)){
			camera.translate(translateAmount, 0, 0);
			
		}
		if(Gdx.input.isKeyPressed(Keys.APOSTROPHE)){

			camera.rotate(1);
			
		}
		if(Gdx.input.isKeyPressed(Keys.PERIOD)){
			camera.rotate(-1);
			
		}
	}
	
	private void renderHitBoxes(){
		
		// shape renderer draws the line for the bound boxes on the ship and
				// bullets
		shapeRenderer.setProjectionMatrix(camera.combined);
		shapeRenderer.begin(ShapeType.Line);
		shapeRenderer.setColor(Color.GREEN);

		shapeRenderer.rect(gameWorld.ship.bounds.x, gameWorld.ship.bounds.y, gameWorld.ship.bounds.width,
				gameWorld.ship.bounds.height);
		shapeRenderer.rect(gameWorld.solarSystem.planets.get(3).bounds.x, gameWorld.solarSystem.planets.get(3).bounds.y
				, gameWorld.solarSystem.planets.get(3).bounds.width, gameWorld.solarSystem.planets.get(3).bounds.height);
		shapeRenderer.rect(gameWorld.spaceStation.bounds.x, gameWorld.spaceStation.bounds.y,
				gameWorld.spaceStation.bounds.width, gameWorld.spaceStation.bounds.width);
		
		for (Projectile bulletBox : gameWorld.bullets) {
			shapeRenderer.rect(bulletBox.bounds.x, bulletBox.bounds.y,
					bulletBox.bounds.width, bulletBox.bounds.height);
			//Gdx.app.log(this.toString(), "render bullet");
		}
		
		for (Comet comet : gameWorld.comets) {
			shapeRenderer.rect(comet.bounds.x, comet.bounds.y,
					comet.bounds.width, comet.bounds.height);
		}
		shapeRenderer.end();
	}
	
	@Override
	public void resize(int width, int height) {
		// sets the camera to the resized
		this.width = Gdx.graphics.getWidth();
		this.height = Gdx.graphics.getHeight();
		
		// need to reset the camera
		camera.setToOrtho(false, width, height);

	}

	@Override
	public void show() {
		// start the playback of the background music
		// when the screen is shown
		// rainMusic.play();
		
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
		dropImage.dispose();
		bucketImage.dispose();
		dropSound.dispose();
		rainMusic.dispose();
		game.dispose();
		shapeRenderer.dispose();
		orbImage.dispose();
		spaceShip.dispose();
		commetImage.dispose();
		earthImage.dispose();
		gameWorld.dispose();
		this.dispose();
	}
	
	public String toMyString(){
		return "GameScreen";
	}
}
