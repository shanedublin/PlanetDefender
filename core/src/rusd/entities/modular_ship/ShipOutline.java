package rusd.entities.modular_ship;

import rusd.entities.Projectile;
import rusd.entities.Ship;
import rusd.methods.TextureNames;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.sun.javafx.scene.text.HitInfo;

public class ShipOutline {
	

	public int numBlocks;
	public int numBrain;
	public int numElectricity;
	public int numThrust;
	public int numWeapon;
	public int numSheild;
	
	/**
	 * The max amount of damage that the shield can take
	 */
	public float shieldMax;
	/**
	 * The current health of the shield
	 */
	public float currentShield;
	
	public float shieldRadius;
	
	
	public float ChargeRate;
	public float maxCharge;
	public float currentCharge;
	
	public boolean isShieldUp;
	public int size;
	
	// the rate at which the ship moves.
	public float speed = 0;
	public Block[][] blocks;
	public Array<Projectile> bullets;
	public float direction;
	public Holder[][] holders;
	/**
	 * number of pixels wide the ship is.
	 */
	public int shipPixel;
	
	//public Ship ship;
	
	public Vector2 center;
	/**
	 * really top Left
	 */ 
	public Vector2 topRight;
	//public float[] vertices = {10,10,20,20,30,40,50,50,10,50};
	public Polygon poly;
	
	
	/**
	 * the ship outline.
	 * creates a frame to hold the ship parts
	 * class does all the calculations 
	 */
	public ShipOutline(){
		this.size = 3;
		this.shipPixel = 16;
		
		numBlocks = 0;
		numThrust = 0;
		speed = 0;
		numWeapon = 0;
		numSheild = 0;
		numElectricity = 0;
		numBrain =0;
		
		
		this.blocks = new Block[size][size];
		numBlocks = size * size;
		System.out.println(numBlocks);
		for(int i = 0; i <blocks.length;i++){
			for (int j = 0; j < blocks[i].length;j ++){
				blocks[i][j] = new Blank(new Vector2(0,0));
			}				
		}
		
		// setting up a pre-made ship
		blocks[0][1] = new Weapon(new Vector2(0, 0));
		blocks[1][1] = new Generator(new Vector2(0, 0));
		blocks[2][1] = new Thrust(new Vector2(0, 0));
		
		blocks[0][0] = new Weapon(new Vector2(0, 0));
		blocks[1][0] = new Generator(new Vector2(0, 0));
		blocks[2][0] = new Thrust(new Vector2(0, 0));
		
		blocks[0][2] = new Weapon(new Vector2(0, 0));
		blocks[1][2] = new Generator(new Vector2(0, 0));
		blocks[2][2] = new Thrust(new Vector2(0, 0));
		
		

		
		
		center = new Vector2(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
		topRight = new Vector2(Gdx.graphics.getWidth()/2-shipPixel*size/2 , Gdx.graphics.getHeight()/2+ shipPixel*size/2);
		bullets = new Array<Projectile>();
		this.direction = 90;
		isValid();
		
		
	}
	
	/**
	 * Create a new Blank ship.
	 * @param size
	 */
	public ShipOutline(int size,int scale){
		this.size = size;
		this.shipPixel = scale;
		
		numBlocks = 0;
		numThrust = 0;
		speed = 0;
		numWeapon = 0;
		numSheild = 0;
		numElectricity = 0;
		numBrain =0;
		
		this.blocks = new Block[size][size];
		numBlocks = size * size;
		System.out.println(numBlocks);
		for(int i = 0; i <blocks.length;i++){
			for (int j = 0; j < blocks[i].length;j ++){
				blocks[i][j] = new Blank(new Vector2(0,0));
			}				
		}
		
	

		
		
		center = new Vector2(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
		topRight = new Vector2(Gdx.graphics.getWidth()/2-shipPixel*size/2 , Gdx.graphics.getHeight()/2+ shipPixel*size/2);
		bullets = new Array<Projectile>();
		this.direction = 0;
		isValid();
		
		//poly = new Polygon(vertices);
		
		
	}
	
	public void update(){
		charge();
		rechargShield();
	}
	
	@Deprecated
	public void fire(){
		// TODO log the gun locations so you dont have to search each time
		for(int i = 0; i < blocks.length;i++)
			for(int j = 0; j <blocks[i].length; j++){
				if(blocks[i][j].isWeapon()){
					
					Vector3 Trajectory;
		Projectile bullet;

		Trajectory = new Vector3((float) Math.cos(direction
				* MathUtils.degreesToRadians), (float) Math.sin(direction
				* MathUtils.degreesToRadians), 0);

		bullet = new Projectile((blocks[i][j].center.x- 2 ),
				(blocks[i][j].center.y - 4 ), 2, 4, 0, 1, 1, TextureNames.Textures.LASER1,
				Trajectory);
		bullet.setDirection(direction);
	
		bullets.add(bullet);
		
				}
				
			}
		
		
		
		
		
	}
	
	public void fire(Vector3 mousePos){
		// TODO log the gun locations so you dont have to search each time
		for(int i = 0; i < blocks.length;i++)
			for(int j = 0; j <blocks[i].length; j++){
				
				if(blocks[i][j].isWeapon() && ! blocks[i][j].destroyed){
					Weapon w = (Weapon) blocks[i][j];
					if(w.canFire())
					if(currentCharge  >  1){
						currentCharge -= .5;
						w.fire();
					
				Projectile bullet;
				float x;
				float y;
				float xRatio;
				float yRatio;
				float hypo;
				
				// TODO get this set up, not that important right now...
				float tempDirection;
				Vector3 Trajectory;
				x = mousePos.x - w.center.x;
				y = mousePos.y - w.center.y;
				hypo = (float)Math.sqrt(x * x + y * y);
				
				xRatio = x / hypo;
				yRatio = y / hypo;
				
				
				
					Trajectory = new Vector3(xRatio,yRatio,0 );
					
					bullet = new Projectile((w.center.x- 2 ),
							(w.center.y - 4 ), 2, 4, 0, 1, 1, TextureNames.Textures.LASER1,
							Trajectory);
					bullet.setDirection(direction);
					
					bullets.add(bullet);
					
				}
				}
			}
		
		
		
		
		
	}
	
	
	public boolean isValid(){
		
		numBlocks = size * size;
		numSheild = 0;
		numThrust = 0;
		numElectricity = 0;
		
		for(int i = 0; i < blocks.length;i++)
			for(int j = 0; j <blocks[i].length; j++){
				if (blocks[i][j] instanceof Generator){
					numElectricity ++;
				}
				else if(blocks[i][j] instanceof Sheild){
					numSheild ++;
				}
				else if(blocks[i][j] instanceof Thrust){
					numThrust ++;
				}
				else if (blocks[i][j] instanceof Weapon){
					//TODO add weapons to an array... 
				}
				
				if(blocks[i][j] instanceof Blank){
					numBlocks --;
					//System.out.println("blank");
				}
				
			}
		
		setSpeed();
		setShield();
		setCharge();
		
		return true;
		
		
	}
	
	public void setCharge(){
		numElectricity = 0;
		for(int i = 0; i < blocks.length;i++)
			for(int j = 0; j <blocks[i].length; j++){
				 if(blocks[i][j] instanceof Generator  && ! blocks[i][j].destroyed){
					numElectricity ++;
				}
			}	
		ChargeRate = (float) numElectricity * .01f;
		maxCharge = numElectricity * 10;
		currentCharge = maxCharge;
		
	}
	public void charge(){
		
		this.currentCharge += ChargeRate;
		if(currentCharge > maxCharge){
			currentCharge = maxCharge;
		}
		
	}
	
	public void setShield(){
		numSheild = 0;
		for(int i = 0; i < blocks.length;i++)
			for(int j = 0; j <blocks[i].length; j++){
				 if(blocks[i][j] instanceof Sheild && ! blocks[i][j].destroyed){
					numSheild ++;
				}
			}
		this.shieldMax = numSheild * 5;
		this.currentShield = shieldMax;
		if(numSheild > 0)
			this.isShieldUp = true;
		this.shieldRadius = shipPixel * size * .75f;
		
	}
	public void hitShield(){
		this.currentShield --;
		if (this.currentShield < 1)
			isShieldUp = false;
	}
	
	public void rechargShield(){
		
		if(currentCharge > (float) numSheild * .005){
			
		currentCharge -=  (float) numSheild * .005;
		this.currentShield += (float) numSheild * .005;
		// don't let the shield charge more than full
		if(this.currentShield > shieldMax){
			currentShield = shieldMax;			
		}
		// after the shield has been down, when it reaches 50% it can turn back on.
		if(this.currentShield > shieldMax / 2){
			isShieldUp = true;
		}
		}
		
	}
	
	public void setCenter(float x, float y){
		
		this.center.x = x;
		this.center.y = y;
	}
	
	public void setSpeed(){
		numThrust = 0;
		for(int i = 0; i < blocks.length;i++)
			for(int j = 0; j <blocks[i].length; j++){
				if(blocks[i][j] instanceof Thrust && !blocks[i][j].destroyed){
					numThrust ++;
					//System.out.println("test"+ blocks[i][j].destroyed);
				}
			}
		
		System.out.println("numblocks is "+ numBlocks);
		System.out.println("numthrust is "+ numThrust);
		if(numBlocks > 0){
			speed = (float) numThrust/ (float) numBlocks *25;
			
		}
		else{
			speed =0;  
		}
		System.out.println("Speed is "+ speed);
	}
	
	public void move(float x,float y){
		this.center.x +=x * speed;
		this.center.y +=y * speed;
		this.topRight.x += x * speed;
		this.topRight.y += y  * speed;
	}
	/**
	 * point the front end of the ship towards the mouse TODO learn vector math
	 * to simplify
	 */
	public void rotateShip(Vector3 mousePos) {
		float x1, x2, x3;
		float y1, y2, y3;
		float angle;

		x1 = mousePos.x;
		y1 = mousePos.y;		
		x2 = center.x;	
		y2 = center.y;
		x3 = x1 - x2;
		y3 = y1 - y2;		
		angle = (float) Math.atan(y3 / x3);
		angle *= MathUtils.radiansToDegrees;

		// takes care of angle sector stuffs
		// if the mouse is in the north east or south east no correction is
		// needed 
		//
		if (x3 >= 0 && y3 >= 0 || x3 >= 0 && y3 <= 0) {
			direction = angle;
		}
		//the remaining two cases are covered here.
		else {
			direction =((angle) + 180);
		}

		//System.out.println(direction);
	

	}
	
	public void blockCenters(){
		for(int i = 0; i < blocks.length;i++)
			for(int j = 0; j <blocks[i].length; j++){	
				
				final float cos = MathUtils.cosDeg(direction+180);
				final float sin = MathUtils.sinDeg(direction+180);
				float x1;
				float y1;
				float p1x;
				float p1y;
				
				
				p1x = i * shipPixel  - shipPixel * size / 2 + shipPixel/2;
				p1y = j * shipPixel - (shipPixel * size/2 - .5f) + shipPixel/2;
				 
				x1 = cos * p1x - sin * p1y;
				y1 = sin * p1x + cos * p1y;
				
				x1 += center.x;
				y1 += center.y;
				
				blocks[i][j].center.x = x1;
				blocks[i][j].center.y = y1;
				
				float cx1;
				float cx2;
				float cx3;
				float cx4;
				
				float cy1;
				float cy2;
				float cy3;
				float cy4;
				
				
				float cp1x;
				float cp2x;
				float cp3x;
				float cp4x;

				float cp1y;
				float cp2y;
				float cp3y;
				float cp4y;
				
				
				final float cos1 = MathUtils.cosDeg(direction);
				final float sin1 = MathUtils.sinDeg(direction);
				
				cx1 = -this.shipPixel/2;
				cy1 = - shipPixel/2;
				
				cx2 = -this.shipPixel/2;
				cy2 =  shipPixel/2;
				
				cx3 = this.shipPixel/2;
				cy3 = shipPixel/2;
				
				cx4 = this.shipPixel/2;
				cy4 = - shipPixel/2;
				
				cp1x = cos1 * cx1 - sin1 * cy1;
				cp1y = sin1 * cx1 + cos1 * cy1;
				
				cp2x = cos1 * cx2 - sin1 * cy2;
				cp2y = sin1 * cx2 + cos1 * cy2;
				
				cp3x = cos1 * cx3 - sin1 * cy3;
				cp3y = sin1 * cx3 + cos1 * cy3;
				
				cp4x = cos1 * cx4 - sin1 * cy4;
				cp4y = sin1 * cx4 + cos1 * cy4;
				
				cx1 += x1;
				cx2 += x1;
				cx3 += x1;
				cx4 += x1;
				
				cy1 += y1;
				cy2 += y1;
				cy3 += y1;
				cy4 += y1;
				
				cp1x +=x1;
				cp2x +=x1;
				cp3x +=x1;
				cp4x +=x1;
			
				cp1y += y1;
				cp2y += y1;
				cp3y += y1;
				cp4y += y1;
				
				
				blocks[i][j].poly.setVertices(new float[]{cp1x,cp1y,cp2x,cp2y,cp3x,cp3y,cp4x,cp4y});
				
				
//				
//
//				blocks[i][j].poly.setVertices(new float[]{
//						blocks[i][j].center.x-shipPixel/2, blocks[i][j].center.y - shipPixel/2, 
//						blocks[i][j].center.x-shipPixel/2, blocks[i][j].center.y + shipPixel/2,
//						blocks[i][j].center.x+shipPixel/2, blocks[i][j].center.y + shipPixel/2,
//						blocks[i][j].center.x+shipPixel/2, blocks[i][j].center.y - shipPixel/2});
				
			//		blocks[i][j].poly.rotate(direction);	
		//		blocks[i][j].poly.setPosition(blocks[i][j].center.x, blocks[i][j].center.y);
				
				
				
				
				
			}
	}
	

	
	public void render(SpriteBatch batch){
		
		// Renders the modular Ship
		for(int i = 0; i < blocks.length;i++)
		for(int j = 0; j <blocks[i].length; j++){

			// this convoluted mess offsets the ship so it renders all the squares in the right spots..
			if(!blocks[i][j].destroyed){
				
			batch.draw(blocks[i][j].tr1,
					center.x + j *(shipPixel)- size * shipPixel /2, 
					center.y - (i+1) *(shipPixel) + size * shipPixel /2, 
//					16,
					(float)shipPixel*size/2-shipPixel*j,
//					16,
					//size*i - size*2 + size/2 , 
					shipPixel * i - shipPixel * (size/2 -.5f),
					shipPixel, shipPixel, 1f, 1f, direction-90);
			}
		
		}
		
		
	}
	
	

}
