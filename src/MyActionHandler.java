import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MyActionHandler implements ActionListener {
	public static int gameOver = 0;
	public static ArrayList trackPipes = new ArrayList();

	@Override
	public void actionPerformed(ActionEvent e) {

		int starting = Main.random1;
		int ending = Main.random2;
		int X = 0;
		int Y = starting;
		int direction = 1;
		boolean flag = true;
		while (flag) {
			if (X == 14 && Y == ending) {
				System.out.println("You win");
				flag = false;
				Main.b.setText("You WON!");
				gameOver = 1;
			} else {

				if ((direction != 2) && (Y != 0)
						&& (Main.panel.pipes[Y][X].isTop() == true && Main.panel.pipes[Y - 1][X].isBottom() == true)) {
					Y = Y - 1;
					direction = 4;
					trackPipes.add(Main.panel.pipes[Y][X]);
				} else if ((direction != 3) && (X != 14)
						&& (Main.panel.pipes[Y][X].isRight() == true && Main.panel.pipes[Y][X + 1].isLeft() == true)) {
					X = X + 1;
					direction = 1;
					trackPipes.add(Main.panel.pipes[Y][X]);
				} else if ((direction != 4) && (Y != 9)
						&& (Main.panel.pipes[Y][X].isBottom() == true && Main.panel.pipes[Y + 1][X].isTop() == true)) {
					Y = Y + 1;
					direction = 2;
					trackPipes.add(Main.panel.pipes[Y][X]);
				} else if ((direction != 1) && (X != 0)
						&& (Main.panel.pipes[Y][X].isLeft() == true && Main.panel.pipes[Y][X - 1].isRight() == true)) {
					X = X - 1;
					direction = 3;
					trackPipes.add(Main.panel.pipes[Y][X]);
				} else {
					System.out.println("Game Over");
					flag = false;
					gameOver = 2;
				}

			}

		}

		Main.panel.repaint();

		if (gameOver == 1) {

			/*
			 * JLabel win = new JLabel("win", SwingConstants.CENTER);
			 * win.setText("YOU WON!"); win.setFont(new Font("Arial", Font.BOLD, 80));
			 * win.setForeground(Color.white);
			 * 
			 * win.setSize(Main.frame.getSize()); win.setOpaque(true);
			 * win.setBackground(Color.green); Main.panel.setVisible(false);
			 * Main.b.setVisible(false); win.setVisible(true);
			 * Main.frame.getContentPane().add(win); Main.frame.setVisible(true);
			 */
			try {
				AudioInputStream audioInputStream = AudioSystem
						.getAudioInputStream(new File("win.wav").getAbsoluteFile());
				Clip clip = AudioSystem.getClip();
				clip.open(audioInputStream);
				clip.start();
			} catch (Exception ex) {
				System.out.println("Error with playing sound.");
				ex.printStackTrace();
			}
		} else if (gameOver == 2) {
			System.out.println("Game Over");

			Main.b.setText("You LOST!");
			JLabel lost = new JLabel("lost", SwingConstants.CENTER);
			lost.setText("YOU LOST!");
			lost.setFont(new Font("Arial", Font.BOLD, 80));
			lost.setForeground(Color.white);

			lost.setSize(Main.frame.getSize());
			lost.setOpaque(true);
			lost.setBackground(Color.red);
			Main.panel.setVisible(false);
			Main.b.setVisible(false);
			lost.setVisible(true);
			Main.frame.getContentPane().add(lost);
			Main.frame.setVisible(true);
			try {
				AudioInputStream audioInputStream = AudioSystem
						.getAudioInputStream(new File("lost.wav").getAbsoluteFile());
				Clip clip = AudioSystem.getClip();
				clip.open(audioInputStream);
				clip.start();
			} catch (Exception ex) {
				System.out.println("Error with playing sound.");
				ex.printStackTrace();
			}
		}

	}
}
