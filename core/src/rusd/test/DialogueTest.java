package rusd.test;

import rusd.myGame;
import rusd.quest.QuestDialogue;
import rusd.windows.ShipUpgradeWindow;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class DialogueTest implements Screen {
	
	final myGame game;
	private Stage stage;
	OrthographicCamera cam;
	Skin skin;
	public ShipUpgradeWindow sup;
	public QuestDialogue dia;
	ShapeRenderer sr;
	
	
	public DialogueTest(final myGame game){
		this.game = game;
		this.sr = new ShapeRenderer(); 
		
		cam = new OrthographicCamera();
		cam.setToOrtho(false,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		
		//sup = new ShipUpgradeWindow(new ScreenViewport(cam),game);
		dia = new QuestDialogue(game, new ScreenViewport(cam));
		
		
		
		
	}
	
	
	@Override
	public void render(float delta) {
	
		cam.update();
		
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		
	
		dia.stage.draw();
		
		
	
//      System.out.println(sup.stage.getHeight());
		
//		sr.setProjectionMatrix(cam.combined);
//		sr.begin(ShapeType.Filled);
//		sr.rect(0, Gdx.graphics.getHeight()/2, 50, 50);
//		sr.end();
//	
		
		
		
		
		Table.drawDebug(dia.stage);
		
		if(Gdx.input.isKeyPressed(Input.Keys.F12)){
			game.setScreen(new DialogueTest(game));
		}
		
		if(Gdx.input.isKeyPressed(Input.Keys.COMMA)){
			//sup.stage.getCamera().translate(0, -1, 0);
			cam.translate(0, 1);
		}
			
		if(Gdx.input.isKeyPressed(Input.Keys.O)){
			sup.stage.getCamera().translate(0, -1, 0);
			
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
