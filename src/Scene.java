
public class Scene {
	
	private Renderable[] rs;
	
	public Scene(Renderable[] rs){
		this.rs = rs;
	}
	
	public Vec3 shoot(Ray r, int n){
		if(n==0) return Vec3.zero;
		Renderable r0 = closestRenderable(r);
		if(r0 != null) return r0.shoot(r, this, n);
		return new Vec3(0.529f, 0.808f, 0.980f);
		//return new Vec3(1,0.9f,0.9f);
	}
	
	private Renderable closestRenderable(Ray r){
		if(rs.length == 0) return null;
		Renderable closest = rs[0];
		float distance = closest.distance(r);
		for(Renderable r0: rs){
			float tempDistance = r0.distance(r);
			if(tempDistance < distance){
				closest = r0;
				distance = tempDistance;
			}
		}
		if(distance == Float.POSITIVE_INFINITY) return null;
		return closest;
	}
}
