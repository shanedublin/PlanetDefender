package rusd.windows;

import javax.swing.text.TabExpander;

import rusd.myGame;
import rusd.entities.modular_ship.Blank;
import rusd.entities.modular_ship.Block;
import rusd.entities.modular_ship.Brain;
import rusd.entities.modular_ship.Generator;
import rusd.entities.modular_ship.Sheild;
import rusd.entities.modular_ship.ShipOutline;
import rusd.entities.modular_ship.Thrust;
import rusd.entities.modular_ship.Weapon;
import rusd.methods.TextureNames.Textures;
import rusd.test.ShipBuilder;
import rusd.test.ShipTestingScreen;
import sun.net.www.http.ChunkedInputStream;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Window.WindowStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.sun.javafx.image.impl.General;

public class ShipBuilderWindow {
	
	final myGame game;
	
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
	
	public ImageButton blerg;
	public ImageButtonStyle is;
	
	public TextButtonStyle ts;
	
	
	public 	Pixmap pixmap;
	
	public int shipSize1;
	
	public Block block;
	public enum BlockType{
		BLANK, BLOCK,BRAIN,SOLAR,SHEILD,THRUST,WEAPON
	}
	public BlockType bt = BlockType.BLANK;
	
	public ShipOutline ship;
	
	public ShipBuilderWindow(ScreenViewport viewport, final myGame game, int shipsize) {
		this.game = game;
		this.shipSize1 = shipsize;
		this.block = new Blank(new Vector2(0,0));
		
		ship = new ShipOutline(shipsize,16);
		
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
		
		is = new ImageButtonStyle();
		
		
		skin.add("default", ws);
		skin.add("default", ls);
		skin.add("default", ts);
		skin.add("default", is);
		
		window = new Window("", ws);
		table = new Table();
		
		
		bTable = new Table();
		descriptionTable = new Table();
		titleTable = new Table();
		Image testI = new Image(Textures.BLOCK);
		Image BlankImage = new Image(Textures.BLANK);
		Image BlockImage = new Image(Textures.BLOCK);
		Image BrainImage = new Image(Textures.BRAIN);
		Image SolarImage = new Image(Textures.SOLAR);
		Image GunImage = new Image(Textures.GUN);
		Image SheildImage = new Image(Textures.SHEILD);
		Image ThrustImage = new Image(Textures.THRUST);
		a = new TextButton("Blank",skin);
		
		up = new TextButton("up", skin);
		down = new TextButton("down", skin);
		TextButton resize = new TextButton("resize", skin);
		TextButton save = new TextButton("save",skin);
		final TextButton check = new TextButton("check",skin);
		
		
		level = new Label(""+shipSize1, skin);
		
		
		window.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		window.setBackground(skin.newDrawable("white", Color.DARK_GRAY));
		
		
		table.setTransform(true);
	
		
		for(int i= 0;i < shipSize1;i++){
		// super fucking ghetto fix later
		final int i1 = i;
			bTable.row();
			for(int j= 0;j < shipSize1;j++){
				// super fucking ghetto fix later
				final int j1 = j;
				final Image img = new Image(block.tr);
				img.addListener(new ClickListener(){
					
					public boolean touchDown(InputEvent event, float x,
							float y, int pointer, int button) {
						//System.out.println("touched");
						img.setDrawable(new TextureRegionDrawable(new TextureRegion(block.tr)));
						img.setName("nigx " + i1 + " nigy " + j1);
						System.out.println("x: "+ i1 + " y: " + j1);
						//block.center.x = i1 * 32 - 16;
						//block.center.y = j1 * 32 - 16;
						
						switch(bt){
						case BLANK:
							ship.blocks[i1][j1] = new Blank(new Vector2(0, 0));
							break;
						case BLOCK:
							ship.blocks[i1][j1] = new Block(new Vector2(0, 0));
							break;
						case BRAIN:
							ship.blocks[i1][j1] = new Brain(new Vector2(0, 0));
							break;
						case SHEILD:
							ship.blocks[i1][j1] = new Sheild(new Vector2(0, 0));
							break;
						case SOLAR:
							ship.blocks[i1][j1] = new Generator(new Vector2(0, 0));
							break;
						case THRUST:
							ship.blocks[i1][j1] = new Thrust(new Vector2(0, 0));
							break;
						case WEAPON:
							ship.blocks[i1][j1] = new Weapon(new Vector2(0, 0));
							break;
						default:
							break;
						
						}
						
					
						
						return super.touchDown(event, x, y, pointer, button);
					}
				});
				bTable.add(img).size(32);
				
			}	
			
		}
		
		bTable.addListener(new ClickListener(){
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
		
			
				return super.touchDown(event, x, y, pointer, button);
			}
		});
		
