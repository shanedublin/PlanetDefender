package rusd.entities.modular_ship;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import rusd.entities.modular_ship.Block.Direction;
import rusd.methods.TextureNames.Textures;

public class Sheild extends Block {

	

	public Sheild(Vector2 center){
		super(center);
		this.tr = Textures.SHEILD1.getTexture();
		this.tr1 = new TextureRegion(tr);
		this.direction = Direction.UP;
	}
}
