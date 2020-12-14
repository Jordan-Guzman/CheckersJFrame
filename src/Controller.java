
public class Controller
{
   Model model;
   public Controller()
   {
      model = new Model();
   }
   
   public void buttonPress()
   {
      model.initTimer();
   }
}
