import java.util.*;
/**
 * Another level for PortalGame, created by overriding the getWalls(), getPotatoes(), and main(String[]) methods.
 */
public class LevelThree extends PortalGame
{
  /**
   * Sets up the walls.
   * @see PortalGame
   */
  public ArrayList<Wall> getWalls() 
  { 
    ArrayList<Wall> wallList = new ArrayList<Wall>(); 
    wallList.add(new Wall (320,25,0,141, true)); 
    wallList.add(new Wall (150,29,303,177,true)); 
    wallList.add(new Wall (100,12,520,282,true)); 
    wallList.add(new Wall (50,12,325,322,true)); 
    wallList.add(new Wall (50,12,142,322,true)); 
    wallList.add(new Wall (50,12,0,322,true)); 
    wallList.add(new Wall (430,15,0,0,true)); 
    wallList.add(new HardWall(25,50,427,0,false)); 
    wallList.add(new HardWall(25,66,427,111,false)); 
    wallList.add(new Wall(86,15,540 ,0,false)); 
    wallList.add(new HardWall(270,15,448,0, false));
    wallList.add(new RedWall( getFieldWidth(),20 ,0,390)); 
    wallList.add(new RedWall( 15 ,75 ,287,66)); 
    wallList.add(new GreenWall( 15,85 ,5,245));   
    placePlayer(38,80);
    return wallList; 
  }
  /**
   * Sets up the Potatoes
   * @see PortalGame
   */
  public ArrayList<Potato> getPotatoes()
  {
    ArrayList<Potato> potatoList = new ArrayList<Potato>();
    potatoList.add(new Potato(50, 50));
    potatoList.add(new Potato(575, 150));
    potatoList.add(new Potato(335, 310));
    potatoList.add(new Potato(155, 310));
    potatoList.add(new Potato(25, 270));
    potatoList.add(new Potato(545, 270));
    
    return potatoList;
  }
 /**
   * Main method.
   * 
   * Used for debugging.
   */
  public static void main(String[] args) {
    PortalGame p = new LevelThree();
    p.setVisible(true);
    p.initComponents();  
  }
}