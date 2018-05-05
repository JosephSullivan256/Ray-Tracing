import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main {
	
	public static void main(String[] args){
		Material mirror = new StandardMaterial(1,new Vec3(0,1,0));
		Material diffuse = new StandardMaterial(20,new Vec3(0.1f,1,0.1f));
		
		Vec3 coral = new Vec3(1.000f, 0.498f, 0.314f);
		Vec3 red = new Vec3(0.804f, 0.361f, 0.361f);
		
		Renderable[] rs = new Renderable[]{
				new RSphere(1f,new Vec3(0,0,6),coral,mirror),
				new RSphere(1f,new Vec3(3,1f,8),coral,mirror),
				new RSphere(1f,new Vec3(-2,-2,9),coral,mirror),
				new RPlane(new Vec3(0,2,0), new Vec3(0,1,0),red,diffuse),
		};
		
		SphereLight[] pointLights = new SphereLight[] {
				new SphereLight(1f,new Vec3(4f,-2f,9),new Vec3(2,2,2)),
		};
		
		Scene s = new Scene(rs,pointLights);
		
		Renderer r = new Renderer();
		
		RenderParameters rp = new RenderParameters((float)Math.PI/1.5f, new Vec3(0,0.5f,0),5);
		
		BufferedImage image = new BufferedImage(1200,1200,BufferedImage.TYPE_INT_ARGB);
		r.render(image, rp, s);
		
		BufferedImage displayImage = new BufferedImage(600,600,BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = displayImage.createGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		g2d.drawImage(image, 0, 0, 600, 600, null);
		g2d.dispose();
		
		display(displayImage);
	}
	
	public static void display(BufferedImage img){
		JFrame frame = new JFrame();
		JLabel label = new JLabel(new ImageIcon(img));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.add(label);
		frame.setUndecorated(true);
		frame.pack();
		frame.setVisible(true);
	}
}
