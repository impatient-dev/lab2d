package lab2d.world;

import java.awt.Paint;

/**Immutable Material that lets you explicitly specify all values.*/
public class CustomMaterial implements Material
{
	private final float density, restitution, friction;
	private final Paint pattern;
	
	public CustomMaterial(float density, float restitution, float friction, Paint pattern)
	{
		this.density = density;
		this.restitution = restitution;
		this.friction = friction;
		this.pattern = pattern;
	}
	
	@Override public float density(){return density;}
	@Override public float restitution(){return restitution;}
	@Override public float friction(){return friction;}
	
	@Override public Paint pattern(){return pattern;}
}
