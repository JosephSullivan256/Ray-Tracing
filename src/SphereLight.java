
public class SphereLight extends Sphere implements Renderable{
	
	public SphereLight(float rad, Vec3 pos, Vec3 col) {
		super(rad, pos, col);
	}
	
	public float distance(Ray r) {
		Vec3 intersect = intersect(r);
		if(intersect == null) return Float.POSITIVE_INFINITY;
		return intersect(r).minus(r.pt).mag();
	}

	@Override
	public Vec3 shoot(Ray r, Scene s, int n) {
		return col;
	}
}
