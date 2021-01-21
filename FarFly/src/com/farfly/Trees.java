package com.farfly;

import res.Sprite;
import java.awt.Graphics2D;
import java.util.Random;

public class Trees implements Updatable, Renderable {
	 	private int treeWidth = 80;
	    private int treeHorizontalSpacing = 250;
	    private int treeVerticalSpacing = 180;
		Sprite sprite = new Sprite();
		GameState gs = new GameState();

	    private float xVel = -5.0f;
	    private float x1, y1;
	    
	    private Random rand;
	    
	    public Trees() {
	        rand = new Random();
	        
	        resetTrees();
	    }
	    
	    public void resetTrees() {	        
	        x1 = Game.WIDTH * 2;  
	        y1 = getRandomY();
	    }
	    
	    public float getX() {
	        return x1;
	    }
	    
	    public float getY() {
	        return y1;
	    }
	    
	    public int getTreeWidth() {
	        return treeWidth;
	    }
	    
	    public int getTreeHorizontalSpacing() {
	        return treeHorizontalSpacing;
	    }
	    
	    public int getTreeVerticalSpacing() {
	        return treeVerticalSpacing;
	    }
	    
	    private int getRandomY() {
	        return rand.nextInt((int)(Game.HEIGHT * 0.4f)) + (Game.HEIGHT / 10);
	    }
	    
	    @Override
	    public void update(Input input) {
	        x1 += xVel;

	        if(x1 + treeWidth < 0) {
	            x1 = Game.WIDTH  + treeHorizontalSpacing;
	            y1 = getRandomY();
	        }
	    }
	    
	    @Override
	    public void render(Graphics2D g, float interpolation) {

	    	g.drawImage(sprite.getImage("FarFly/res/res/bg.png"),0,0,null);
    
	        // Tree 1
	        g.drawImage(sprite.getImage("FarFly/res/res/tree.png"),(int) (x1 -10 + (xVel * interpolation)),(int) y1 - 700,null);
	        g.drawImage(sprite.getImage("FarFly/res/res/tree.png"),(int) (x1 -10 + (xVel * interpolation)),(int) y1 + getTreeVerticalSpacing(),null);
	        
	    }
}
