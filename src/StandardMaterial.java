
public class StandardMaterial implements Material {
	
	private int raysCount;
	private Vec3 weights;
	
	public StandardMaterial(int raysCount, Vec3 weights){
		this.raysCount = raysCount;
		this.weights = weights;
	}
	
	@Override
	public Vec3 getColor(Vec3 pt, Vec3 normal, Vec3 dir, Scene s, int n) {
		Vec3 perpendicular = normal.cross(dir);
		
		Vec3 light = Vec3.zero;
		for(int i = 0; i < raysCount; i++){
			Vec3 ran = weights.times(Vec3.random());
			Vec3 nDir = normal.scaledBy(ran.x).plus(dir.scaledBy(ran.y)).plus(perpendicular.scaledBy(ran.z)).scaledBy(1f/(ran.x+ran.y+ran.z));
			Ray nReflect = new Ray(pt,nDir);
			light = light.plus(s.shoot(nReflect, n));
		}
		
		return light.scaledBy(1f/(float)raysCount);
	}

}
