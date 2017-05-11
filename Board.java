import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener
{
	private Timer timer;
	private NyanCat cat;
	private ArrayList<Meteor> meteors;
	private boolean ingame;
	private final int ICAT_X = 40;
	private final int ICAT_Y = 60;
	private final int B_WIDTH = 1296;
	private final int B_HEIGHT = 734;
	private final int DELAY = 15;

	//Starting locations for the meteors
	private final int[][] pos = {
		{700, 350}, {2500, 59}, {1380, 89},
		{780, 109}, {580, 139}, {680, 239},
		{790, 259}, {760, 50}, {790, 150},
		{980, 209}, {560, 45}, {510, 70},
		{930, 159}, {590, 80}, {530, 60},
		{940, 59}, {990, 30}, {920, 200},
		{900, 259}, {660, 50}, {540, 90},
		{810, 220}, {860, 20}, {740, 180},
		{820, 128}, {490, 170}, {700, 30}
	};

	public Board()
	{
		initBoard();
	}

	public void initBoard()
	{
		addKeyListener(new TAdapter());
		setFocusable(true);
		setBackground(new Color(1,68,121));
		ingame = true;

		setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));

		cat = new NyanCat(ICAT_X, ICAT_Y);

		initMeteors();

		timer = new Timer(DELAY, this);
		timer.start();
	}

	public void initMeteors()
	{
		meteors = new ArrayList<>();

		for (int[] p : pos)
		{
			meteors.add(new Meteor(p[0],p[1]));
		}
	}

	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		if(ingame)
		{
			drawObjects(g);
		}
		else
		{
			drawGameOver(g);
		}

		Toolkit.getDefaultToolkit().sync();
	}

	private void drawObjects(Graphics g)
	{
		if(cat.isVisible())
		{
			g.drawImage(cat.getImage(), cat.getX(), cat.getY(),this);
		}

		ArrayList<Laser> las = cat.getLasers();

		for(Laser l : las)
		{
			if(l.isVisible())
			{
				g.drawImage(l.getImage(),l.getX(),l.getY(),this);
			}
		}

		for(Meteor m : meteors)
		{
			if(m.isVisible())
			{
				g.drawImage(m.getImage(),m.getX(),m.getY(),this);
			}
		}
		g.setColor(Color.WHITE);
		g.drawString("Meteors left: " + meteors.size(), 5, 15);
	}

	private void drawGameOver(Graphics g)
	{
		String message = "GAME OVER";
		Font small = new Font("Helvetica", Font.BOLD, 14);
		FontMetrics fm = getFontMetrics(small);

		g.setColor(Color.WHITE);
		g.setFont(small);
		g.drawString(message,(B_WIDTH - fm.stringWidth(message))/2,B_HEIGHT / 2);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		inGame();

		updateCat();
		updateLasers();
		updateMeteors();

		checkCollisions();

		repaint();
	}

	private void inGame()
	{
		if(!ingame)
		{
			timer.stop();
		}
	}

	private void updateCat()
	{
		if(cat.isVisible())
		{
			cat.move();
		}
	}

	private void updateLasers()
	{
		ArrayList<Laser> ls = cat.getLasers();

		for(int i = 0; i < ls.size();i++)
		{
			Laser l = ls.get(i);

			if(l.isVisible())
			{
				l.move();
			}
			else
			{
				ls.remove(i);
			}
		}
	}

	private void updateMeteors()
	{
		if(meteors.isEmpty())
		{
			ingame = false;
			return;
		}

		for(int i = 0; i < meteors.size(); i++)
		{
			Meteor m = meteors.get(i);
			if(m.isVisible())
			{
				m.move();
			}
			else
			{
				meteors.remove(i);
			}
		}
	}

	public void checkCollisions()
	{
		Rectangle rCat = cat.getBounds();

		for (Meteor m : meteors)
		{
			Rectangle rMeteor = m.getBounds();

			if (rCat.intersects(rMeteor))
			{
				cat.setVisible(false);
				m.setVisible(false);
				ingame = false;
			}
		}

		ArrayList<Laser> ls = cat.getLasers();

		for(Laser l : ls)
		{
			Rectangle rLaser = l.getBounds();

			for(Meteor m : meteors)
			{
				Rectangle rMeteor = m.getBounds();

				if (rLaser.intersects(rMeteor))
				{
					l.setVisible(false);
					m.setVisible(false);
				}
			}
		}
	}//CHECK COLLISIONS

	private class TAdapter extends KeyAdapter
	{
		@Override
		public void keyReleased(KeyEvent e)
		{
			cat.keyReleased(e);
		}

		@Override
		public void keyPressed(KeyEvent e)
		{
			cat.keyPressed(e);
		}
	}
}