
public class Controller
{
   Model model;
   boolean buttonPress;
   
   public Controller()
   {
      buttonPress = false;
   }
   
   public void pressButton()
   {
      buttonPress = true;
   }
   
   public boolean getButtonPress()
   {
      return buttonPress;
   }
   
}
