/*
 * File name: BAHEScreen.java
 *
 * Programmer: Michael Jesus Reyes
 *
 * Date: Nov 11, 2017
 */
package reyesProjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * <Insert C;ass Description Here>
 *
 * @author Michael Jesus Reyes
 *
 */
public class BAHEScreen extends JPanel implements ActionListener, KeyListener
{
	private BAHE game;
	private Board board;
	private Puck[] movingObjects;
	//private Puck puck1;
	//private Puck puck2;
	
	public BAHEScreen(BAHE game)
	{
		this.game = game;
		setBackground(Color.BLACK);
		
		board = new Board(game);
		movingObjects = new Puck[2];
		//puck1 = new Puck(game, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D, 100, 100);
		//puck2 = new Puck(game, 0, 0, 0, 0, 150, 100); // The zeros mean no keys are assigned?
		movingObjects[0] = new Puck(game, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D, 100, 100);
		movingObjects[1] = new Puck(game, 0, 0, 0, 0, 150, 100); // The zeros mean no keys are assigned?
		//movingObjects[2] = new Puck(game, 0, 0, 0, 0, 250, 200); // The zeros mean no keys are assigned?
		
		Timer timer = new Timer(5,this);
		timer.start();
		
		addKeyListener(this);
		setFocusable(true);
	}
	
	public Puck[] getMovingObjects()
	{
		return movingObjects;
	}
	
	public void update()
	{
		//update all moving objects
		movingObjects[0].update();
		movingObjects[1].update();
		//movingObjects[0].checkPuckCollision(movingObjects[1]);
		//movingObjects[1].checkPuckCollision(movingObjects[0]);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		update();
		repaint();
	}
	
	@Override
	public void keyPressed(KeyEvent e)
	{
		movingObjects[0].keyPressed(e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		movingObjects[0].keyReleased(e.getKeyCode());
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
		;		
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		board.paint(g);
		movingObjects[0].paint(g);
		movingObjects[1].paint(g);
		g.drawLine(movingObjects[0].getCenterXPos(), movingObjects[0].getCenterYPos(), movingObjects[1].getCenterXPos(), movingObjects[1].getCenterYPos());
	}
}
