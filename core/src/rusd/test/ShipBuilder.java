package rusd.test;

import rusd.myGame;
import rusd.entities.modular_ship.ShipOutline;
import rusd.quest.QuestDialogue;
import rusd.windows.ShipBuilderWindow;
import rusd.windows.ShipUpgradeWindow;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class ShipBuilder implements Screen {

	final myGame game;
	
	OrthographicCamera cam;
	
	
	public ShapeRenderer sr;
	public ShipOutline ship;
	public int size = 16;
	public ShipBuilderWindow sw;
	public int shipSize;
	public  ShipBuilder(final myGame game,int shipSize){
		this.game = game;
		this.shipSize = shipSize;
		
		this.sr = new ShapeRenderer(); 
		
		cam = new OrthographicCamera();
		cam.setToOrtho(false,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		
		
		sw = new ShipBuilderWindow(new ScreenViewport(cam), game,shipSize);
		ship = new ShipOutline(5,16);
		
		
		
	}
	
	
	@Override
	public void render(float delta) {
	
		cam.update();
		
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//		
//		sr.setProjectionMatrix(cam.combined);
//		sr.setColor(Color.LIGHT_GRAY);
//		sr.begin(ShapeType.Filled);
//		sr.rect(0, 0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
//		
		
//		sr.end();
		
		sw.stage.draw();
		Table.drawDebug(sw.stage);
		
		game.batch.setProjectionMatrix(cam.combined);
		game.batch.begin();
		
		// Renders the modular Ship
		for(int i = 0; i < sw.ship.blocks.length;i++)
		for(int j = 0; j <sw.ship.blocks[i].length; j++){

			// this convoluted mess offsets the ship so it renders all the squares in the right spots..
			game.batch.draw(sw.ship.blocks[i][j].tr1,
					50 + j *(size), 
					size*sw.ship.blocks.length-i*(size) , 
					(float)size*sw.ship.blocks.length/2-size*j,
					size/2 +size*i -size*2, 
					size, size, 1f, 1f, 0);
		
		}

		
		game.batch.end();
		
		
		
		if(Gdx.input.isKeyPressed(Input.Keys.F12)){
			game.setScreen(new ShipBuilder(game,shipSize));
			System.out.println("New ShipBuilder");
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
		sw.dispose();
		
		sr.dispose();
		
		
		
		
		
	}

}
