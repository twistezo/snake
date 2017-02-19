import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

/**
 * 
 * @author twistezo
 * 
 * Object Apple is a 20x20 pixels rectangle.
 *
 */

public class Apple {
	private int appleX;
	private int appleY;
	private static Image appleSmall;

	public Apple(int appleX, int appleY) {
		this.appleX = appleX;
		this.appleY = appleY;
	}
	
	/** Paint snake rectangle */
	public void drawApple(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
//		Color appleRed = new Color(240, 0, 0);
//		Color appleBrown = new Color(150, 50, 0);
//		
//		g2d.setColor(appleBrown);
//		g2d.fill3DRect(appleX, appleY, Snake.SNAKE_SIZE, Snake.SNAKE_SIZE, true);
//		g2d.setColor(appleRed);
//		g2d.fill3DRect(appleX+3, appleY+3, Snake.SNAKE_SIZE-6, Snake.SNAKE_SIZE-6, true);
		g2d.drawImage(appleSmall, appleX, appleY, null);
		
	}
	
	public static void setAppleSmall(Image apple){
		appleSmall = apple;
	}
	
	/** Setters/Getters for Apple */
	public void setAppleX (int appleX){
		this.appleX = appleX;
	}
	
	public int getAppleX (){
		return appleX;
	}
	
	public void setAppleY (int appleY){
		this.appleY = appleY;
	}
	
	public int getAppleY (){
		return appleY;
	}
	
}
