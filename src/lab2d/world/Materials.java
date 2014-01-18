package lab2d.world;

import java.awt.Color;

/**A set of Materials for a particular DWorld.*/
public class Materials
{
	/**Damping for dynamic bodies.*/
	public static final float LINEAR_DAMPING = 0f, ANGULAR_DAMPING = 0f;
	
	public final Material worldBoundaryMaterial = new CustomMaterial(1f, 1f, 0f, new Color(0f, .4f, 1f));
	public final Material boxTempMaterial = new CustomMaterial(.3f, .8f, .4f, Color.red);
	public final Material woodMaterial = new CustomMaterial(.5f, .3f, .8f, new Color(.7f, .3f, .05f));
	public final Material leafMaterial = new CustomMaterial(.1f, .1f, .5f, new Color(.05f, .8f, .1f));
}
