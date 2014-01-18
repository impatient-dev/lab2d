package lab2d.world.natural;

import java.util.*;

import org.jbox2d.callbacks.QueryCallback;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;

import lab2d.world.*;
import lab2d.world.drawers.RectangularAppearance;

/**A central wooden trunk that spawns leaves and TODO fruit around it.*/
public class Tree implements Plant
{
	private TreeSpecies species = TreeSpecies.INSTANCE;
	private RectangularAppearance drawer;
	private Body body;
	private List<TreeLeaf> leaves = new ArrayList<>();
	
	/*NUTRITION*/
	
	/**The biological health of this tree.*/
	private float health;
	/**The current nutrition level of this tree.*/
	private float nutrition;
	/**If false, nutrition needs to be calculated before it is next used.*/
	private boolean nutritionValid = false;
	/**Lists nearby Plants and their nutrition effects.*/
	private Map<Plant,Float> nearbyPlantNutritionEffects = new HashMap<>();
	
	/**Maximal constructor*/
	public Tree(DWorld world, Vec2 pos, float angle)
	{
		//initialize nutrition info
		health = species.healthInitial;
		
		
		drawer = new RectangularAppearance(species.extent, species.extent, world.materials().woodMaterial);
		
		BodyDef bd = new BodyDef();
		bd.type = BodyType.STATIC;
		bd.angle = angle;
		bd.position = pos;
		bd.userData = this;
		body = world.getPhysicsWorld().createBody(bd);
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(species.extent, species.extent, new Vec2(0,0), 0f);
		body.createFixture(shape, world.materials().woodMaterial.density());
		
		for(int c = 0; c < species.initialLeaves; c++)
			spawnNewLeaf(world);
		health = species.healthInitial;
		
		world.addThing(this);
		world.addPlant(this);
	}
	
	@Override public Drawer getDrawer(){return drawer;}
	@Override public Body getBody(){return body;}
	public TreeSpecies getSpecies(){return species;}
	
	@Override public void plantUpdate(DWorld world, float timeChange)
	{
		//TODO
	}
	
	public void delete(DWorld world)
	{
		world.removeThing(this);
		world.removePlant(this);
	}
	
	public void destroy(DWorld world){delete(world);}
	
	@Override public void kill(DWorld world){destroy(world);}
	
	
	/**Translate an angle relative to this tree (counterclockwise from east)
	 * to the angle a leaf at that tree-angle must be rotated to point outward.*/
	private float treeToLeafAngle(float angle)
	{
		return angle - (float)Math.PI / 2;
	}
	/**Inverse of treeToLeafAngle*/
	private float leafToTreeAngle(float angle)
	{
		return angle + (float)Math.PI / 2;
	}
	
	
	/**Attempt to create a new leaf at a random location. Does not affect health or anything.
	 * Returns whether or not the attempt succeeds--it will fail if it happens to pick an angle too close to another leaf's angle.*/
	private boolean spawnNewLeaf(DWorld world)
	{
		float leafAngle = world.randAngle();
		
		//see if the angle is too close to any existing angles
		for(TreeLeaf leaf : leaves)
			if(Math.abs(leafAngle - leaf.getBody().getAngle()) < 0.3f)
				return false;
		
		//create the new leaf
		float treeAngle = leafToTreeAngle(leafAngle);
		float distance = species.leafMaxDistance;//world.randFloat(species.extent, species.leafMaxDistance);
		Vec2 pos = new Vec2(body.getPosition().x + distance * (float)Math.cos(treeAngle), body.getPosition().y + distance * (float)Math.sin(treeAngle));
		TreeLeaf leaf = new TreeLeaf(this, pos, leafAngle, world);
		world.addThing(leaf);
		leaves.add(leaf);
		return true;
	}
	
	
	/**Determine the nutrition effect of a Plant.
	 * If the Plant has an effect, that Plant and its effect will be put in nearbyPlantNutritionEffects.
	 * The caller is responsible for ensuring the argument is a non-null Plant that is not this plant,
	 * and also not a plant that has already been provided to this method
	 * (I suppose it would be OK if the Plant had been removed and now we want to re-add it, but why would that happen?)*/
	public void logNutritionEffectOfPlant(Plant otherPlant, Vec2 myPos)
	{
		if(otherPlant == this || nearbyPlantNutritionEffects.containsKey(otherPlant))
			throw new RuntimeException();//TODO temporary check
		
		/**The nutrition effect of another Tree is interpolated according to the square of its distance.
		 * 0 distance corresponds to max nutrition effect; max distance^2 corresponds to 0 effect.*/
		if(otherPlant instanceof Tree)
		{
			Vec2 theirPos = otherPlant.getBody().getPosition();
			float distance2 = theirPos.sub(myPos).lengthSquared();//square distance
			float maxDistance2 = species.nutritionRadius * species.nutritionRadius;
			if(distance2 >= maxDistance2)
				return;
			float effect = (maxDistance2 - distance2) / maxDistance2 * species.maxAdjacentTreeNutrition;
			nearbyPlantNutritionEffects.put(otherPlant, effect);
		}
		
		//else must be some kind of Plant we don't care about
	}
	
	
	/**Class that receives and processes nearby plants from an AABB search,
	 * populating the lists in this Tree.
	 * Assumes that this Plant is not in the results (so do the query before adding this plant).*/
	private class MyQueryHandler implements QueryCallback
	{
		private final Vec2 myPos;
		public MyQueryHandler(Vec2 myPos){this.myPos = myPos;}
		
		@Override public boolean reportFixture(Fixture fixture)
		{
			Object obj = fixture.getBody().getUserData();
			
			if(obj != null && obj instanceof Plant)
				logNutritionEffectOfPlant((Plant)obj, myPos);
			
			return true;//keep going
		}
		
	}
}
