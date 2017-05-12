public class Star extends Sprite{

	private final int INITIAL_X = 1296;
	private final int INITIAL_Y = 734;

	public Star(int x, int y){

		super(x,y);
		initStar();
	}
	private void initStar(){

		loadImage("images\\star.png");
		getImageDimensions();
		setVisible(true);
	}
	public void move()
	{
		boolean flip = true;
		if(x<0)
			x = INITIAL_X;
		x-=3;

		/*if(flip){
			if(y == 0){
				flip = false;
				y += 3;}
			}
		if(!flip){
			if(y>= 734){
				flip = true;
				y-=3;}
			}
		*/
	}
}//end of class