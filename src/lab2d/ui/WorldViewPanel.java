package lab2d.ui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import org.jbox2d.common.Vec2;

import lab2d.math.*;
import static lab2d.math.Unit.*;
import lab2d.world.DWorld;

/**A JPanel that shows a view of a game world.
 * This panel mainly draws stuff; user-interaction logic should be in a containing component.*/
public class WorldViewPanel extends JPanel
{
	/**The farthest zoomed out one can go.*/
	private static final double ZOOM_MIN = 5.0 / METER;
	/**The farthest zoomed in one can go.*/
	private static final double ZOOM_MAX = 100.0 / METER;
	
	/**The farthest in and out the user can scroll.*/
	public static final double SCROLL_MIN = 0, SCROLL_MAX = 100;
	
	private DWorld world;
	private Vec2 pos;
	
	/**User scroll actions change this linearly.
	 * Linear changes in this value correspond to nonlinear changes to zoom.
	 * Required to always be valid and within the relevant limits.*/
	private double scroll = (SCROLL_MAX - SCROLL_MIN) * 0.25 + SCROLL_MIN;//start 25% zoomed out
	/**Pixels per game unit. Increases when you scroll in. Determined by the scroll value; see scrollToZoom.*/
	private double zoom = scrollToZoom(scroll);
	
	
	
	
	public WorldViewPanel(DWorld world, Vec2 pos)
	{
		this.world = world;
		this.pos = pos;
		setDoubleBuffered(true);
		requestFocusInWindow();
	}
	
	
	@Override public void paint(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g;
		g2.translate(pos.x + getWidth()/2, pos.y + getHeight()/2);
		g2.scale(zoom, zoom);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		world.paint(g2);
	}
	
	
	/**Get the current scroll level (controls zoom).*/
	public double getScroll(){return scroll;}
	/**Set the current scroll level, which will consequently set the zoom level.
	 * Scroll values that are too low or too high will be "rounded" to the closest valid value.*/
	public void setScroll(double scroll)
	{
		if(scroll < SCROLL_MIN)
			this.scroll = SCROLL_MIN;
		else if(scroll > SCROLL_MAX)
			this.scroll = SCROLL_MAX;
		else
			this.scroll = scroll;
		this.zoom = scrollToZoom(this.scroll);
	}
	
	
	/**Maps convenient scroll units to useful zoom units.*/
	private double scrollToZoom(double scroll)
	{
		double percentage = (scroll - SCROLL_MIN) / (SCROLL_MAX - SCROLL_MIN);//percentage is in [0,1]
//		percentage = Math.sqrt(percentage);//still in [0,1], but scaled so zooming at closer zooms is more responsive
		return  percentage * (ZOOM_MAX - ZOOM_MIN) + ZOOM_MIN;
	}
}
