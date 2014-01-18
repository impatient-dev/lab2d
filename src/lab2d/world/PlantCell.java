package lab2d.world;

/**Since we're lazy, the world is divided into square PlantCells.
 * Plants only interact with plants in their own cell, plus plants in adjacent cells.
 * Abstract because we need different classes for real plant cells (inside the world)
 * and virtual plant cells (outside the world, make life easier for the real ones).*/
public abstract class PlantCell
{
	/**The world's upper-left cell is (0,0). Cells outside the world's borders may have negative coordinates.*/
	public final int coordinateX, coordinateY;
	protected final PlantCellGrid grid;
	
	public PlantCell(int coordinateX, int coordinateY, PlantCellGrid grid)
	{
		this.coordinateX = coordinateX;
		this.coordinateY = coordinateY;
		this.grid = grid;
	}
}
