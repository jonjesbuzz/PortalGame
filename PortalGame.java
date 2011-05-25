import java.awt.event.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
/*
 * This code is protected under the Gnu General Public License (Copyleft), 2005 by
 * IBM and the Computer Science Teachers of America organization. It may be freely
 * modified and redistributed under educational fair use.
 */
/**
 * The driver for Portal.
 * This class includes a default level, but other levels can be created by
 * subclassing this class and overriding the <code>getWalls()</code> and <code>main(String[] args)</code> methods.
 */

public class PortalGame extends Game implements MouseListener, KeyListener{
  // Add any state variables or object references here
  private final static int PLAYER_MOVE = 1;
  private final static int PLAYER_JUMP = 60;
  private final static int MOUSE_HEIGHT = 50;
  
  private int playerStartX;
  private int playerStartY;
  
  private PortalTrail trail;
  private Player chell;
  private Portal bluePortal;
  private Portal orangePortal;
  private ArrayList<Wall> walls;
  private ArrayList<Potato> potatoes;
  private int collectedPotatoes;
  private int totalPotatoes;
  //Cheating console
  private String cheatCode;
  private Scanner keyboard;
  //BG Music
  private MusicPlayer bgMusic;
  /**
   * Fill in this method with code that tells the game what to do
   * before actual play begins
   *
   * Sets up the Portal Playing Field.  Do not override this method to build new levels.
   */
  public void setup()
  {
    System.out.println("Loading...");
    setTitle("Loading..."); 
    bgMusic = new MusicPlayer();
    bgMusic.play();
    dispMessage("GLaDOS: Welcome!", "<html>  Welcome to the Aperture Science Enrichment Center.\n<br>" + 
                "  Today you will be accomplishing a puzzle using the Aperture Science Handheld Portal Device.\n<br>" + 
                "  Use the mouse button to place a Portal.  Left-click for blue, right-click for orange.\n<br>" +
                "  Use D/Left Arrow to move left and the A/Right Arrow to move right. Use Shift/Space to jump.\n<br>" +
                "  Reach the green exit wall without touching the red walls.\n<br>" +
                "  Good luck.  As part of a required test protocol, we will not monitor your test chamber.\n<br>" + 
                "  At the end of the testing procedure, there will be cake.", 600, 150);
    setSize(720, 450);
    setBackground(Color.GRAY);
    cheatCode = "";
    keyboard = new Scanner(System.in);
    addKeyListener(this);
    addMouseListener(this);
    chell = new Player();
    add(chell);
    this.walls = getWalls();
    this.potatoes = getPotatoes();
    for (int i = 0; i < walls.size(); i++)
    {
      this.add(walls.get(i));
    }
    for (int i = 0; i < potatoes.size(); i++)
    {
      this.add(potatoes.get(i));
    }
    totalPotatoes = potatoes.size();
    setTitle("Thinking with Portals!  Collected Potatoes: " + collectedPotatoes + "/" + totalPotatoes);
    System.out.println("Done! - Begin play");
  }
  
