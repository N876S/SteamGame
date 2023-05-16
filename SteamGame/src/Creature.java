import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Creature {
	short indexType;
	int dmg;
	int hp;
	int movespd;
	int attlen;
	int range;
	Image img;
	String imgURL;
	boolean attacking = false;
	float healthPercentage = 1.0f;
	int maxhp;
	
	int tx;
	int ty;
	int txi;
	int x = tx * Board.tileSize + txi;
	int y = ty * Board.tileSize;
	
	public Creature(short indexType, int x, int y) {
		this.indexType = indexType;
		dmg = CreatureInfo.defaultStats[0][indexType];
		hp = CreatureInfo.defaultStats[1][indexType];
		movespd = CreatureInfo.defaultStats[2][indexType];
		attlen = CreatureInfo.defaultStats[3][indexType];
		range = CreatureInfo.defaultStats[4][indexType];
		imgURL = CreatureInfo.imageStrings[indexType];
		maxhp = hp;
		img = Toolkit.getDefaultToolkit().getImage(imgURL);
		this.x = x;
		this.y = y;
	}
	
	public void drawCreature(Graphics g) {
		g.drawImage(img, x, y, Board.tileSize, Board.tileSize, null);
	}
	
	public void drawHealthBar(Graphics g) {
		java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
		g2.setStroke(new BasicStroke(3));
		g2.setColor(Color.BLACK);
		g2.drawRect(x + 15, y + 70, 60, 10);
		
		try {
			if(healthPercentage >= 0.0f && healthPercentage <= 1.0f) {
				g.setColor(new Color(Math.round(255*(1-healthPercentage)),Math.round(255*healthPercentage),0));
			}
		} catch (Exception e) {
			
		}
		g.fillRect(x+16, y+71, Math.round(58*healthPercentage), 8);
	}
	
	public void updateHealthBar() {
		healthPercentage = (float) hp / (float) maxhp;
	}
	
	public void updateCreature() {
		
	}
}
