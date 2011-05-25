import java.util.*;
/**
 * The most advanced PortalGame level.
 */
public class LevelTwo extends PortalGame
{
  /**
   * Sets up the walls.
   * @see PortalGame
   */
  public ArrayList<Wall> getWalls() 
  { 
    ArrayList<Wall> wallList = new ArrayList<Wall>(); 
    wallList.add(new Wall (720,25,0,378, true)); 
    wallList.add(new Wall (490,15,0,290, true)); 
    wallList.add(new HardWall (22,127,608,305, false)); 
    wallList.add(new Wall (65,20,182,174, false));
    wallList.add(new Wall (268,20,300,174, true));
    wallList.add(new Wall (15,169,232,10, false));
    wallList.add(new Wall (140,18,232,10, false));
    wallList.add(new Wall (16,105,356,10, false));
    wallList.add(new Wall (22,80,508,0, false));
    wallList.add(new Wall (116,20,508,150, false));
    wallList.add(new HardWall (27,216,693,0, false));
    wallList.add(new Wall (105,10,618,216, false));
    wallList.add(new Wall (4,60,619,156, false));
    wallList.add(new Wall (213,20,510,0, false));
    wallList.add(new GreenWall (60,20,630,196));
    placePlayer(20,310);
    
    
    return wallList;
  }
  /**
   * Sets up the Potatoes
   * @see PortalGame
   */
  public ArrayList<Potato> getPotatoes()
  {  
    ArrayList<Potato> potatoList = new ArrayList<Potato>(); 
    potatoList.add(new Potato (670, 322)); 
    potatoList.add(new Potato (670, 350)); 
    potatoList.add(new Potato (318, 142)); 
    potatoList.add(new Potato (650, 160)); 
    return potatoList;
  }
  /**
   * Main method.
   * 
   * Used for debugging.
   */
  public static void main(String[] args) {
    PortalGame p = new LevelTwo();
    p.setVisible(true);
    p.initComponents();  
  }
}