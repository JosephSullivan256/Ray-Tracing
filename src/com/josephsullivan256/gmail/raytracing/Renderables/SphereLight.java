package com.josephsullivan256.gmail.raytracing.Renderables;
import com.josephsullivan256.gmail.raytracing.core.Renderable;
import com.josephsullivan256.gmail.raytracing.core.Scene;
import com.josephsullivan256.gmail.raytracing.math.Ray;
import com.josephsullivan256.gmail.raytracing.math.Sphere;
import com.josephsullivan256.gmail.raytracing.math.Vec3;

public class SphereLight extends Sphere implements Renderable {

	public SphereLight(float rad, Vec3 pos, Vec3 col) {
		super(rad, pos, col);
	}

	public float distance(Ray r) {
		Vec3 intersect = intersect(r);
		if (intersect == null)
			return Float.POSITIVE_INFINITY;
		return intersect(r).minus(r.pt).mag();
	}

	@Override
	public Vec3 shoot(Ray r, Scene s, int n) {
		return col;
	}
}
