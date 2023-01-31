import java.awt.Color;
import java.awt.Graphics;

public class Game {
	private int x;

	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(-1, -1, 1280, 720);

		g.setColor(Color.BLACK);
		g.fillRect(x, 100, 100, 100);
	}

	public void update() {
		x++;
	}
}
