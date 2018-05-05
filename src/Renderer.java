import java.awt.image.BufferedImage;

public class Renderer {
	
	public Renderer(){
		
	}
	
	public void render(BufferedImage image, RenderParameters rp, Scene s){
		int width = image.getWidth();
		int height = image.getHeight();
		float hfov = rp.fov/2f;
		Vec3 pos = rp.pos;
		int n = rp.n;
		
		float pi = (float) Math.PI;
		
		for(int x = 0; x < width; x++){
			float xtheta = pi/2f-hfov+2*hfov*((float)x)/((float)width);
			
			float xcos = (float) Math.cos(xtheta);
			float xsin = (float) Math.sin(xtheta);
			
			for(int y = 0; y < height; y++){
				float ytheta = -hfov+2*hfov*((float)y)/((float)height); //technically wrong... ytheta should span a shorter range typically cause height<width
				
				
				float ycos = (float) Math.cos(ytheta);
				float ysin = (float) Math.sin(ytheta);
				
				Ray r = new Ray(pos,new Vec3(xcos,ysin,Math.abs(xsin*ycos)));
				Vec3 col = s.shoot(r,n);
				image.setRGB(x, y, col.toColor().getRGB());
			}
		}
	}
}
