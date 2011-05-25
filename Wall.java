import java.awt.*;
/**
 * A wall that can be Portalled.
 */
public class Wall extends GameObject implements Portalable
{
  private boolean isFloor;
  /**
   * Constructor.
   * 
   * Constructs a Wall object with the specified width, height, and x,y location.<br />
   * By default it is not a floor.
   * @param width Width of wall
   * @param height Height of wall
   * @param x x-coordinate
   * @param y y-coordinate
   *//*
  public Wall(int width, int height, int x, int y)
  {
    setX(x);
    setY(y);
    setSize(width, height);
    setColor(Color.LIGHT_GRAY);
    isFloor = false;
  }*/
    /**
   * Constructor.
   * 
   * Constructs a Wall object with the specified width, height, and x,y location, and floor content.
   * @param width Width of wall
   * @param height Height of wall
   * @param x x-coordinate
   * @param y y-coordinate
   * @param isFloor whether or not the Wall is a floor.
   */
  public Wall(int width, int height, int x, int y, boolean isFloor)
  {
    setX(x);
    setY(y);
    setSize(width, height);
    setColor(Color.LIGHT_GRAY);
    this.isFloor = isFloor;
  }
  /**
   * Implementing the Portalable interface.
   * 
   * Returns true; you can portal this wall.
   */
  public boolean canPortal()
  {
    return true;
  }
  /**
   * Default act method.
   * 
   * A Wall does absolutely nothing when it acts.
   */
  public void act()
  {
  }
  /**
   * Returns whether or not the wall is a floor.
   */
  public boolean isFloor()
  {
    return isFloor;
  }
}
      
    
                