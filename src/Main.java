import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main {
	
	public static void main(String[] args){
		Material mirror = new StandardMaterial(1,new float[]{0,1,0,0});
		Material diffuse = new StandardMaterial(2,new float[]{0.05f,1f,0.05f,0});
		
		Vec3 c1 = new Vec3(1.000f, 0.498f, 0.314f);
		Vec3 c2 = //new Vec3(0,0.5f,1f);
				new Vec3(0.804f, 0.361f, 0.361f);
		
		Renderable[] rs = new Renderable[]{
				//new SphereLight(1f,new Vec3(4f,-2f,9),new Vec3(1,1,1).scaledBy(2)),
				new RSphere(1f,new Vec3(0,0,6),c1,mirror),
				new RSphere(1f,new Vec3(3,1f,8),c1,mirror),
				new RSphere(1f,new Vec3(-2,-2,9),c1,mirror),
				new RPlane(new Vec3(0,2,0), new Vec3(0,1,0),c2,diffuse),
		};
		
		Scene s = new Scene(rs);
		
		Renderer r = new Renderer();
		
		RenderParameters rp = new RenderParameters((float)Math.PI/2f, new Vec3(0,0.5f,0),4);
		
		BufferedImage image = new BufferedImage(400,400,BufferedImage.TYPE_INT_ARGB);
		r.render(image, rp, s);
		
		/*BufferedImage displayImage = new BufferedImage(600,600,BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = displayImage.createGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		g2d.drawImage(image, 0, 0, 600, 600, null);
		g2d.dispose();*/
		
		Display d = new Display(image);
		float i = 0;
		while(true){
			i++;
			rp = new RenderParameters((float)Math.PI/((float)Math.sin(i/10f)+2f), new Vec3(3f*(float)Math.sin((i)/10f+Math.PI/2f),0.5f,0),4);
			r.render(image, rp, s);
			d.update(image);
		}
	}
}
