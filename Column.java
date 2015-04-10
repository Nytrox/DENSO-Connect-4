/**
 * Used to keep track of the tiles in a given column.
 * This is used to easily track where the computer/human moved. Instead of searching all 42
 * tiles, this function will allow the code to only search through 7 columns. Efficiency.
 * 
 * This code also creates a bitboard similar to this structure
 * .  .  .  .  .  .  .  TOP
 * 5 12 19 26 33 40 47
 * 4 11 18 25 32 39 46
 * 3 10 17 24 31 38 45
 * 2  9 16 23 30 37 44
 * 1  8 15 22 29 36 43
 * 0  7 14 21 28 35 42  BOTTOM
 * 
 * except instead of an entire board, it's just one column
 * 
 * There are 2 bitboards: one for Red and one for Yellow.
 * 
 * Coded by Henry Gan.
 */
public class Column {
	private int PreVal = 0; 									// Previous Value
	private int value = 0;										// # of pieces in the row
	private int coordinate;
	private char[] Red = new char[7];							// Red char array, used for Bitboard
	private char[] Yellow = new char[7];						// Yellow char array, used for Bitboard
	
	/**
     * Creates a default Column with empty pieces: Nothing is in this column
     *
     */
	public Column() {
		resetString();
	}
	
	/**
     * Creates a Column with the fields set to the provided values.
     *
     * Value is the number of tiles total in the given column
     * PreVal is the previous value (probably 0)
     */
	public Column(int coord){
		this.PreVal = 0;
		this.value = 0;
		this.coordinate = coord;
		resetString();
	}

	/**
     * Returns the total number of tiles in the column
     *
     */
	public int getPrevValue() {
        return this.PreVal;
    }
	
	/**
     * Returns the total number of tiles in the column
     *
     */
	public int getValue() {
        return this.value;
    }
	
	/**
     * Returns the x coordinate of the column
     *
     */
	public int getCoordinate() {
        return this.coordinate;
    }
	
	/**
     * Returns the string orientation of the Red Bitboard
     *
     */
	public String RedtoString(){
		return new String(Red);
	}
	
	/**
     * Returns the string orientation of the Yellow Bitboard
     *
     */
	public String YellowtoString(){
		return new String(Yellow);
	}
	
	/**
     * Sets the total number of tiles in the column
     *
     */
	public void setValue() {
		if(this.value > 6){
			return;
		}
		this.PreVal = this.value;
        this.value++;
    }
	
	public void resetValues(){
		this.PreVal = 0;
        this.value = 0;
	}
	
	/**
     * Sets the coordinates of the column
     *
     */
	public void setCoordinate(int x) {
        this.coordinate = x;
    }
	
	/**
     * Changes the char array for Red
     *
     */
	public void changeRed() {
		if(Red[value-1] != '0' | Yellow[value-1] != '0'){
			return;
		}
		Red[value-1] = '1';
	}
	
	/**
     * Changes the char array for Yellow
     *
     */
	public void changeYellow() {
		if(Red[value-1] != '0' | Yellow[value-1] != '0'){
			return;
		}
		Yellow[value-1] = '1';
		this.isChanged();
	}
	
	/**
     * Boolean to see if column value changed
     *
     */
	public boolean isChanged(){
		if (this.PreVal != this.value){
			this.PreVal = this.value;
			return true;
		}
		return false;
	}
	
	/**
     * Empties out the String
     *
     */
	public void resetString(){
		for(int i = 0; i < 7; i ++){
			this.Red[i] = '0';
		}
		for(int i = 0; i < 7; i ++){
			this.Yellow[i] = '0';
		}
	}
	
	
	/**
	 * Ensures that the column has less than 6 pieces
	 */
	public boolean isFull(){
		if (this.value >= 6){
			return true;
		}
		return false;
	}
	
	/**
     * Ensures that the column has less than 6 pieces (Max Column size is 6)
     *
     */
	public void isValid() throws Exception {
		
		if ((this.value != this.PreVal) && (this.value != this.PreVal + 1)) {
			throw new Exception("Current value conflicts with previous value");
		}
	}
	
}