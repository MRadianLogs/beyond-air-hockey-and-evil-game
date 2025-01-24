/*
 * File name: Puck.java
 *
 * Programmer: Michael Jesus Reyes
 *
 * Date: Nov 11, 2017
 */
package reyesProjects;

import java.awt.Color;
import java.awt.Graphics;

/**
 * <Insert C;ass Description Here>
 *
 * @author Michael Jesus Reyes
 *
 */
public class Puck 
{
	private BAHE game;
	private static final int RADIUS=20; //Normally 20
	private int x, y, xa, ya;
	private int centerX;
	private int centerY;
	private Color currentColor;
	private int up, down, left, right;
	
	public Puck(BAHE game, int upKey, int downKey, int leftKey, int rightKey, int startingXPos, int startingYPos)
	{
		this.game = game;
		this.up = upKey;
		this.down = downKey;
		this.left = leftKey;
		this.right = rightKey;
		this.x = startingXPos;
		this.y = startingYPos;
		//How do I decide where pucks spawn?
		//3 on each side. In triangle formation?
		
		this.setColor(Color.BLUE);
	}
	
	public int getRadius()
	{
		return RADIUS;
	}
	
	public int getCenterXPos()
	{
		return centerX;
	}
	
	public int getCenterYPos()
	{
		return centerY;
	}
	
	public void setColor(Color newColor)
	{
		this.currentColor = newColor;
	}
	
	public Color getColor()
	{
		return currentColor;
	}
	
	public void update()
	{
		x += xa;
		y += ya;
		centerX = x + RADIUS;
		centerY = y + RADIUS;
		//Need to check for collisions
		checkMovingObjectCollision();
		//Need to check what side each puck is on WIN CONDITION
	}
	
	public void checkWallCollision(Barrier wall)
	{
		
		//Ex. Hit on top section, flip ya.
		//Ex. Hit on side sections, flip xa.
		//Ex. Hit on top corner, flip xa and xy.
	}
	
	public void checkMovingObjectCollision()
	{
		Puck[] movingObjects = this.game.getScreen().getMovingObjects();
		for(int i = 0; i < movingObjects.length; i++)
		{
			if(movingObjects[i]!= this)
			{
				if(Math.sqrt((double)(Math.pow((movingObjects[i].getCenterYPos()-this.getCenterYPos()),2) + Math.pow((movingObjects[i].getCenterXPos()-this.getCenterXPos()),2))) <= (this.getRadius() + movingObjects[i].getRadius()))
				{
					//Collision
					this.setColor(Color.RED);
					movingObjects[i].setColor(Color.RED);
				}
				else
				{
					this.setColor(Color.BLUE);
					movingObjects[i].setColor(Color.BLUE);
				}
			}
		}
	}
	
	/*
	public void checkPuckCollision(Puck otherPuck)
	{
		//If combination of two radius >= distance from centers then collision.
		// Distance between two points is: d = sqrt((x2-x1)^2+(y2-y1)^2)
		System.out.println("Distance between circles: " + (Math.sqrt((double)(Math.pow((otherPuck.getCenterYPos()-this.getCenterYPos()),2) + Math.pow((otherPuck.getCenterXPos()-this.getCenterXPos()),2)))));
		System.out.println("Sum of radii: " + (this.getRadius() + otherPuck.getRadius()));
		if(Math.sqrt((double)(Math.pow((otherPuck.getCenterYPos()-this.getCenterYPos()),2) + Math.pow((otherPuck.getCenterXPos()-this.getCenterXPos()),2))) <= (this.getRadius() + otherPuck.getRadius()))
		{
			//Collision
			this.setColor(Color.RED);
			otherPuck.setColor(Color.RED);
		}
		else
		{
			this.setColor(Color.BLUE);
			otherPuck.setColor(Color.BLUE);
		}
	}
	*/
	
	public void keyPressed(int keyCode)
	{
		if(keyCode == up)
		{
			this.ya = -1;
		}
		else if(keyCode == down)
		{
			this.ya = 1;
		}
		if(keyCode == left)
		{
			this.xa = -1;
		}
		else if(keyCode == right)
		{
			this.xa = 1;
		}
	}
	
	public void keyReleased(int keyCode)
	{
		if(keyCode == up || keyCode == down)
		{
			this.ya = 0;
		}
		if(keyCode == left || keyCode == right)
		{
			this.xa = 0;
		}
	}
	
	public void paint(Graphics g)
	{
		g.setColor(currentColor);
		g.fillOval(x, y, RADIUS*2, RADIUS*2);
	}
}
