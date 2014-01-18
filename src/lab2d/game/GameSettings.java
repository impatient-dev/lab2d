package lab2d.game;

/**Settings for a specific game.*/
public class GameSettings
{
	private int drawPeriodMS, physicsPeriodMS, plantPeriodMS;
	
	public GameSettings(int renderPeriodMS, int physicsPeriodMS, int plantPeriodMS)
	{
		this.drawPeriodMS = renderPeriodMS;
		this.physicsPeriodMS = physicsPeriodMS;
		this.plantPeriodMS = plantPeriodMS;
	}
	public GameSettings()
	{
		this(1000 / 60, 1000 / 60, 1000 * 1);//60 FPS, 60 FPS, 1 s
	}
	
	/**Get the number of milliseconds that should separate the beginning of each render (draw, paint) operation.*/
	public int getRenderPeriodMS(){return drawPeriodMS;}
	/**Get the number of milliseconds that should separate the beginning of each physics update.*/
	public int getPhysicsPeriodMS(){return physicsPeriodMS;}
	/**Get the number of milliseconds that should separate the beginning of each plant update.*/
	public int getPlantPeriodMS(){return plantPeriodMS;}
}
