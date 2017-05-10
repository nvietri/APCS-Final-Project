import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Sprite //Acts as a parent class to anything with a sprite
{
	protected int x,y,width,height;
	protected boolean vis;
	protected Image image;

	public Sprite(int x, int y)
	{
		this.x = x;//Sets coordinates
		this.y = y;
		vis = true;
	}

	protected void getImageDimensions() //Sets the images dimesnions depending on the sprite
	{
		width = image.getWidth(null);
		height = image.getHeight(null);
	}

	protected void loadImage(String imageName)//Sets the sprite's image
	{
		ImageIcon i = new ImageIcon(imageName);
		image = i.getImage();
	}

	public Image getImage()
	{
		return image;
	}

	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}

	public boolean isVisible()
	{
		return vis;
	}

	public void setVisible(boolean visible)
	{
		vis = visible;
	}

	public Rectangle getBounds()
	{
		return new Rectangle(x,y,width,height);
	}
}