import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Food {
	short indexType;
	int dmg;
	int hp;
	int movespd;
	int attlen;
	int cost;
	int cd;
	int range;
	boolean attacking = false;
	Image img;
	String imgURL;
	float healthPercentage = 1.0f;
	int maxhp;
	
	int tx;
	int ty;
	int txi;
	int x = tx * Board.tileSize + txi;
	int y = ty * Board.tileSize;
	
	public Food(short indexType, int x, int y) {
		this.indexType = indexType;
		dmg = FoodInfo.defaultStats[0][indexType];
		hp = FoodInfo.defaultStats[1][indexType];
		movespd = FoodInfo.defaultStats[2][indexType];
		attlen = FoodInfo.defaultStats[3][indexType];
		cost = FoodInfo.defaultStats[4][indexType];
		cd = FoodInfo.defaultStats[5][indexType];
		range = FoodInfo.defaultStats[6][indexType];
		imgURL = FoodInfo.imageStrings[indexType];
		maxhp = hp;
		img = Toolkit.getDefaultToolkit().getImage(imgURL);
		this.x = x;
		this.y = y;
	}
	
	public void drawFood(Graphics g) {
		g.drawImage(img, x, y, Board.tileSize, Board.tileSize, null);
	}
	public void drawHealthBar(Graphics g) {
		java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
		g2.setStroke(new BasicStroke(3));
		g2.setColor(Color.BLACK);
		g2.drawRect(x + 15, y + 70, 60, 10);
		
		try {
			g.setColor(new Color(Math.round(255*(1-healthPercentage)),Math.round(255*healthPercentage),0));
		} catch (Exception e) {
			System.out.println("Colour baldness");
		}
		g.fillRect(x+16, y+71, Math.round(58*healthPercentage), 8);
	}
	
	public void updateHealthBar() {
		healthPercentage = (float) hp / (float) maxhp;
	}
	
	public void updateFood() {
		
	}
}
