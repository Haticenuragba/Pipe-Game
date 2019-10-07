import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

import javax.print.DocFlavor.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DrawPanel extends JPanel {

	public Pipes pipes[][] = new Pipes[10][15];;

	public int lineIndex;
	public int colIndex;
	int programExecuted = 0;
	SecureRandom randomNumbers = new SecureRandom();
	int random;
	int initialX = 10;
	int initialY = 10;

	public Pipes[][] getPipes() {
		return pipes;
	}

	public void setPipes(Pipes[][] pipes) {
		this.pipes = pipes;
	}

	public DrawPanel() {
		 super();
		this.setSize(750, 500);
		this.setLayout(null);
		
	        this.setOpaque(true);
	        this.setBackground(Color.cyan);

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 15; j++) {
				random = randomNumbers.nextInt(3);
				if (random == 0) {
					pipes[i][j] = new IPipe();

				} else if (random == 1) {
					pipes[i][j] = new LPipe();

				} else if (random == 2) {
					pipes[i][j] = new LPipe();

				}

				pipes[i][j].setX(initialX + j * Pipes.w);
				pipes[i][j].setY(initialY + i * Pipes.h);

			}

		}

		this.addMouseListener(new MyMouseHandler());

		Image img = new ImageIcon().getImage();
		if (pipes[Main.random1][0] instanceof LPipe) {
			pipes[Main.random1][0].setImg(new ImageIcon("L_pipeBlue.jpg").getImage());

		} else {
			pipes[Main.random1][0].setImg(new ImageIcon("I_pipeBlue.jpg").getImage());
		}
		if (pipes[Main.random2][14] instanceof LPipe) {
			pipes[Main.random2][14].setImg(new ImageIcon("L_pipeBlue.jpg").getImage());

		} else {
			pipes[Main.random2][14].setImg(new ImageIcon("I_pipeBlue.jpg").getImage());
		}

	}

	public void paintComponent(Graphics g) {
		setOpaque(true);
		if (MyActionHandler.gameOver == 0) {
			if (programExecuted == 0) {

				for (int i = 0; i < 10; i++) {
					for (int j = 0; j < 15; j++) {
						SecureRandom randomNumbers = new SecureRandom();
						int random = randomNumbers.nextInt(4);
						for (int a = 0; a <= random; a++) {
							pipes[i][j].Rotate(g);
						}
						pipes[i][j].draw(g);

					}

				}
				this.setBackground(Color.cyan);
				programExecuted++;

			} else {

				pipes[lineIndex][colIndex].Rotate(g);

			}
		} else {

			for (int i = 0; i < MyActionHandler.trackPipes.size(); i++) {
				if (MyActionHandler.trackPipes.get(i) instanceof LPipe) {
					((Pipes) MyActionHandler.trackPipes.get(i)).setImg(new ImageIcon("L_pipeBlue.jpg").getImage());
				} else {
					((Pipes) MyActionHandler.trackPipes.get(i)).setImg(new ImageIcon("I_pipeBlue.jpg").getImage());

				}
				Graphics2D g2d = (Graphics2D) g;
				AffineTransform at = AffineTransform.getRotateInstance(
						Math.toRadians(((Pipes) MyActionHandler.trackPipes.get(i)).getCurrentAngle() - 90),
						((Pipes) MyActionHandler.trackPipes.get(i)).getX() + Pipes.w / 2,
						((Pipes) MyActionHandler.trackPipes.get(i)).getY() + Pipes.h / 2);
				g2d.setTransform(at);

				((Pipes) MyActionHandler.trackPipes.get(i)).draw(g);

			}

		}

	}

	public class MyMouseHandler implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			try {
				AudioInputStream audioInputStream = AudioSystem
						.getAudioInputStream(new File("clickSound.wav").getAbsoluteFile());
				Clip clip = AudioSystem.getClip();
				clip.open(audioInputStream);
				clip.start();
			} catch (Exception ex) {
				System.out.println("Error with playing sound.");
				ex.printStackTrace();
			}
			lineIndex = (e.getY() - initialY) / Pipes.h;
			colIndex = (e.getX() - initialX) / Pipes.w;
			if (lineIndex < 10 && colIndex < 15) {

				repaint();

			}

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

	/*
	 * public class MyActionHandler implements ActionListener{
	 * 
	 * 
	 * @Override public void actionPerformed(ActionEvent e) { //fromLeft=1,
	 * fromTop=2, fromRight=3, fromBottom=4 int starting = Main.random1; int ending
	 * = Main.random2; int X = 0; int Y = starting; int direction = 1; for(int i=0;
	 * i<150; i++) { if(direction==1) {
	 * if(pipes[Y][X].isTop()==true&&pipes[Y-1][X].isBottom()==true) { Y = Y-1;
	 * direction = 4; } else
	 * if(pipes[Y][X].isRight()==true&&pipes[Y][X+1].isLeft()==true) { X = X + 1;
	 * direction = 1; } else
	 * if(pipes[Y][X].isBottom()==true&&pipes[Y+1][X].isTop()==true) { Y = Y + 1;
	 * direction = 2; } else { System.out.println("Game Over"); } } else
	 * if(direction==2) {
	 * if(pipes[Y][X].isRight()==true&&pipes[Y][X+1].isLeft()==true) { X = X + 1;
	 * direction = 1; } else
	 * if(pipes[Y][X].isBottom()==true&&pipes[Y+1][X].isTop()==true) { Y = Y + 1;
	 * direction = 2; } else
	 * if(pipes[Y][X].isLeft()==true&&pipes[Y][X-1].isRight()==true) { X = X -1;
	 * direction = 3; } } else if(direction==3) {
	 * if(pipes[Y][X].isTop()==true&&pipes[Y-1][X].isBottom()==true) { Y = Y-1;
	 * direction = 4; } else
	 * if(pipes[Y][X].isLeft()==true&&pipes[Y][X-1].isRight()==true) { X = X -1;
	 * direction = 3; } else
	 * if(pipes[Y][X].isBottom()==true&&pipes[Y+1][X].isTop()==true) { Y = Y + 1;
	 * direction = 2; } } else if(direction==4) {
	 * if(pipes[Y][X].isTop()==true&&pipes[Y-1][X].isBottom()==true) { Y = Y-1;
	 * direction = 4; } else
	 * if(pipes[Y][X].isRight()==true&&pipes[Y][X+1].isLeft()==true) { X = X + 1;
	 * direction = 1; } else
	 * if(pipes[Y][X].isLeft()==true&&pipes[Y][X-1].isRight()==true) { X = X -1;
	 * direction = 3; } }
	 * 
	 * } }
	 * 
	 * 
	 * 
	 * }
	 * 
	 */

}