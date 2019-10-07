import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.swing.ImageIcon;


public class IPipe extends Pipes{

	public IPipe(){
		this.setImg(new ImageIcon("I_pipe.jpg").getImage());
		this.setTop(true);
		this.setBottom(true);
		this.setLeft(false);
		this.setRight(false);
	}

	public void draw(Graphics g) {
		g.drawImage(this.getImg(), getX(), getY(), w, h, null);
		
	}
	
}
