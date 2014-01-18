package lab2d.world;

import org.jbox2d.dynamics.Body;

/**The most basic interface for objects that can exist in a DWorld.
 * A WorldThing corresponds to exactly one JBox2D Body, but any number of fixtures.
 * (This implies that every WorldThing has a position and orientation, but not necessarily a physical body.)
 * A WorldThing's Body must have the WorldThing as its userData (fixtures don't need to use userData).
 * A WorldThing's constructor should immediately create its physical Body
 * and add the object to the world via all relevant world.add* methods. (Plants should call addPlant in addition to addThing, etc.)*/
public interface WorldThing
{
	/**Get the visual representation of this object.*/
	public abstract Drawer getDrawer();
	
	/**Get the physical body corresponding to this Thing.
	 * Note: it is better create and use overrideable methods on the VisualObject
	 * than to directly modify its Body.*/
	public abstract Body getBody();
	
	/**Completely remove and deregister the thing from the DWorld,
	 * without any frills like spawning debris or a corpse.*/
//	public abstract void delete(DWorld world);
	
	/**Like delete(), but can spawn debris/a corpse/an explosion/etc. and do other logical things
	 * that should happen when this type of object is destroyed.*/
//	public abstract void destroy(DWorld world);
}
