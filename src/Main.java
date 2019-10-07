import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.security.SecureRandom;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Main {

	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	static SecureRandom randomNumbers = new SecureRandom();
	public static final int random1 = randomNumbers.nextInt(10);
	public static final int random2 = randomNumbers.nextInt(10);
	public static DrawPanel panel = new DrawPanel();
	public static JButton b = new JButton();
	public static JFrame frame = new JFrame();

	public static void main(String[] args) {

		System.out.println("Starting " + random1);
		System.out.println("Ending " + random2);

		panel.setSize(770, 520);
		panel.setBackground(Color.CYAN);
		
		frame.getContentPane().setLayout(null);
		
		frame.setSize(780, 610);
		frame.setResizable(false);
		frame.setBackground(Color.cyan);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.add(panel);

		b.setText("Flow!");
		b.setSize(750, 50);
		b.setBackground(Color.cyan);
		b.setLocation(10, 520);
		b.setFont(new Font("Arial", Font.BOLD, 40));
		b.setForeground(Color.white);
		b.addActionListener(new MyActionHandler());
		frame.add(b);
	}
}
