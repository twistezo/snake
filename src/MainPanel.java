import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class MainPanel extends JPanel implements KeyListener {
	
	public static final int AREA_WIDTH = 410;
	public static final int AREA_HEIGHT = 410;
	private ArrayList<Snake> snakes; 
	private ArrayList<Apple> apples;
	private Logic logic;
	private int timerDelay = 100;
	private static JLabel score;
	private JFrame mainFrame;
	private Timer timer;
	private static boolean gameOver = false;
	private static int scoreInt;

	public MainPanel(){
		
		/** Create Logic Object and 
		 * getting snakes & apples objects
		 */
		logic = new Logic();
		snakes = logic.getSnakes();
		apples = logic.getApples();
		logic.generateStartSnake();
		
		/** Create listener for Swing Timer
		 * to create movement in the game
		 */
        timer = new Timer(timerDelay, new swingTimerListener());
        timer.start();
    	
        score = new JLabel("Score: ");
        
        add(score);
        setPreferredSize(new Dimension(AREA_WIDTH, AREA_HEIGHT));
        setFocusable(true);
    	addKeyListener(this);
    	
        mainFrame = new JFrame();
        mainFrame.add(this);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setResizable(false);
        mainFrame.setAlwaysOnTop(true);
        mainFrame.setVisible(true);
        
	}
	
	/** Do movements in every tick swing timer */
	public class swingTimerListener implements ActionListener {
		public void actionPerformed(ActionEvent a) {
			
			/** For each snake rectangle */
    		logic.move();
    		logic.checkCollision();
    		
    		if(gameOver){
 				dialogYesNo();
 			}
    		
            repaint();
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for (Snake snake : snakes) {
			for (Apple apple : apples){
			
				apple.drawApple(g);
				snake.drawSnake(g);
			}
		}
	}
	
    public void keyPressed(KeyEvent e) {
  		switch(e.getKeyCode()) {
  		
  		case KeyEvent.VK_UP :
  			logic.setDirections("UP");
	  		break;
	  		
  		case KeyEvent.VK_DOWN :
  			logic.setDirections("DOWN");
	  		break;
	  		
  		case KeyEvent.VK_LEFT :
  			logic.setDirections("LEFT");
	  		break;
	  		
  		case KeyEvent.VK_RIGHT :
  			logic.setDirections("RIGHT");
  			
	  		break;
  		}
    }
 
    public void keyReleased(KeyEvent e) {
    }
 
    public void keyTyped(KeyEvent e) {
    }
    
    public static int getAREA_WIDTH(){
    	return AREA_WIDTH;
    }
    
    public static void setScore(int scor){
    	scoreInt = scor;
		score.setText("Score: " +scor);
	}
    
    /** Pop-up YES/NO message  */
	public void dialogYesNo(){
		
		int dialogButton = JOptionPane.YES_NO_OPTION;
		int dialogResult = JOptionPane.showConfirmDialog(this, "Your result: " +scoreInt+ "\nPlay again?"
				, "Game Over", dialogButton);
		
		/** YES option */
		if(dialogResult == 0) {
		  	mainFrame.dispose();
			timer.stop();
			gameOver = false;
			SwingUtilities.invokeLater(() -> new MainPanel());
			
		/** NO option */
		} else {
			mainFrame.dispose();
			timer.stop();
		} 
	}
	
	public static void setGameOver(boolean game){
		gameOver = game;
	}
    
}
