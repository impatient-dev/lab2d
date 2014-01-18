package lab2d.world.natural;

/**Contains settings for a species of trees.
 * Will eventually be augmented to allow evolution.
 * Immutable.*/
public class TreeSpecies
{
	/**Since this class doesn't do much yet, we'll provide a single instance.*/
	public static final TreeSpecies INSTANCE = new TreeSpecies();
	
	
	/**The maximum number of leaves a tree of this species can have.*/
	public final int maxLeaves = 10;
	/**The minimum number of leaves a tree of this species can start with.
	 TODO flesh out the exact meaning of this variable.*/
	public final int initialLeaves = 4;
	
	/**Currently, every tree of a species has the same size.
	 * Extent is like a radius--half the length of a side (tree trunks are square).*/
	public final float extent = 0.7f;
	
	/**Half the width of a triangular leaf.*/
	public final float leafHalfWidth = 0.8f;
	/**Half the height of a triangular leaf.*/
	public final float leafHalfHeight = 0.8f;
	
	/**The farthest away from a tree a leaf can be.*/
	public final float leafMaxDistance = 2f;
	
	/**Array of angles for leaves to appear at.
	 * Used to prevent bunching up and make trees look more orderly.
	 * This array may have more or fewer entries than maxLeaves--use a modulus operator.*/
	public final float[] leafAngles = {1f, 3f, 4.5f, 0.3f, 5.2f, 3.9f, 2f, 2.7f, 5.7f, 6.2f, 4.8f};
	
	
	/**How much nutrition each leaf provides.*/
	public final float nutritionPerLeaf = 1f;
	/**The farthest away any other Plant can be from this Tree while still affecting its nutrition.*/
	public final float nutritionRadius = 10f;
	/**The nutrition effect (penalty) of another tree that is nutritionRadius away.*/
	public final float maxAdjacentTreeNutrition = -1.2f * maxLeaves * nutritionPerLeaf;
	
	/**The amount of health a tree starts with.*/
	public final float healthInitial = 10f;
	/**The amount of nutrition a tree "produces" by itself.
	 * This is negative--it costs nutrition to maintain a tree, which must be offset by positive nutrition from leaves.*/
	public final float baseNutrition = -2 * nutritionPerLeaf;
	/**A tree's health change during each plantUpdate is its nutrition times the period of the update times this.
	 * Larger values speed up growth.*/
	public final float nutritionToHealthMultiplier = 1f;
	/**How much health it costs to spawn a new leaf.*/
	public final float spawnLeafHealthCost = 5f;
}
