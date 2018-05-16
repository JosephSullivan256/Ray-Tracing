package com.josephsullivan256.gmail.raytracing;
import com.josephsullivan256.gmail.raytracing.core.Material;
import com.josephsullivan256.gmail.raytracing.core.Scene;
import com.josephsullivan256.gmail.raytracing.math.Ray;
import com.josephsullivan256.gmail.raytracing.math.Vec3;

public class SimpleMaterial implements Material {

	private int raysCount;
	private float[] weights;

	public SimpleMaterial(int raysCount, float[] weights) {
		this.raysCount = raysCount;
		this.weights = weights;
	}

	@Override
	public Vec3 getColor(Vec3 pt, Vec3 normal, Vec3 in, Scene s, int n) {
		Vec3 refl = in.scaledBy(-1f).minus(normal.scaledBy(-2*normal.dot(in)));
		
		Vec3 perpendicular = normal.cross(refl);

		Vec3 light = Vec3.zero;
		for (int i = 0; i < raysCount; i++) {
			float r1 = (float) Math.random() * weights[0];
			float r2 = (float) Math.random() * weights[1];
			float r3 = ((float) Math.random() - 0.5f) * 2 * weights[2];
			Vec3 nDir = normal.scaledBy(r1).plus(refl.scaledBy(r2))
					.scaledBy(1f / (r1 + r2)).plus(perpendicular.scaledBy(r3));
			Ray nReflect = new Ray(pt, nDir);
			light = light.plus(s.shoot(nReflect, n-1));
		}

		return light.scaledBy(1f / (float) raysCount);
	}

	/*
	 * @Override public Vec3 getColor(Vec3 pt, Vec3 normal, Vec3 refl, Vec3
	 * refr, Scene s, int n) {
	 * 
	 * }
	 */
}
