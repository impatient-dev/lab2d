package lab2d.world;

import lab2d.math.Vec2l;
import org.jbox2d.common.Vec2;
/**An object that has an appearance, position, and direction.*/
public abstract class VisualThing
{
	private Vec2l pos;
	private int dir;
	protected DWorld world;
	
	public VisualThing(DWorld world, Vec2l pos, int angle){this.world = world; this.pos = pos; this.dir = angle;}
	
	/**Register this object with the provided world, putting itself into all necessary categories.
	 * The basic VisualObject only needs to go in the visual list; subclasses should override to set themselves up for e.g. physics updates.*/
	public void registerWith(DWorld world){}
	
	public Vec2l pos(){return pos;}
	public int dir(){return dir;}
	public void dir(int dir){this.dir = dir;}
	public DWorld world(){return world;}
	
	public abstract Drawer getDrawer();
	
	/**Destroy this object, possibly leaving debris or a corpse.*/
	public void destroy(){}
	/**Remove this object from the world without leaving anything.*/
	public void delete(){}
}
