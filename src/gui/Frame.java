package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class Frame implements ActionListener {

	private Timer timer = new Timer(100, this);

	private static int time = 0;

	public static int getTime() {
		return time;
	}

	private Grid grid;
	private JLabel label;

	public Frame() {
		JFrame frame = new JFrame("pAIn");

		frame.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();

		grid = new Grid();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 80;
		c.gridx = 0;
		c.gridy = 0;
		frame.add(grid, c);

		label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 20;
		c.gridx = 1;
		c.gridy = 0;
		frame.add(label, c);

		frame.setSize(1000, 1000);
		frame.setLocationRelativeTo(null);
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				grid.kill();
			}
		});
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);

		timer.start();
	}

	public static void main(String[] args) {
		new Frame();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == timer) {
			grid.repaint();
			time += 1;
			int health = grid.health();
			if (health == 0) {
				label.setText("DEAD");
				label.setForeground(Color.RED);
				label.setFont(new Font("Serif", Font.BOLD, 40));
			} else {
				label.setText(String.format("%30d", health));
			}
		}

	}

}