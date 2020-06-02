package rusd;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class VictoryScreen implements Screen{

	final myGame game;
	boolean victory;
	String message;	
	OrthographicCamera camera;
	public VictoryScreen(final myGame gam,boolean victory){
		game = gam;
		this.victory = victory;
		this.message = " Pou won!";
		camera = new OrthographicCamera();
		camera.setToOrtho(false,800,480);
	}
	public VictoryScreen(final myGame gam,boolean victory,String string){
		game = gam;
		this.victory = victory;
		this.message = string;
		camera = new OrthographicCamera();
		camera.setToOrtho(false,800,480);
	}
	
		
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0,0,0.2f,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		game.batch.setProjectionMatrix(camera.combined);
		game.batch.begin();
		if(victory == true)
			game.font.draw(game.batch, message, 100, 150);
		if(victory == false)
			game.font.draw(game.batch, message +" You Lost, Humanity has no hope.", 100, 150);
		game.font.draw(game.batch, "Press \"R\" to Play again", 100, 125);
		game.font.draw(game.batch, "Press \"Esc\" to close", 100, 100);
		game.batch.end();
		
		
		if(Gdx.input.isTouched()){
			
			//dispose();
			//Gdx.app.exit();
		}

		 if(Gdx.input.isKeyPressed(Keys.ESCAPE)){
			 Gdx.app.exit();
			 dispose();
		 }
		
		 if(Gdx.input.isKeyPressed(Keys.R)){
			
			game.setScreen(new GameScreen(game));
			dispose();
		 }
		 
	
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
