import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * A class modelling a tic-tac-toe (noughts and crosses, Xs and Os) game in a very
 * simple GUI window.
 * 
 * @author Lynn Marshall
 * @version November 8, 2012
 */

public class TicTacToeFrame extends TicTacToe 
{ 
   private JFrame TicTacToeFrame;
   private JTextArea status; // text area to print game status
   
   /** 
    * Constructs a new Tic-Tac-Toe board and sets up the basic
    * JFrame containing a JTextArea in a JScrollPane GUI.
    */
   public TicTacToeFrame()
   { 
       super();
       
       TicTacToeFrame = new JFrame("TicTacToe");
       TicTacToeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
       JScrollPane scrollPane = new JScrollPane(status);
       
       
       TicTacToeFrame.pack(); 
       TicTacToeFrame.setVisible(true); 
   }
   
   /**
    * Prints the board to the GUI using toString().
    */
    public void print() 
    {  
       status = new JTextArea(super.toString() , 30,30);
       
       TicTacToeFrame.add(status);
       TicTacToeFrame.pack(); 
       TicTacToeFrame.setVisible(true); 
    }

}