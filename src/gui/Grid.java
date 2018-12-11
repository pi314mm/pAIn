package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class Grid extends JPanel {

	private int size = 800;
	private int scale = 2;
	private int mouseSize = 50;
	private static final long serialVersionUID = 1L;


	private Pixel[][] grid = new Pixel[size / scale][size / scale];

	private int afterDead = 50;

	public Grid() {
		super();
		setPreferredSize(new Dimension(size, size));
		setMinimumSize(new Dimension(size, size));
		setMaximumSize(new Dimension(size, size));
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				grid[i][j] = new Pixel();
			}
		}
		MouseAdapter mouse = new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getX() / scale;
				int y = e.getY() / scale;
				for (int i = x - mouseSize / 2; i < x + mouseSize / 2; i++) {
					for (int j = y - mouseSize / 2; j < y + mouseSize / 2; j++) {
						if (0 <= i && i < grid.length && 0 <= j && j < grid[i].length) {
							grid[i][j].help();
						}
					}
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}
		};
		addMouseListener(mouse);
		addMouseMotionListener(mouse);

	}

	@Override
	public void paint(Graphics g) {
		g.setColor(new Color(0,0,0,0));
		g.fillRect(0, 0, getWidth(), getHeight());
		int life = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length; j++) {
				grid[i][j].update(Frame.getTime());
				g.setColor(grid[i][j].getColor());
				g.fillRect(i * scale, j * scale, scale, scale);
				life += grid[i][j].getLife();
			}
		}
		if (life == 0) {
			afterDead--;
			if (afterDead == 0) {
				System.exit(0);
			}
		}
	}

	public int health() {
		int life = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length; j++) {
				life += grid[i][j].getLife();
			}
		}
		return life;
	}

	public void kill() {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				grid[i][j].kill();
			}
		}
	}
}
