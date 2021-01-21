package com.farfly;

import res.Sprite;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import javax.swing.JFrame;

public class Game {
	private Canvas game = new Canvas();
	public final static int WIDTH = 600, HEIGHT = 800;
	private boolean isRunning = false;
	public void setGameover(boolean isRunning) {
		this.isRunning = isRunning;
	}
	public static boolean gameover = false;
	private String gameName = "FAR FLY";
	public static Sprite sprite = new Sprite();
	GameState gs = new GameState();
	private Input input;
    static long startingTime;
	public static Button startButton = new Button(WIDTH/2-78,HEIGHT/2-43+50,156,87,sprite.getImage("FarFly/res/res/button.png"));

	private ArrayList<Updatable> updatables = new ArrayList<>();
	private ArrayList<Renderable> renderables = new ArrayList<>();

	public void addUpdatable(Updatable u) {
		updatables.add(u);
	}

	public void removeUpdatable(Updatable u) {
		updatables.remove(u);
	}

	public void addRenderable(Renderable r) {
		renderables.add(r);
	}

	public void removeRenderable(Renderable r) {
		renderables.remove(r);
	}

	public void setRunningTrue(boolean running) {
		
		running=true;
	}
	
	public void setRunningFalse(boolean running) {
		running=false;
	}
	
	public void start() {
		// Initialise windows
		Dimension gameSize = new Dimension(Game.WIDTH, Game.HEIGHT);
		JFrame gameWindow = new JFrame(gameName);
		gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameWindow.setSize(gameSize);
		gameWindow.setResizable(false);
		gameWindow.setVisible(true);
		
		game.setSize(gameSize);
		game.setMaximumSize(gameSize);
		game.setMinimumSize(gameSize);
		game.setPreferredSize(gameSize);
		
		gameWindow.add(game);
		gameWindow.setLocationRelativeTo(null);

		// Initialise input
		input = new Input();
		game.addKeyListener(input);
		game.addMouseListener(new MouseHandler());

		// Game loop
		final int TICKS_PER_SECOND = 60;
		final int SKIP_TICKS = 1000 / TICKS_PER_SECOND;
		final int MAX_FRAMESKIP = 5;

		long nextGameTick = System.currentTimeMillis();
		int loops;
		float interpolation;

		long timeAtLastFPSCheck = 0;
		int ticks = 0;

		boolean running = true;
				
		while (running) {
			loops = 0;

			while (System.currentTimeMillis() > nextGameTick && loops < MAX_FRAMESKIP) 
			{
				update();
				ticks++;

				nextGameTick += SKIP_TICKS;
				loops++;
			}

			interpolation = (float) (System.currentTimeMillis() + SKIP_TICKS - nextGameTick) / (float) SKIP_TICKS;
			render(interpolation);

			if (System.currentTimeMillis() - timeAtLastFPSCheck >= 1000) 
			{
				System.out.println("FPS: " + ticks);
				gameWindow.setTitle(gameName + " - FPS: " + ticks);
				ticks = 0;
				timeAtLastFPSCheck = System.currentTimeMillis();
			}
		}
	}

	private void update() {
		if(gameover==false && isRunning==true) {
			for (Updatable u : updatables) {
				u.update(input);
			}
		}
	}

	private void start(Input input) {
		if (input.isSpacePressed()) {
			isRunning = true;
			startingTime = System.currentTimeMillis();
		}
	}
	
	public static long getStartingTime() {
		return startingTime;
	}

	private void render(float interpolation) {
		BufferStrategy bs = game.getBufferStrategy();
		if (bs == null) 
		{
			game.createBufferStrategy(2);
			return;
		}

		Graphics2D g = (Graphics2D) bs.getDrawGraphics();
		g.clearRect(0, 0, game.getWidth(), game.getHeight());
		if (!isRunning) 
		{
			g.drawImage(sprite.getImage("FarFly/res/res/startscreen.png"),0,0,null);
			start(input);
		}

		else 
		{
			for (Renderable r : renderables) {
				r.render(g, interpolation);
			}
			
			isRunning = true;
			if(gameover) {
				g.drawImage(sprite.getImage("FarFly/res/res/GO-to.png"),WIDTH/2-112,HEIGHT/2-55-100,null);
				Game.startButton.render(g);
				startingTime = System.currentTimeMillis();
			}
		}
		g.dispose();
		bs.show();
	}

	public boolean getIsRunning() {
		return isRunning;
	}
}
