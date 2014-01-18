package lab2d.world;

/**Implements some basic WorldThing behavior for you so you don't have to repeat it in each class
 * or inherit from a central class.
 * To use this, just instantiate one for your object and forward the relevant method calls.
 * 
 * If you're lazy enough, you can even just copy-paste these lines of code:
	private final WorldThingBasicBehavior basicBehavior = new WorldThingBasicBehavior(this);
 * and
	@Override public boolean exists(){return basicBehavior.exists();}
	@Override public void delete(DWorld world){basicBehavior.delete(world);}
	@Override public void destroy(DWorld world){delete(world);}
	*/
public class WorldThingBasicBehavior
{
	private final WorldThing thing;
	private boolean exists = true;
	
	public WorldThingBasicBehavior(WorldThing thing)
	{
		this.thing = thing;
	}
	
	/**Returns true until delete() or destroy() is called.*/
	public boolean exists()
	{
		return exists;
	}

	/**Implements WorldThing.delete() by removing the object from the world and destroying its body.*/
	public void delete(DWorld world)
	{
		world.removeThing(thing);
		world.getPhysicsWorld().destroyBody(thing.getBody());
		exists = false;
	}
	
	/**Does the same thing as delete().*/
	public void destroy(DWorld world)
	{
		delete(world);
	}
	
}
