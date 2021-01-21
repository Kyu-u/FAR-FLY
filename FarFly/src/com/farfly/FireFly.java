package com.farfly;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import res.Sprite;

public class FireFly implements Updatable, Renderable {
	private float x, y;
    private float yVel;
    private float baseYVel = -6.0f;
    private float gravity = 0.25f;
    private Trees tree;
    long elapsed;
    long startingTime;
    GameState gs = new GameState();

    private int score = 0;
    private ScoreState s;
    
    public int getScore() {
    	return score;
    }
    public int getY() {
    	return (int) y;
    }
    public int getX() {
    	return (int) x;
    }
    
    private Font gameFont = new Font("Arial Rounded MT Bold", Font.BOLD, 30);
    
    private BufferedImage flapUp;
    private BufferedImage flapDown;
    public FireFly (Trees tree, ScoreState s) {
        resetFly();
        
        this.s = s;
        this.tree = tree;

        try {
            flapUp = Sprite.getSprite("up.png");
            flapDown = Sprite.getSprite("down.png");
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            System.exit(1);
        }
    }
    
    public void resetFly() {
        x = 277;
        y = 428;
        yVel = baseYVel;
    }
    
    private void flap() {
        yVel = baseYVel;
    }

    @Override
    public void update(Input input) {
   		long current = System.currentTimeMillis();
   		int elapsed = (int) (current-Game.getStartingTime())/250;
   		s.setScore(elapsed);
        y += yVel;
        yVel += gravity;
        
        if(y < 0) {
            y = 0;
            yVel = 0;
        }

        
        if(input.isSpacePressed()) {
            flap();
        }
        
//    	Check collision
        if((( x+49 >= tree.getX() || x-49 >= tree.getX() ) && 
        		( x+49 <= tree.getX() + tree.getTreeWidth() || x-49 <= tree.getX() + tree.getTreeWidth() ) && 
        		( y <= tree.getY() || y >= tree.getY() + tree.getTreeVerticalSpacing())) || y >= Game.HEIGHT ) {
            tree.resetTrees();
            Game.gameover=true; 
            resetFly();
            s.setScore(0);
        }

    }

    @Override
    public void render(Graphics2D g, float interpolation) {
    	
        g.setColor(Color.decode("#D3C9E1"));

        g.drawImage(yVel <= 0 ? flapUp : flapDown, (int) x, (int) (y + (yVel * interpolation)), null);
        
        g.setFont(gameFont);
    }
    
}
