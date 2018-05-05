
public class SphereLight extends Sphere{
	
	public SphereLight(float rad, Vec3 pos, Vec3 col) {
		super(rad, pos, col);
	}
	
	public float distance(Ray r) {
		Vec3 intersect = intersect(r);
		if(intersect == null) return Float.POSITIVE_INFINITY;
		return intersect(r).minus(r.pt).mag();
	}
	
	public Vec3 col(Ray r){
		return col;//.scaledBy(1f/perpendicularDistance2(r));
	}
}
