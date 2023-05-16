import java.awt.Color;
import java.awt.Graphics;

public class Board {
	
	public final static int tileSize = Window.height/2/4;
	public int tileWidthCount;
	public int tileHeightCount;
	public int startGridX;
	public int startGridY;
	
	public Board(int twc, int thc) {
		this.tileWidthCount = twc;
		this.tileHeightCount = thc;
		startGridX = (Window.width-tileSize*tileWidthCount)/2;
		startGridY = (Window.height-tileSize*tileHeightCount)/2 - tileSize;
	}
	
	public void drawBoard(Graphics g) {
		g.setColor(Color.black);
		
		for(int i = 0; i < tileWidthCount; i++) {
			for(int j = 0; j < tileHeightCount; j++) {
				g.drawRect(
						i*tileSize + startGridX,
						j*tileSize + startGridY,
						tileSize,
						tileSize);
			}
		}
	}
}
