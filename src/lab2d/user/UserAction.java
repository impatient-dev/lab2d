package lab2d.user;

import lab2d.ui.*;

/**Abstract parent class for actions taken by the user that affect a game window (but not necessarily the game state).*/
public abstract class UserAction
{
	public abstract void doAction(GameWindow window);
}
