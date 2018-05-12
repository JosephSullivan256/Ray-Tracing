import java.awt.Color;

public class Vec3 {
	public final float x,y,z;
	public Vec3(float x, float y, float z){ this.x = x; this.y = y; this.z = z; }
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
	
	public static final Vec3 zero = new Vec3(0,0,0);
	public static Vec3 random() { return new Vec3((float)Math.random(),(float)Math.random(),(float)Math.random()); }
	
	public static final float epsilon = 0.00001f;
}
