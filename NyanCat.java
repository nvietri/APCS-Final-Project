import java.awt.Image;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import java.util.ArrayList;

public class NyanCat extends Sprite//implements KeyListener
{
	private int changeX, changeY;//Changes position when key is pressed
	private ArrayList<Laser> lasers;


	public NyanCat(int x, int y)
	{
		super(x,y);
		initCat();//Assigns image and starting coordinates
	}

	private void initCat()
	{
		lasers = new ArrayList<>();//All projectiles fired by Nyancat
		loadImage("images\\NyanCat.png");
		getImageDimensions();
	}

	public void move()//Moves the Sprite by changing its coordinates
	{
		x += changeX;
		y += changeY;

		if(x < 1)
			x = 1;
		if(y < 1)
			y = 1;
	}

	public ArrayList getLasers()
	{
		return lasers;
	}

	public void fire()
	{
		lasers.add(new Laser(x + width, y - height - 35));
	}

	//This listens for the player to press a key, assigned to arrow keys
	public void keyPressed(KeyEvent e)
	{
		int key = e.getKeyCode();//Key pressed variable

		if (key == KeyEvent.VK_LEFT)
		{
			changeX = -1; //If the arrow key is left
		}

		if (key == KeyEvent.VK_RIGHT)
		{
			changeX = 1;//Right arrow key
		}

		if (key == KeyEvent.VK_UP)
		{
			changeY = -1;//Up arrow
		}

		if (key == KeyEvent.VK_DOWN)
		{
			changeY = 1;
		}
		if (key == KeyEvent.VK_SPACE)
		{
			fire();
		}
	}

	//Listens for the player to release a key, and stops the sprite
	public void keyReleased(KeyEvent e)
	{
		int key = e.getKeyCode();//Key pressed variable

		if (key == KeyEvent.VK_LEFT)
		{
			changeX = 0; //If the arrow key is left
		}

		if (key == KeyEvent.VK_RIGHT)
		{
			changeX = 0;//Right arrow key
		}

		if (key == KeyEvent.VK_UP)
		{
			changeY = 0;//Up arrow
		}

		if (key == KeyEvent.VK_DOWN)
		{
			changeY = 0;
		}
	}


}