/*
 * File name: BAHE.java
 *
 * Programmer: Michael Jesus Reyes
 *
 * Date: Nov 11, 2017
 */


import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * This is the main class for the Beyond Air Hockey and Evil Game. It is responsible for creating the JFrame(the screen), and 
 * starting the processes of the game by creating an initializing the BAHEScreen object(the visual aspect of the game on the JFrame). 
 *
 * @author Michael Jesus Reyes
 *
 */
public class BAHE extends JFrame
{
	private final static int WIDTH = 1920, HEIGHT = 1080;
	private BAHEScreen screen;
	
	public BAHE()
	{
		//setSize(WIDTH, HEIGHT);
		setTitle("Beyond Air Hockey and Evil");
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		screen = new BAHEScreen(this);
		screen.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		add(screen);
		pack();
		setVisible(true);
	}

	public int getWidth()
	{
		return WIDTH;
	}
	public int getHeight()
	{
		return HEIGHT;
	}
	
	public BAHEScreen getScreen()
	{
		return screen;
	}
	
	public static void main(String[] args)
	{
		new BAHE();
	}
}