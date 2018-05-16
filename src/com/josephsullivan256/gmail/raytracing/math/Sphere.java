package com.josephsullivan256.gmail.raytracing.math;

public class Sphere {

	public final float rad;
	public final Vec3 pos, col;

	public Sphere(float rad, Vec3 pos, Vec3 col) {
		this.rad = rad;
		this.pos = pos;
		this.col = col;
	}

	public Vec3 intersect(Ray r) {
		float t = getMidIntersectT(r);
		if (t < 0)
			return null;
		Vec3 r1 = r.r(t);
		float pd2 = r1.minus(pos).mag2();
		if (pd2 > rad * rad) {
			// System.out.println("HI");
			return null;
		}
		float a = (float) Math.sqrt(rad * rad - pd2);
		return r.r(t - a);
	}

	public float getMidIntersectT(Ray r) {
		return (pos.dot(r.dir) - r.pt.dot(r.dir)) / r.dir.mag2();
	}

	public float perpendicularDistance2(Ray r) {
		float t = getMidIntersectT(r);
		if (t <= Vec3.epsilon)
			return Float.POSITIVE_INFINITY;
		Vec3 r1 = r.r(t);
		return r1.minus(pos).mag2();
	}

	public float perpendicularDistance(Ray r) {
		return (float) Math.sqrt(perpendicularDistance2(r));
	}

	public Vec3 normal(Vec3 surfacePt) {
		return surfacePt.minus(pos).normalized();
	}

	public Ray reflect(Ray r) {
		Vec3 intersect = intersect(r);
		if (intersect == null)
			return null;
		Vec3 n = intersect.minus(pos).normalized();
		Vec3 dir = r.dir.minus(n.scaledBy(2 * r.dir.dot(n)));
		return new Ray(intersect, dir);
	}
}
