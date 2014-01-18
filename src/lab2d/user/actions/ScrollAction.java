package lab2d.user.actions;

import lab2d.ui.GameWindow;
import lab2d.user.UserAction;

public class ScrollAction extends UserAction
{
	private final double amount;
	public ScrollAction(double amount){this.amount = amount;}
	
	@Override public void doAction(GameWindow window)
	{
		window.scroll(amount);
	}

}
