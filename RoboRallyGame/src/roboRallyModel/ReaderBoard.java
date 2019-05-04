package roboRallyModel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author 180034882
 *
 */
public class ReaderBoard {
    private ArrayList<String> boardHeight;
	private char[][] boardPositions;
	private String boardLoc;
	private char[] tileChars;
	private Tile[] tileTypes;
	private int noOfFlags, noOfStarts;
	private ArrayList<String> boardErrors;
	
	/**
	 * @param boardLoc
	 * @throws IOException 
	 */
	public ReaderBoard() {
		tileChars = new char[]{'.', 'x', 'A', 'B', 'C', 'D', '1', '2', '3', '4', '^', 'v', '<', '>', '+', '-', 
				'n', 'N', 'e', 'E', 's', 'S', 'w', 'W'};
		this.boardHeight = new ArrayList<String>();
		this.noOfStarts = 0;
		this.noOfFlags = 0;
		boardErrors = new ArrayList<>();
	}
	
	/**
	 * this returns the location of the baord in memory
	 * @param boardLoc
	 */
	public void setBoardLocation(String boardLoc) {
		this.boardLoc = boardLoc;
		splitBoard();
	}
    /**
     * this splits the board correctly and stores the results to be used
     */
	private boolean fileCorrect = false;
	public void splitBoard() {
		int lines = 0;
		try (BufferedReader br = new BufferedReader(new FileReader(boardLoc)))
		{
			if(br.ready()) {
				String sCurrentLine;
				while ((sCurrentLine = br.readLine()) != null) {
					if(lines == 0 && !sCurrentLine.equals("format 1")) {
						boardErrors.add("The .brd file is not in the correct format!");
						break;
					}
					
					sCurrentLine.split("\\s+");
					boardHeight.add(sCurrentLine);
					lines++;
				}
				
				if(lines > 1) {
					fileCorrect = true;
					boardPositions = new char[boardHeight.size()-1][];
					
					int j = 0;
					for(int i = 1; i < boardHeight.size(); i++){
						boardPositions[j] = boardHeight.get(i).toCharArray();
						j++;
					}

					for(int i = 0; i < boardPositions.length-1; i++) {
						if(boardPositions[i+1].length != boardPositions[0].length) {
							boardErrors.add("There are mismatch rows and columns in the .brd file!");
							fileCorrect = false;
							break;
						}
					}
				}
				else {
					boardErrors.add("The .brd file was empty!");
				}
			}else {
				boardErrors.add("The .brd file was empty!");
			}
		} catch (IOException e) {
			boardErrors.add("This .brd file does not exist!");
		}
	}
	
	/**
	 * @return
	 * @throws IOException
	 */
	public Tile[][] readBoardTiles() throws IOException{
		if(fileCorrect) {
			Tile[][] boardTiles = new Tile[boardPositions.length][boardPositions[0].length];
			
			for(int i = 0; i < boardPositions.length; i++) {
				for(int j = 0; j < boardPositions[0].length; j++) {	
					Tile tile = checkTileType(boardPositions[i][j]);
					
					tile.setTileForm(boardPositions[i][j]);
					boardTiles[i][j] = tile;
				}
			}
			
			if(getNoOfStarts() < 1) {
				boardErrors.add("There are no starting positions in the .brd file!");
			}
			
			if(getNoOfFlags() < 1) {
				boardErrors.add("There are no flags in the .brd file!");
			}
			
			return boardTiles;
		}
		return null;
	}
	
	/**
	 * @param bPos
	 * @return
	 * @throws IOException 
	 */
	public Tile checkTileType(char bPos) throws IOException{
		Flag flag = new Flag();
		Gear gear = new Gear();
		Empty empty = new Empty();
		Pit pit = new Pit();
		Start start = new Start();
		StraightConveyorBelt SCBelt = new StraightConveyorBelt();
		CornerConveyorBelt CCBelt = new CornerConveyorBelt();

		for(int k = 0; k < tileChars.length; k++) {
			if(bPos == tileChars[k]) {
				tileTypes = new Tile[] {empty, pit, start, start, start, start,
						flag, flag, flag, flag, SCBelt, SCBelt, SCBelt, SCBelt, gear, gear,
						CCBelt, CCBelt, CCBelt, CCBelt, CCBelt, CCBelt, CCBelt, CCBelt};
				
				switch(bPos) {
					case '1': case '2': case '3': case '4':
						noOfFlags++;
						break;
					case 'A': case 'B': case 'C': case 'D':
						noOfStarts++;
						break;
				}

				tileTypes[k].setTileType(bPos);
				return tileTypes[k];
			}else if(k == tileChars.length-1 && bPos != tileChars[k]){
				boardErrors.add("The .brd file contains an invalid character('" + bPos + "')");
				break;
			}
		}		
		
		return empty;
	}
	
	/**
	 * returns the location of specific tile 
	 * @param tile
	 * @param boardTiles
	 * @return tile locatiom
	 */
	public int[] getTileLocation(Tile tile, Tile[][] boardTiles) {
		int[] tileLoc;
		
		for(int i = 0; i < boardTiles.length; i++) {
			for(int j = 0; j < boardTiles[0].length; j++) {
				if(boardTiles[i][j].getTileType() == tile.getTileType()) {
					tileLoc = new int[]{j, i};
					return tileLoc;
				}
			}
		}
		
		tileLoc = new int[]{0, 0};
		return tileLoc;
	}
	
	/**
	 * @return
	 */
	public int[] getBoardSize() {
		int[] size = new int[] {boardHeight.size()-1, boardHeight.get(1).length()};
		return size;
	}
	
	/**
	 * gets the number of flags on a board
	 * @return number of flags on the board being read
	 */
	public int getNoOfFlags() {
		return noOfFlags;
	}
	
	/**
	 * gets the number of starts on the boar being read
	 * @return
	 */
	public int getNoOfStarts() {
		return noOfStarts;
	}
	
	/**
	 * gets the number of errors on the board being read
	 * @return board error length
	 */
	public int getBoardErrorsLength() {
		return boardErrors.size();
	}
	
	/**
	 * gets the array of all board errors
	 * @return string errors
	 */
	public String getBoardErrors() {
		String errors = "";
		
		if(getBoardErrorsLength() > 0) {
			errors = "Error 1: " + boardErrors.get(0);
			
			for(int i = 1; i < getBoardErrorsLength(); i++) {
				errors += "\nError "+(i+1)+": "+boardErrors.get(i);
			}
		}

		return errors;
	}
}
