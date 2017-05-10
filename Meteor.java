public class Meteor extends Sprite
{
	private final int INITIAL_X = 1296; //The edge of the board

	public Meteor(int x, int y)
	{
		super(x,y);
		initMeteor();
	}

	private void initMeteor()
	{
		loadImage("images\\Meteor.png");
		getImageDimensions();
	}

	public void move()
	{
		if (x<0)
			x = INITIAL_X;
		x -=1;
	}
}