
public class StandardMaterial implements Material {
	
	private int raysCount;
	private float[] weights;
	
	public StandardMaterial(int raysCount, float[] weights){
		this.raysCount = raysCount;
		this.weights = weights;
	}
	
	@Override
	public Vec3 getColor(Vec3 pt, Vec3 normal, Vec3 refl, Vec3 refr, Scene s, int n) {
		Vec3 perpendicular = normal.cross(refl);
		
		Vec3 light = Vec3.zero;
		for(int i = 0; i < raysCount; i++){
			float r1 = (float) Math.random()*weights[0];
			float r2 = (float) Math.random()*weights[1];
			float r3 = ((float) Math.random()-0.5f)*2*weights[2];
			float r4 = (float) Math.random()*weights[3];
			Vec3 nDir = normal.scaledBy(r1).plus(refl.scaledBy(r2)).plus(refr.scaledBy(r4)).scaledBy(1f/(r1+r2+r4)).plus(perpendicular.scaledBy(r3));
			Ray nReflect = new Ray(pt,nDir);
			light = light.plus(s.shoot(nReflect, n));
		}
		
		return light.scaledBy(1f/(float)raysCount);
	}

}
