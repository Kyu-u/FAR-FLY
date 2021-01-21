package com.farfly;

public class GameState {
	private boolean gameover = false;
	public GameState() {
		this.gameover=false;
	}
	public boolean isGameover() {
		return gameover;
	}

	public void setGameoverTrue() {
		this.gameover = true;
	}
	
	public void setGameoverFalse() {
		this.gameover = false;
	}
}
