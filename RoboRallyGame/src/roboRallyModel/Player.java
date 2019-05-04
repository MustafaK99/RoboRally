package roboRallyModel;

import java.io.IOException;

/**
 * @author 180034882
 *
 */
public class Player {
	private String playerName;
	private String currentAction ;
	private String[] actions;
	private Robot playerRobot;
	private int instructionCounter;
	private int currentRound;
	private String[] currentRoundArr;
	private int noOfFlags;
	private int noOfStarts;
	
	
	/**
	 * @param givenName
	 * @param givenActions
	 * @param currentIndex
	 * @throws IOException
	 */
	public Player(String givenName, String givenActions,int currentIndex) throws IOException {
		this.playerName = givenName;
		this.actions = givenActions.split("\\+");
		this.playerRobot = new Robot(givenName, currentIndex);
		this.currentAction = "";
		this.currentRound = 0;
		this.noOfFlags = 0;
		this.noOfStarts = 0;
	}
	
	/**
	 * this function returns the name of a given player 
	 * @return player name
	 */
	public String getName () {	
		return this.playerName;
	}
	
	/**
	 * @param givenRound
	 * this function returns the current instruction of a player based on the round the game is currently in
	 * @return
	 */
	public String getCurrentInstruction(int givenRound) {
		String currentRoundActions = this.actions[givenRound];
		currentRoundArr =  currentRoundActions.split("(?!^)");
		currentAction = currentRoundArr[this.instructionCounter];
		return currentAction;
	}
	
	/**
	 * this returns all the instructions a particular player has in a given game
	 * @return  instructions for a player 
	 */
	public String[] getInstruction() {
		return this.actions;
	}
	
	/**
	 * this returns the robot of each player 
	 * @return
	 */
	public Robot getPlayerRobot() {	
		return this.playerRobot;
	}
	
	/**
	 * this sets the current round the game is in for each player 
	 * @param currentRound
	 */
	public void setCurrentRound(int currentRound) {
		this.currentRound = currentRound;
	}
	
	/**
	 * this returns the current round the game is on for each player
	 * @return 
	 */
	public int getCurrentRound() {
		return this.currentRound;
	}
	
	/**
	 * this gets the length of instructions for the currentRound
	 * @return
	 */
	public int getCurrentRoundLength() {
		return this.currentRoundArr.length;
	}
	/**
	 * this increments the instruction counter for each player to make sure that the correct number of instructions
	 * are executed for each player
	 */
	
	public void incrementInstructionCount() {
		this.instructionCounter++;
	}
	/**
	 * other classes use this function to reset the instruction counter to zero
	 */
	
	public void resetInstructionCount() {
		this.instructionCounter = 0;
	}
	
	/**
	 * gets the current index of a particular instruction
	 * @return how much instructions have been executed
	 */
	public int getInstructionCount() {
		return this.instructionCounter;
	}
	
	/**
	 * @param value
	 * this updates the instruction counter 
	 */
	public void setInstructionCount(int value) {
		this.instructionCounter = value;
	}
	
	/**
	 * @return the number of instructions for the whole player 
	 */
	public int getRounds() {
		return this.actions.length;
	}
	
	/**
	 * @param noOfStarts
	 */
	public void setNoOfStarts(int noOfStarts) {
		this.noOfStarts = noOfStarts;
	}
	
	/**
	 * @return
	 */
	public int getNoOfStarts() {
		return noOfStarts;
	}
	
	/**
	 * this sets the number of flags
	 * @param noOfFlags
	 */
	public void setNoOfFlags(int noOfFlags) {
		this.noOfFlags = noOfFlags;
	}
	
	/** 
	 * this returns the number of flags a player collected 
	 * @return flag amount
	 */
	public int getNoOfFlags() {
		return noOfFlags;
	}
}
