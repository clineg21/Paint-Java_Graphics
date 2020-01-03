import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
* This class is for enabling the closing of a window.
*
* @author Frank Klawonn
* Last change 07.01.2005
*/
   public class MyFinishWindow extends WindowAdapter
   {
     public void windowClosing(WindowEvent e)
     {
       System.exit(0);
     }
   }