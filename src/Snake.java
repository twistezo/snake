import java.awt.*;

/**
 * 
 * @author twistezo
 * 
 * Object Snake is a 20x20 pixels rectangle.
 *
 */

public class Snake {
	private int snakeX;
	private int snakeY;
	public static final int SNAKE_SIZE = 20;
	
	
	/** Constructor for class */
	public Snake(int snakeX, int snakeY){
		this.snakeX = snakeX;
		this.snakeY = snakeY;
	}

	/** Paint snake rectangle */
	public void drawSnake(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		Color snakeBrown = new Color(150, 100, 50);
		Color snakeGreen = new Color(0, 200, 0);
		g2d.setColor(snakeBrown);
		g2d.fill3DRect(snakeX, snakeY, SNAKE_SIZE, SNAKE_SIZE, true);
		g2d.setColor(snakeGreen);
		g2d.fill3DRect(snakeX+3, snakeY+3, SNAKE_SIZE-6, SNAKE_SIZE-6, true);
	}
		
	/** Setters/Getters for snake */
	public void setSnakeXY (int x, int y){
		snakeX = x;
		snakeY = y;
	}
	
	public void setSnakeX (int rectX){
		snakeX = rectX;
	}
	
	public int getSnakeX (){
		return snakeX;
	}
	
	public void setSnakeY (int rectY){
		snakeY = rectY;
	}
	
	public int getSnakeY (){
		return snakeY;
	}
	
}