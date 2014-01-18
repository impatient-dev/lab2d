package lab2d.world;

/**A grid of PlanCells.
 * The grid consists mostly of real cells, which cover the world (and might spill across the south and east borders a little),
 * with a border 1-thick of virtual cells outside the world.*/
public class PlantCellGrid
{
	private final float cellDimension;
	/**Accessed via [x][y] coordinates.*/
	private final PlantCell[][] grid;
	
	/**@param cellDimension the side length of each square cell.*/
	public PlantCellGrid(DWorld world, float cellDimension)
	{
		this.cellDimension = cellDimension;
		int columns = (int)Math.ceil(world.maxX() * 2 / cellDimension);
		int rows = (int)Math.ceil(world.maxY() * 2 / cellDimension);
		grid = new PlantCell[columns][rows];
		
		for(int x = 0; x < columns; x++)
		{
			for(int y = 0; y < rows; y++)
			{
				grid[x][y] = null;//TODO
			}
		}
	}
	
	/**Get the plant cell at the provided indices.*/
	public PlantCell getCell(int x, int y)
	{
		return grid[x][y];
	}
}
