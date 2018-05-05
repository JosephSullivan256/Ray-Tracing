
public class Scene {
	
	private Renderable[] rs;
	private SphereLight[] ls;
	
	public Scene(Renderable[] rs, SphereLight[] ls){
		this.rs = rs;
		this.ls = ls;
	}
	
	public Vec3 shoot(Ray r, int n){
		if(n==0) return Vec3.zero;
		Renderable r0 = closestRenderable(r);
		SphereLight l0 = closestSphereLight(r);
		if(r0 != null && l0 != null){
			float d1 = r0.distance(r);
			float d2 = l0.distance(r);
			if(d1 < d2) return r0.shoot(r, this, n);;
			return l0.col(r);
		} else if (r0 != null){
			return r0.shoot(r, this, n);
		} else if (l0 != null){
			return l0.col(r);
		}
		return new Vec3(0.529f, 0.808f, 0.980f);
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
	
	private SphereLight closestSphereLight(Ray r){
		if(ls.length == 0) return null;
		SphereLight closest = ls[0];
		float distance = closest.distance(r);
		for(SphereLight l0: ls){
			float tempDistance = l0.distance(r);
			if(tempDistance < distance){
				closest = l0;
				distance = tempDistance;
			}
		}
		if(distance == Float.POSITIVE_INFINITY) return null;
		return closest;
	}
	
	public SphereLight[] getSphereLights(){
		return ls;
	}
}
