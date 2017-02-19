import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

/** 
 * 
 * @author twistezo
 * 
 * Class with snake game logic
 *
 */

public class Logic {
	private String directions = "DEFAULT";
	private ArrayList<Snake> snakes = new ArrayList<Snake>();
	private ArrayList<Apple> apples = new ArrayList<Apple>();
	private int snakeX;
	private int snakeY;
	public static final int SNAKE_START_X = 100;
	public static final int SNAKE_START_Y = 200;
	public static final int APPLE_X = 300;
	public static final int APPLE_Y = 200;
	private static boolean gameOver = false;
	private boolean duplicates = false;
	
	public void generateStartSnake(){
		
		/** Generate a couple of snake rectangles on start
		 *  x=100, 80, 60								*/
		for (int i=0; i<40; i++){
			snakes.add(new Snake(SNAKE_START_X-i*20, SNAKE_START_Y));
		}
		
		/** Add one apple on start */
		apples.add(new Apple(APPLE_X, APPLE_Y));
		
		/** setApple image */
		createAppleImage();
		
		/** Set snake Head to right position relative to tail */
		snakeX = snakes.get(0).getSnakeX();
		snakeY = snakes.get(0).getSnakeY();
	}
	
	/** Method for createAppleImage from file */
	public Image createAppleImage(){
		BufferedImage apple = null;
		Image appleSmall = null;
		
		try {
			apple = ImageIO.read(getClass().getResource("apple.png"));
			appleSmall = apple.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
			Apple.setAppleSmall(appleSmall);
			
		} catch (IOException e) {
			System.out.println("No File");
		}
		return appleSmall;
	}
	
	/** Snake movement declarations. 
	 * Everytime when user press arrow key
	 * move() method create in snakes[0] new Snake object 
	 * and remove last Snake object in snakes ArrayList	
	 * If snakeX or snakeY is close to screen size border
	 * snake stop moving. 
	 * */
	public void move() {
		int increment = Snake.SNAKE_SIZE;
		
		/** Send new XY pos. to FIRST Snake object */
		snakes.get(0).setSnakeXY(snakeX, snakeY);
		
		switch (directions){
		
			case "UP": 
				if(snakeY == 0){
					snakeY = 0;
					gameOver = true;
					MainPanel.setGameOver(gameOver);
				}
				else{
					snakes.add(0, new Snake(snakeX, snakeY -increment));
					snakes.remove(snakes.size()-1);
					snakeY -= increment;
				}
				break;
				
			case "LEFT": 
				if(snakeX == 0){
					snakeX = 0;
					gameOver = true;
					MainPanel.setGameOver(gameOver);
				}
				else{
					snakes.add(0, new Snake(snakeX -increment, snakeY));
					snakes.remove(snakes.size()-1);
					snakeX -= increment;
				}
				break;
				
			case "DOWN":
				if(snakeY == MainPanel.AREA_HEIGHT-10){
					snakeY = MainPanel.AREA_HEIGHT-10;
					gameOver = true;
					MainPanel.setGameOver(gameOver);
				}
				else{
					snakes.add(0, new Snake(snakeX, snakeY +increment));
					snakes.remove(snakes.size()-1);
					snakeY += increment;
				}
				break;
				
			case "RIGHT":
				if(snakeX == MainPanel.AREA_WIDTH-10){
					snakeX = MainPanel.AREA_WIDTH-10;
					gameOver = true;
					MainPanel.setGameOver(gameOver);
				}
				else{
					
					snakes.add(0, new Snake(snakeX +increment, snakeY));
					snakes.remove(snakes.size()-1);
					snakeX += increment;
				}
				break;
				
			default:
				break;
		}
	
	}
	
	/** When snakes[0] coordinates are the same as Apple coordinates,
	 * Apple change its position and snakes get new Snake object in the
	 * last position of snakes ArrayList.
	 * Else if checks when snake eat itself.
	 */
	public void checkCollision(){
		if( snakeX == apples.get(0).getAppleX() && snakeY == apples.get(0).getAppleY() ){
			apples.get(0).setAppleX(generateXY());
			apples.get(0).setAppleY(generateXY());
			
			snakes.add(snakes.size()-1, new Snake(snakeX, snakeY));
			
			MainPanel.setScore(snakes.size()-3);
		}
		else if( snakeX != apples.get(0).getAppleX() && snakeY != apples.get(0).getAppleY() ){
		
		duplicates = checkXY();
		if(duplicates){
			gameOver = true;
			MainPanel.setGameOver(gameOver);
			}
		}
	}
	
	public boolean checkXY(){
		boolean hasDuplicate = false;
		ArrayList<Integer> listX = new ArrayList<Integer>();
		ArrayList<Integer> listY = new ArrayList<Integer>();
		
		for(int i=0; i<snakes.size(); i++){
			listX.add(snakes.get(i).getSnakeX());
			listY.add(snakes.get(i).getSnakeY());
		}
		
//		System.out.println("listX: " +listX);
//		System.out.println("listY: " +listY);
		
		for(int i=1; i<listX.size(); i++){
			if(	
				listX.get(0).equals(listX.get(i))
				&&
				listY.get(0).equals(listY.get(i))
				){
				
				hasDuplicate = true;
				break;
			}
			else{
				hasDuplicate = false;
			}
		}
		
		return hasDuplicate;
	}
	
	/** Generate random number between 0 and 500 with step 20 
	 * for new random Apple postition
	 * */
	public int generateXY(){
		int step = 20;
		int areaWidth = MainPanel.getAREA_WIDTH();
		int xy = (int) (Math.random()*(areaWidth/step)+1)*step;
		return xy;
	}
	
	public void setSnakes(ArrayList<Snake> snakes){
		this.snakes = snakes;
	}
	
	public ArrayList<Snake> getSnakes(){
		return snakes;
	}
	
	public void setApples(ArrayList<Apple> apples){
		this.apples = apples;
	}
	
	public ArrayList<Apple> getApples(){
		return apples;
	}
	
	public void setDirections(String directions){
		this.directions = directions;
	}
	
	public String getDirections(){
		return directions;
	}
	
	
}
