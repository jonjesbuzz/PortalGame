/**
 * PortalTrail is an object which determines where the portal will be placed.<br />
 * Created May 12, 2011.
 */
import java.awt.*;

public class PortalTrail extends GameObject
{
  private static final int WIDTH = 10;
  private static final int HEIGHT = 10;
  private int destinationX;
  private int destinationY;
  private int slope; //Either change in Y per X or vice versa.
  private int xFactor; //Used to determine which way to shoot PortalTrail
  private int yFactor; //Used to determine which way to shoot PortalTrail (if change in X per y)
  private boolean slopeLessThanZero;
  
  /**
   * The constructor to set up a PortalTrail object.
   * 
   * The destination is used as where the mouse is clicked inside PortalGame.<br />
   * The PortalTrail starts at (startX, startY) and goes through (destinationX, destinationY).
   * @param color The PortalTrail's color
   * @param destinationX X-Coordinate of the destination of this PortalTrail
   * @param destinationY Y-Coordinate of the destination of this PortalTrail
   * @param startX X-Coordinate of where the PortalTrail should begin.
   * @param startY Y-Coordnate of where the PortalTrail should end.
   */
  public PortalTrail(Color color, int destinationX, int destinationY, int startX, int startY)
  {
    setColor(color);
    this.destinationX = destinationX;
    this.destinationY = destinationY;
    setX(startX);
    setY(startY);
    if (destinationX - getX() != 0)
      slope = (destinationY - getY()) / (destinationX - getX());
    else
      slope = (destinationY - getY()) / (destinationX - getX() + 1);
    if (slope == 0)
    {
      if (destinationY - getY() != 0)
        slope = (destinationX - getX()) / (destinationY - getY());
      else
        slope = (destinationX - getX()) / (destinationY - getY() + 1);
      slopeLessThanZero = true;
    }
    if (destinationX > startX)
      xFactor = 1;
    else
      xFactor = -1;
    if (destinationY < startY)
      yFactor = -1;
    else
      yFactor = 1;
    setSize(WIDTH, HEIGHT);
    //System.out.println("Made PortalTrail"); //Used for debugging.
  }
  /**
   * A PortalTrail acts by going in a straight line through the (destinationX, destinationY) point.
   */
  public void act()
  {
    if (!slopeLessThanZero)
    {
      if (xFactor == 1)
        setY(getY() + slope);
      else
        setY(getY() - slope);
      setX(getX() + xFactor);
    }
    else if (slopeLessThanZero)
    {
      if (yFactor == 1)
        setX(getX() + slope);
      else
        setX(getX() - slope);
      setY(getY() + yFactor);
    }
  }
  /**
   * Returns the slope of this line.
   */
  public int getSlope()
  {
    return slope;
  }
  /**
   * Returns whether or not the slope is less than zero.
   * 
   * In other words, if the slope is less than zero, then the slope becomes change in X for every y.
   */
  public boolean slopeLessThanZero()
  {
    return slopeLessThanZero;
  }
}