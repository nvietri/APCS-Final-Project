public class Star extends Sprite{

	private final int INITIAL_X = 1296;

	public Star(int x, int y){

		super(x,y);
		initStar();
	}
	private void initStar(){

		loadImage("images\\Star.png");
		getImageDimentsions();
	}
	public void move()
	{
		if(x<0)
			x = INITIAL_X;
		x-=2;
	}
}//end of class