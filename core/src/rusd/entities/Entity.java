package rusd.entities;




import rusd.methods.TextureNames.Textures;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Entity {
// location in the game world

public float x;
public float y;
// direction that the object is rotated. 0 - 360
public float direction;
public int health;
public int armor;
public int maxHealth;


public Rectangle bounds;
public Vector2 center;
public String textureName;

public TextureRegion textureRegion;


/**
 * default constructor
 */
public Entity(){
	
	this.x = 0;
	this.y = 0;
	this.direction = 0;
	this.armor = 1;
	this.health = 1;
	this.maxHealth = 1;
	this.textureRegion = Textures.BLANK1;
	this.bounds = new Rectangle(x,y,1,1);
	this.center = new Vector2(0,0);
	
}

public Entity(float x, float y, float width, float height, float dir, int hp, int armor,TextureRegion tr){
	this.x = x;
	this.y = y;
	
	this.direction = dir;
	this.health = hp;
	this.armor =armor;
	this.textureRegion = tr;
	this.maxHealth = hp;
	
	this.bounds = new Rectangle(x, y , width, height);
	this.center = new Vector2(x +width/2, y + height/2);
			
}
/**
 * TODO fix math in planet maker so center works right.
 * taking a short cut here
 * @param x
 * @param y
 */
protected void setCenter(float x, float y){
	this.center.x = x;
	this.center.y = y;
	
	this.x = x - this.bounds.width/2;
	this.y = y - this.bounds.height/2;
			
	
}


/**
 * @return the x
 */
public float getX() {
	return x;
}


/**
 * Sets x and resets the bounds every time x moves
 * 
 */
public void setX(float x) {
	this.x = x;
	this.bounds.x = x;
	centerX();

}


/**
 * @return the y
 */
public float getY() {
	return y;
}


/**
 * Sets y and resets the bounds every time y moves
 * 
 */
public void setY(float y) {
	this.y = y;
	this.bounds.y = y;
	centerY();
}

private void centerX(){
	this.center.x = x + bounds.width/2;
	
}
private void centerY(){
	
	this.center.y = y + bounds.height/2;
}

/**
 * @return the direction
 */
public float getDirection() {
	return direction;
}


/**
 * @param direction the direction to set
 */
public void setDirection(float direction) {
	this.direction = direction ;
}


/**
 * @return the health
 */
public int getHealth() {
	return health;
}


/**
 * @param health the health to set
 */
public void setHealth(int health) {
	this.health = health;
}

public void modHealth(int health){
	this.health +=health;
}

/**
 * @return the armor
 */
public int getArmor() {
	return armor;
}


/**
 * @param armor the armor to set
 */
public void setArmor(int armor) {
	this.armor = armor;
}





	
}
