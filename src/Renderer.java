import java.awt.image.BufferedImage;

public class Renderer {
	
	public Renderer(){
		
	}
	
	public void render(BufferedImage image, RenderParameters rp, Scene s){
		float width = image.getWidth();
		float height = image.getHeight();
		float xfov = rp.fov;
		float yfov = xfov*height/width;
		Vec3 pos = rp.transform.times(Vec3.zero.asMatrix4p()).asVec3();
		int n = rp.n;
		
		for(int x = 0; x < width; x++){
			float xtheta = -xfov*0.5f+xfov*((float)x)/(width);
			
			float xcos = (float) Math.cos(xtheta);
			float xsin = (float) Math.sin(xtheta);
			
			for(int y = 0; y < height; y++){
				float ytheta = -yfov*0.5f+yfov*((float)y)/(height); //technically wrong... ytheta should span a shorter range typically cause height<width
				
				float ycos = (float) Math.cos(ytheta);
				float ysin = (float) Math.sin(ytheta);
				
				Vec3 direction = rp.transform.times(new Vec3(xsin*ycos,ysin,xcos*ycos).asMatrix4d()).asVec3();
				Ray r = new Ray(pos,direction);
				Vec3 col = s.shoot(r,n);
				image.setRGB(x, y, col.toColor().getRGB());
			}
		}
	}
}
