/*
 * File name: Puck.java
 *
 * Programmer: Michael Jesus Reyes
 *
 * Date: Nov 11, 2017
 */

import java.awt.Color;
import java.awt.Graphics;

public class Puck 
{
	private BAHE game;
	private static final int RADIUS=20; //Normally 20
	private int xPos, yPos, xVelocity, yVelocity, xAcceleration, yAcceleration;
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
		this.xPos = startingXPos;
		this.yPos = startingYPos;
		//How do I decide where pucks spawn?
		//3 on each side. In triangle formation?
		xVelocity = 0;
		yVelocity = 0;
		xAcceleration = 0;
		yAcceleration = 0;

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
		xVelocity += xAcceleration;
		yVelocity += yAcceleration;
		xPos += xVelocity;
		yPos += yVelocity;
		centerX = xPos + RADIUS;
		centerY = yPos + RADIUS;
		//Need to check for collisions
		checkMovingObjectCollision();
		checkWallCollision();
		//Need to check what side each puck is on WIN CONDITION
	}
	
	public void checkWallCollision()
	{
		//Check all the walls and check if the puck is colliding with any of them. If it is, then flip the direction of the puck.
		//Ex. Hit on top or bottom section, flip yVelocity.
		if(yPos <= game.getScreen().getBoard().getTopBounds() || yPos >= game.getScreen().getBoard().getBottomBounds())
		{
			yVelocity = -yVelocity;
		}
		//Ex. Hit on side sections, flip xVelocity.
		if(xPos <= game.getScreen().getBoard().getLeftBounds() || xPos >= game.getScreen().getBoard().getRightBounds())
		{
			xVelocity = -xVelocity;
		}
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
			this.yAcceleration = -1;
		}
		else if(keyCode == down)
		{
			this.yAcceleration = 1;
		}
		if(keyCode == left)
		{
			this.xAcceleration = -1;
		}
		else if(keyCode == right)
		{
			this.xAcceleration = 1;
		}
	}
	
	public void keyReleased(int keyCode)
	{
		if(keyCode == up || keyCode == down)
		{
			this.yAcceleration = 0;
		}
		if(keyCode == left || keyCode == right)
		{
			this.xAcceleration = 0;
		}
	}
	
	public void paint(Graphics g)
	{
		g.setColor(currentColor);
		g.fillOval(xPos, yPos, RADIUS*2, RADIUS*2);
	}
}
