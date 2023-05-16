import java.awt.Color;
import java.awt.Graphics;

public class Game {
	
	static Level l1 = new Level();
	
	public Game() {
		
	}

	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(-1,-1,Window.width+1, Window.height+1);
		g.setColor(Color.RED);
		g.drawString("FPS: " + Integer.toString(Main.fps), 10, 20);
		g.drawString("UPS: " + Integer.toString(Main.ups), 10, 35);
		l1.drawLevel(g);
	}

	public void update() {
		l1.updateLevel();
	}
}

/*
 * TODO LIST
 * - encrypter
 * 1. Collision Between Creatures and Foods
 *  - Add ranged attacks
 * 2. Design Food Statistics
 * 3. Title Screen & Save System
 * 4. Clean Up Warnings & Organize Into Packages
 *
 * 
 * */
