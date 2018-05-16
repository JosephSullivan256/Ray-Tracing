package com.josephsullivan256.gmail.raytracing.math;
import java.awt.Color;

public class Vec3 {
	public final float x,y,z;
	public Vec3(float x, float y, float z){ this.x = x; this.y = y; this.z = z; }
	public Vec3(float azimuth, float zenith){
		float xsin = (float)Math.sin(azimuth);
		float xcos = (float)Math.cos(azimuth);
		float ysin = (float)Math.sin(zenith);
		float ycos = (float)Math.cos(zenith);
		
		this.x = xsin*ycos;
		this.y = ysin;
		this.z = xcos*ycos;
	}
	public Vec3(float xsin, float xcos, float ysin, float ycos){
		this.x = xsin*ycos;
		this.y = ysin;
		this.z = xcos*ycos;
	}
	public float dot(Vec3 v) { return x*v.x+y*v.y+z*v.z; }
	public float mag2() { return dot(this); }
	public float mag() { return (float) Math.sqrt(mag2()); }
	public Vec3 scaledBy(float f) { return new Vec3(x*f, y*f, z*f); }
	public Vec3 normalized() { return scaledBy(1f/mag()); }
	public Vec3 plus(Vec3 v) { return new Vec3(x+v.x,y+v.y,z+v.z); }
	public Vec3 minus(Vec3 v) { return plus(v.scaledBy(-1f)); }
	public Color toColor(){ return new Color(Math.min(x,1f), Math.min(y,1f), Math.min(z,1f)); }
	public Vec3 times(Vec3 v) { return new Vec3(x*v.x,y*v.y,z*v.z); }
	public Vec3 cross(Vec3 v) { return new Vec3(y*v.z-v.y*z,x*v.z-v.x*z,x*v.y-y*v.x); }
	public Matrix asMatrix(){ return new Matrix(new float[][]{{x},{y},{z}}); }
	public Matrix asMatrix4p(){ return new Matrix(new float[][]{{x},{y},{z},{1}}); } //position
	public Matrix asMatrix4d(){ return new Matrix(new float[][]{{x},{y},{z},{0}}); } //direction
	
	@Override
	public String toString() { return "<"+x+","+y+","+z+">"; }
	@Override
	public boolean equals(Object obj){
		Vec3 v = (Vec3) obj;
		return v.x==x && v.y==y && v.z==z;
	}
	
	public static final Vec3 zero = new Vec3(0,0,0);
	public static Vec3 random() { return new Vec3((float)Math.random(),(float)Math.random(),(float)Math.random()); }
	
	public static final float epsilon = 0.00001f;
	public static final float PI = (float) Math.PI;
}
