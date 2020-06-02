package rusd.entities.modular_ship;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import rusd.entities.modular_ship.Block.Direction;
import rusd.methods.TextureNames.Textures;

public class Brain extends Block{
	

	public Brain(Vector2 center){
		super(center);
		this.tr = Textures.BRAIN;
		this.tr1 = new TextureRegion(tr);
		this.direction = Direction.UP;
	}

}
