package world;

import java.util.Iterator;

import rusd.entities.Planet;
import rusd.methods.TextureNames;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
// TODO handle the location and the moving of all the planets
public class SolarSystem {
	public Array<Planet> planets;
	private float scale = 20;
	// these are the radius of the plannets taken from wiki
	// http://en.wikipedia.org/wiki/List_of_Solar_System_objects_by_size
	// average radius in KM
	private float ratioSun = 696000;
	private float ratioMercury = 2440;
	private float ratioVenus = 6052;
	private float ratioEarth = 6371;
	private float ratioMoon = 1737;
	private float ratioMars = 3390;
	private float ratioJupiter = 69911 ;
	private float ratioSaturn = 58232;
	private float ratioUranus = 25362 ;
	private float ratioNeptune = 24622;
	private float ratioPluto  = 1184;
	// orbit distance
	// http://www.qrg.northwestern.edu/projects/vss/docs/space-environment/3-orbital-lengths-distances.html
	// average length, most planets have an elliptical pattern in km
	// http://www.telescope.org/nuffield/pas/solar/solar7.html
	private float orbitSun = 0;
	private float orbitMercury = 58000000/scale;
	private float orbitVenus = 108000000/scale;
	private float orbitEarth = 150000000/scale;
	private float orbitMoon = 50/scale;
	private float orbitMars = 228000000/scale;
	private float orbitJupiter = 778000000;
	private float orbitSaturn = 1429000000;
	private float orbitUranus = 2871000000L;
	private float orbitNeptune = 4504000000L;
	private float orbitPluto  = 5913000000L;
	
	// time in months to orbit the sun (earth days)	
	private float timeSun = 0;
	private float timeMercury = 88;
	private float timeVenus = 224.7f;
	private float timeEarth = 365.2f;
	private float timeMoon = 0;
	private float timeMars = 687;
	private float timeJupiter = 4332 ;
	private float timeSaturn = 10760;
	private float timeUranus = 30700 ;
	private float timeNeptune = 60200;
	private float timePluto  = 90600;

	
	
	private float time = 0;
	
	// add the rest of the planets
	public SolarSystem(){
		this.planets = new Array<Planet>();
		//manualy set all teh planets into the solar system. 
		Planet sun = new Planet("sun", 0, 0, ratioSun*2, orbitSun, timeSun, TextureNames.Textures.SUN);
		Planet mercury = new Planet("Mercury", orbitMercury, 0, ratioMercury*2, orbitMercury, timeMercury,TextureNames.Textures.MERCURY);
		Planet venus = new Planet("Venus", orbitVenus, 0, ratioVenus*2, orbitVenus, timeVenus,TextureNames.Textures.VENUS);
		Planet earth = new Planet("Earth", orbitEarth, 0, ratioEarth, orbitEarth, timeEarth,TextureNames.Textures.EARTH);
		Planet mars = new Planet("Mars", orbitMars, 0, ratioMars, orbitMars, timeMars,TextureNames.Textures.MARS);
		
		
		planets.add(sun);
		planets.add(mercury);
		planets.add(venus);
		planets.add(earth);
		planets.add(mars);
		
	}
	
	public void movePlanets(){
		time++;
		Iterator<Planet> iterPlanet = planets.iterator();
		
		while (iterPlanet.hasNext()){
			Planet planet = iterPlanet.next();
			
			planet.setX((float) Math.cos((planet.orbitDist * 360 * time / planet.orbitTime * MathUtils.degreesToRadians)));
			planet.setY((float) Math.sin((planet.orbitDist * 360 * time / planet.orbitTime * MathUtils.degreesToRadians)));
			
		}
			
		
	}
	
	

}
