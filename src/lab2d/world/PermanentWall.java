package lab2d.world;

import lab2d.world.drawers.RectangularAppearance;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;

/**A static, unchanging, invincible wall.*/
public class PermanentWall implements WorldThing
{
	private WorldThingBasicBehavior basicBehavior = new WorldThingBasicBehavior(this);
	private RectangularAppearance drawer;
	private final Body body;
	
	public PermanentWall(Vec2 pos, float halfWidth, float halfThickness, float angle, Material material, DWorld world)//TODO material
	{
		drawer = new RectangularAppearance(halfWidth, halfThickness, material);
		
		BodyDef bd = new BodyDef();
		bd.type = BodyType.STATIC;
		bd.allowSleep = true;
		bd.angle = angle;
		bd.position = pos;
		bd.userData = this;
		body = world.getPhysicsWorld().createBody(bd);
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(halfWidth, halfThickness, new Vec2(0,0), 0);
		
		FixtureDef fd = new FixtureDef();
		fd.density = material.density();
		fd.friction = material.friction();
		fd.restitution = material.restitution();
		fd.shape = shape;
		body.createFixture(fd);
		
		world.addThing(this);
	}
	
	@Override public Drawer getDrawer(){return drawer;}
	@Override public Body getBody(){return body;}
	@Override public boolean exists(){return basicBehavior.exists();}
	@Override public void delete(DWorld world){basicBehavior.delete(world);}
	@Override public void destroy(DWorld world){basicBehavior.destroy(world);}

}
