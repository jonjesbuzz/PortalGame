import javazoom.jl.player.*;
import java.io.*;
/**
 * MusicPlayer lets you play an MP3 file in the background while using your application.<br />
 * It extends Thread to do this.<br />
 * Some methods will throw IllegalStateExceptions and other errors if the files are not correct. <br />
 * You must have JLayer 1.0.1 or higher to use this class.
 */
public class MusicPlayer extends Thread
{
   private javazoom.jl.player.Player music;
   private FileInputStream song;
   /**
    * Sets up the music player with the default bgmusic.mp3 file
    */
   public MusicPlayer()
   {
     try
     {
       song = new FileInputStream("bgmusic.mp3");
       music = new javazoom.jl.player.Player(song);
     }
     catch (Exception e){
       e.printStackTrace();
     }
   }
   /**
    * Sets up the music player with the specified file name.
    * Just the file name will look inside the current directory.
    * You must use .mp3 at the end and the file name must MATCH EXACTLY!!!
    * @param fileLoc the File's location
    */
   public MusicPlayer(String fileLoc)
   {
     try
     {
       song = new FileInputStream(fileLoc);
       music = new javazoom.jl.player.Player(song);
     }
     catch (Exception e){
       e.printStackTrace();
     }
   }
   /**
    * Sets up the Music Player with the specified file input stream.
    * For advanced people.
    */
   public MusicPlayer(FileInputStream file)
   {
     song = file;
     try{
     music = new javazoom.jl.player.Player(song);
     }
     catch(Exception e){
       e.printStackTrace();
     }
   }
   /**
    * Runs the thread.
    */
   public void run()
   {
     try 
     {
       music.play();
     }
     catch (Exception e)
     {
       e.printStackTrace();
     }
   }
   /**
    * Returns the FileInputStream
    */
   public FileInputStream getFileInputStream()
   {
     return song;
   }
   /**
    * Begins playing the song.
    */
   public void play()
   {
     this.start();
   }
   /**
    * Stops the song.
    * You cannot restart it afterwards.
    */
   public void stopSong()
   {
     music.close();
   }
   /**
    * Returns whether or not music is finished.
    */
   public boolean isComplete()
   {
     return music.isComplete();
   }
   /**
    * Returns the song time.
    */
   public String getTime()
   {
      int position = music.getPosition();
      position /= 1000;
      int seconds = position / 60;
      int minutes = position / 60 / 60;
      String time = minutes + ":" + seconds;
      return time;
   }
   /**
    * Sends back the time.
    */
   public String toString()
   {
     return getTime() + "\nPlaying music.";
   }
}