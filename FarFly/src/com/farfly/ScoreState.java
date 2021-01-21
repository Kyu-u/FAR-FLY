package com.farfly;

import java.awt.Graphics2D;

public class ScoreState implements Updatable, Renderable {
	private int score;

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public void render(Graphics2D g, float interpolation) {
		// TODO Auto-generated method stub
        g.drawString("SCORE: " + score, 20, 50);

	}

	@Override
	public void update(Input input) {
		// TODO Auto-generated method stu
		
	}
}