package lab2d.app;

import java.awt.event.*;
import static java.awt.event.KeyEvent.*;

import lab2d.ui.*;
import lab2d.user.actions.*;

public class Controls
{
	private int moveUp = VK_W, moveDown = VK_S, moveLeft = VK_A, moveRight = VK_D;
	private int lookUp = VK_UP, lookDown = VK_DOWN, lookLeft = VK_LEFT, lookRight = VK_RIGHT;
	private int pause = VK_ESCAPE;
	
	private double scrollZoomMultiplier = -2;

	
	public int moveUp(){return moveUp;}
	public int moveDown(){return moveDown;}
	public int moveLeft(){return moveLeft;}
	public int moveRight(){return moveRight;}
	
	public int lookUp(){return lookUp;}
	public int lookDown(){return lookDown;}
	public int lookLeft(){return lookLeft;}
	public int lookRight(){return lookRight;}
	
	
	
	public MouseAdapter getInGameMouseHandler(GameWindow window){return new InGameMouseHandler(window);}
	
	
	
	/**Handles mouse input when in-game. TODO does this logic really belong in this class?*/
	private class InGameMouseHandler extends MouseAdapter
	{
		private final GameWindow window;
		public InGameMouseHandler(GameWindow window){this.window = window;}
		
		@Override public void mouseWheelMoved(MouseWheelEvent event){window.applyAction(new ScrollAction(event.getWheelRotation() * scrollZoomMultiplier));}
		@Override public void mouseClicked(MouseEvent event){}
	}
}
