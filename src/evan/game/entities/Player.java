package evan.game.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import evan.game.main.GamePanel;

public class Player {
	
	private boolean right = false, left = false, jumping = false, falling = false;
	
	private double x, y;
	private int width, height;
	
	private double hSpeed = 100;
	private double currentHSpeed = 0;
	private double maxHSpeed = 6;
	
	private double vHeight = 64;
	private double vTime = 2;
	private double initVelocity = 200; //2 * vHeight / vTime;
	private double currentVSpeed = initVelocity;
	private double maxVSpeed = 8;
	private double gravity = 200; //2 * vHeight / (vTime * vTime);
	
	// Old
	/*private double jumpSpeed = 5;
	private double currentJumpSpeed = jumpSpeed;
	
	private double maxFallSpeed = 5;
	private double currentFallSpeed = 0.1;*/

	/*public Player(int width, int height) {
		x = GamePanel.WIDTH / 2;
		y = GamePanel.HEIGHT / 2;
		this.width = width;
		this.height = height;
	}*/
	
	public Player(int width, int height) {
		//super(x, y, speed, 20, 20, 20, 40);
		
		x = GamePanel.WIDTH / 2;
		y = GamePanel.HEIGHT / 2;
		this.width = width;
		this.height = height;
	}
	
	public void update(double delta) {
		
		if (right) {
			x += hSpeed * delta;
		}
		
		if (left) {
			x -= hSpeed * delta;
		}
		
		if (jumping) {
			currentVSpeed += gravity * delta;
			y += currentVSpeed * delta;
			
			//currentJumpSpeed -= 0.1;
			
			/*if (currentJumpSpeed <= 0) {
				currentJumpSpeed = jumpSpeed;
				jumping = false;
				falling = true;
			}*/
		}
		
		/*if (falling) {
			y += currentFallSpeed;
			
			if (currentFallSpeed < maxFallSpeed) {
				currentFallSpeed += 0.1;
			}
		} else if (!falling) {
			currentFallSpeed = 0.1;
		}*/
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect((int) x, (int) y, width, height);
	}
	
	public void keyPressed(int k) {
		
		if (k == KeyEvent.VK_RIGHT) {
			right = true;
		}
		
		if (k == KeyEvent.VK_LEFT) {
			left = true;
		}
		
		if (k == KeyEvent.VK_UP) {
			jumping = true;
			currentVSpeed = -initVelocity;
		}
		
	}
	
	public void keyReleased(int k) {
		
		if (k == KeyEvent.VK_RIGHT) {
			right = false;
		}
		
		if (k == KeyEvent.VK_LEFT) {
			left = false;
		}
		
	}
	
}
