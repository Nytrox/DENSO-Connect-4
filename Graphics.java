/*
 * This is essentially the Main function. It handles the majority of the code.
 * Creates the columns, reads the input/output data from TelNet(maybe PuTTY)
 * Checks wins by looking at the pixels in the Mustrum win pixels.
 * (May add string variable to each column and check wins that way) 
 * 
 * Also compiles the Column data into strings to determine the winner.
 * Every 2d board is represented as a bitboard as such.
 *
 * .  .  .  .  .  .  .  TOP
 * 5 12 19 26 33 40 47
 * 4 11 18 25 32 39 46
 * 3 10 17 24 31 38 45
 * 2  9 16 23 30 37 44
 * 1  8 15 22 29 36 43
 * 0  7 14 21 28 35 42  BOTTOM
 * 
 * There are 2 bitboards: one for Red and one for Yellow.
 * 0 represents a empty cell, 1 represents a filled cell.
 * The bitboard is stored in an unsigned 64 bit integer variable.
 * The bits 6, 13, 20, 27, 34, 41, >= 48 have to be 0.
 * 
 * I split the checkwin methods into 2 different methods because doing so
 * speeds up the code. Although marginal, in gigaflops, it's significant.
 * And also, Java isn't exactly the fastest language.
 * 
 * Coded by Henry Gan.
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.concurrent.TimeUnit;

public class Graphics{
	//Size of board is 350x300
	//Size of monitor is 1366x768
	//Monitor 2 size is 1600x900
	
	private static Column[] C = new Column[7];											//Makes an Array of 7 Columns
	private static boolean EndDemo = false;
	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	static int width = ((int) screenSize.getWidth())/2 - 203;							//WIDTH IS ScreenWidth/2 - 203
	static int height = ((int) screenSize.getHeight())/2 + 141;							//HEIGHT IS ScreenHeight/2 + 141
	
	public static void main(String[] args) throws Exception {
		int MoveHere = 0;
		
		TelNet telnet = new TelNet("192.168.0.1", "", "");								//Default IP for RC8 is 192.168.0.1, along with no password or username
		try {
    		System.out.println("Got Connection...");									//Confirms connection
    		TelNet.sendCommand("Starting");												//Honestly...not sure if this line matters
    		System.out.println("Opening Algorithm");									//Opening Mustrum
			Runtime run = Runtime.getRuntime();
			run.exec("Mustrum.exe");													//Mustrum should be in the same folder
			TimeUnit.SECONDS.sleep(5);													//Used to wait until Mustrum opens
    	} catch (Exception e) {
    		e.printStackTrace();
    		System.out.println("failed");
    	}
		
		for(int x = 0; x < 7; x++){														//Ensures the Array is null
			C[x] = new Column(width + (50*x));											//Sets the coordinate to stuff. 
		}																				//Width: 480-780
																						
		while(!EndDemo){																//Some infinite loop
			while(TelNet.getInputStream() == null){
				TimeUnit.SECONDS.sleep(1);
			}
			MoveHere = Integer.parseInt(TelNet.readUntil("9")) - 1;						//9 means the line ended. All numbers end with a nine (column 4 is sent as 49)
			System.out.println("Human went to " + MoveHere);
			if(C[MoveHere].isFull()){													//NEWLY ADDED CODE PLEASE CHECK THX BBZ URDABES
				TelNet.sendCommand("Full");
				TelNet.sendCommand(" ");
				continue;
			}
			C[MoveHere].setValue();
			MrRoboto.MoveMouse(C[MoveHere].getCoordinate(), height);					//Move the mouse to that Column
			MrRoboto.ClickMouse();
			C[MoveHere].changeYellow();													//Changes the string of the Column to Y
			TimeUnit.SECONDS.sleep(5);
			if (CheckWinYellow()){														//Checks if Player 1 won
				TelNet.sendCommand("Lose");
				System.out.println("Lose");
				resetgame();
				continue;
			}
			
			for(int i = 0; i < 7; i ++){												//Loop to go across the columns
				if(!Color.BLACK.equals(MrRoboto.getColor(width + (50*i),				//The second it sees a black (empty) tile, GO THERE
						height - (50*(C[i].getValue())))) && C[i].getValue() != 6 ){	//Height: 275-525
					System.out.println("Computer went " + i);							
					C[i].setValue();
					C[i].changeRed();													//Changes the string of the Column
					TelNet.sendCommand(String.valueOf(i));
					if (!CheckWinRed()){
						TelNet.sendCommand(" ");
					}
					else{
						TimeUnit.SECONDS.sleep(5);
						TelNet.sendCommand("Win");
						System.out.println("Win");
						resetgame();
						break;
					}
					if(C[0].getValue() == 6 && C[1].getValue() == 6 && C[2].getValue() == 6 && C[3].getValue() == 6
							&& C[4].getValue() == 6 && C[5].getValue() == 6 && C[6].getValue() == 6
							&& !CheckWinRed() && !CheckWinYellow()){
						TimeUnit.SECONDS.sleep(5);
						TelNet.sendCommand("Win");
						System.out.println("Tie");
						resetgame();
						break;
					}
					TelNet.sendCommand("Testing");
					break;																//end
				}
			}
		}
		
    	System.out.println("Disconnecting");
    	telnet.disconnect();
    	TimeUnit.SECONDS.sleep(10);
    	Runtime.getRuntime().exec("java -jar C4.jar");
    	System.out.println("RESTART");
    	System.exit(0);
	}
	
	/**
     * Checks who wins via the bitboard in Column.java and bit operations from the Fhourstones algorithm.
     * Returns a boolean value to signify if the provided game board has a winner.
     * Checks the board for Red
     */
	public static boolean CheckWinRed(){
		String key = C[0].RedtoString() + C[1].RedtoString() + C[2].RedtoString() + C[3].RedtoString()
				+ C[4].RedtoString() + C[5].RedtoString() + C[6].RedtoString();			//Combines Strings to 1D array
		long bitBoard = Long.parseLong(key, 2);
	    long diag1 = bitBoard & (bitBoard>>6); 											// check diagonal \
	    long hori = bitBoard & (bitBoard>>7); 											// check horizontal -
	    long diag2 = bitBoard & (bitBoard>>8); 											// check diagonal /
	    long vert = bitBoard & (bitBoard>>1); 											// check vertical |
	    return ((diag1 & (diag1 >> 2*6)) |
	            (hori & (hori >> 2*7)) |
	            (diag2 & (diag2 >> 2*8)) |
	            (vert & (vert >> 2))) != 0;
	}
	
	/**
     * Checks who wins via the bitboard in Column.java and bit operations from the Fhourstones algorithm.
     * Returns a boolean value to signify if the provided game board has a winner.
     * Checks the board for Yellow
     */
	public static boolean CheckWinYellow(){
		String key = C[0].YellowtoString() + C[1].YellowtoString() + C[2].YellowtoString() + C[3].YellowtoString()
				+ C[4].YellowtoString() + C[5].YellowtoString() + C[6].YellowtoString();//Combines Strings to 1D array
		long bitBoard = Long.parseLong(key, 2);
	    long diag1 = bitBoard & (bitBoard>>6); 											// check diagonal \
	    long hori = bitBoard & (bitBoard>>7); 											// check horizontal -
	    long diag2 = bitBoard & (bitBoard>>8); 											// check diagonal /
	    long vert = bitBoard & (bitBoard>>1); 											// check vertical |
	    return ((diag1 & (diag1 >> 2*6)) |
	            (hori & (hori >> 2*7)) |
	            (diag2 & (diag2 >> 2*8)) |
	            (vert & (vert >> 2))) != 0;
	}
	
	/**
     * Resets the game
	 * @throws Exception 
     *
     */
	public static void resetgame() throws Exception{									//tell robot to clear game
		TimeUnit.SECONDS.sleep(10);
		MrRoboto.Close();
		for(int x = 0; x < 7; x++){														//Ensures the Array is null
			C[x].resetValues();															//Does it twice to set preValue to 0 too
			C[x].resetString();
		}
		EndDemo = true;
	}
}
