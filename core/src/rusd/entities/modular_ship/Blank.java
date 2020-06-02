package rusd.entities.modular_ship;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import rusd.methods.TextureNames.Textures;


/**
 * DONT DRAW THIS SKIP OVER IT.
 * @author Shane
 *
 */
public class Blank extends Block {

	
	
	public Blank(Vector2 center){
		super(center);
	this.tr = Textures.BLANK;	
	this.tr1 = new TextureRegion(tr);
	}
}
