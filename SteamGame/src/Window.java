import java.awt.Dimension;

import javax.swing.JFrame;

public class Window {
	JFrame f = new JFrame();
	
	public Window(Main main) {
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack();
		f.setVisible(true);
		f.setTitle("Steam Game");
		f.setPreferredSize(new Dimension(800,800));
		f.add(main);
	}
}
