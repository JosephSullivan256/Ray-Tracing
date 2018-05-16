package com.josephsullivan256.gmail.raytracing.core;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.josephsullivan256.gmail.raytracing.SimpleMaterial;
import com.josephsullivan256.gmail.raytracing.math.Matrix;
import com.josephsullivan256.gmail.raytracing.math.Vec3;
import com.josephsullivan256.gmail.raytracing.pbr.LambertianMaterial;
import com.josephsullivan256.gmail.raytracing.renderables.RPlane;
import com.josephsullivan256.gmail.raytracing.renderables.RSphere;
import com.josephsullivan256.gmail.raytracing.renderables.SphereLight;
import com.josephsullivan256.gmail.raytracing.util.Display;

public class Main {
	
	public static void main(String[] args){
		Material mirror = new LambertianMaterial(16,5);/*new SimpleMaterial(1,new float[]{1,0,0});*/
		Material diffuse = new LambertianMaterial(16,5);/*new SimpleMaterial(1,new float[]{0.05f,1f,0.05f});*/
		
		Vec3 c1 = new Vec3(1.000f, 0.498f, 0.314f);
		Vec3 c2 = //new Vec3(0,0.5f,1f);
				new Vec3(0.804f, 0.361f, 0.361f);
		
		Renderable[] rs = new Renderable[]{
				new SphereLight(1f,new Vec3(4f,-2f,9),new Vec3(1,1,1).scaledBy(2)),
				new RSphere(1f,new Vec3(0,0,6),c1,diffuse),
				new RSphere(1f,new Vec3(3,1f,8),c1,diffuse),
				new RSphere(1f,new Vec3(-2,-2,9),c1,diffuse),
				new RPlane(new Vec3(0,2,0), new Vec3(0,1,0),c2,mirror),
		};
		
		Scene s = new Scene(rs);
		
		Renderer r = new Renderer();
		
		RenderParameters rp = new RenderParameters((float)Math.PI/2f, Matrix.t44(new Vec3(0,0.5f,0)),5);
		
		BufferedImage image = new BufferedImage(200,200,BufferedImage.TYPE_INT_RGB);
		r.render(image, rp, s);
		
		try {
			ImageIO.write(image, "jpg", new File("output.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Display d = new Display(image);
		/*float i = 0;
		while(true){
			i++;
			rp = new RenderParameters((float)Math.PI/2f, Matrix.rx44(i/10f),4);
			r.render(image, rp, s);
			d.update(image);
		}*/
	}
}
