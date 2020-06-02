package rusd.methods;

import com.badlogic.gdx.math.Vector2;

public abstract class MyMath {
	
	
	/**
	 * given two points returns the  hypotenuse between the two points
	 * @param a
	 * @param b
	 * @return
	 */
	public static float hypo(Vector2 a, Vector2 b){
		
		float x;
		float y;
		float hypo;	
		x = b.x - a.x;
		y = b.y - a.y;
		hypo = (float) Math.sqrt(x* x + y * y);				
		return hypo;
		
	}


}