  /**
   * Fill in this method with code that tells the playing field what to do
   * from one moment to the next
   */
  public void act()
  {
    repaint(); //For the portals.
    int chellX = chell.getX();
    int chellY = chell.getY();
    if (chellX < 0 || chellX > getFieldWidth() || chellY < -5 || chellY > getFieldHeight() + 5)
      resetGame(); //There is some room for jumping in Y.
    if (chell.collides(walls.get(walls.size() - 1)))
    {
      
      stopGame();
      int completed = ((int)(((double)collectedPotatoes / totalPotatoes) * 100));
      dispMessage("GLaDOS: You win! ", "<html>Percent Completed: " + completed + "%<br />Unbelievable, you [SUBJECT NAME HERE] must be the pride of [SUBJECT HOMETOWN HERE].<br>Your cake will be shipped in 5 millenia.<br>" + 
                  "You will be missed.  Goodbye.</html>", 300, 150);
    }
    for (int i = 0; i < potatoes.size(); i++)
    {
      if (chell.collides(potatoes.get(i)))
      {
        collectedPotatoes++;
        remove(potatoes.get(i));
        potatoes.remove(potatoes.get(i));
        setTitle("Thinking with Portals!  Collected Potatoes: " + collectedPotatoes + "/" + totalPotatoes);
      }
    }
    for (int i = 0; i < walls.size(); i++)
    {
      if (walls.get(i).collides(chell) && i != 0 && !(walls.get(i) instanceof GreenWall) && !walls.get(i).isFloor())
      {
        if (chell.getX() > walls.get(i).getX())
          chell.setX(chell.getX() + 5);
        else
          chell.setX(chell.getX() - 5);
        if (walls.get(i) instanceof RedWall)
        {
          String [] lines = new String [6];
          lines[0]= "<html>The Enrichment Center promises to always provide a safe testing environment.<br> In dangerous testing environments, <br>the Enrichment Center promises to always provide useful advice.</html>";
          lines[1]= "<html>Spectacular. You appear to understand how a portal<br> affects forward momentum, or to be more precise,<br> how it does not.</html>";
          lines[2]= "<html>Have I lied to you? [pause] I mean, in this room?<br> Trust me, leave that thing alone.</html>";
          lines[3]= "<html>Two plus two is f-f-f-f... ten.<br> [distorted sounds] ...in base four;<br> I'm fine!</html>";
          lines[4]= "<html>Keep doing whatever it is you think you're doing.<br> Killing you and giving you good advice aren't mutually exclusive.<br> The rocket really is the way to go.</html>";
          lines[5]= "<html>The cake is a lie.</html>";
          
          String message = lines[(int)(Math.random()*lines.length)];
          dispMessage("GLaDOS: Sorry ", message, 300, 150);
          resetGame();
        }
      }
      if (trail != null)
      {
        if (walls.get(i).collides(trail))
        {
          if (walls.get(i).canPortal())
          {
            Color portalColor = trail.getColor();
            if (portalColor.equals(Color.BLUE))
            {
              if (bluePortal != null)              {
                remove(bluePortal);
                bluePortal = null;
              }
              bluePortal = new Portal(portalColor, trail.getX(), trail.getY());
              add(bluePortal);
            }
            else
            {
              if (orangePortal != null)
              {
                remove(orangePortal);
                orangePortal = null;
              }
              orangePortal = new Portal(portalColor, trail.getX(), trail.getY());
              add(orangePortal);
            }
          }
          remove(trail);
        }
        
      }
      
    }
    for (int i = 0; i < walls.size(); i++)
    {
      if (chell.collides(walls.get(i)) && walls.get(i).isFloor())
      {
        chell.fall(false);
        break;
      }
      if (!chell.collides(walls.get(0)))
        chell.fall(true);
    }
    
    if (orangePortal != null && bluePortal != null)
    {
      if (chell.collides(orangePortal))
      {
        chell.setX(bluePortal.getX() + 15);
        chell.setY(bluePortal.getY() + 55);
      }
      if (chell.collides(bluePortal))
      {
        chell.setX(orangePortal.getX() + 15);
        chell.setY(orangePortal.getY() + 55);
      }
      
      
    }
  }
  
  // Add any additional methods here
  /**
   * Sends the PortalTrail object flying across the screen.
   * @param mouseEvent The mouse click location.
   */
  public void mouseReleased(MouseEvent mouseEvent)
  {
    //BUTTON1 = Left-click, BUTTON3 = right click.
    //  System.out.println("Portal placement location selected.\n" + "x: " + mouseEvent.getX() + "\ty: " + mouseEvent.getY() + "\tButton: " + mouseEvent.getButton());
    Color trailColor = null;
    if (mouseEvent.getButton() == MouseEvent.BUTTON1)
      trailColor = Color.BLUE;
    else if (mouseEvent.getButton() == MouseEvent.BUTTON3)
      trailColor = Color.ORANGE;
    trail = new PortalTrail(trailColor, mouseEvent.getX(), mouseEvent.getY() - MOUSE_HEIGHT, chell.getX(), chell.getY());
    add(trail);
  }
  /**
   * Use this method to process the key that was pressed.
   * 
   * This method moves Chell depending on the KeyEvent's keypress.
   * @param keyEvent the key pressed
   */
  public void keyPressed(KeyEvent keyEvent)
  {
    char pressedKey = keyEvent.getKeyChar();
    int pressedKeyVal = keyEvent.getKeyCode();
    if (pressedKey == 'd' || pressedKey == 'D' || pressedKeyVal == KeyEvent.VK_RIGHT)
      chell.setXVelocity(PLAYER_MOVE);
    if (pressedKey == 'a' || pressedKey == 'A' || pressedKeyVal == KeyEvent.VK_LEFT)
      chell.setXVelocity(-PLAYER_MOVE);
    if (pressedKey == ' ' || pressedKeyVal == KeyEvent.VK_SHIFT)
      chell.setY(chell.getY() - PLAYER_JUMP);
    if (pressedKeyVal == KeyEvent.VK_HOME) //Moves you home! :D
      resetGame();
  }
  /* Keyboard */
  /**
   * Supplies a console-based cheats interface... for the heck of it.
   * 
   * Pressing <code>`</code> will activate the console's cheat window.
   * Override to make your own cheats.
   * @param keyEvent The key typed.
   */
  public void keyTyped(KeyEvent keyEvent)
  {
    if (keyEvent.getKeyChar() == '`')
    {
      System.out.print("Enter cheat code: ");
      cheatCode = keyboard.nextLine();
    }
    
    if (cheatCode.equalsIgnoreCase("chell.x"))
      System.out.println("Chell's X: " + chell.getX());
    if (cheatCode.equalsIgnoreCase("chell.y"))
      System.out.println("Chell's Y: " + chell.getY());
    if (cheatCode.equalsIgnoreCase("stop!!"))
      System.exit(0);
    if (cheatCode.equalsIgnoreCase("chell.c"))
      System.out.println("x:"+ chell.getX() + "\ty:" + chell.getY());
    if (cheatCode.equalsIgnoreCase("reset"))
    {
      resetGame();
    }
    if (cheatCode.equalsIgnoreCase("economize"))
    {
      System.out.print("Enter delay num: ");
      setDelay(keyboard.nextInt());
    }
    if (cheatCode.equals("help"))
      System.out.println("chell.x - Chell's X\nchell.y - Chell's Y\nchell.c - Coordinates of Chell\nstop!! - stops game\n" + 
                         "reset - resets field\neconomize - sets the delay");
    
    cheatCode = "";
  }
  /**
   * Stops Player from moving.
   * @param keyEvent The KeyEvent pressed.
   */
  public void keyReleased(KeyEvent keyEvent)
  {
    chell.setXVelocity(0);
  }
  /**
   * Dummy method for MouseListener
   */
  public void mouseClicked(MouseEvent mouseEvent){}
  /**
   * Dummy method for MouseListener
   */
  public void mousePressed(MouseEvent mouseEvent){}
  /**
   * Dummy method for MouseListener
   */
  public void mouseEntered(MouseEvent mouseEvent){}
  /**
   * Dummy method for MouseListener
   */
  public void mouseExited(MouseEvent mouseEvent){}
  
