package lab2d.world;

import java.util.*;

import org.jbox2d.common.Vec2;

import lab2d.world.natural.*;
import lab2d.world.test.*;

public class WorldGeneration
{
	private static final int BOXES = 10, TREES = 5;
	/**Generates a world with some random boxes.*/
	public static DWorld generateBasic(Random rand, Vec2 extents)
	{
		DWorld world = new DWorld(rand, extents);
		
		generateWorldBoundary(world);
		
		for(int c = 0; c < BOXES; c++)
		{
			Vec2 pos = world.randPos();
			Vec2 vel = new Vec2(world.randFloat(-5,5), world.randFloat(-5,5));
			float extentA = world.randFloat(0.5f, 1.5f), extentB = world.randFloat(.6f, 1.5f) * extentA;
			float angle = world.randFloat((float)Math.PI * 2);
			
			new Box(world, extentA, extentB, world.materials().boxTempMaterial, pos, angle, vel);
		}
		
		for(int c = 0; c < TREES; c++)
		{
			Vec2 pos = world.randPos();
			float angle = world.randFloat((float)Math.PI * 2);
			
			new Tree(world, pos, angle);
		}
		
		return world;
	}
	
	/**Generates 4 PermanentWalls to encase a DWorld.*/
	public static void generateWorldBoundary(DWorld world)
	{
		//Half how far away from the world edge the walls extend (half their thickness in 1 dimension)
		final float extentX = world.maxX() * 0.2f, extentY = world.maxY() * 0.2f;
		
		//east wall
		new PermanentWall(new Vec2(world.maxX() + extentX, 0), world.maxY() + extentY*2, extentX,
				(float)(Math.PI/2), world.materials().worldBoundaryMaterial, world);
		//north wall
		new PermanentWall(new Vec2(0, world.minY() - extentY), world.maxX() + extentX*2, extentY,
				0, world.materials().worldBoundaryMaterial, world);
		//west wall
		new PermanentWall(new Vec2(world.minX() - extentX, 0), world.maxY() + extentY*2, extentX,
				(float)(Math.PI/2), world.materials().worldBoundaryMaterial, world);
		//south wall
		new PermanentWall(new Vec2(0, world.maxY() + extentY), world.maxX() + extentX*2, extentY,
				0, world.materials().worldBoundaryMaterial, world);
	}
}
