package roboRallyModel;

import java.io.IOException;

/**
 * @author 180034882
 *
 */
public abstract class Tile {
	private String imgLoc;
	private char tileType;
	
	/**
	 * sets the image location of a tile
	 * @param imgLoc
	 */
	public void setTileImage(String imgLoc) {
		this.imgLoc = imgLoc;
	}
	
	/**
	 * returns the location of a tile image
	 * @return image location
	 */
	public String getTileImage() {
		return imgLoc;
	}

	/**
	 * @param tileType
	 * sets the type of tile 
	 */
	public void setTileType(char tileType) {
		this.tileType = tileType;
	}
	
	/**
	 * returns the tile type e.g. flag, gear, etc
	 * @return tile type
	 */
	public char getTileType() {
		return tileType;
	}
	
	/**
	 * sets the tile form this is an abstract method because each tile class implements this differently based on its individual parameters
	 * and conditions to fulfill the game
	 * @param form
	 */
	public abstract void setTileForm(char form);
	
	/**
	 * @throws IOException 
	 * this is an abstract method which implements the tile behaviour for each different tile type
	 */
	public abstract void tileBehaviour(Player currentPlayer, Tile[][] boardTiles) throws IOException;
}
