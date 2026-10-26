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
	private static final int RADIUS=50; //Normally 20
	private int xPos, yPos, xVelocity, yVelocity, xAcceleration, yAcceleration;
	private int centerX;
	private int centerY;
	private Color currentColor;
	private int up, down, left, right;
  private double collisionDirectionX;
  private double collisionDirectionY;
	
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

    this.collisionDirectionX=0.0;
    this.collisionDirectionY=0.0;
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
		//Updates will happen from manager.
	}

  public void updateMovement()
  {
    xVelocity += xAcceleration;
		yVelocity += yAcceleration;
		xPos += xVelocity;
		yPos += yVelocity;
		centerX = xPos + RADIUS;
		centerY = yPos + RADIUS;
  }
	
	public void checkWallCollision()
	{
		//Check all the walls and check if the puck is colliding with any of them. If it is, then flip the direction of the puck.
		//Ex. Hit on top or bottom section, flip yVelocity.
		if(yPos <= game.getScreen().getBoard().getTopBounds() || (yPos+RADIUS*2) >= game.getScreen().getBoard().getBottomBounds())
		{
			yVelocity = -yVelocity;
		}
		//Ex. Hit on side sections, flip xVelocity.
		if(xPos <= game.getScreen().getBoard().getLeftBounds() || (xPos+RADIUS*2) >= game.getScreen().getBoard().getRightBounds())
		{
			xVelocity = -xVelocity;
		}
	}
	
	public void checkMovingObjectCollision(Puck otherPuck)
	{
		//Use squared distances instead to save performance cost of using square root!
			if(otherPuck!= this)
			{
        //calculate and store distance
        double puck1CenterY = this.getCenterYPos();
        double puck1CenterX = this.getCenterXPos();
        double puck2CenterY = otherPuck.getCenterYPos();
        double puck2CenterX = otherPuck.getCenterXPos();
        double xDistance = puck2CenterX-puck1CenterX;
        double yDistance = puck2CenterY-puck1CenterY;
        double distanceSquared = xDistance*xDistance + yDistance*yDistance;
        double radiusSum = otherPuck.getRadius() + this.getRadius();
        if(distanceSquared < (radiusSum * radiusSum))
				{
					//Collision
					this.setColor(Color.RED);
					otherPuck.setColor(Color.RED);

          //Calculate collision direction
          double distance = Math.sqrt(distanceSquared);
          this.collisionDirectionX = xDistance / distance;
          this.collisionDirectionY = yDistance / distance;

          //Caculate overlap to be able to shift pucks back
          double overlap = radiusSum - distance;
          this.xPos -= collisionDirectionX * overlap/2;
          this.yPos -= collisionDirectionY * overlap/2;
          this.xAcceleration = 0;
          this.yAcceleration = 0;
          otherPuck.xPos += collisionDirectionX * overlap/2;
          otherPuck.yPos += collisionDirectionY * overlap/2;
          otherPuck.xAcceleration=0;
          otherPuck.yAcceleration=0;

          double relativeXVelocity = otherPuck.xVelocity - this.xVelocity;
          double relativeYVelocity = otherPuck.yVelocity - this.yVelocity;
          double velocityAlongCollisionNormal = relativeXVelocity*collisionDirectionX + relativeYVelocity*collisionDirectionY;

          if (velocityAlongCollisionNormal > 0) {
            //Skip the impulse, pucks already moving apart from previous frame calculation and impulse.
            return;
          }
          
          double puckRestitution = 1.0;
          double puckMass = 1.0;
          double impulseForce = (-1*(1+puckRestitution)*velocityAlongCollisionNormal)/((1/puckMass)+(1/puckMass));

          double xImpulse = impulseForce*collisionDirectionX;
          double yImpulse = impulseForce*collisionDirectionY;

          //Apply the impulse
          this.xVelocity-=xImpulse/puckMass;
          this.yVelocity-=yImpulse/puckMass;
          otherPuck.xVelocity+=xImpulse/puckMass;
          otherPuck.yVelocity+=yImpulse/puckMass;
				}
				else
				{
					this.setColor(Color.BLUE);
					otherPuck.setColor(Color.BLUE);
				}
			}
	}
	
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
    g.setColor(Color.ORANGE);
    g.drawLine(this.getCenterXPos(), this.getCenterYPos(), (int)(this.getCenterXPos()+(25*collisionDirectionX)), (int)(this.getCenterYPos()+(25*collisionDirectionY)));
	}
}
