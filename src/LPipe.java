import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.swing.ImageIcon;


public class LPipe extends Pipes{

	public LPipe(){
		this.setImg(new ImageIcon("L_pipe.jpg").getImage());
		this.setTop(true);
		this.setBottom(false);
		this.setRight(true);
		this.setLeft(false);
	}

	public void draw(Graphics g) {
		g.drawImage(this.getImg(), getX(), getY(), w, h, null);
		
	}

	
}