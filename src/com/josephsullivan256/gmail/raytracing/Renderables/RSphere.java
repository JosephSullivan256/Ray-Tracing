package com.josephsullivan256.gmail.raytracing.Renderables;
import com.josephsullivan256.gmail.raytracing.core.Material;
import com.josephsullivan256.gmail.raytracing.core.Renderable;
import com.josephsullivan256.gmail.raytracing.core.Scene;
import com.josephsullivan256.gmail.raytracing.math.Ray;
import com.josephsullivan256.gmail.raytracing.math.Sphere;
import com.josephsullivan256.gmail.raytracing.math.Vec3;

public class RSphere extends Sphere implements Renderable {

	private Material m;

	public RSphere(float rad, Vec3 pos, Vec3 col, Material m) {
		super(rad, pos, col);
		this.m = m;
	}

	@Override
	public float distance(Ray r) {
		Vec3 intersect = intersect(r);
		if (intersect == null)
			return Float.POSITIVE_INFINITY;
		return intersect(r).minus(r.pt).mag();
	}

	@Override
	public Vec3 shoot(Ray r, Scene s, int n) {
		Vec3 intersect = this.intersect(r);
		if (intersect != null) {
			Vec3 normal = normal(intersect);
			Vec3 in = r.dir.scaledBy(-1f);
			Vec3 light = m.getColor(intersect, normal, in, s, n);
			return light.times(col);
		}
		return Vec3.zero;
	}
}
