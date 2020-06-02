package rusd.methods;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public interface TextureNames{
	BitmapFont font = new BitmapFont();
	TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("Texture.pack"));
	
	
	static public class Names{
		public static final String EARTH = "Earth";
		public static final String SHIP = "Earth";
		public static final String MARS= "Earth";
		public static final String SUN = "Earth";
		public static final String COMET = "Earth";
		public static final String BULLET = "Earth";
		public static final String VENUS = "Earth";
		public static final String MERCURY = "Earth";
		
	}
	
	static public class Textures{
//		public static final TextureRegion SHIP = new TextureRegion(new Texture(Gdx.files.internal("SpaceShip.png")));
		public static final TextureRegion SHIP = new TextureRegion(atlas.findRegion("SpaceShip"));
		public static final TextureRegion SPACESTATION = new TextureRegion(atlas.findRegion("SpaceStation"));
		public static final TextureRegion EARTH = new TextureRegion(atlas.findRegion("Earth"));
		public static final TextureRegion SUN = new TextureRegion(atlas.findRegion("Sun"));
		public static final TextureRegion VENUS = new TextureRegion(atlas.findRegion("Venus"));
		public static final TextureRegion MERCURY = new TextureRegion(atlas.findRegion("mercury"));
		public static final TextureRegion ORB = new TextureRegion(atlas.findRegion("orb"));
		public static final TextureRegion MARS = new TextureRegion(atlas.findRegion("Mars"));
		public static final TextureRegion COMET = new TextureRegion(atlas.findRegion("commet"));
		public static final Texture BACKGROUND 	= new Texture(Gdx.files.internal("BackGround.png"));
		//TODO Add to texture thing
		public static final Texture TITLE = new Texture(Gdx.files.internal("Title.png"));
		public static final Texture BLOCK = new Texture(Gdx.files.internal("block.png"));
		public static final Texture BRAIN = new Texture(Gdx.files.internal("Brain.png"));
		public static final Texture SOLAR = new Texture(Gdx.files.internal("Generator.png"));
		public static final Texture GUN = new Texture(Gdx.files.internal("Gun.png"));
		public static final Texture THRUST = new Texture(Gdx.files.internal("Thrust.png"));
		public static final Texture SHEILD = new Texture(Gdx.files.internal("Sheild.png"));
		public static final TextureRegion SHEILD1 = new TextureRegion(SHEILD, 32, 32);
		
		public static final Texture BLANK = new Texture(Gdx.files.internal("transperant.png"));
		public static final TextureRegion BLANK1 = new TextureRegion(BLANK);
		public static final Texture LASER = new Texture(Gdx.files.internal("laser.png"));
		public static final TextureRegion LASER1 = new TextureRegion(LASER);
		public static final Texture ENEMY = new Texture(Gdx.files.internal("Enemy1.png"));
		public static final TextureRegion ENEMY1 = new TextureRegion(ENEMY);
		
		public static final Texture WARPGATE = new Texture(Gdx.files.internal("WarpGate.png"));
		public static final TextureRegion WARPGATE1 = new TextureRegion(WARPGATE);
		
		
		
		
	}
	
	
}
