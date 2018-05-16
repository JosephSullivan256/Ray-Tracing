package com.josephsullivan256.gmail.raytracing.core;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import com.josephsullivan256.gmail.raytracing.math.Ray;
import com.josephsullivan256.gmail.raytracing.math.Vec2i;
import com.josephsullivan256.gmail.raytracing.math.Vec3;
import com.josephsullivan256.gmail.raytracing.util.Pair;

public class Renderer {
	
	public Renderer(){
		
	}
	
	public void render(BufferedImage image, RenderParameters rp, Scene s){
		ThreadBuilder tb = new ThreadBuilder(image,rp,s);
		(new Thread(tb)).start();
		
		boolean finished = false;
		while(!finished) {
			List<Pair<Vec2i,WorkerThread>> threads = tb.getThreads();
			synchronized(threads) {
				for(int i = threads.size()-1; i >= 0; i--) {
					Pair<Vec2i,WorkerThread> thread = threads.get(i);
					if(thread.b.finished) {
						threads.remove(i);
						image.setRGB(thread.a.x, thread.a.y, thread.b.getCol().toColor().getRGB());
					}
				}
				if(threads.size()==0 && tb.finished) finished = true;
			}
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static class WorkerThread implements Runnable{
		private Ray r;
		private Scene s;
		private int n;
		private boolean finished = false;
		
		private Vec3 col;
		
		public WorkerThread(Ray r, Scene s, int n) {
			this.r = r;
			this.s = s;
			this.n = n;
		}
		
		@Override
		public void run() {
			col = s.shoot(r, n);
			this.finished = true;
		}
		
		public Vec3 getCol() {
			return col;
		}
		
		public boolean isFinished() {
			return finished;
		}
	}
	
	private static class ThreadBuilder implements Runnable{
		
		private BufferedImage image;
		private RenderParameters rp;
		private Scene s;
		
		private boolean finished = false;
		
		private List<Pair<Vec2i,WorkerThread>> threads;
		
		public ThreadBuilder(BufferedImage image, RenderParameters rp, Scene s) {
			this.image = image;
			this.rp = rp;
			this.s = s;
			
			this.threads = new ArrayList<Pair<Vec2i,WorkerThread>>();
		}

		@Override
		public void run() {
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
					float ytheta = -yfov*0.5f+yfov*((float)y)/(height);
					
					float ycos = (float) Math.cos(ytheta);
					float ysin = (float) Math.sin(ytheta);
					
					Vec3 direction = rp.transform.times(new Vec3(xsin*ycos,ysin,xcos*ycos).asMatrix4d()).asVec3();
					Ray r = new Ray(pos,direction);
					
					WorkerThread wt = new WorkerThread(r,s,n);
					(new Thread(wt)).start();
					
					synchronized(threads) {
						threads.add(Pair.init(new Vec2i(x,y), wt));
					}
				}
				if(x%5==0) System.out.println((float)x/(float)width);
			}
			finished = true;
		}
		
		public List<Pair<Vec2i,WorkerThread>> getThreads(){
			return threads;
		}
		
		public boolean isFinished() {
			return finished;
		}
	}
}
