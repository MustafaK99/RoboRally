package roboRallyModel;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;
import javafx.stage.Modality;
import javafx.stage.Stage;
import roboRallyController.BoardController;

/**
 * @author 180034882
 *
 */
@SuppressWarnings("unused")
public class Robot {
	private String currentPlayer;
	private String robotName;
	private int robotNumber ;
	//private BufferedImage image;
	private String imgLoc;
	private String[] allImgLoc;
	private char startPos;
	private int x, y;
	private ReaderBoard rb;
	private String direction;
	private int flagsCollected;
	private boolean gotOffSCBelt;

	/**
	 * @param playerName
	 * @param givenRobotNumber
	 * @throws IOException
	 */
	public Robot(String playerName, int givenRobotNumber) throws IOException {
		gotOffSCBelt = false;
		this.flagsCollected = 0;
		this.direction = "North";
		this.allImgLoc = new String[] {".\\Assets\\Images\\robotIcons\\r2D2.png", ".\\Assets\\Images\\robotIcons\\bb8.png",
				".\\Assets\\Images\\robotIcons\\c3PO.png", ".\\Assets\\Images\\robotIcons\\darthVadar.png"};
		
		rb = new ReaderBoard();
		
	   switch (givenRobotNumber) {
	       case 0:
	    	   currentPlayer = playerName;
	    	   robotNumber = givenRobotNumber;
	    	   this.robotName = "R2-D2";
	    	   imgLoc = this.allImgLoc[0];
	    	   break;

	       case 1:
	    	   currentPlayer = playerName;
	    	   robotNumber = givenRobotNumber;
	    	   this.robotName = "BB-8";
	    	   imgLoc = this.allImgLoc[1];
	    	   break;
	       case 2:
	    	   currentPlayer = playerName;
	    	   robotNumber = givenRobotNumber;
	    	   this.robotName = "C-3PO";
	    	   imgLoc = this.allImgLoc[2];
	    	   break;
	       default:
	    	   currentPlayer = playerName;
	    	   robotNumber = givenRobotNumber;
	    	   this.robotName = "Darth Vadar";
	    	   imgLoc = this.allImgLoc[3];
	    	   break;
	   }
	}

	/**
	 * gets the image of each location 
	 * @return image location
	 */
	public String getImgLoc() {
		return this.imgLoc;
	}
	
	/**
	 * returns an array of all the images within the array
	 * @return array of all images
	 */
	public String[] getAllImgLoc() {
		return this.allImgLoc;
	}
	
	/**
	 * returns the name of each player 
	 * @return currentPlayer
	 */
	public String getPlayerName() {

		return this.currentPlayer;
	}

	/**
	 * returns the robots number which is used to identify it
	 * @return robot number
	 */
	public int getNumber() {
		return this.robotNumber;

	}
	
	/**
	 * the name of the robot used to display on the game board
	 * @return current robot name
	 */
	public String getRobotName() {
		return this.robotName;
	}
	
