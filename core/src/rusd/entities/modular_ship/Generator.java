package rusd.entities.modular_ship;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import rusd.methods.TextureNames.Textures;

public class Generator extends Block {

	
	public Generator(Vector2 center){
		super(center);
		
		this.tr = Textures.SOLAR;
		this.tr1 = new TextureRegion(tr);
		this.direction = Direction.UP;
	}
}
