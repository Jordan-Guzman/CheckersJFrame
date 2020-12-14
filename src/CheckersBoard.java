
public class CheckersBoard
{
   private boolean[][] boardLayout = new boolean[8][8];
   private boolean redSquare;
   private boolean blackSquare;
   
   public CheckersBoard()
   {
      redSquare = true;
      blackSquare = false;
      setBlackBoard();
   }
   
   private void setBlackBoard()
   {
      for (int i = 0; i < 8; i++)
      {
         boardLayout[i][0] = blackSquare;
         
         for(int j = 0; j < 8; j++)
         {
            boardLayout[i][j] = blackSquare;
         }
      }
      setCheckerBoardPattern();
   }
   
   public void setCheckerBoardPattern()
   {
      for(int row = 0; row < 8; row++)
      {
         if(row % 2 == 0)
         {
            boardLayout[row][0] = redSquare;
            
            for(int col = 0; col < 8; col += 2)
            {
               boardLayout[row][col] = redSquare;
            }
         }
         else
         {
            for(int col = 1; col < 8; col += 2)
            {
               boardLayout[row][col] = redSquare;
            }
         }
      }
   }
   
   public boolean[][] getBoardPattern()
   {
      return boardLayout;
   }
   
   public void consoleBoardPattern()
   {
      for (int i = 0; i < 8; i++)
      {
         System.out.println("row " + i + " " + boardLayout[i][0]);
         
         for(int j = 0; j < 8; j++)
         {
            System.out.println("column " + j + " " + boardLayout[i][j]);
         }
      }
   }
}