		table.row().pad(10);
		table.add(a);		
		table.row().pad(10);
		table.add(BlockImage);
		table.row().pad(10);
		table.add(BrainImage);
		table.row().pad(10);
		table.add(SolarImage);
		table.row().pad(10);
		table.add(GunImage);
		table.row().pad(10);
		table.add(SheildImage).size(32);
		table.row().pad(10);
		table.add(ThrustImage);
		table.row().pad(10);
		table.add(check);
		table.row().pad(10);
		table.add(save);
		table.row().pad(10);
		table.add(up);
		table.row().pad(10);
		table.add(level);
		table.row().pad(10);
		table.add(down);		
		table.row().pad(10);
		table.add(resize);
		
		check.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {

				if(check.isChecked()){
					check.setChecked(false);
					ship.isValid();
				}
				
			}
		});
		
		save.addListener(new ChangeListener(){

			@Override
			public void changed(ChangeEvent event, Actor actor) {
			
			game.getScreen().dispose();
			ship.isValid();
			game.setScreen(new ShipTestingScreen(game,ship));
			
			
				
			}
			
		});
		

		up.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {

				if(up.isChecked()){
					up.setChecked(false);
					shipSize1++;
					shipSize1++;
					level.setText(shipSize1 +"");
				}
				
			}
		});
		
		down.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				
				if(down.isChecked()){
					down.setChecked(false);
					shipSize1--;
					shipSize1--;
					level.setText(shipSize1 +"");
				}
				
			}
		});
		
		
		BlankImage.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				block = new Blank(new Vector2());
				bt = BlockType.BLANK;
				super.clicked(event, x, y);
			}
		});
		
		a.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				block = new Blank(new Vector2());
				bt = BlockType.BLANK;
				super.clicked(event, x, y);
			}
		});
		
		
		BlockImage.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				block = new Block(new Vector2());
				bt = BlockType.BLOCK;
				super.clicked(event, x, y);
			}
		});
		
		BrainImage.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				block = new Brain(new Vector2());
				bt = BlockType.BRAIN;
				super.clicked(event, x, y);
			}
		});
		
		SolarImage.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				block = new Generator(new Vector2());
				bt = BlockType.SOLAR;
				super.clicked(event, x, y);
			}
		});
		
		GunImage.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				block = new Weapon(new Vector2());
				bt = BlockType.WEAPON;
				super.clicked(event, x, y);
			}
		});
		
		SheildImage.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				block = new Sheild(new Vector2());
				bt = BlockType.SHEILD;
				super.clicked(event, x, y);
			}
		});
		
		ThrustImage.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				block = new Thrust(new Vector2());
				bt = BlockType.THRUST;
				super.clicked(event, x, y);
			}
		});
		
		resize.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				
				game.setScreen(new ShipBuilder(game,shipSize1));
				super.clicked(event, x, y);
			}
		});
		
		
		
		window.setTransform(true);
		window.add(new Label("Customize Your Ship", skin));
		window.row();
		window.add(bTable);
		window.add(table);
		
		
		stage.addActor(window);
		
		window.debug();
	
		//bTable.debug();
		//
		
	}
	
	public void dispose(){
		stage.dispose();
		
	}

}
