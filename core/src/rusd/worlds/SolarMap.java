package rusd.worlds;

import java.util.Iterator;

import rusd.entities.Planet;
import rusd.entities.Satellite;
import rusd.methods.TextureNames;
import rusd.methods.TextureNames.Textures;
import rusd.quest.Quest;
import rusd.windows.ShipUpgradeWindow;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class SolarMap {
	
	public Array<SpacePlace> spacePlaces;
	public Array<Planet> planets;
	public Array<Satellite> satellites;
	public float scale = 1000;
	public float planetScale = 10;
	public UniverseMap map;
	public OrthographicCamera cam;
	public ShipUpgradeWindow shipUpgradeWindow;
	
	
	private float planetRadiusSun = 696000/planetScale;
	private float planetRadiusMercury = 2440;
	private float planetRadiusVenus = 6052;
	private float planetRadiusEarth = 6371;
	private float planetRadiusMoon = 1737;
	private float planetRadiusMars = 3390;
	private float planetRadiusJupiter = 69911 ;
	private float planetRadiusSaturn = 58232;
	private float planetRadiusUranus = 25362 ;
	private float planetRadiusNeptune = 24622;
	private float planetRadiusPluto  = 1184;
	// orbit distance
	// http://www.qrg.northwestern.edu/projects/vss/docs/space-environment/3-orbital-lengths-distances.html
	// average length, most planets have an elliptical pattern in km
	// http://www.telescope.org/nuffield/pas/solar/solar7.html
	private float orbitRadiusSun = 0;
	private float orbitRadiusMercury = 58000000/scale;
	private float orbitRadiusVenus = 108000000/scale;
	private float orbitRadiusEarth = 150000000/scale;
	private float orbitRadiusMoon = 50;
	private float orbitRadiusMars = 228000000/scale;
	private float orbitRadiusJupiter = 778000000;
	private float orbitRadiusSaturn = 1429000000;
	private float orbitRadiusUranus = 2871000000L;
	private float orbitRadiusNeptune = 4504000000L;
	private float orbitRadiusPluto  = 5913000000L;
	
	// time in months to orbit the sun (earth days)	
	private float orbitTimeSun = 0;
	private float orbitTimeMercury = 88;
	private float orbitTimeVenus = 224.7f;
	private float orbitTimeEarth = 365.2f;
	private float orbitTimeMoon = 0;
	private float orbitTimeMars = 687;
	private float orbitTimeJupiter = 4332 ;
	private float orbitTimeSaturn = 10760;
	private float orbitTimeUranus = 30700 ;
	private float orbitTimeNeptune = 60200;
	private float orbitTimePluto  = 90600;
	
	
	public SolarMap(UniverseMap map){
		this.map = map;
		planets = new Array<Planet>();
		
		
		Planet sun = new Planet(planetRadiusSun, orbitRadiusSun, orbitTimeSun, Textures.SUN);
		Planet mercury = new Planet(planetRadiusMercury, orbitRadiusMercury, orbitTimeMercury, Textures.MERCURY);
		Planet venus = new Planet(planetRadiusVenus, orbitRadiusVenus, orbitTimeVenus, Textures.VENUS);
		Planet earth = new Planet(planetRadiusEarth, orbitRadiusEarth, orbitTimeEarth, Textures.EARTH);
		Planet mars = new Planet(planetRadiusMars, orbitRadiusMars, orbitTimeMars, Textures.MARS );
		
		Satellite SpaceStation = new Satellite(earth.x - 1000, earth.center.y, 256, 256, 90, 10, 20, Textures.SPACESTATION, earth);

		satellites = new Array<Satellite>();
		
		satellites.add(SpaceStation);
		
		planets.add(sun);
		planets.add(mercury);
		planets.add(venus);
		planets.add(earth);
		planets.add(mars);
		
		spacePlaces = new Array<SpacePlace>();
		
		SpacePlace SpaceStationSP = new SpacePlace(SpaceStation.bounds.width,SpaceStation.bounds.height,this,SpaceStation);
		SpaceStationSP.setQuest(new Quest());
		
		for(int i =0; i < planets.size; i ++){
			
			SpacePlace temp = new SpacePlace(planets.get(i).radius * 2 +1024 , planets.get(i).radius * 2+1024, this,planets.get(i));
			temp.setQuest(new Quest(0));
			spacePlaces.add(temp);
		}
		spacePlaces.add(SpaceStationSP);
		
		
		cam = new OrthographicCamera();
		cam.setToOrtho(false,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		shipUpgradeWindow = new ShipUpgradeWindow(new ScreenViewport(cam),map.game);
		
		
	//	shipUpgradeWindow.window.setVisible(false);
		
		
	}
	public void update(){
		Vector3 mousePos = new Vector3();
		mousePos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
		cam.unproject(mousePos);
		
		
		map.ship.rotateShip(mousePos);	
		map.ship.move();
		cam.position.x= map.ship.x;
		cam.position.y = map.ship.y;
		
	}
	
	public void fixShip(){
		
		map.ship.setThrust(7000);
		map.ship.setX(cam.position.x);
		map.ship.setY(cam.position.y);
		
	}
	
	public void render(SpriteBatch batch){
		// if the upgrade is visible take the input.
		if(shipUpgradeWindow.window.isVisible()){
			Gdx.input.setInputProcessor(shipUpgradeWindow.stage);
		}
		else{
			Gdx.input.setInputProcessor(map.myInputProc);
		}
			
		update();
		cam.update();
		map.game.bgBatch.begin();
		map.game.bgBatch.draw(Textures.BACKGROUND, 0,0 , Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		map.game.bgBatch.end();
		Iterator<Planet> itor = planets.iterator();
		
		
		batch.setProjectionMatrix(this.cam.combined);
		batch.begin();
		
		int i = 0;
		while(itor.hasNext()){
			
			Planet p = itor.next();
			
			batch.draw(p.textureRegion, p.x, p.y, p.bounds.width/2, p.bounds.height/2, p.bounds.width, p.bounds.height, 1,1, p.direction);
			TextureNames.font.draw(batch, p.x +"",0 , Gdx.graphics.getHeight()-i*20);
			i++;
		}
		for(int j =0;j < satellites.size; j ++){
			Satellite s = satellites.get(j);
			batch.draw(s.textureRegion, s.x, s.y, s.bounds.width/2, s.bounds.height/2, s.bounds.width, s.bounds.height, 1, 1, s.direction);
			
		}
		
		
		
		batch.draw(map.ship.textureRegion, map.ship.x, map.ship.y, map.ship.bounds.width/2, 
				map.ship.bounds.height/2, map.ship.bounds.width, map.ship.bounds.height, 1, 1, 
				map.ship.getDirection()-90);
		batch.end();
		
		if(map.drawBounds){
		
		map.game.sr.setProjectionMatrix(cam.combined);
		map.game.sr.setColor(Color.GREEN);
		map.game.sr.begin(ShapeType.Line);
		
		Iterator<SpacePlace> itor3 = spacePlaces.iterator();
				while(itor3.hasNext()){
					SpacePlace s = itor3.next();
					map.game.sr.rect(s.solarBounds.x, s.solarBounds.y, s.solarBounds.width, s.solarBounds.height);
					
				}
		
//				Iterator<Planet> itor2 = planets.iterator();
//		while(itor2.hasNext()){
//			Planet p = itor2.next();
//			map.game.sr.rect(p.x, p.y, p.bounds.width, p.bounds.height);
//		}
		
		map.game.sr.end();
		 
		}
		
		//shipUpgradeWindow.stage.act();
		
		//System.out.println(shipUpgradeWindow.stage.getViewport().getViewportWidth());
		//shipUpgradeWindow.stage.getViewport().project(cam.position);
		
		// puts the window in the middle of the screen
		// it does not scale it to the right size though..
		
		shipUpgradeWindow.window.setPosition(cam.position.x - shipUpgradeWindow.window.getWidth()/2,
				cam.position.y - shipUpgradeWindow.window.getHeight()/2);
		//shipUpgradeWindow.stage.getViewport().setWorldSize(1280000, 720);
		
		
		shipUpgradeWindow.stage.draw();
	
		//System.out.println("blerg "+shipUpgradeWindow.stage.getWidth());
		
		
		
	
		
		
	}
	
	
	
	
	
	
}
