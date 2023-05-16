
import java.awt.Graphics;

import javax.swing.JPanel;

public class Main extends JPanel implements Runnable{
	private static final long serialVersionUID = 1L;
	
	Game game = new Game();
	MouseHandler mh = new MouseHandler();
	
	// gameloop variables
    Thread thread = new Thread(this);
    private boolean running;
    private double updateRate = 1.0d / 60.0d;
    private long nextStatTime;
    private static int fpsc;
    private static int upsc;
    public static int fps;
    public static int ups;
    
    private Main() {
    	thread.start();
    }

	public static void main(String[] args) {
		Main main = new Main();
		new Window(main, main.mh);
		Runtime r = Runtime.getRuntime();
		r.addShutdownHook(new Exiter());
	}

	public void run() {
		running = true;
        double accumulator = 0;
        long currentTime = System.currentTimeMillis();
        long lastUpdate = System.currentTimeMillis();
        double lastRenderTimeInSeconds = currentTime - lastUpdate;
        nextStatTime = System.currentTimeMillis() + 1000;

        while (running) {
            currentTime = System.currentTimeMillis();
            lastRenderTimeInSeconds = (currentTime - lastUpdate) / 1000d;
            accumulator += lastRenderTimeInSeconds;
            lastUpdate = currentTime;

            while (accumulator > updateRate) {
                update();
                upsc++;
                accumulator -= updateRate;
            }
            repaint();
            fpsc++;
            if (System.currentTimeMillis() > nextStatTime) {
                fps = fpsc;
                ups = upsc;
                fpsc = 0;
                upsc = 0;
                nextStatTime = System.currentTimeMillis() + 1000;
            }
        }
	}
	
	private void update() {
		game.update();
	}
	
	public void paint(Graphics g) {
		game.draw(g);
	}
	
}
