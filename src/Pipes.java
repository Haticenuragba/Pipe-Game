import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;

public abstract class Pipes {
	
	
	private int x;
	private int y;
	private boolean top;
	private boolean right;
	private boolean bottom;
	private boolean left;
	private Image img;
	
	
	
	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}
	private int currentAngle = 90;
	public static final int h = 50; //(int) screenSize.getWidth()/15 - 20;
	public static final int w = 50; //(int) screenSize.getWidth()/15 - 20;
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public int getCurrentAngle() {
		return currentAngle;
	}

	public void setCurrentAngle(int currentAngle) {
		this.currentAngle = currentAngle;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isTop() {
		return top;
	}

	public void setTop(boolean top) {
		this.top = top;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isBottom() {
		return bottom;
	}

	public void setBottom(boolean bottom) {
		this.bottom = bottom;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}


	
	public abstract void draw(Graphics g);
	public void Rotate(Graphics g) {
		boolean temp;
		Graphics2D g2d = (Graphics2D)g;
		AffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(currentAngle), x + w/2, y + h/2);	
		g2d.setTransform(at);
		temp = top;
		top = left;
		left = bottom;
		bottom = right;
		right = temp;
		currentAngle = currentAngle + 90;
		this.draw(g);
	}


}