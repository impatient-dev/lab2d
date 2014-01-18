package lab2d.world.drawers;

import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.geom.AffineTransform;

import lab2d.world.*;

import org.jbox2d.common.*;
import org.jbox2d.dynamics.Body;

/**Draws an isosceles triangle.*/
public class TriangularAppearance implements Drawer
{
	/**A triangle of halfWidth and halfHeight 1.*/
	private static final java.awt.Shape TRIANGLE_SHAPE = new java.awt.Polygon(new int[]{1,0,-1}, new int[]{0,2,0}, 3);
	
	private float halfWidth, halfHeight;
	private Paint pattern;
	
	/**@param extentWidth half the width of the base of the triangle
	 * @param extentHeight half the height of the triangle (base to top vertex)*/
	public TriangularAppearance(float extentWidth, float extentHeight, Material material)
	{
		this.halfWidth = extentWidth;
		this.halfHeight = extentHeight;
		this.pattern = material.pattern();
	}
	
	
	@Override public void paint(WorldThing thing, Graphics2D g)
	{
		AffineTransform saved = g.getTransform();
		
		Body body = thing.getBody();
		Vec2 pos = body.getPosition();
		
		g.translate(pos.x, pos.y);
		g.rotate(body.getAngle());
		g.scale(halfWidth, halfHeight);
		g.setPaint(pattern);
		g.fill(TRIANGLE_SHAPE);
		
		g.setTransform(saved);
	}
}
