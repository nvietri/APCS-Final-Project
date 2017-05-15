
import java.awt.EventQueue;
import javax.swing.JFrame;

public class Application extends JFrame
{
	public Application()
	{
		initUI();//Calls the init method
	}

	private void initUI()//Sets up the playing field
	{
		add(new Board());//Adds the board

		//MAY NEED TO CHANGE THIS DEPENDING ON PICTURE SIZE ????
		setSize(1296,734);//Sets the size

		setTitle("NyanCat");//Name of window
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Closes window
		setLocationRelativeTo(null);//Sets it in the middle of the screen
	}

	public static void main(String[] args)
	{
		//This is an open parenthesis
		EventQueue.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				Application app = new Application();
				app.setVisible(true);
			}
		});//END PARENTHESIS, WIERD BUT NEEDED
	}
}