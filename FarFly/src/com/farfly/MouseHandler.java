package com.farfly;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler implements MouseListener {
	 @Override
	    public void mouseClicked(final MouseEvent e) {
	        if (Button.checkCollision(e.getX(), e.getY(), Game.startButton) && Game.gameover) {
	            Game.startButton.pressed = true;
	            System.out.println("pressed");
	            Game.gameover = false;
	            Game.startingTime=System.currentTimeMillis();
	            Game.startButton.pressed = false;
	        }
	    }
	    
	    @Override
	    public void mousePressed(final MouseEvent e) {

	    }
	    
	    @Override
	    public void mouseReleased(final MouseEvent e) {
	    }
	    
	    @Override
	    public void mouseEntered(final MouseEvent e) {
	    }
	    
	    @Override
	    public void mouseExited(final MouseEvent e) {
	    }
}