import java.awt.Graphics;

import javax.swing.JPanel;

public class Main extends JPanel implements Runnable{
	private static final long serialVersionUID = 1L;
	
	Game game = new Game();
	
	// gameloop variables
    Thread thread = new Thread(this);
    private boolean running;
    private double updateRate = 1.0d / 60.0d;
    private long nextStatTime;
    private int fps;
    private int ups;
    
    public Main() {
    	thread.start();
    }

	public static void main(String[] args) {
		Main main = new Main();
		new Window(main);
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
                ups++;
                accumulator -= updateRate;
            }
            repaint();
            fps++;
            if (System.currentTimeMillis() > nextStatTime) {
                System.out.println(ups);
                System.out.println(fps);
                fps = 0;
                ups = 0;
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
