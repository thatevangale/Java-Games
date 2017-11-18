package evan.game.main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import evan.game.gamestate.GameStateManager;

public class GamePanel extends JPanel implements Runnable, KeyListener {
	
	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 640;
	public static final int HEIGHT = 480;
	
	private Thread thread;
	private boolean isRunning = false;
	
	private int FPS = 60;
	private long targetTime = 1000 / FPS;
	
	protected GameStateManager gsm;
	protected Timer timer;
	
	public GamePanel() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		addKeyListener(this);
		setFocusable(true);
		
		timer = new Timer();
		gsm = new GameStateManager();
		
		start();
	}
	
	private void start() {
		timer.init();
		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public void run() {
		long start, elapsed, wait;
		float delta;
		
		while (isRunning) {
			//start = System.nanoTime();
			
			/* Get delta time */
			start = (long) (timer.getTime() * 1000L);
            delta = timer.getDelta();
			
			update(delta);
			repaint();
			
			elapsed = (long) (timer.getTime() * 1000L);
			
			/*elapsed = System.nanoTime() - start;
			wait = targetTime - elapsed / 1000000;
			
			if (wait <= 0) {
				wait = 5;
			}*/
			
			try {
				Thread.sleep(start + targetTime - elapsed);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void update(float delta) {
		gsm.update(delta);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.clearRect(0, 0, WIDTH, HEIGHT);
		
		gsm.render(g);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		gsm.keyPressed(e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		gsm.keyReleased(e.getKeyCode());
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
