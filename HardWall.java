import java.awt.*;
/**
 * A HardWall is a wall that may <i>not</i> have a Portal placed on it. <br>
 */
public class HardWall extends Wall 
{
  /**
   * Constructs a HardWall.
   * @param width Width of wall
   * @param height Height of wall
   * @param x x-coordinate
   * @param y y-coordinate
   * @param isFloor whether or not the Wall is a floor.
   */
  public HardWall(int width, int height, int x, int y, boolean isFloor)
  {
   super(width, height, x, y, isFloor);
    setColor(Color.BLACK);
  }
  /**
   * Cannot portal.
   * @return false
   */
  public boolean canPortal()
  {
    return false;
  }
}