
public interface Renderable {
	public float distance(Ray r);
	public Vec3 shoot(Ray r, Scene s, int n);
}
