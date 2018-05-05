
public class RSphere extends Sphere implements Renderable {

	private Material m;
	
	public RSphere(float rad, Vec3 pos, Vec3 col, Material m) {
		super(rad, pos, col);
		this.m = m;
	}
	
	@Override
	public float distance(Ray r) {
		Vec3 intersect = intersect(r);
		if(intersect == null) return Float.POSITIVE_INFINITY;
		return intersect(r).minus(r.pt).mag();
	}

	@Override
	public Vec3 shoot(Ray r, Scene s, int n) {
		Ray reflect = reflect(r);
		if(reflect != null){
			Vec3 normal = normal(r.pt);
			Vec3 dir = reflect.dir;
			Vec3 light = m.getColor(reflect.pt, normal, dir, s, n-1);
			return light.times(col);
		}
		return Vec3.zero;
	}
}
