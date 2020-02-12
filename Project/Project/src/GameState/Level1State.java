package GameState;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import Main.GamePanel;
import TileMap.*;
import Entity.*;
import Entity.Enemies.Slugger;
import Entity.Enemies.Dekhoda;
import Audio.AudioPlayer;
import javax.swing.JOptionPane;

public class Level1State extends GameState{
	
	private TileMap tileMap;
	private Background bg;
	
	private Player player;
	
	private ArrayList<Enemy> enemies;
	private ArrayList<Explosion> explosions;
	//private ArrayList<Player> players;
	
	private HUD hud;
	
	private AudioPlayer bgMusic;
	
	public Level1State(GameStateManager gsm){
		this.gsm = gsm;
		init();
		bgMusic = new AudioPlayer("/Music/bgm.mp3");
		bgMusic.play();
	}
	
	public void init() {
		
		tileMap = new TileMap(30);
		tileMap.loadTiles("/Tilesets/tileset2.gif");
		tileMap.loadMap("/Maps/level1-1.map");
		tileMap.setPosition(0, 0);
		tileMap.setTween(1);
		
		bg = new Background("/Backgrounds/bg.gif", 0.1);
		
		player = new Player(tileMap);
		player.setPosition(100,100);
		
		populateEnemies();
		populateDekhodas();
		
		explosions = new ArrayList<Explosion>();
		
		hud = new HUD(player);
		
		//bgMusic = new AudioPlayer("/Music/bgm.mp3");
		//bgMusic.play();
		
	}
	
	private void populateEnemies(){
		enemies = new ArrayList<Enemy>();
		
		Slugger s;
		Point[] points = new Point[] {
			new Point(150, 50),
			new Point(200, 100),
			new Point(210, 200),
			new Point(860, 200),
			new Point(960, 200),
			new Point(1525, 200),
			new Point(1680, 200),
			new Point(1800, 200),
			new Point(2930, 200),
			new Point(2950, 200),
			new Point(2970, 200),
			new Point(2990, 200),
			new Point(3010, 200),
			new Point(3030, 200),
			new Point(3050, 200),
			new Point(3070, 200),
			new Point(3090, 200),
			new Point(3110, 200),
			new Point(3130, 200),
			new Point(3150, 200),
			new Point(3170, 200)
			
			
		};
		
		for(int i = 0; i < points.length; i++){
			s = new Slugger(tileMap);
			s.setPosition(points[i].x, points[i].y);
			enemies.add(s);
		}
		
	}
	private void populateDekhodas(){
		new ArrayList<Dekhoda>();
		
		Dekhoda d;
		Point[] points = new Point[] {
			new Point(3150, 100)
			
			
		};
		
		for(int i = 0; i < points.length; i++){
			d = new Dekhoda(tileMap);
			d.setPosition(points[i].x, points[i].y);
			enemies.add(d);
		}
		
	}
	
	public void update() {
		
		
		// update player
		player.update();
		tileMap.setPosition(
				GamePanel.WIDTH / 2 - player.getx(),
				GamePanel.HEIGHT / 2 - player.gety()
				);
		
		// set background
		bg.setPosition(tileMap.getx(), tileMap.gety());
		
		// attack enemies
		player.checkAttack(enemies);
		
		// update all enemies
		for(int i = 0; i < enemies.size(); i++){
			Enemy e = enemies.get(i);
			e.update();
			if(e.isDead()){
				enemies.remove(i);
				i--;
				explosions.add(
						new Explosion(e.getx(), e.gety()));
			}
		}
		 	if(player.isDead()){
				//Component frame = null;
				//JOptionPane.showMessageDialog(frame, "You Died!!");
				//System.exit(0);
				int counter = 0;
	        	counter+=1;
	        	if (counter==2){ JOptionPane.showMessageDialog(null, "Game Over");
		           System.exit(0);
		           }
				 int reply = JOptionPane.showConfirmDialog(null, 
						 "YOU DIED, would you like to retry, or quit?", 
						 "Message", 
						 JOptionPane.YES_NO_OPTION);
			        if (reply == JOptionPane.YES_OPTION) {
			        
			        	JOptionPane.showMessageDialog(null, "Next Attempt");
			        	init();
			        }
			        else {
			           JOptionPane.showMessageDialog(null, "Game Over");
			           System.exit(0);
			        }
			}
		/*
		// update player
			for (int i = 0; i < players.size(); i++){
				Player p = players.get(i);
				p.update();
				if(player.isDead()){
				players.remove(i);
				i--;
				explosions.add(new Explosion(p.getx(),p.gety()));
				Component frame = null;
				JOptionPane.showMessageDialog(frame, "You Died!!");
				}
				//player.notifyDeath();
			}
		*/
		
		
		// update all explosions
		for(int i = 0; i < explosions.size(); i++ ){
			explosions.get(i).update();
			if(explosions.get(i).shouldRemove()){
				explosions.remove(i);
				i--;
			}
		}
		
	}
	/*
	private BufferStrategy strategy;
	Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
	public boolean isDead() {return player.dead;}
	public void notifyDeath() {
			message = "You died!! Play again?";
			g.setColor(Color.white);
			g.drawString(message,(800-g.getFontMetrics().stringWidth(message))/2,250);
		}
	*/
	public void draw(Graphics2D g) {
		
		//draw bg
		bg.draw(g);
		
		//draw tilemap
		tileMap.draw(g);
		
		//draw Player
		player.draw(g);
		
		//draw enemies
		for(int i = 0; i < enemies.size(); i++){
			enemies.get(i).draw(g);
		}
		
		// draw explosions
		for(int i = 0; i < explosions.size(); i++){
			explosions.get(i).setMapPosition(
					(int)tileMap.getx(),
					(int)tileMap.gety()
					);
			explosions.get(i).draw(g);
		}
		
		//draw HUD
		hud.draw(g);
		
		
	}
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_LEFT) player.setLeft(true);
		if(k == KeyEvent.VK_RIGHT) player.setRight(true);
		if(k == KeyEvent.VK_UP) player.setUp(true);
		if(k == KeyEvent.VK_DOWN) player.setDown(true);
		if(k == KeyEvent.VK_W) player.setJumping(true);
		if(k == KeyEvent.VK_E) player.setGliding(true);
		if(k == KeyEvent.VK_R) player.setScratching();
		if(k == KeyEvent.VK_F) player.setFiring();
	}
	public void keyReleased(int k) {
		if(k == KeyEvent.VK_LEFT) player.setLeft(false);
		if(k == KeyEvent.VK_RIGHT) player.setRight(false);
		if(k == KeyEvent.VK_UP) player.setUp(false);
		if(k == KeyEvent.VK_DOWN) player.setDown(false);
		if(k == KeyEvent.VK_W) player.setJumping(false);
		if(k == KeyEvent.VK_E) player.setGliding(false);
	}
}