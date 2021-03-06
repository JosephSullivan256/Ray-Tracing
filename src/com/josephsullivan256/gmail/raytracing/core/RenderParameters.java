package com.josephsullivan256.gmail.raytracing.core;
import com.josephsullivan256.gmail.raytracing.math.Matrix;

public class RenderParameters {
	public final float fov;
	public final Matrix transform;
	public final int n;

	public RenderParameters(float fov, Matrix transform, int n) {
		this.fov = fov;
		this.transform = transform;
		this.n = n;
	}
}
