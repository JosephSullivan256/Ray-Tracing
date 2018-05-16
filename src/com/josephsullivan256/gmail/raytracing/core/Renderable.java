package com.josephsullivan256.gmail.raytracing.core;
import com.josephsullivan256.gmail.raytracing.math.Ray;
import com.josephsullivan256.gmail.raytracing.math.Vec3;

public interface Renderable {
	public float distance(Ray r);

	public Vec3 shoot(Ray r, Scene s, int n);
}
