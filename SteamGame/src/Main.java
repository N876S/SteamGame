import javax.swing.JPanel;

public class Main extends JPanel implements Runnable{
	private static final long serialVersionUID = 1L;
	
	Game game = new Game();

	public static void main(String[] args) {
		Main main = new Main();
		new Window(main);
	}

	public void run() {
		
	}
	
	
}
