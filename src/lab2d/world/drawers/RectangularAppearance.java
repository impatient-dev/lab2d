package lab2d.world.drawers;

import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.geom.AffineTransform;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;

import lab2d.world.*;

/**Draws a rectangle.*/
public class RectangularAppearance implements Drawer
{
	private float extentW, extentH;
	private Paint pattern;
	
	/**@param extentWidth half the width of the rectangle */
	public RectangularAppearance(float extentWidth, float extentHeight, Material material)
	{
		this.extentW = extentWidth;
		this.extentH = extentHeight;
		this.pattern = material.pattern();
	}
	
	
	@Override public void paint(WorldThing thing, Graphics2D g)
	{
		AffineTransform saved = g.getTransform();
		
		Body body = thing.getBody();
		Vec2 pos = body.getPosition();
		
		g.translate(pos.x, pos.y);
		g.rotate(body.getAngle());
		g.scale(extentW, extentH);
		g.setPaint(pattern);
		g.fillRect(-1, -1, 2, 2);
		
		g.setTransform(saved);
	}
}
