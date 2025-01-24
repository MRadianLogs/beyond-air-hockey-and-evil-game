/*
 * File name: Barrier.java
 *
 * Programmer: Michael Jesus Reyes
 *
 * Date: Nov 11, 2017
 */
package reyesProjects;

import java.awt.Graphics;
import java.awt.Polygon;
import java.util.Random;

/**
 * <Insert C;ass Description Here>
 *
 * @author Michael Jesus Reyes
 *
 */
public class Barrier
{
	private int difficulty; //May be used in future to have multiple barrier gaps.
	//private int gameWidth;      // =800-5;
	//private int gameHeight;     // =500-35;
	//private int scoreBoardHeight;     // = 75;
	//private int boardWidth;        //= 20;
	private int gap = 100; 
	private int barrierWidth = 150;
	private int barrierGapBuffer = 40;
	private int barrierMiddleGapXRange = 20;
	
	private int randomYLeft;// = randomWithRange((scoreBoardHeight+boardWidth+barrierGapBuffer), (gameHeight-boardWidth-barrierGapBuffer-gap)); //min, max
	private int randomYRight;// = randomWithRange((scoreBoardHeight+boardWidth+barrierGapBuffer), (gameHeight-boardWidth-barrierGapBuffer-gap));
	private int randomYMiddle;// = randomWithRange((scoreBoardHeight+boardWidth+barrierGapBuffer), (gameHeight-boardWidth-barrierGapBuffer-gap));
	private int randomXMiddle;// = randomWithRange( ((gameWidth/2)-barrierMiddleGapXRange), ((gameWidth/2)+barrierMiddleGapXRange));
	
	private Polygon topBarrier;
	private Polygon bottomBarrier;
	
	private int[] topBarrierXCords;// = {(gameWidth/2)-(barrierWidth/2), (gameWidth/2)+(barrierWidth/2), (gameWidth/2)+(barrierWidth/2), randomXMiddle, (gameWidth/2)-(barrierWidth/2)};
	private int[] topBarrierYCords;// = {scoreBoardHeight+boardWidth, scoreBoardHeight+boardWidth, randomYRight, randomYMiddle+6, randomYLeft};
	private int[] bottomBarrierXCords;// = {(gameWidth/2)-(barrierWidth/2), (gameWidth/2)+(barrierWidth/2), (gameWidth/2)+(barrierWidth/2), randomXMiddle, (gameWidth/2)-(barrierWidth/2)};
	private int[] bottomBarrierYCords;// = {gameHeight-boardWidth, gameHeight-boardWidth, randomYRight+gap, randomYMiddle+gap, randomYLeft+gap};
	
	public Barrier(int gameWidth, int gameHeight, int scoreBoardHeight, int boardWidth)
	{
		//this.gameWidth = gameWidth;
		//this.gameHeight = gameHeight;
		//this.scoreBoardHeight = scoreBoardHeight;
		//this.boardWidth = boardWidth;
		
		this.randomYLeft = randomWithRange((scoreBoardHeight+boardWidth+barrierGapBuffer), (gameHeight-boardWidth-barrierGapBuffer-gap)); //min, max
		this.randomYRight = randomWithRange((scoreBoardHeight+boardWidth+barrierGapBuffer), (gameHeight-boardWidth-barrierGapBuffer-gap));
		this.randomYMiddle = randomWithRange((scoreBoardHeight+boardWidth+barrierGapBuffer), (gameHeight-boardWidth-barrierGapBuffer-gap));
		this.randomXMiddle = randomWithRange( ((gameWidth/2)-barrierMiddleGapXRange), ((gameWidth/2)+barrierMiddleGapXRange));
		
		this.topBarrierXCords = new int[] {(gameWidth/2)-(barrierWidth/2), (gameWidth/2)+(barrierWidth/2), (gameWidth/2)+(barrierWidth/2), randomXMiddle, (gameWidth/2)-(barrierWidth/2)};
		this.topBarrierYCords = new int[] {scoreBoardHeight+boardWidth, scoreBoardHeight+boardWidth, randomYRight, randomYMiddle+6, randomYLeft};
		this.bottomBarrierXCords = new int[] {(gameWidth/2)-(barrierWidth/2), (gameWidth/2)+(barrierWidth/2), (gameWidth/2)+(barrierWidth/2), randomXMiddle, (gameWidth/2)-(barrierWidth/2)};
		this.bottomBarrierYCords = new int[] {gameHeight-boardWidth, gameHeight-boardWidth, randomYRight+gap, randomYMiddle+gap, randomYLeft+gap};
		
		topBarrier = new Polygon(topBarrierXCords, topBarrierYCords, 5);
		bottomBarrier = new Polygon(bottomBarrierXCords, bottomBarrierYCords, 5);
	}
	
	public int randomWithRange(int min, int max)
	{
		Random rand = new Random();
		int randomNum = rand.nextInt(max - min + 1) + min;
		return randomNum;
	}
	
	public Polygon getTopBarrierBounds() //Will return an array of all the bounds for the top barrier. In the ball class, it will go through the array and check if it intercepts any of the bounds and needs to rebound.
	{
		return topBarrier;
	}
	
	public Polygon getBottomBarrierBounds() //Will return an array of all the bounds for the bottom barrier
	{
		return bottomBarrier;
	}
	
	public void paint(Graphics g)
	{
		g.fillPolygon(topBarrier);
		g.fillPolygon(bottomBarrier);
	}
}
