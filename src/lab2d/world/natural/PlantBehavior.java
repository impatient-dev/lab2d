package lab2d.world.natural;

import lab2d.world.*;

/***/
public interface PlantBehavior
{
	/**Whether or not this object actually has any plant behavior.
	 * If false, you shouldn't bother sending plant updates to this object.*/
	public abstract void hasPlantBehavior();
	
	public abstract void plantUpdate(DWorld world);
}