	/**
	 * sets the start position of a robot on the board
	 * @param noOfStarts
	 * @param givenStartPos
	 * @param bTiles
	 * @throws IOException
	 */
	public void setStartPos(int noOfStarts, char givenStartPos, Tile[][] bTiles) throws IOException {
		if(noOfStarts != 0) {
			this.startPos = givenStartPos;
			Tile startTile = rb.checkTileType(startPos);
			int[] startTileLoc = rb.getTileLocation(startTile, bTiles);
			int[] xy = new int[2];
			boolean[] tileOccupied = new boolean[] {false, false, false, false, false};

			for(int i = 0; i < getAllImgLoc().length; i++) {
				if(bTiles[startTileLoc[1]][startTileLoc[0]].getTileImage().equals(getAllImgLoc()[i])){
					tileOccupied[0] = true;
				}
			}
			
			for(int i = 0; i < getAllImgLoc().length; i++) {
				if(startTileLoc[1]-1 >= 0) {
					if(bTiles[startTileLoc[1]-1][startTileLoc[0]].getTileImage().equals(getAllImgLoc()[i])){
						tileOccupied[1] = true;
					}
				}else {
					tileOccupied[1] = true;
				}
			}
			
			for(int i = 0; i < getAllImgLoc().length; i++) {
				if(startTileLoc[0]+1 < bTiles[0].length) {
					if(bTiles[startTileLoc[1]][startTileLoc[0]+1].getTileImage().equals(getAllImgLoc()[i])){
						tileOccupied[2] = true;
					}
				}else {
					tileOccupied[2] = true;
				}
			}
			
			for(int i = 0; i < getAllImgLoc().length; i++) {
				if(startTileLoc[1]+1 < bTiles.length) {
					if(bTiles[startTileLoc[1]+1][startTileLoc[0]].getTileImage().equals(getAllImgLoc()[i])){
						tileOccupied[3] = true;
					}
				}else {
					tileOccupied[3] = true;
				}
			}
			
			for(int i = 0; i < getAllImgLoc().length; i++) {
				if(startTileLoc[0]-1 >= 0) {
					if(bTiles[startTileLoc[1]][startTileLoc[0]-1].getTileImage().equals(getAllImgLoc()[i])){
						tileOccupied[4] = true;
					}
				}else {
					tileOccupied[4] = true;
				}
			}
			
			if(!tileOccupied[0]){
				xy[0] = 0;
				xy[1] = 0;
			}else if(!tileOccupied[1]) {
				xy[0] = 0;
				xy[1] = -1;
			}else if(!tileOccupied[2]) {
				xy[0] = +1;
				xy[1] = 0;
			}else if(!tileOccupied[3]) {
				xy[0] = 0;
				xy[1] = +1;
			}else if(!tileOccupied[4]) {
				xy[0] = -1;
				xy[1] = 0;
			}

			setRobotX((startTileLoc[0]+xy[0]));
			setRobotY((startTileLoc[1]+xy[1]));
		}else {
			setRobotX(0);
			setRobotY(0);
		}
	}
	
	/**
	 * gets the start position for a robot
	 * @return
	 */
	public char getStartPos() {
		return this.startPos;
	}

	/**
	 * sets the x coordinate for a robot
	 * @param x
	 */
	public void setRobotX(int x) {
		this.x = x;
	}
	
	/**
	 * gets the robots x coordinate
	 * @return x
	 */
	public int getRobotX() {
		return x;
	}
	
	/**
	 * sets the robots Y coordinate
	 * @param y
	 */
	public void setRobotY(int y) {
		this.y = y;
	}
	
	/**
	 * gets the robots y coordinate
	 * @return
	 */
	public int getRobotY() {
		return y;
	}
	
	/**
	 * moves each robot 
	 * @param players
	 * @param currentPlayer
	 * @param bTiles
	 * @throws IOException
	 */
	public void move(Player[] players, Player currentPlayer, Tile[][] bTiles) throws IOException {
		int currentPlayerX = currentPlayer.getPlayerRobot().getRobotX();
		int currentPlayerY = currentPlayer.getPlayerRobot().getRobotY();
		String currentInstruction = currentPlayer.getCurrentInstruction(currentPlayer.getCurrentRound());
		
		bTiles[currentPlayerY][currentPlayerX].setTileForm(bTiles[currentPlayerY][currentPlayerX].getTileType());

		switch(currentInstruction) {
			case "F": case "B":
				int[] xy = currentPlayer.getPlayerRobot().getDirectionIncrement(players, currentPlayer, bTiles);			
				
				if(xy[0] == -1 || xy[1] == -1 || xy[0] == 1 || xy[1] == 1) {
					currentPlayer.getPlayerRobot().checkRobotOnTile(players, currentPlayer, bTiles, xy);
					currentPlayer.getPlayerRobot().setRobotX(currentPlayerX+xy[0]);
					currentPlayer.getPlayerRobot().setRobotY(currentPlayerY+xy[1]);
				}
				break;
			case "L":
				currentPlayer.getPlayerRobot().turnLeft();
				break;
			case "R":
				currentPlayer.getPlayerRobot().turnRight();
				break;
			case "W": default:
				break;
			case "U":
				currentPlayer.getPlayerRobot().uTurn();
				break;
		}
	}
	
