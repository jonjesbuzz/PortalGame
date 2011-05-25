public class EnrichmentCenter
{
  /**
   * Array of Portal Games.
   */
  private static PortalGame[] games;
  /**
   * Number of levels
   */
  private static final int NUM_LEVELS = 3;
  /**
   * This main method gets a random level from the array and begins playing it.
   */
  public static void main(String[] args)
  {
    games = new PortalGame[NUM_LEVELS];
    games[0] = new PortalGame(); //Contains basic level
    games[1] = new LevelTwo();
    games[2] = new LevelThree();
    int gameIndex = (int)(Math.random() * NUM_LEVELS);
    /* Default code from Pong.java */
    PortalGame game = games[gameIndex]; //Modified because levels are created above.
    game.setVisible(true);
    game.initComponents();
  }
}