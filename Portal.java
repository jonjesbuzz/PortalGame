import java.awt.*;
/**
 * A Portal is a GameObject which teleports a user from one Portal to another.<br>
 * In PortalGame (and subclasses), when the Player collides with a Portal, they exit the other.
 */
public class Portal extends GameObject
{
  /**
   * Default width.
   */
  private static final int WIDTH = 10;
  /**
   * Default height.
   */
  private static final int HEIGHT = 50;
  /**
   * Constructs a Portal.
   * 
   * When used with PortalGame, color must either be ORANGE or BLUE.
   * @param color The Portal color, ORANGE or BLUE.
   * @param x x-location
   * @param y y-location
   */
  public Portal(Color color, int x, int y)
  {
    setColor(color);
    setX(x);
    setY(y);
    setSize(WIDTH, HEIGHT);
  }
  /**
   * Acts by doing nothing.
   */
  public void act()
  {
  }
}