	/**
	 * moves robot back to start after they die
	 * @param currentPlayer
	 * @param bTiles
	 * @throws IOException
	 */
	public void moveRobotToStart(Player currentPlayer, Tile[][] bTiles) throws IOException {
		int currentPlayerX = currentPlayer.getPlayerRobot().getRobotX();
		int currentPlayerY = currentPlayer.getPlayerRobot().getRobotY();
		char currentPlayerStartPos = currentPlayer.getPlayerRobot().getStartPos();
		
		currentPlayer.getPlayerRobot().setStartPos(currentPlayer.getNoOfStarts(), currentPlayerStartPos, bTiles);
		currentPlayerX = currentPlayer.getPlayerRobot().getRobotX();
		currentPlayerY = currentPlayer.getPlayerRobot().getRobotY();
	}
	
	/**
	 * checks what tile each robot is on then executes the tile behaviour 
	 * @param players
	 * @param currentPlayer
	 * @param bTiles
	 * @param xy
	 * @throws IOException
	 */
	public void checkRobotOnTile(Player[] players, Player currentPlayer, Tile[][] bTiles, int[] xy) throws IOException {
		int currentPlayerX = currentPlayer.getPlayerRobot().getRobotX();
		int currentPlayerY = currentPlayer.getPlayerRobot().getRobotY();
		int playerInFrontX = 0, playerInFrontY = 0;

		for(int i = 0; i < players.length; i++) {
			if(currentPlayerY+xy[1] == players[i].getPlayerRobot().getRobotY() &&
				currentPlayerX+xy[0] == players[i].getPlayerRobot().getRobotX()){
				playerInFrontX = players[i].getPlayerRobot().getRobotX();
				playerInFrontY = players[i].getPlayerRobot().getRobotY();

				bTiles[currentPlayerY+xy[1]][currentPlayerX+xy[0]].setTileForm(bTiles[currentPlayerY+xy[1]][currentPlayerX+xy[0]].getTileType());

				if(xy[0] == -1 || xy[0] == 1) {
					if(playerInFrontX != 0 && playerInFrontX != bTiles[0].length-1) {
						String prevDir = players[i].getPlayerRobot().getRobotDirection();
						players[i].getPlayerRobot().setRobotDirection(currentPlayer.getPlayerRobot().getRobotDirection());
						players[i].getPlayerRobot().checkRobotOnTile(players, players[i], bTiles, xy);
						players[i].getPlayerRobot().setRobotDirection(prevDir);
						
						players[i].getPlayerRobot().setRobotX(playerInFrontX+xy[0]);
					} 
					else {
						players[i].getPlayerRobot().moveRobotToStart(players[i], bTiles);
					}
				}else if(xy[1] == -1 || xy[1] == 1) {
					if(playerInFrontY != 0 && playerInFrontY != bTiles.length-1) {
						String prevDir = players[i].getPlayerRobot().getRobotDirection();
						players[i].getPlayerRobot().setRobotDirection(currentPlayer.getPlayerRobot().getRobotDirection());
						players[i].getPlayerRobot().checkRobotOnTile(players, players[i], bTiles, xy);
						players[i].getPlayerRobot().setRobotDirection(prevDir);
						
						players[i].getPlayerRobot().setRobotY(playerInFrontY+xy[1]);
					}
					else {
						players[i].getPlayerRobot().moveRobotToStart(players[i], bTiles);
					}
				}
			}
		}
	}
	
	/**
	 * sets the direction the robot is facing
	 * @param direction
	 */
	public void setRobotDirection(String direction) {
		this.direction = direction;
	}
	
	/**
	 * gets the direction the robot is facing 
	 * @return direction
	 */
	public String getRobotDirection() {
		return this.direction;
	}
	/**
	 * turns the robot left
	 */
	public void turnLeft() {
		switch(this.direction) {
			case "North":
				this.direction = "West";
				break;
			case "East":
				this.direction = "North";
				break;
			case "South":
				this.direction = "East";
				break;
			case "West":
				this.direction = "South";
				break;
		}
	}
	
