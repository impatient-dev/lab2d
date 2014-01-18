package lab2d.world;

import java.awt.Paint;

/**Specifies the characteristics of a material that objects can be made from.*/
public interface Material
{
	public float density();
	public float friction();
	public float restitution();
	
	public Paint pattern();
}
