/**
 * Handles what the mouse and keyboard does. Essentially what java.awt.Robot is, but more.
 * I really really wanted this to print "Domo Arigato"
 * 
 * Coded by Henry Gan.
 */

import java.awt.Color;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.Random;

public class MrRoboto {
	
	/**
     * Sets the position of the mouse to coordinate (x,y)
     *
     */
	public static void MoveMouse(int x, int y) throws Exception {
		Robot OptimusPrime = new Robot();
		OptimusPrime.mouseMove(x, y);
	}
	
	/**
     * Left Click
     *
     */
	public static void ClickMouse() throws Exception {
		Robot Megatron = new Robot();
		Megatron.mousePress(InputEvent.BUTTON1_MASK);
		Megatron.mouseRelease(InputEvent.BUTTON1_MASK);
	}
	
	/**
     * Delay a certain amount of time (in ms)
     *
     */
	public static void Wait(int x) throws Exception{
		Robot Bumblebee = new Robot();
		Bumblebee.delay(x);
	}
	
	/**
     * Gets the color of the pixel on the screen at position (x,y)
     *
     */
	public static Color getColor(int x, int y) throws Exception{
		Robot Qalyn = new Robot();
		return Qalyn.getPixelColor(x, y);
	}
	
	/**
     * Presses Ctrl + N
     *
     */
	public static void Close() throws Exception {
		Robot Robby = new Robot();
		Robby.keyPress(KeyEvent.VK_CONTROL);
		Robby.keyPress(KeyEvent.VK_E);
		Robby.delay(15);
		Robby.keyRelease(KeyEvent.VK_E);
		Robby.keyRelease(KeyEvent.VK_CONTROL);
	}
	
	/**
     * Presses B
     *
     */
	/*public static void Back() throws Exception {
		robot.keyPress(KeyEvent.VK_B);
		robot.delay(15);
		robot.keyRelease(KeyEvent.VK_B);
	}
	*/
	/**
     * Presses F
     *
     */
	/*public static void Forward() throws Exception {
		robot.keyPress(KeyEvent.VK_F);
		robot.delay(15);
		robot.keyRelease(KeyEvent.VK_F);
	}
	*/
	/**
     * Generates a random string message for the human based on the input integer x\
     * 0 = win
     * 1 = tie
     * 2 = lose
     * 3 = taunt
     *
     */
	public String GenString(int x){
		String Message = null;
		Random rand = new Random();
		String[] Win = {"Congrats! You played perfectly!", "Your win was out of my calculations.",
				"You won? But...these...are not the droids...I'm looking for?", 
				"Syntax error. Cannot compute loss.", "GG. You win. I will defeat you next time."};
		String[] Lose = {"You lost! Muahahaha!", "Better luck next time!", "Another one bites the dust",
				"I beat you with my eyes closed! Oh wait...", "You lose! Maybe there should be a handicap.",
				"My processor defeated your processor", "All I do is win win win no matter what.", "n00b against my 1337 skillz"};
		String[] Taunt = {"Do you even lift bro?", "1v1 me bro.", "Get on my level.",
				"I only need 15 seconds to think.", "This isn't even my final form",
				"I don't even need an Intel i7 QuadCore Processor to defeat you", "I don't even need to run in parallel!",
				"Look ma! Only one arm!"};
		if(x == 0){
			Message = Win [rand.nextInt(Win.length)];
		}
		if(x == 1){
			Message = "You tied! However, in Connect 4, a tie is considered a win for whoever goes 2nd because that means Player 2 outplayed Player 1. So I won. Haha.";
		}
		if(x == 2){
			Message = Lose [rand.nextInt(Lose.length)];
		}
		if(x == 3){
			Message = Taunt [rand.nextInt(Taunt.length)];
		}
		return Message;
	}
}
