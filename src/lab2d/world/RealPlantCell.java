package lab2d.world;

import java.util.*;

/**A plant cell that can actually contain plants.
 * Contains a Collection of all the Plants in this cell.
 * Also contains counts of the different types of plants in this cell, which are updated by the add/remove methods.*/
public class RealPlantCell extends PlantCell
{
	private final Set<Plant> plants = new HashSet<>();
	private int trees = 0, treeLeaves = 0;
			
			
	public RealPlantCell(int coordinateX, int coordinateY, PlantCellGrid grid)
	{
		super(coordinateX, coordinateY, grid);
		// TODO Auto-generated constructor stub
	}
	
	private PlantCell westCell(){return grid.getCell(coordinateX + 1, coordinateY);}
	private PlantCell northCell(){return grid.getCell(coordinateX, coordinateY - 1);}
	private PlantCell eastCell(){return grid.getCell(coordinateX - 1, coordinateY);}
}
