import java.awt.Dimension;

import javax.swing.JFrame;

public class Window {
	JFrame f = new JFrame();
	
	public static final int width = 1280;
	public static final int height = 720;
	
	public Window(Main main, MouseHandler mh) {
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.setTitle("Steam Game");
		f.setPreferredSize(new Dimension(width,height));
		f.setResizable(false);
		f.pack();
		f.setLocationRelativeTo(null);
		f.add(main);
		f.addMouseListener(mh);
	}
}
