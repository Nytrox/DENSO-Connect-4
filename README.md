# DENSO-Connect-4
The important Connect 4 files used for the Henry DENSO Robot DemoCell

These are the java files used for my Connect 4 DemoCell for DENSO Robotics.

Graphics.java: The main function. This handles the majority of the code: creating the columns, reading the input/output data from the TelNet files, analyzes the output from Mustrum (the Connect 4 algorithm/program), and checks the winrate using the Fhourstones Benchmark. 

The Fhourstones Benchmark takes the bitboard version of each color (one for Red and one for Yellow), where 0 is an empty cell and 1 is a filled cell. The bitboard is stored in an unsigned 64 bit integer variable and with a few simple bitwise operations, it can determine if a board state is in a win, loss, or tie in constant time O(1) (instead of searching manually for all winning possibilities, which is in degree O(N^2) to O(N^3))


Column.java: Used to keep track of the tiles in a given column. This is used to easily track where the computer/human moved. Instead of searching all 42 tiles, this function will allow the code to only search through 7 columns. This code also creates a bitboard similar to this structure
 * .  .  .  .  .  .  .  TOP
 * 5 12 19 26 33 40 47
 * 4 11 18 25 32 39 46
 * 3 10 17 24 31 38 45
 * 2  9 16 23 30 37 44
 * 1  8 15 22 29 36 43
 * 0  7 14 21 28 35 42  BOTTOM
 

MrRoboto.java: Handles the mouse and keyboard commands. Uses java.awt.Robot and java.awt.Color and the keyboard commands. Honestly, I should've just made this a joke class that just prints "Domo Arigato"


TelNet.java: Handles the TelNet communication between the java program and the WinCapsIII program.

The WinCapsIII programs and the Mustrum programs are not available in this repository at this time.
