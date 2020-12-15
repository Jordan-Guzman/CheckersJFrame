import javax.swing.*;
import java.awt.event.*;

public class GameTimer extends Thread implements Runnable
{
   int hr = 0, min = 0, sec = 0, i = 0;
   private String strTime = "00:00:00 ";
   int count;
   ActionListener begin = new StartTimer();
   Timer timer = new Timer(1000, begin);
   
   //constructor instantiates counter
   public GameTimer()
   {
      count = 0;
   }
   
   public void startRun()
   {
      count++;
      startThread();
      
   }
   
   //private class that is called when timer starts (action listener for timer.start()
   private class StartTimer implements ActionListener
   {
      public synchronized void actionPerformed(ActionEvent e)
      {
         if (count == 2)
         {
            count = 0;
            timer.stop();
         }
         
         if (sec < 59) 
         {
            sec++;
         }
         else
         {
            sec = 0;
            min++;
         }
        
         if (min >= 60)
         {
            min = 0;
            hr++;
         }
         strTime = String.format("%02d" + ":" + "%02d" + ":" + "%02d", hr, min, sec);
         System.out.println(strTime);
         setTimerValue(strTime);
      }
   }
   
   public void startThread()
   {
      Thread timerThread = new Thread(this);
      timerThread.start();
   }
 
   public void run()
   {
      timer.start();
   }
   
   private void setTimerValue(String str)
   {
      this.strTime = str;
   }
   
   public String getTimerValue()
   {
      return strTime;
   }
}


//Try to create GameTimer in View and use Controller to start the timer