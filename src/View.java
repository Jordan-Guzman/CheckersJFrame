import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JFrame implements ActionListener
{
   private static final long serialVersionUID = 1L;
   CheckersBoard checkersBoard;
   JPanel board = new JPanel();
   boolean[][] boardPattern;
   JPanel[][] filledBoard = new JPanel[8][8];
   Color redSquare = Color.RED;
   Color blackSquare = Color.BLACK;
   JFrame frame;
   GameTimer timer;
   
   int count = 0;
   JButton timerButton;
   JButton piece;
   HumanPlayer human;
   Controller controller;
   JPanel timerBar;
   JLabel timerText = new JLabel("00:00:00");
   JLabel blackPiece;
   JButton redPiece;
   JButton blackTile;
   String str = "00:00:00";
   Icon pawnRed;
   Icon pawnBlack;
   Icon kingRed;
   Icon kingBlack;
   ActionListener redMove = new RedPieceMove();
   ActionListener moveToBlackTile = new MoveToBlackTile();
   
   //Strings for the image icon paths
   String blackKing = "checkersPieces" + "/" + "King" + " " + "(Black)" + ".png";
   String redKing = "checkersPieces" + "/" + "King" + " " + "(Red)" + ".png";
   String blackPawn = "checkersPieces" + "/" + "Pawn" + " " + "(Black)" + ".png";
   String redPawn = "checkersPieces" + "/" + "Pawn" + " " + "(Red)" + ".png";
   
   public View()
   {
      pawnRed = new ImageIcon(redPawn);
      pawnBlack = new ImageIcon(blackPawn);
      kingRed = new ImageIcon(redKing);
      kingBlack = new ImageIcon(blackKing);
      timer = new GameTimer();
      frame = new JFrame();
      controller = new Controller();
      human = new HumanPlayer();
      checkersBoard = new CheckersBoard();
      frame.setSize(850, 850);
      frame.setTitle("Checkers");
      frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
      frame.setVisible(true);
      frame.setLayout(new GridLayout(9, 8));
      boardPattern = checkersBoard.getBoardPattern();
      loadBoard();
      active();
   }
   
   public void active()
   {
      while(frame.isDisplayable())
      {
         timerText.setText(timer.getTimerValue());
      }
   }
   
   public View(String str)
   {
      this.str = str;
      active(str);
   }
   
   //this is the filled board
   private void loadBoard()
   {
      int row = 0;
      int col = 0;
      
      for (row = 0; row < 8; row++)
      {
         JPanel square = new JPanel();
         square.setLayout(new BorderLayout());
         
         if(boardPattern[row][0] == true)
         {
            square.setBackground(redSquare);
            filledBoard[row][0] = square;
         }
         else
         {
            square.setBackground(blackSquare);
            filledBoard[row][0] = square;
            if(row <= 2)
            {
               square.add(blackPiece = new JLabel(pawnBlack), BorderLayout.CENTER);
               blackPiece.setBackground(Color.BLACK);
            }
            else if(row > 4)
            {
               square.add(redPiece = new JButton(pawnRed), BorderLayout.CENTER);
               redPiece.setBackground(Color.BLACK);
            }
            else if((row > 2) && (row <= 4))
            {
               square.add(blackTile = new JButton(), BorderLayout.CENTER);
               blackTile.setBackground(Color.BLACK);
            }
         }
         frame.add(square);
         
         for(col = 1; col < 8; col++)
         {
            JPanel square2 = new JPanel();
            square2.setLayout(new BorderLayout());
            
            if(boardPattern[row][col] == true)
            {
               square2.setBackground(redSquare);
               filledBoard[row][col] = square2;
            }
            else
            {
               square2.setBackground(blackSquare);
               filledBoard[row][col] = square2;
               if(row <= 2)
               {
                  square2.add(blackPiece = new JLabel(pawnBlack), BorderLayout.CENTER);
                  blackPiece.setBackground(Color.BLACK);
               }
               else if(row > 4)
               {
                  square2.add(redPiece = new JButton(pawnRed), BorderLayout.CENTER);
                  redPiece.setBackground(Color.BLACK);
                  redPiece.addActionListener(redMove);
               }
               else if((row > 2) && (row <= 4))
               {
                  square2.add(blackTile = new JButton(), BorderLayout.CENTER);
                  blackTile.setBackground(Color.BLACK);
                  blackTile.addActionListener(moveToBlackTile);
               }
            }
            frame.add(square2);
         }
      }
      
      for(int n = 0; n < 6; n++)
      {
         if(n == 4)
         {
            //add JLabel that updates text of timer
            frame.add(timerBar = new JPanel());
            timerBar.setBackground(Color.LIGHT_GRAY);
            timerBar.add(timerText);
         }
         timerBar();
      }
      frame.add(timerButton());
   }
//*/ 
   public void active(String str)
   {
      while(this.frame.isDisplayable())
      {
         timerText.setText(str);
         System.out.println(str);
         timerText.repaint();
      }
   }
   
   public Icon getRedPawn()
   {
      return this.pawnRed;
   }
   
   public Icon getBlackPawn()
   {
      return this.pawnBlack;
   }
   
   public void setTimerText(String str)
   {
      System.out.println(str);
      timerText.setText(str);
      timerText.repaint();
   }
   
   public String getTimerText()
   {
      return this.str;
   }
   
   private void timerBar()
   {
      timerBar = new JPanel();
      timerBar.setBackground(Color.LIGHT_GRAY);
      frame.add(timerBar);
   }
   
   public JButton timerButton()
   {
      timerButton = new JButton("Start");
      timerButton.addActionListener(this);
      return timerButton;
   }
   
   private void resetButtonCount()
   {
      count = 0;
   }
   
   public void actionPerformed(ActionEvent e)
   {
      if(count < 1)
      {
         timerButton.setText("Stop");
         count++;
      }
      else
      {
         timerButton.setText("Start");
         resetButtonCount();
      }
      controller.pressTimerButton();
      timerButtonPressed();
   }
   
   public void timerButtonPressed()
   {
      if(controller.getTimerButtonPress() == true)
      {
         timer.startRun();
      }
      else
      {
         System.out.println("Error. Timer not working.");
      }
   }
   
   private class RedPieceMove implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         System.out.println("red button pressed");
      }
   }
   
   private class MoveToBlackTile implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         System.out.println("Move to this black tile");
      }
   }
   
   
   public boolean checkMove()
   {
      return true;
   }
   
   public void move()
   {
      if(checkMove() == true)
      {
         
      }
   }
}