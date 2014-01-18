package lab2d.app;

import java.util.*;

import org.jbox2d.common.Vec2;

import lab2d.game.*;
import lab2d.ui.*;
import lab2d.world.WorldGeneration;

public class Application
{
	private AppSettings settings = new AppSettings();
	private Collection<GameRunner> games = new HashSet<>();
	private Collection<GameWindow> windows = new HashSet<>();
	
	
	public Application()
	{
		GameRunner game = new GameRunner(WorldGeneration.generateBasic(new Random(), new Vec2(15,15)), new GameSettings());
		GameWindow window = new GameWindow(this, game.getWorld(), new Vec2(0,0));
		game.registerGameWindow(window);
		game.launch();
	}
	
	
	public static void main(String[] args)
	{
		new Application();
	}
	
	public AppSettings settings(){return settings;}
}
