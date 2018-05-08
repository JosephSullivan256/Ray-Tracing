import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Display {
	private ImageIcon icon;
	private JLabel label;
	
	public Display(BufferedImage img){
		JFrame frame = new JFrame();
		ImageIcon icon = new ImageIcon(img);
		label = new JLabel(icon);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.add(label);
		frame.setUndecorated(false);
		frame.pack();
		frame.setVisible(true);
	}
	
	public void update(BufferedImage img){
		label.setIcon(new ImageIcon(img));
	}
}
