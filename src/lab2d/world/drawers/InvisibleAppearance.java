package lab2d.world.drawers;

import java.awt.Graphics2D;

import lab2d.world.*;

/**Doesn't draw anything.*/
public class InvisibleAppearance implements Drawer
{
	@Override public void paint(WorldThing thing, Graphics2D g){}
	
	@Override public String toString(){return "invisible";}
}
