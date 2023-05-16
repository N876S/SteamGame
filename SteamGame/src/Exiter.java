
public class Exiter extends Thread{
	public void run() {
		try {
			Game.l1.saveGame();
			System.out.println("Successfully saved, closing down now.");
		} catch (Exception e) {
			System.out.println("Save failed, progress most likely lost.");
		}
	}
}
