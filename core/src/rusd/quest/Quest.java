package rusd.quest;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import rusd.entities.Comet;
import rusd.entities.Projectile;
import rusd.methods.TextureNames.Textures;

public class Quest {
	
	// set to true when quest is over
	public boolean completed = false;
	// set to true if you chose to help the space place
	public boolean help = true;
	// set to true if you either fail to help or don't hurt them...
	public boolean failed = false;
	// do a planet defense quest
	public int numObjects = 20;
	public Array<Projectile> comets;	
	public Long timeSince;
	
	public enum type{
		COMET, INVADERS, DESTROY, TRADE, NONE, BOSS,
	}
	public type qtype;
	
	// use this for chosing the type of quest
	public Quest(int type){
		
		switch(type){
		case 0:
			noQuest();
			break;
		case 1:
			CometQuest();
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		case 5:
			break;
			
		}
		
		
	}
	// random quest
	public Quest(){
		this.help = true;
		this.failed = false;
		
		CometQuest();
		this.timeSince = TimeUtils.nanoTime();
		
	}
	
	public void noQuest(){
		qtype = type.NONE;
		this.failed = false;
		this.completed = true;
		
	}
	
	public void CometQuest(){
		this.help = true;
		this.failed = false;
			
		this.timeSince = TimeUtils.nanoTime();
				
		qtype = type.COMET;
		comets = new Array<Projectile>();
	}
	
	public void update(){
	
			switch(qtype){
			case COMET:
				if(numObjects >=0){					
					
				if(TimeUtils.nanoTime() - timeSince > 1000000000){
					spawnComet();
				}
				}
					
				Iterator<Projectile> itor = comets.iterator();
				while(itor.hasNext()){
					Comet c = (Comet) itor.next();
					c.move(100);
					
				}
				break;
				
			default:
					
				break;
			
			
			}
		
	
	
	}

	
	public void render(SpriteBatch batch){
		
		
		batch.begin();
		switch(qtype){
		case COMET:
			Iterator<Projectile> itor = comets.iterator();
			
			while(itor.hasNext()){
				Comet c = (Comet) itor.next();
				batch.draw(Textures.COMET, c.center.x, c.center.y,c.bounds.width,c.bounds.height);
			
			}
			
			break;
			
		default:
				
			break;
		
		
		}
		
		batch.end();
		
		
		
	}
	
	public void spawnComet(){
		
		float randAngle;
		randAngle = MathUtils.random(0,360);
		
		randAngle*= MathUtils.degreesToRadians;
		float x, y;
		
		x = (float) Math.cos(randAngle) * 1000;
		y = (float) Math.sin(randAngle) * 1000;
		Vector3 tempVect = new Vector3(-x/1000,-y/1000,0);
		
		
		
		Comet c = new Comet(x-8, y-8, 16, 16, 0, 1, 1, Textures.COMET, tempVect);
		
		
		comets.add(c);
		timeSince = TimeUtils.nanoTime();
		numObjects--;
	//	Gdx.app.log("Quest", "Spawned c " + numObjects);
		
	}
	

}
