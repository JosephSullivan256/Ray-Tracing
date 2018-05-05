
public class Ray {
	public final Vec3 pt, dir;
	public Ray(Vec3 pt, Vec3 dir) { this.pt = pt; this.dir = dir.normalized(); }
	public final Vec3 r(float t) { return pt.plus(dir.scaledBy(t)); }
}
