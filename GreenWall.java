import java.awt.*;
/**
 * This wall will end the level.
 * It is not Portalable
 */
public class GreenWall extends Wall 
{
  /**
   * Makes a GreenWall.
   * 
   * It is not a floor since the game is going to end when it is touched.<br />
   * The wall color is green. :P
   * @param width Wall width
   * @param height Wall height
   * @param x x-coordinate
   * @param y y-coordinate.
   */
  public GreenWall(int width, int height, int x, int y)
  {
   super(width, height, x, y, false);
    setColor(Color.GREEN);
  }
  /**
   * The wall can NEVER have a Portal on it.
   * @return false
   */
  public boolean canPortal()
  {
    return false;
  }
}