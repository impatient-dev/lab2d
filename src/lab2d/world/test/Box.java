package lab2d.world.test;

import org.jbox2d.collision.shapes.*;
import org.jbox2d.common.*;
import org.jbox2d.dynamics.*;

import lab2d.world.*;
import lab2d.world.drawers.RectangularAppearance;


/**A rectangular box that spins and moves.*/
public class Box implements WorldThing
{
	private final WorldThingBasicBehavior basicBehavior = new WorldThingBasicBehavior(this);
	private RectangularAppearance drawer;
	private Body body;
	
	/**Creates a new Box.
	 * @param extentWidth half the width of the box
	 * @param extentHeight half the height of the box*/
	public Box(DWorld world, float extentWidth, float extentHeight, Material material, Vec2 pos, float angle, Vec2 vel)
	{
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DYNAMIC;
		bodyDef.position = pos;
		bodyDef.linearVelocity = vel;
		bodyDef.angularVelocity = 0.3f;//XXX
		bodyDef.linearDamping = Materials.LINEAR_DAMPING;
		bodyDef.angularDamping = Materials.ANGULAR_DAMPING;
		bodyDef.angle = angle;
		bodyDef.userData = this;
		body = world.getPhysicsWorld().createBody(bodyDef);
		
		PolygonShape polygon = new PolygonShape();
		polygon.setAsBox(extentWidth,  extentHeight, new Vec2(0,0), 0);
		
		body.createFixture(polygon, material.density());
		
		this.drawer = new RectangularAppearance(extentWidth, extentHeight, material);
		
		world.addThing(this);
	}
	
	
	@Override public Drawer getDrawer(){return drawer;}
	@Override public Body getBody(){return body;}
	@Override public boolean exists(){return basicBehavior.exists();}
	@Override public void delete(DWorld world){basicBehavior.delete(world);}
	@Override public void destroy(DWorld world){delete(world);}
}
