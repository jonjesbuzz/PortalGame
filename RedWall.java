import java.awt.*;
/**
 * A RedWall is a Wall that cannot be Portalled and will reset the player.
 */
public class RedWall extends Wall 
{
  /**
   * Constructs a RedWall.  A RedWall cannot be a floor because that will reset the Player
   * @param width Width of wall
   * @param height Height of wall
   * @param x x-coordinate
   * @param y y-coordinate
   */
  public RedWall(int width, int height, int x, int y)
  {
   super(width, height, x, y, false);
    setColor(Color.RED);
  }
  /**
   * Since a RedWall will reset the player, a Portal cannot be in contact with a RedWall (this would reset you).
   * @return false
   */
  public boolean canPortal()
  {
    return false;
  }
}