package roboRallyModel;

import java.io.IOException;

/**
 * @author 180034882
 *
 */
public class Gear extends Tile{
	
	private ReaderBoard rb;

	/* (non-Javadoc)
	 * @see roboRallyModel.Tile#setTileForm(java.lang.String)
	 */
	@Override
	public void setTileForm(char form) {
		if(form == '+') {
			setTileImage(".\\Assets\\images\\tiles\\rotateCW.jpg");
		}
		else if(form == '-') {
			setTileImage(".\\Assets\\images\\tiles\\rotateACW.jpg");
		}
		setTileType(form);
	}

	private boolean active = false;
	private int count = 0;
	/* (non-Javadoc)
	 * @see roboRallyModel.Tile#tileBehaviour(int[], javafx.scene.layout.GridPane)
	 */
	@Override
	public void tileBehaviour(Player currentPlayer, Tile[][] bTiles) throws IOException {
		count++;
		if(active == false) {
			rb = new ReaderBoard();
			int currentPlayerX = currentPlayer.getPlayerRobot().getRobotX();
			int currentPlayerY = currentPlayer.getPlayerRobot().getRobotY();
			
			Tile tile = rb.checkTileType(bTiles[currentPlayerY][currentPlayerX].getTileType());
			
			switch(tile.getTileType()) {
				case '+':
					currentPlayer.getPlayerRobot().turnRight();
					break;
				case '-':
					currentPlayer.getPlayerRobot().turnLeft();
					break;
			}
			active = true;
		}
		
		int bLength = bTiles.length+bTiles[0].length;
		
		if(count == bLength) {
			active = false;
			count = 0;
		}
	}
}
