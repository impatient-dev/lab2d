package lab2d.world;

import java.awt.Graphics2D;

/**Interface for classes that can draw something. An object's appearance is represented by a Drawer.*/
public interface Drawer
{
	/**Draws the provided WorldThing.
	 * Must not change the thing (e.g. movement, destruction).
	 * This method should assume the Graphics object provided has been transformed such that (0,0) is the origin of the world,
	 * (1,0) is directly east, (0,1) is directly north, and (0,1) and (0,0) are exactly 1 game-unit away from each other.
	 * If this method applies any transformations to the graphics object, it must undo them before returning.*/
	public void paint(WorldThing thing, Graphics2D g);
}
