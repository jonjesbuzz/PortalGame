import java.awt.Color;
/**
 * A Potato is a scoring object which determines the percentage of completion.
 */
public class Potato extends GameObject
{
  /**
   * Width of Potato
   */
  private static final int WIDTH = 5;
  /**
   * Height of Potato
   */
  private static final int HEIGHT = 5;
  /**
   * Constructs a Potato at the (x,y) coordinate location.
   * @param x x-location
   * @param y y-location
   */
  public Potato(int x, int y)
  {
    super();
    setSize(WIDTH, HEIGHT);
    setX(x);
    setY(y);
    setColor(new Color(150, 75, 0));
  }
  /**
   * A potato acts by doing nothing.
   */
  public void act()
  {
  }
}