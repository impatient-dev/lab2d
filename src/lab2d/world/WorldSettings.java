package lab2d.world;

/**Settings for a DWorld.*/
public class WorldSettings
{
	private Materials materials = new Materials();
	public Materials materials(){return materials;}
	
	/**How many milliseconds should elapse between each update on a plant. Plant updates are fairly infrequent.*/
	public int plantUpdatePeriodMS(){return 5 * 1000;}
}
