public class Laser extends Sprite
{
	private final int BOARD_WIDTH = 1296;
	private final int LASER_SPEED = 2; //Speed of missles, should be faster than the cat

	public Laser(int x, int y)
	{
		super(x,y);
		initLaser();
	}

	private void initLaser()
	{
		loadImage("images\\laser.png");
		getImageDimensions();
	}

	public void move()
	{
		x += LASER_SPEED;
		if (x > BOARD_WIDTH)
			vis = false;
	}
}