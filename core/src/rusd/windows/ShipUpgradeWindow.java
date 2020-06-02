package rusd.windows;

import rusd.myGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.ui.Window.WindowStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.viewport.ScreenViewport;


public class ShipUpgradeWindow   {
	public myGame game;
	
	public Skin skin;
	public Stage stage;
	
	
	public Window window;
	public WindowStyle ws;
	
	
	public Table table;
	public Table bTable;
	public Table descriptionTable;
	public Table titleTable;
	
	
	
	public Label title;
	public Label subTitle;
	public Label level;
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
	
	
	
	
	public ShipUpgradeWindow(ScreenViewport viewport,myGame game) {
		this.game = game;
		
		final String strEngine = "Upgrades to the engine increase regular and boosted move speed.";
		final String strSheild= "Upgrades to the Sheild increase the amount of damage the sheild can take before Depleting. ";
		final String strCapacitor = "Lasers, Sheilds, and rocket boosters all require energy.  Upgrading the capacitor not only increases the energy cpacity "
				+ " of the capacitor, but also the rate at which the sheilds and lasers can recharge.";
		final String strHull = "When stuff gets by your sheild the hull takes the damage. Upgrade your hull to allow the ship to take more"
				+ " hits and less damage per hit.";
		final String strLaser = "Upgrading the laser increases the fire rate as well as the damage that each beam does to the target.";
		
		
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
		ls = new LabelStyle(skin.getFont("default"), Color.WHITE);
		//ls.background = skin.newDrawable("white", Color.RED);
		ts = new  TextButtonStyle(skin.newDrawable("white", new Color(131/255f, 150/255f, 156/255f, 1)), skin.newDrawable("white", Color.LIGHT_GRAY),
				skin.newDrawable("white",Color.LIGHT_GRAY) , skin.getFont("default"));
		
		skin.add("default", ws);
		skin.add("default", ls);
		skin.add("default", ts);
		
		window = new Window("", ws);
		table = new Table();
		
		
		bTable = new Table();
		descriptionTable = new Table();
		titleTable = new Table();
	
		
		
		
		
		
		
		
		
		title = new Label("Ship Upgrades", skin);
		subTitle = new Label("Upgrades",skin);
		level = new Label("1",skin);
		description= new Label("Make a selection",skin);
		description.setWrap(true);
		
		
		a = new TextButton("Engine", skin);
		b = new TextButton("Sheild", skin);
		c = new TextButton("Capacitor", skin);
		d = new TextButton("Hull", skin);
		e = new TextButton("Laser", skin);

		up = new TextButton("+", skin);
		down = new TextButton("-", skin);
		exit = new TextButton("Exit", skin);
		
		window.setSize(Gdx.graphics.getWidth()*.8f, Gdx.graphics.getHeight()*.8f);
		window.setPosition(Gdx.graphics.getWidth()/2 - window.getWidth()/2,Gdx.graphics.getHeight()/2 - window.getHeight()/2);
		window.setMovable(true);
		window.setVisible(true);
		window.background(skin.newDrawable("white", Color.BLACK));
		// allows the widow to be drawn outside the stage.
		window.setKeepWithinStage(false);
		
		//bTable.setHeight(window.getHeight()*5/8);
		//bTable.setWidth(300);
		bTable.row();
		bTable.add(a).pad(10).fill().expand();
		bTable.row();
		bTable.add(b).pad(10).fill().expand();
		bTable.row();
		bTable.add(c).pad(10).fill().expand();
		bTable.row();
		bTable.add(d).pad(10).fill().expand();
		bTable.row();
		bTable.add(e).pad(10).fill().expand();
		bTable.background(skin.newDrawable("white", Color.DARK_GRAY));
		
		
		descriptionTable.row().pad(10);
		descriptionTable.add(subTitle).colspan(3);
		descriptionTable.row().padRight(10).padLeft(10).padTop(4).padBottom(4);
		descriptionTable.add(description).colspan(3).height(window.getHeight()*4/8-20).width(window.getWidth()*5/8-20);
		descriptionTable.row().pad(10);
		descriptionTable.add(up).fill();
		descriptionTable.add(level);
		descriptionTable.add(down).fill();
		descriptionTable.background(skin.newDrawable("white", Color.DARK_GRAY));
		
		titleTable.add(title);
		titleTable.background(skin.newDrawable("white", Color.DARK_GRAY));
		
		table.row().width(window.getWidth()*7/8).height(window.getHeight()/8).colspan(2);
		table.add(titleTable).center().bottom();
		table.row().height(window.getHeight()*5/8);
		table.add(bTable).width(window.getWidth()*2/8).fill();
		table.add(descriptionTable).width(window.getWidth()*5/8).fill();
		table.row().height(window.getHeight()/8);
		table.add(exit).colspan(2).pad(10).fill().expand();
		table.background(skin.newDrawable("white", Color.DARK_GRAY));
		
		
		
		
		
		a.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				
				if(a.isChecked()){
					subTitle.setText("Engine");
					description.setText(strEngine);
					b.setChecked(false);
					c.setChecked(false);
					d.setChecked(false);
					e.setChecked(false);
					
				}
				
			}
		});
		
		b.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				
				if(b.isChecked()){
					subTitle.setText("Sheilds");
					description.setText(strSheild);
					a.setChecked(false);
					c.setChecked(false);
					d.setChecked(false);
					e.setChecked(false);
					
				}
				
			}
		});
		
		c.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				
				if(c.isChecked()){
					subTitle.setText("Capacitors");
					description.setText(strCapacitor);
					a.setChecked(false);
					b.setChecked(false);
					d.setChecked(false);
					e.setChecked(false);
					
				}
				
			}
		});
		
		d.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				
				if(d.isChecked()){
					subTitle.setText("Hull Strength");
					description.setText(strHull);
					a.setChecked(false);
					b.setChecked(false);
					c.setChecked(false);
					e.setChecked(false);
					
				}
				
			}
		});
		
		e.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				
				if(e.isChecked()){
					subTitle.setText("Laser");
					description.setText(strLaser);
					a.setChecked(false);
					b.setChecked(false);
					c.setChecked(false);
					d.setChecked(false);
					
				}
				
			}
		});
		
		up.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if(up.isChecked()){
					
					up.setChecked(false);
				}
				
			}
		});
		
		down.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if(down.isChecked()){
					
					down.setChecked(false);
				}
				
			}
		});
		
		exit.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if(exit.isChecked()){
					window.setVisible(false);
					exit.setChecked(false);
				}
				
			}
		});
		
		
		
		//bTable.debug();
		//descriptionTable.debug();		
		//table.debug();		
		window.debug();
	//	window.padTop(20);
		//titleTable.debug();		
		window.add(table);
		stage.addActor(window);
		
		window.setVisible(false);
		
		
		
		
	}

}
