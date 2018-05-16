package com.josephsullivan256.gmail.raytracing.core;
import com.josephsullivan256.gmail.raytracing.math.Vec3;

public interface Material {
	public Vec3 getColor(Vec3 pt, Vec3 normal, Vec3 in, Scene s, int n);
}
