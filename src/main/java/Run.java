import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Snake Game
 * 
 * @author twistezo
 * 
 * @todo startup window with difficulty level
 * @todo paint snake with .png
 * @todo when snake goes right can't turn left. The same with rest arrow keys.
 *
 */

public class Run {
	
	 public static void main(String[] args){
			
		 	/** Set Windows look'n'fell */
		 	try {
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
					| UnsupportedLookAndFeelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
		 
			/** Start GUI in swing thread */
			SwingUtilities.invokeLater(() -> new MainPanel());
		}
}
