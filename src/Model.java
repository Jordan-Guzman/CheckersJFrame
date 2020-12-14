
public class Model
{
   GameTimer timer;
   String time;
   View view;
   
   public Model()
   {
      timer = new GameTimer();
   }
   
   public void initTimer()
   {
      timer.startRun();
   }
}
