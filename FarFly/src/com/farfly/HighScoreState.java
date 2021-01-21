package com.farfly;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.*;
import java.util.Scanner;

public class HighScoreState implements Updatable, Renderable {
	private int highscore = 0;

	String fileName = "highscore.txt";

	public void read() {
		try {
			File hs = new File("highscore.txt");
			Scanner scanner = new Scanner(hs);
			while (scanner.hasNextLine()) {
				String data = scanner.nextLine();
				int hsInt = Integer.parseInt(data);
				highscore = hsInt;
			}
			scanner.close();

		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	public void write(int highscore) {
		try {
			FileWriter myWriter = new FileWriter("highscore.txt");
			myWriter.write(Integer.toString(highscore));
			myWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	ScoreState s;

	public HighScoreState(ScoreState s) {
		this.s = s;
	}

	public int getHighscore() {
		return highscore;
	}

	public void setHighscore(int highscore) {
		write(highscore);
//        this.highscore = read();
	}

	@Override
	public void render(Graphics2D g, float interpolation) {
		// TODO Auto-generated method stub
		g.setColor(Color.GREEN);
		g.drawString("HIGHSCORE: " + highscore, Game.WIDTH - 350, 50);
	}

	@Override
	public void update(Input input) {
		read();
		if (s.getScore() > this.highscore)
			setHighscore(s.getScore());
		// write
	}

}