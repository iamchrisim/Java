package Entity.Enemies;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import Entity.Animation;
import Entity.Enemy;
import TileMap.TileMap;


public class Dekhoda extends Enemy {
	
	private BufferedImage[] sprites;
	
	public Dekhoda(TileMap tm){
		
		super(tm);
		
		moveSpeed = 0.4;
		maxSpeed = 0.4;
		fallSpeed = 0.2;
		maxFallSpeed = 10.0;
		
		width = 60;
		height = 100;
		cwidth = 50;
		cheight = 90;
		
		health = maxHealth = 30;
		damage = 1;
		
		//load sprites
		
		try{
			BufferedImage spritesheet = ImageIO.read(
					getClass().getResourceAsStream(
							"/Sprites/Enemies/Dekhoda.png"
							)
					
					);
			sprites = new BufferedImage[1];
			for(int i = 0; i < sprites.length; i++){
				sprites[i] = spritesheet.getSubimage(
						i * width,
						0,
						width,
						height
						);
			}
			
					
			}
		catch(Exception e){
			e.printStackTrace();
		}
		animation = new Animation();
		animation.setFrames(sprites);
		animation.setDelay(300);
		
		right = true;
		facingRight = true;
		
	}
	
	private void getNextPosition(){
		
		//movement
				if(left){
					dx -= moveSpeed;
					if(dx < -maxSpeed){
						dx = -maxSpeed;
					}
				
				}
				else if (right){
					dx += moveSpeed;
					if(dx > maxSpeed){
						dx = maxSpeed;
					}
				}
				
				//falling
				if(falling){
					dy += fallSpeed;
				}
				
	}
	
	public void update()
	{
		// update position
		getNextPosition();
		checkTileMapCollision();
		setPosition(xtemp, ytemp);
		
		//check flinching
		if(flinching){
			long elapsed = 
					(System.nanoTime() - flinchTimer) / 1000000;
			if(elapsed > 400){
				flinching = false;
			}
		}
		// if it hits a wall, go other direction
		if(right && dx == 0){
			right = false;
			left = true;
			facingRight = false;
			
		}
		else if(left && dx == 0){
			right = true;
			left = false;
			facingRight = true;
		}
		
		// update animation
		animation.update();
		
	}
	public void draw(Graphics2D g){
		
		//if(notOnScreen()) return;
		
		setMapPosition();
		
		super.draw(g);
		
		
		
	}
}