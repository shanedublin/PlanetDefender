package rusd.methods;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public abstract class Intersects {

	public static boolean LineLine(float x1, float y1, float x2, float y2, float x3, float y3, float x4, float y4){
		
		//  Checks to see that the orientations are different between
		//  the points on the line
		return CCW(x1,y1,x3,y3,x4,y4) != CCW(x2,y2,x3,y3,x4,y4) && 
				CCW(x1,y1,x2,y2,x3,y3) != CCW(x1,y1,x2,y2,x4,y4); 
	}
	
	
	public static boolean LineLine(Vector2 A1, Vector2 A2, Vector2 B1, Vector2 B2){
		return LineLine(A1.x,A1.y,A2.x,A2.y,B1.x,B1.y,B2.x,B2.y);
		
	}
	
	/**
	 * CCW (Counter Clock Wise)
	 * checks to see if the points are arranged in a counter clockwise position
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @param x3
	 * @param y3
	 * @return
	 */
	public static boolean CCW(float x1, float y1, float x2, float y2, float x3, float y3 ){
		
		
		// this is some sort of dot product math, I don't fully understand
		//http://bryceboe.com/2006/10/23/line-segment-intersection-algorithm/
		return (y3-y1) * (x2-x1) > (y2 - y1) * (x3 - x1);
	}
}
