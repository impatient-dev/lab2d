package lab2d.world;

/**Interface for plants. Plants must be stationary (physically static) and are not allowed to move.
 * Plants get a special update at a very low frequency.*/
public interface Plant extends WorldThing
{
	/**Update the plant-y aspects of an object, such as its growth or reproduction.
	 * This is a fairly infrequent update.
	 * @param timeChange the amount of time between plant updates.
	 * This will normally be constant throughout a game.*/
	public void plantUpdate(DWorld world, float timeChange);
	
	/**Make a living thing wither and die in place.*/
	public void kill(DWorld world);
}
