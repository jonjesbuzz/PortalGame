import java.awt.*;
/**
 * Player.java <br />
 * The Player class maintains the player's movement and her location and size.
 */
public class Player extends GameObject
{
  /**
   * The default player width.  The Player width should never be changed.
   */
  public static final int WIDTH = 10;
  /**
   * The default player height.  The Player height should never be changed.
   */
  public static final int HEIGHT = 50;
  /**
   * The gravity constant.
   */
  private static final int GRAVITY = 6;
  private int currGravity; //The current gravity
  private int xVelocity;
  /**
   * Constructs a Player with the default width, height and Orange color.
   */
  public Player()
  {
    super();
    setSize(WIDTH, HEIGHT);
    setColor(Color.ORANGE);
  }
  /**
   * Moves the player.
   * 
   * Moves player on the x-axis using the x-velocity and the y-axis using the current gravity.<br />
   * Gravity is, according to PortalGame, stopped when Player is in contact with a Wall whose isFloor() returns true.
   */
  public void act()
  {
    setX(getX() + xVelocity);
    setY(getY() + currGravity);
  }
  /**
   * Makes the player fall or not fall depending on the boolean parameter.
   * @param shouldFall Whether or not the player should fall.
   */
  public void fall(boolean shouldFall)
  {
    if (shouldFall)
      currGravity = GRAVITY;
    else
      currGravity = 0;
  }
  /**
   * Sets the x-velocity movement.
   * @param xVelocityIn movement factor.
   */
  public void setXVelocity(int xVelocityIn)
  {
    xVelocity = xVelocityIn;
  }
  /**
   * Gets the x-velocity movement at the current moment.
   * @return the x-velocity movement.
   */
  public int getXVelocity()
  {
    return xVelocity;
  }
}