	/**
	 * turns the robot right
	 */
	public void turnRight() {
		switch(this.direction) {
			case "North":
				this.direction = "East";
				break;
			case "East":
				this.direction = "South";
				break;
			case "South":
				this.direction = "West";
				break;
			case "West":
				this.direction = "North";
				break;
		}
	}
	/**
	 * turns the robot in the opposite direction to which it is currently facing
	 */
	public void uTurn() {
		switch(this.direction) {
			case "North":
				this.direction = "South";
				break;
			case "East":
				this.direction = "West";
				break;
			case "South":
				this.direction = "North";
				break;
			case "West":
				this.direction = "East";
				break;
		}
	}
	
	/**
	 * @param players
	 * @param currentPlayer
	 * @param bTiles
	 * @return
	 * @throws IOException
	 */
	public int[] getDirectionIncrement(Player[] players, Player currentPlayer, Tile[][] bTiles) throws IOException {
		int[] xy = new int[2];
		int currentPlayerX = currentPlayer.getPlayerRobot().getRobotX();
		int currentPlayerY = currentPlayer.getPlayerRobot().getRobotY();

		if(currentPlayer.getCurrentInstruction(currentPlayer.getCurrentRound()).equals("F")) {
			switch(currentPlayer.getPlayerRobot().getRobotDirection()) {
				case "North":
					if(currentPlayerY != 0) {
						xy[0] = 0;
						xy[1] = -1;
					}
					else {
						moveRobotToStart(currentPlayer, bTiles);
					}
					break;
				case "East":
					if(currentPlayerX != bTiles[0].length-1) {
						xy[0] = 1;
						xy[1] = 0;
					}
					else {
						moveRobotToStart(currentPlayer, bTiles);
					}
					break;
				case "South":
					if(currentPlayerY != bTiles.length-1) {
						xy[0] = 0;
						xy[1] = 1;
					}
					else {
						moveRobotToStart(currentPlayer, bTiles);
					}
					break;
				case "West":
					if(currentPlayerX != 0) {
						xy[0] = -1;
						xy[1] = 0;
					}
					else {
						moveRobotToStart(currentPlayer, bTiles);
					}
					break;
			}
		}
		else if(currentPlayer.getCurrentInstruction(currentPlayer.getCurrentRound()).equals("B")) {
			switch(currentPlayer.getPlayerRobot().getRobotDirection()) {
				case "North":
					if(currentPlayerY != bTiles.length-1) {
						xy[0] = 0;
						xy[1] = 1;
					}
					else {
						moveRobotToStart(currentPlayer, bTiles);
					}
					break;
				case "East":
					if(currentPlayerX != 0) {
						xy[0] = -1;
						xy[1] = 0;
					}
					else {
						moveRobotToStart(currentPlayer, bTiles);
					}
					break;
				case "South":
					if(currentPlayerY != 0) {
						xy[0] = 0;
						xy[1] = -1;
					}
					else {
						moveRobotToStart(currentPlayer, bTiles);
					}
					break;
				case "West":
					if(currentPlayerX != bTiles[0].length-1) {
						xy[0] = 1;
						xy[1] = 0;
					}
					else {
						moveRobotToStart(currentPlayer, bTiles);
					}
					break;
			}
		}
		
		return xy;
	}
	
	/**
	 * everytime a flag is collected a value is incremented 
	 */
	
	public void collectFlag() {
		this.flagsCollected++;
	}
	
	/**
	 * returns the number of flags collected
	 * @return flags collected 
	 */
	public int getFlagsCollected() {
		return this.flagsCollected;
	}
	
	/**
	 * sets if the robot is of straight conveyor belt
	 * @param gotOffSCBelt
	 */
	public void setGotOffSCBelt(boolean gotOffSCBelt) {
		this.gotOffSCBelt = gotOffSCBelt;
	}
	
	/**
	 * gets the boolean for if a robot has come from a straight conveyor belt
	 * @return gotOffSCBelt
	 */
	public boolean getGotOffSCBelt() {
		return this.gotOffSCBelt;
	}
}
