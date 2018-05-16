package com.josephsullivan256.gmail.raytracing.Renderables;
import com.josephsullivan256.gmail.raytracing.core.Material;
import com.josephsullivan256.gmail.raytracing.core.Renderable;
import com.josephsullivan256.gmail.raytracing.core.Scene;
import com.josephsullivan256.gmail.raytracing.math.Ray;
import com.josephsullivan256.gmail.raytracing.math.Vec3;

public class RPlane implements Renderable {
	public final Vec3 pt, normal, col;
	private Material m;

	public RPlane(Vec3 pt, Vec3 normal, Vec3 col, Material m) {
		this.pt = pt;
		this.normal = normal.normalized();
		this.col = col;
		this.m = m;
	}

	@Override
	public float distance(Ray r) {
		Vec3 intersect = intersect(r);
		if (intersect == null)
			return Float.POSITIVE_INFINITY;
		return intersect.minus(r.pt).mag();
	}

	@Override
	public Vec3 shoot(Ray r, Scene s, int n) {
		Vec3 intersect = intersect(r);
		if (intersect != null) {
			Vec3 in = r.dir.scaledBy(-1f);
			Vec3 light = m.getColor(intersect, normal, in, s, n);
			return light.times(col);
		}
		return Vec3.zero;
	}

	public float intersectT(Ray r) {
		return (pt.dot(normal) - r.pt.dot(normal)) / r.dir.dot(normal);
	}

	public Vec3 intersect(Ray r) {
		float t = intersectT(r);
		if (t <= Vec3.epsilon)
			return null;
		return r.r(t);
	}

	public Ray reflect(Ray r) {
		Vec3 intersect = intersect(r);
		if (intersect == null)
			return null;
		Vec3 dir = r.dir.minus(normal.scaledBy(2 * r.dir.dot(normal)));
		return new Ray(intersect, dir);
	}
}
