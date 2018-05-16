package com.josephsullivan256.gmail.raytracing.pbr;

import java.util.ArrayList;
import java.util.List;

import com.josephsullivan256.gmail.raytracing.core.Material;
import com.josephsullivan256.gmail.raytracing.core.Scene;
import com.josephsullivan256.gmail.raytracing.math.Matrix;
import com.josephsullivan256.gmail.raytracing.math.Ray;
import com.josephsullivan256.gmail.raytracing.math.Vec3;
import com.josephsullivan256.gmail.raytracing.util.Pair;

public class LambertianMaterial implements Material {
	
	private static Vec3 defaultNormal = new Vec3(0,0,1);
	
	private List<Pair<Vec3, Float>> distrib;
	
	public LambertianMaterial(int azimuthRays, int zenithRays){
		distrib = new ArrayList<Pair<Vec3,Float>>(azimuthRays*zenithRays);
		float total = 0;
		for(int i = 0; i < azimuthRays; i++){
			float azimuth = i*(2f*Vec3.PI/((float)azimuthRays));
			float xcos = (float)Math.cos(azimuth);
			float xsin = (float)Math.sin(azimuth);
			for(int j = 0; j < zenithRays; j++){
				float zenith = j*(0.5f*Vec3.PI/((float)zenithRays+1));
				float ycos = (float)Math.cos(zenith);
				float ysin = (float)Math.sin(zenith);
				if(ycos > 0.1f) {
					distrib.add(Pair.init(new Vec3(xcos,xsin,ycos,ysin), ycos));
					total+=ycos;
				}
			}
		}
		
		for(int i = 0; i < distrib.size(); i++){
			Pair<Vec3, Float> dir = distrib.get(i);
			distrib.set(i, Pair.init(dir.a, dir.b/total));
		}
		/*float azimuth = 0;
		float zenith = 0;
		float xcos = (float)Math.cos(azimuth);
		float xsin = (float)Math.sin(azimuth);
		float ycos = (float)Math.cos(zenith);
		float ysin = (float)Math.sin(zenith);
		distrib = new ArrayList<Pair<Vec3,Float>>();
		distrib.add(Pair.init(new Vec3(xcos,xsin,ycos,ysin), 1f));*/
	}
	
	// in and normal must be normalized
	@Override
	public Vec3 getColor(Vec3 pt, Vec3 normal, Vec3 in, Scene s, int n) {
		Matrix net = Matrix.m33i;
		
		if(!normal.equals(defaultNormal)){
			Vec3 rotationAxis = normal.cross(defaultNormal);
			float mag1 = rotationAxis.mag();
			Matrix rotation = Matrix.rz33(normal.dot(defaultNormal), mag1);
			rotationAxis = rotationAxis.scaledBy(1f/mag1);
			Vec3 perpAxis = rotationAxis.cross(normal).normalized();
			Matrix p = Matrix.columnVectors(normal, perpAxis, rotationAxis);
			Matrix p1 = p.transpose(); //inverse b/c orthonormal matrix
			
			net = p.times(rotation.times(p1));
		}
		
		Vec3 color = Vec3.zero;
		for(Pair<Vec3, Float> dir: distrib){
			Vec3 vec = net.times(dir.a.asMatrix()).asVec3();
			color = color.plus(s.shoot(new Ray(pt,vec), n-1).scaledBy(dir.b));
		}
		return color;
	}

}
