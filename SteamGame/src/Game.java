import java.awt.Graphics;

public class Game {
	private int x;

	public void draw(Graphics g) {
		g.fillRect(x, 0, 100, 100);
	}

	public void update() {
		x++;
	}
}
