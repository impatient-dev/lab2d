package lab2d.game;

/**Settings for a specific game.*/
public class GameSettings
{
	private int drawPeriodMS, physicsPeriodMS;
	
	public GameSettings(int renderPeriodMS, int physicsPeriodMS)
	{
		this.drawPeriodMS = renderPeriodMS;
		this.physicsPeriodMS = physicsPeriodMS;
	}
	public GameSettings()
	{
		this(1000 / 60, 1000 / 60);//60 FPS
	}
	
	/**Get the number of milliseconds that should separate the beginning of each render (draw, paint) operation.*/
	public int getRenderPeriodMS(){return drawPeriodMS;}
	/**Get the number of milliseconds that should separate the beginning of each physics update.*/
	public int getPhysicsPeriodMS(){return physicsPeriodMS;}
}