  /**
   * Returns the walls needed for a level.
   * 
   * If you are making another level, override this method to 
   * <b>Postconditions:</b><br />
   * The object at <code>size() - 1</code> is the GreenWall.<br />
   * There is only one GreenWall object. <br />
   * There is at least one Wall that returns true for isFloor() <br />
   * size() >= 2 <br />
   * placePlayer(int, int) is called at the end of the method (before return) to set the player's location to unset default location..
   * @return An ArrayList of Wall objects.
   */
  public ArrayList<Wall> getWalls()
  {
    ArrayList<Wall> wallList = new ArrayList<Wall>();
    wallList.add(new Wall (getFieldWidth(), 10, 0, getFieldHeight() - 10, true));
    wallList.add(new Wall(10, 100, 200, getFieldHeight() - 110, false));
    wallList.add(new Wall(getFieldWidth(), 10, 0, 0, false));
    wallList.add(new RedWall(10, 100, 300, getFieldHeight() - 110));
    wallList.add(new GreenWall(10, 100, getFieldWidth() - 10, getFieldHeight() - 100));
    placePlayer(0, getFieldHeight() - Player.HEIGHT - wallList.get(0).getHeight());
    return wallList;
  }
  /**
   * Returns the potatoes of a level.
   * 
   * <b>Postconditions</b><br>
   * null is not returned.<br>
   * There is at least one potato.
   */
  public ArrayList<Potato> getPotatoes()
  {  
    ArrayList<Potato> potatoList = new ArrayList<Potato>(); 
    potatoList.add(new Potato (20, 310)); 
    potatoList.add(new Potato (30, 310)); 
    potatoList.add(new Potato (500, 310)); 
    potatoList.add(new Potato (78, 310)); 
    potatoList.add(new Potato (450, 310)); 
    return potatoList;
  }
  
  /**
   * Places the player on the screen.
   * You should call this method before the return statement in the getWalls() method.
   * Player has two constants to help with placement.
   * @param x the x-coordinate for the player.
   * @param y the y-coordinate for the player.
   */
  public void placePlayer(int x, int y)
  {
    playerStartX = x;
    playerStartY = y;
    chell.setX(x);
    chell.setY(y);
  }
  /**
   * Resets the playing field.
   * 
   * This will NOT remove the portals from the field.<br />
   * You know, experimentation is good, so people can try failing to succeed.
   */
  public void resetGame()
  {
    stopGame();
    chell.setXVelocity(0);
    repaint();
    placePlayer(playerStartX, playerStartY);
    startGame();
  }
  /**
   * Displays a dialog with the String in the parameter.
   * @param title Dialog title
   * @param message Dialog message in HTML format.
   * @param width Width
   * @param height Height
   */
  public void dispMessage(String title, String message, int width, int height)
  {
    JDialog dialog = new JDialog(this, title, true);
    dialog.getContentPane().add(new JLabel(message));
    Rectangle r = this.getBounds();
    dialog.setLocation(r.x + r.width / 2 - 100, r.y + r.height / 2 - 50);
    
    dialog.setSize(width, height);
    dialog.setVisible(true);
  }
  /**
   * This code has been provided for you, and should not be modified
   */
  public static void main(String[] args) {
    PortalGame p = new PortalGame();
    p.setVisible(true);
    p.initComponents(); 
  }
}