/*
 * File name: Board.java
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
public class Board
{
	//private BAHE game; Not needed? Because of gameVars
	private int gameHeight;
	private int gameWidth;
	private int scoreBoardHeight = 75;
	private int scoreBoardWidth = 100;
	private Color scoreBoardColor = new Color(55, 247, 247); //Neon light bluish
	private int boardWidth = 20;
	private Color boardWallColor = new Color(0, 255, 0); //Neon green
	private Barrier barrier;
	private Color barrierColor = new Color(55, 30, 216);
	
	public Board(BAHE game)
	{
		//this.game = game; Not needed? Because of gameVars
		gameHeight = game.getHeight()-35; //35 pixels cut off on bottom. 
		gameWidth = game.getWidth()-5; //5 pixels cut off on side.
		barrier = new Barrier(gameWidth, gameHeight, scoreBoardHeight, boardWidth);
	}
	
	//Needed?
	public void update()
	{
		
	}

	public int getLeftBounds()
	{
		return boardWidth;
	}
	
	public int getRightBounds()
	{
		return gameWidth - boardWidth;
	}
	
	public int getTopBounds()
	{
		return scoreBoardHeight + boardWidth;
	}
	
	public int getBottomBounds()
	{
		return gameHeight - boardWidth;
	}

	public void paint(Graphics g)
	{
		//Drawing Board
		g.setColor(boardWallColor);
		g.fillRect(0, scoreBoardHeight, gameWidth, gameHeight-scoreBoardHeight);
		g.setColor(Color.BLACK);
		g.fillRect(boardWidth, scoreBoardHeight+boardWidth, gameWidth-(boardWidth*2), gameHeight-scoreBoardHeight-(boardWidth*2));
		
		//Drawing ScoreBoard
		g.setColor(scoreBoardColor);
		g.drawLine(gameWidth/2, 0, gameWidth/2, scoreBoardHeight);
		g.fillRect((gameWidth/2)+1, 0, scoreBoardWidth, scoreBoardHeight);
		g.fillRect((gameWidth/2)-scoreBoardWidth-1, 0, scoreBoardWidth, scoreBoardHeight);
		
		//Drawing barrier
		g.setColor(barrierColor);
		barrier.paint(g);
	}
}
