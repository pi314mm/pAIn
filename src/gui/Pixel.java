package gui;

import java.awt.Color;
import java.util.Random;

public class Pixel {
	private int life = 255 * 2;
	private static Random rand = new Random();
	public static final int lifeSpan = 10000;

	public int helpfulness = 100;
	public static final int increase = 10;
	public static final int decrease = 10;

	public int getLife() {
		return life;
	}

	public Color getColor() {
		Color c = Color.WHITE;
		try {
			c = new Color(Integer.min(255 * 2 - life, 255), Integer.min(255, life), 0);
		} catch (Exception e) {
			System.err.println(life);
		}
		return c;
	}

	public void decriment(boolean help) {
		life -= decrease;
		life = Integer.max(life, 0);
		if (!help) {
			if (rand.nextBoolean()) {
				helpfulness++;
				helpfulness = Integer.min(helpfulness, 100);
			}
		}
	}

	public void increment(boolean help) {
		if (!isDead() || help) {
			life += increase;
			life = Integer.min(life, 255 * 2);
		}
		if (!help) {
			if (rand.nextBoolean()) {
				helpfulness++;
				helpfulness = Integer.min(helpfulness, 100);
			}
		} else {
			if (rand.nextBoolean()) {
				helpfulness--;
				helpfulness = Integer.max(helpfulness, 0);
			}
		}
	}

	public void kill() {
		life = 0;
	}

	public boolean isDead() {
		return life == 0;
	}

	public void update(int time) {
		if (rand.nextInt(lifeSpan) > time) {
			increment(false);
		} else {
			decriment(false);
		}
	}

	public void help() {
		if (rand.nextInt(100) < helpfulness) {
			increment(true);
		} else {
			decriment(true);
		}

	}

}
