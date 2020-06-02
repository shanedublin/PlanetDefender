package rusd;

import rusd.myGame.graphics;
import rusd.methods.TextureNames.Textures;
import rusd.test.AlphaScreen;
import rusd.test.DialogueTest;
import rusd.test.ShipBuilder;
import rusd.test.solarmapTest;
import rusd.worlds.UniverseMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Window.WindowStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.sun.java.swing.plaf.windows.WindowsInternalFrameTitlePane.WindowsTitlePaneLayout;

public class MainMenuScreen implements Screen {
	
	final myGame game;
	private Stage stage;
	// Stage has a camera no point to making anther one...
	OrthographicCamera camera;
	Skin skin;
	
	public MainMenuScreen(final myGame gam){
		game = gam;
		
		//camera = new OrthographicCamera();
		//camera.setToOrtho(false,Gdx.graphics.getWidth()	,Gdx.graphics.getHeight());
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
	
		skin = new Skin();
		
		Pixmap pixmap = new Pixmap(1,1,Format.RGBA8888);
		pixmap.setColor(Color.WHITE);
		pixmap.fill();
		Pixmap pixmap2 = new Pixmap(1,1,Format.RGBA8888);
		pixmap2.setColor(new Color(0, 0, 0, .9f));
		pixmap2.fill();
		skin.add("white", new Texture(pixmap));
		skin.add("gray", new Texture(pixmap2));
		skin.add("default", new BitmapFont());
		

		// Configure a TextButtonStyle and name it "default". Skin resources are stored by type, so this doesn't overwrite the font.
		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.up = skin.newDrawable("white", Color.DARK_GRAY);
		textButtonStyle.down = skin.newDrawable("white", Color.DARK_GRAY);
		textButtonStyle.checked = skin.newDrawable("white", Color.BLUE);
		textButtonStyle.over = skin.newDrawable("white", Color.LIGHT_GRAY);
		textButtonStyle.font = skin.getFont("default");
		
		TextFieldStyle textFieldStyle = new TextFieldStyle();
		//textFieldStyle.background = skin.newDrawable("white",Color.DARK_GRAY);
		textFieldStyle.font = skin.getFont("default");
		textFieldStyle.fontColor = Color.BLUE;
		
		
		
		
		LabelStyle ls = new LabelStyle(skin.getFont("default"), Color.WHITE);

		
		WindowStyle ws = new WindowStyle();
		ws.titleFont = (skin.getFont("default"));
		ws.titleFontColor = textFieldStyle.fontColor = Color.WHITE;
		//TODO
		ws.background = skin.getDrawable("gray");
		
		
		skin.add("default", ws);		
		skin.add("default", ls);
		skin.add("default", textButtonStyle);
		skin.add("default", textFieldStyle);
		// Create a table that fills the screen. Everything else will go inside this table.
		Table table = new Table();
		Table optionsTable = new Table();
		optionsTable.debug();
		
		table.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		table.setPosition(0, 0);
		//table.setFillParent(true);
		

		// Create a button with the "default" TextButtonStyle. A 3rd parameter can be used to specify a name other than "default".
		final TextButton Start = new TextButton("Play", skin);
		Start.setSize(Gdx.graphics.getWidth()/3, Gdx.graphics.getHeight()/10);
		final TextButton Exit = new TextButton("Exit", skin);
		final TextButton Options = new TextButton("Options",skin);
		final TextButton Story = new TextButton("Story",skin);
		//final TextField Title = new TextField("Planet Defender",  skin);
		final TextButton CustomShip = new TextButton("Build a custom Ship",skin);
		//final Label Title = new Label("Totaly Awesome Game", skin);
		final Image Title = new Image(Textures.TITLE);
		
		//Title.setScale(2);
		Container wrapper = new Container(Title);
		wrapper.setTransform(true);
		wrapper.setOrigin(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
		wrapper.setScale(2);
		
		
		//wrapper.center();
		//table.setTransform(true);
		//table.add(wrapper).expandX().fillX().expandY().fillY().align(Align.top).padBottom(20);
		table.add(Title).padBottom(20);
		
	
		
		table.row();		
		table.add(Start).width(Start.getWidth()).height(Start.getHeight()).padBottom(Gdx.graphics.getHeight()/20);
		table.row();
		table.add(Story).width(Start.getWidth()).height(Start.getHeight()).padBottom(Gdx.graphics.getHeight()/20);
		table.row();
		table.add(Options).width(Start.getWidth()).height(Start.getHeight()).padBottom(Gdx.graphics.getHeight()/20);
		table.row();
		table.add(CustomShip).width(Start.getWidth()).height(Start.getHeight()).padBottom(Gdx.graphics.getHeight()/20);
		table.row();
		table.add(Exit).width(Start.getWidth()).height(Start.getHeight()).padBottom(Gdx.graphics.getHeight()/20);
		
	//	table.debug();
		
		
		
		
		// all the settings for the options window.
		//
		//
		//
		final Window optionsWindow = new Window("Options", skin);
		optionsWindow.setMovable(true);
		optionsWindow.setSize(Gdx.graphics.getWidth()*.6f, Gdx.graphics.getHeight()*.6f);
		//center the options window
		optionsWindow.setPosition(Gdx.graphics.getWidth()/2- optionsWindow.getWidth()/2,Gdx.graphics.getHeight()/2-optionsWindow.getHeight()/2);
		// the top padding top is what you can drag the window with
		optionsWindow.padTop(20);
		optionsWindow.debug();
		optionsWindow.add(optionsTable);
		
		// The Buttons for the Options Window
		Label oTitle = new Label("Options ", ls);
		final Label sLevel = new Label ("10",ls);
		final Label sText = new Label ("Sound",ls);
		final Label gLevel = new Label ("High",ls);
		final Label gText = new Label ("Graphics",ls);
		final Label eText = new Label("Exit",ls);
		final TextButton eOptions = new TextButton("Back", skin);		
		final TextButton sUp = new TextButton("+",skin);
		final TextButton sDown = new TextButton("-",skin);
		final TextButton gUp = new TextButton("+",skin);
		final TextButton gDown = new TextButton("-",skin);
		
		
	
		optionsTable.setSize(optionsWindow.getWidth(), optionsWindow.getHeight());;
		// TODO figure out how to center the text....
		
//		optionsTable.row().fillX().padBottom(20);
//		optionsTable.add().colspan(1);
//		optionsTable.add(oTitle).height(optionsTable.getHeight()/6);
//		optionsTable.add().colspan(1);
		
		optionsTable.row().padBottom(20);
		optionsTable.add(sText).colspan(3);
		
		
		optionsTable.row().padBottom(20);
		optionsTable.add(sDown).width(optionsTable.getWidth()/3).height(optionsTable.getHeight()/6).fill();
		optionsTable.add(sLevel);
		optionsTable.add(sUp).width(optionsTable.getWidth()/3).height(optionsTable.getHeight()/6).fill();
		optionsTable.row().padBottom(20);

		optionsTable.add(gText).colspan(3);
		optionsTable.row().padBottom(20);
		
		optionsTable.add(gDown).width(optionsTable.getWidth()/3).height(optionsTable.getHeight()/6).fill();
		optionsTable.add(gLevel).padLeft(10).padRight(10);
		optionsTable.add(gUp).width(optionsTable.getWidth()/3).height(optionsTable.getHeight()/6).fill();
		optionsTable.row().padBottom(20);
		
		//optionsTable.add(eText).colspan(3);
		optionsTable.row().padBottom(20);
		optionsTable.add(eOptions).colspan(3).expandX().fillX().height(optionsTable.getHeight()/6);
		
		
		gUp.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if(gUp.isChecked()){
					gLevel.setText("High");
					game.glevel = graphics.HIGH;
					gUp.setChecked(false);
				}
				
			}
		});
		gDown.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if(gDown.isChecked()){
					gLevel.setText("Low");
					game.glevel = graphics.LOW;
					gDown.setChecked(false);
				}
			}
		});
		
		
		sDown.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if(sDown.isChecked()){
					game.Sound --;
					if (game.Sound < 0){
						game.Sound = 0;
					}					
					sLevel.setText("" + game.Sound);
					sDown.setChecked(false);
				}
					
				
			}
		});
		
		sUp.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				
				// if pressed down then run
				// prevents this from running twice.
				if(sUp.isChecked()){					
				game.Sound ++;
				if(game.Sound > 10){
					game.Sound = 10;
				}
				sLevel.setText("" + game.Sound);
				sUp.setChecked(false);
				}
				
				
			}
		});
		
		optionsWindow.setVisible(false);		
		
		
		// Starts the game mofo
		Start.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(new AlphaScreen(game));
				dispose();
				
			}
		});
		
		CustomShip.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(new ShipBuilder(game,5));
				dispose();
				
			}
		});
		
		eOptions.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
					
				optionsWindow.setVisible(false);
			}
		});
		
		
		Story.addListener(new ChangeListener() {@Override
			public void changed(ChangeEvent event, Actor actor) {
				System.out.println("Clicked! Is checked: " + Story.isChecked());
				Story.setText("Good job!");
				//game.setScreen(new solarmapTest(gam));
				game.setScreen(new DialogueTest(gam));
			}
		});
		
		Options.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				optionsWindow.setVisible(true);
				
				
			}
		});
		
		Exit.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				Gdx.app.exit();
			}
		});

		

		stage.addActor(table);
		stage.addActor(optionsWindow);
		
	}

	@Override
	public void render(float delta) {
		//Gdx.gl.glClearColor(0,0,0.2f,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		
		
		//Table.drawDebug(stage);
		
		
		
		
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
		stage.dispose();
		//this.dispose();
		
	}

}
