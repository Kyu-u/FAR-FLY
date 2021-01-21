package com.farfly;

public class FarFly {
	public static void main(String[] args) {
		Game g = new Game();
		ScoreState s = new ScoreState();
		Trees t = new Trees();
		FireFly f = new FireFly(t, s);
		HighScoreState hs = new HighScoreState(s);

		g.addRenderable(t);
		g.addUpdatable(t);

		g.addRenderable(f);
		g.addUpdatable(f);

		g.addRenderable(s);
		g.addUpdatable(s);

		g.addRenderable(hs);
		g.addUpdatable(hs);

		g.start();
	}
}
