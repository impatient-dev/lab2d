package lab2d.world;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.*;

import lab2d.math.*;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;

/**A virtual world. A World contains lists of various types of objects, such as Plants and basic WorldThings.
 * Each type has corresponding add and remove methods; a WorldThing that is also a Plant must be manually added via 2 methods.*/
public class DWorld
{
	/*DIMENSIONS*/
	
	/**Maximum x- and y-values. (NOT x- and y-distance across the world.)*/
	private Vec2 extents;
	public float minX(){return -extents.x;}
	public float maxX(){return extents.x;}
	public float minY(){return -extents.y;}
	public float maxY(){return extents.y;}
	public float max(Component component){return component == Component.X ? maxX() : maxY();}
	public float min(Component component){return component == Component.X ? minX() : minY();}
	public float xrange(){return 2*extents.x;}
	public float yrange(){return 2*extents.y;}
	
	
	/*RANDOM*/
	
	private Random randSrc;
	
	/**Generates a random number from min to max, inclusive, using the world's "official" random source.*/
	public long randLong(long min, long max){return Math.abs(randSrc.nextLong()) % (max - min + 1) + min;}//java modulo operator screws up negative numbers
	/**Generates a random number from 0 to max, inclusive, using the world's "official" random source.*/
	public long randLong(long max){return randLong(0,max);}
	/**Generates a random vector with each component in the range [0, maxComponent] (inclusive).*/
	public Vec2l randVec2l(long maxComponent){return new Vec2l(randLong(maxComponent), randLong(maxComponent));}
	/**Generates a random number from min to max, inclusive, using the world's "official" random source.*/
	public int randInt(int min, int max){return randSrc.nextInt(max - min + 1) + min;}
	/**Generates a random number from 0 to max, inclusive, using the world's "official" random source.*/
	public int randInt(int max){return randInt(0, max);}
	public float randFloat(float min, float max){return randSrc.nextFloat() * (max - min) + min;}
	public float randFloat(float max){return randFloat(0, max);}
	public float randAngle(){return randFloat((float)Math.PI * 2);}
	public Vec2 randVec2(float maxComponent){return new Vec2(randFloat(maxComponent), randFloat(maxComponent));}
	/**Generates a random position within the world's boundaries. Every square meter is equally likely.*/
	public Vec2 randPos(){return new Vec2(randFloat(minX(), maxX()), randFloat(minY(), maxY()));}
	/**Generates a random position within radius of the provided position.
	 * All radii are equally likely.*/
	public Vec2 randPosWithin(Vec2 originalPos, float maxRadius)
	{
		float angle = randAngle();
		float distance = randFloat(maxRadius);
		return new Vec2(originalPos.x + distance * (float)Math.cos(angle), originalPos.y + distance * (float)Math.sin(angle));
	}
	/**Generates a random position that is between minRadius and maxRadius away from the originalPos.*/
	public Vec2 randPosWithin(Vec2 originalPos, float minRadius, float maxRadius)
	{
		float angle = randAngle();
		float distance = randFloat(minRadius, maxRadius);
		return new Vec2(originalPos.x + distance * (float)Math.cos(angle), originalPos.y + distance * (float)Math.sin(angle));
	}
	
	
	/*PHYSICS*/
	
	/**Number of velocity iterations for JBox2D's World.step().
	 * Higher is slower but more accurate. Recommended: 8.*/
	private static final int JBOX2D_ITERATIONS_VELOCITY = 8;
	/**Number of position iterations for JBox2D's World.step().
	 * Higher is slower but more accurate. Recommended:3.*/
	private static final int JBOX2D_ITERATIONS_POSITION = 3;
	
	private World physicsWorld = new World(new Vec2(0,0));
	public World getPhysicsWorld(){return physicsWorld;}
	
	
	/*SPECIAL WORLD OBJECTS*/
	
	private WorldSettings settings;
	public WorldSettings settings(){return settings;}
	
	private Materials materials;
	public Materials materials(){return materials;}
	
	
	
	/**CONSTRUCTOR*/
	
	/**@param extents half the width and half the height of the world. (Like radii.)*/
	public DWorld(Random randSrc, Vec2 extents, WorldSettings settings)
	{
		this.randSrc = randSrc;
		this.extents = extents.clone();
		this.settings = settings;
		this.materials = settings.materials();
	}
	
	
	/*INTERNAL COLLECTIONS*/
	
	private Collection<WorldThing> things = new HashSet<>();
	public void addThing(WorldThing thing){things.add(thing);}
	public void removeThing(WorldThing thing){things.remove(thing);}
	
	private Collection<Plant> plants = new HashSet<>();
	/**Plants to be removed after the next plantUpdate.*/
	private List<Plant> removePlants = new ArrayList<>();
	public void addPlant(Plant plant){plants.add(plant);}
	/**Schedules a Plant to be removed from the Plant collection after the next plantUpdate.
	 * (Removing plants during the update would cause problems.)*/
	public void removePlant(Plant plant){removePlants.add(plant);}
	
	
	/*OPERATIONS*/
	
	/**Draws all VisualThings registered with this world.
	 * The provided Graphics2D object must meet the requirements in Drawer.paint(...),
	 * and additionally should have a clip region specified that covers approximately the region the user should see.*/
	public synchronized void paint(Graphics2D g)
	{
		g.setColor(Color.black);
		g.fillRect((int)minX(), (int)minY(), (int)maxX() * 2, (int)maxY() * 2);
		
		for(WorldThing thing : things)
			thing.getDrawer().paint(thing, g);
	}
	
	/**Does a physicsUpdate(...) for each PhysicsThing registered with this world.*/
	public synchronized void updatePhysics(float timeChange)
	{
		physicsWorld.step(timeChange, JBOX2D_ITERATIONS_VELOCITY, JBOX2D_ITERATIONS_POSITION);
	}
	
	/**Does a plantUpdate() for each Plant registered with this world,
	 * then removes any plants that were sent to removePlant().*/
	public synchronized void updatePlants(float timeChange)
	{
		for(Plant plant : plants)
			plant.plantUpdate(this, timeChange);
		
		if(!removePlants.isEmpty())
		{
			for(Plant plant : removePlants)
				plants.remove(plant);
			removePlants.clear();
		}
	}
}
