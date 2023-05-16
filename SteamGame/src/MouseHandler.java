import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler extends Game implements MouseListener{

	public void mouseClicked(MouseEvent e) {
		/*for(int i = 0; i < 10; i++) {
			Game.l1.war();
		}*/
	}

	public void mousePressed(MouseEvent e) {
	
		if(e.getButton() == MouseEvent.BUTTON1) {
			Game.l1.activeFoods.add
			(new Food(
					(short) 0,
					(int) Math.floor((e.getX() - 20) / Board.tileSize) * Board.tileSize + 10,
					(int) Math.floor((e.getY() + 13) / Board.tileSize) * Board.tileSize - 45
					)
			);
		}
		if(e.getButton() == MouseEvent.BUTTON3) {
			
		}
		
	}
	

	public void mouseReleased(MouseEvent e) {
		
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}
}
