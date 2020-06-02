package rusd;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class myGame extends Game {
	public SpriteBatch batch;
	public BitmapFont font;
	public SpriteBatch hudBatch;
	public SpriteBatch bgBatch;
	public ShapeRenderer sr;
	
	public int Sound = 10;
	public enum graphics{
		HIGH, LOW
	}
	public graphics glevel = graphics.HIGH;

	public void create(){
		batch = new SpriteBatch();
		font = new BitmapFont();
		hudBatch  = new SpriteBatch();
		sr = new ShapeRenderer();
		bgBatch = new SpriteBatch();
		
		this.setScreen(new MainMenuScreen(this));
		
	}
	
	public void render(){
		super.render();
		
	}
	
	public void dispose(){
		batch.dispose();
		font.dispose();
		hudBatch.dispose();
		sr.dispose();
		
	}
	
	

}
