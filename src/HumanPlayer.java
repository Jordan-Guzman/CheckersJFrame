
public class HumanPlayer
{
   Controller controller;
   
   public HumanPlayer()
   {
      controller = new Controller();
   }
   
   public void buttonPress()
   {
      controller.buttonPress();
   }
}
