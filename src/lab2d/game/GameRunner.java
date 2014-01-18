package lab2d.game;

import static lab2d.math.Util.*;

import java.util.*;

import lab2d.ui.GameWindow;
import lab2d.world.*;

/**Controls the running of a game--the timing of updates, etc.
 * Windows, servers, etc. register with this to receive events/signals (I don't have a name for them yet).*/
public class GameRunner
{
	private DWorld world;
	private GameSettings settings;
	private Set<GameWindow> gameWindows = new HashSet<>();
	private Worker worker = new Worker();
	private Thread workerThread = null;
	
	private volatile long gameTime = 0;
	private volatile long nextRenderTime = 0, nextPhysicalTime = 0;
	private final float renderPeriod, physicsPeriod;
	
	
	/**Runs a game for the provided world with the provided settings.*/
	public GameRunner(DWorld world, GameSettings settings)
	{
		this.world = world;
		this.settings = settings;
		renderPeriod = settings.getRenderPeriodMS() / 1000.0f;
		physicsPeriod = settings.getPhysicsPeriodMS() / 1000.0f;
	}
	
	
	public void launch()
	{
		if(workerThread != null)
			throw new IllegalStateException("You can't launch a game when it's already running!");
		
		for(GameWindow window : gameWindows)
			window.setVisible(true);//TODO get rid of this
		worker.reset();
		workerThread = new Thread(worker);
		workerThread.start();
	}
	
	public void stop(){if(worker != null) workerThread.interrupt();}
	
	
	
	public DWorld getWorld(){return world;}
	
	/**Register a window to receive a repaint() call whenever render should take place.*/
	public void registerGameWindow(GameWindow window){gameWindows.add(window);}
	public void deregisterGameWindow(GameWindow window){gameWindows.remove(window);}
	
	
	
	
	/**Does the actual running of the game in a separate thread.*/
	private class Worker implements Runnable
	{
		private long lastTickStart = System.currentTimeMillis();
		
		public void run()
		{
			while(!Thread.interrupted())
			{
				long tickStart = System.currentTimeMillis();
				gameTime += tickStart - lastTickStart;
				
				if(nextRenderTime <= gameTime)
				{
					for(GameWindow window : gameWindows)
						window.repaint();
					nextRenderTime += settings.getRenderPeriodMS();
				}
				
				if(nextPhysicalTime <= gameTime)
				{
					world.updatePhysics(physicsPeriod);
					nextPhysicalTime += settings.getPhysicsPeriodMS();
				}
				
				
				lastTickStart = tickStart;
				long toSleep = min(nextRenderTime, nextPhysicalTime) - gameTime;
				
				if(toSleep > 0)
					try{Thread.sleep(toSleep);}catch(InterruptedException ex){}
			}
		}
		
		
		/**Tells the Worker to start counting up from right now, rather than trying to "catch up" after a pause,
		 * immediately after the worker is created, etc.*/
		public void reset()
		{
			lastTickStart = System.currentTimeMillis();
		}
	}
}