package rusd.quest;

import javafx.scene.Scene;
import rusd.myGame;
import rusd.entities.modular_ship.ShipOutline;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Window.WindowStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/**
 * A window that pops up displaying some information
 * also alows you to select some information that 
 * might determine the outcome of the quest
 * 
 * @author Shane
 *
 */
public class QuestDialogue {
	public String text1;
	public String text2;
	public String text3;
	public String text4;
	
	
	public myGame game;
	
	public Skin skin;
	public Stage stage;
	
	public Dialog dialog;
	
	
	
	public Window window;
	public WindowStyle ws;
	
	
	public Table table;
	public Table bTable;
	public Table descriptionTable;
	public Table titleTable;
	
	
	
	public Label description;

	public LabelStyle ls;
	
	public TextButton a;
	public TextButton b;
	public TextButton c;
	public TextButton d;
	public TextButton e;

	public TextButton up;
	public TextButton down;
	public TextButton exit;
	
	public TextButtonStyle ts;
	
	
	public 	Pixmap pixmap;
	public boolean choice;
	
	
	
	
	public QuestDialogue(myGame game, ScreenViewport viewport) {
		this.game = game;
		this.text1 = "The Station is in peril.  Go the the aid and save the station.";
		this.choice = true;
	
		
		ShipOutline so = new ShipOutline();
		stage = new Stage(viewport);
		
		Gdx.input.setInputProcessor(stage);
		//start a new skin
		skin = new Skin();
		
		// set up a pix map
		pixmap = new Pixmap(1,1,Format.RGBA8888);
		pixmap.setColor(Color.WHITE);
		pixmap.fill();
		skin.add("white", new Texture(pixmap));
		skin.add("default", new BitmapFont());
		
		
		
		// Define the new Styles
		ws = new WindowStyle(skin.getFont("default"), Color.BLACK, skin.getDrawable("white"));		
		ls = new LabelStyle(skin.getFont("default"), Color.BLACK);
		//ls.background = skin.newDrawable("white", Color.RED);
		ts = new  TextButtonStyle(skin.newDrawable("white", new Color(131/255f, 150/255f, 156/255f, 1)), skin.newDrawable("white", Color.LIGHT_GRAY),
				skin.newDrawable("white",Color.LIGHT_GRAY) , skin.getFont("default"));
		
		skin.add("default", ws);
		skin.add("default", ls);
		skin.add("default", ts);
		
		
		a = new TextButton("No", skin);
		b = new TextButton("Yes/Ok",skin);
		description = new Label(text1, skin);
		description.setWrap(true);
		
		window = new Window("Quest", skin);
		window.setSize(Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/4);
		window.setPosition(Gdx.graphics.getWidth()/2 - window.getWidth()/2,Gdx.graphics.getHeight()/2 - window.getHeight()/2);
		window.background(skin.newDrawable("white", Color.DARK_GRAY));
		
		
		window.padTop(20);
		window.row().colspan(2);
		window.add(description).fill().expand();
		window.row();
		window.add(a).fill().expand();
		window.add(b).fill().expand();
		
		
		stage.addActor(window);
		
		window.debug();
		
		a.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {

				if(a.isChecked()){
					a.setChecked(false);
					choice = false;
					window.setVisible(false);
					
				}
				
			}
		});
		
		b.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				
				if(b.isChecked()){
					b.setChecked(false);
					choice = true;
					window.setVisible(false);
				}
				
			}
		});
		
		
		
		
		//stage.addActor(window);
		
	//	window.setVisible(false);
		
		
		
		
	}

}
