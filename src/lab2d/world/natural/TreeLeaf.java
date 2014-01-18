package lab2d.world.natural;

import lab2d.world.*;
import lab2d.world.drawers.TriangularAppearance;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.*;
import org.jbox2d.dynamics.*;

public class TreeLeaf implements WorldThing
{
	private Tree tree;
	private TriangularAppearance drawer;
	private Body body;
	private final WorldThingBasicBehavior basicBehavior = new WorldThingBasicBehavior(this);
	
	
	/**Creates a leaf at the provided location with the provided angle (rotates the tip counterclockwise from .*/
	public TreeLeaf(Tree tree, Vec2 pos, float angle, DWorld world)
	{
		this.tree = tree;
		
		//create the appearance
		final float halfWidth = tree.getSpecies().leafHalfWidth, halfHeight = tree.getSpecies().leafHalfHeight;
		drawer = new TriangularAppearance(halfWidth, halfHeight, world.materials().leafMaterial);
		
		//create the physical Body
		
		BodyDef bd = new BodyDef();
		bd.type = BodyType.STATIC;
		bd.position = pos;
		bd.angle = angle;
		bd.userData = this;
		body = world.getPhysicsWorld().createBody(bd);
		
		PolygonShape shape = new PolygonShape();
		shape.set(new Vec2[]{new Vec2(halfWidth, 0), new Vec2(0, 2 * halfHeight), new Vec2(-halfWidth, 0)},  3);
		body.createFixture(shape, world.materials().leafMaterial.density());
		
		
		world.addThing(this);
	}
	
	@Override public Body getBody(){return body;}
	@Override public Drawer getDrawer(){return drawer;}
	@Override public boolean exists(){return basicBehavior.exists();}
	@Override public void delete(DWorld world)
	{
		basicBehavior.delete(world);
		tree.notifyLeafDead(this);
	}
	@Override public void destroy(DWorld world){delete(world);}
}
