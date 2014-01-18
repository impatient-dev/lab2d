package lab2d.ui;

import java.awt.Dimension;
import java.awt.event.MouseListener;

import javax.swing.*;

import org.jbox2d.common.Vec2;

import lab2d.app.*;
import lab2d.math.*;
import lab2d.user.*;
import lab2d.world.*;


/**Window that allows the user to play/watch a game.*/
public class GameWindow extends JFrame
{
	private final Application app;
	private DWorld world;
	
	/*SCHEMATIC*/
	
	{
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(1000, 1000));
		setResizable(true);
	}
	
	
	private WorldViewPanel worldViewPanel;
	
	public GameWindow(Application app, DWorld world, Vec2 viewPos)
	{
		this.app = app;
		this.world = world;
		worldViewPanel = new WorldViewPanel(world, viewPos);
		getContentPane().add(worldViewPanel);
		
		pack();
		setLocationRelativeTo(null);
		java.awt.event.MouseAdapter mouseListener = app.settings().controls().getInGameMouseHandler(this);
		worldViewPanel.addMouseListener(mouseListener);
		worldViewPanel.addMouseWheelListener(mouseListener);
	}
	
	/**Change the current zoom by amount. The max and min are constants in WorldViewPanel.*/
	public void scroll(double amount){worldViewPanel.setScroll(worldViewPanel.getScroll() + amount);}
	
	
	public void applyAction(UserAction action){action.doAction(this);}
}
