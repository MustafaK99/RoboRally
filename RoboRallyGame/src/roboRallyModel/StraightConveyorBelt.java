package roboRallyModel;

import java.io.IOException;

/**
 * @author 180034882
 *
 */
public class StraightConveyorBelt extends Tile{
	
	/* (non-Javadoc)
	 * @see roboRallyModel.Tile#setTileForm(java.lang.String)
	 */
	@Override
	public void setTileForm(char form) {
		switch(form) {
			case '^':
				setTileImage(".\\Assets\\images\\tiles\\up.jpg");
				break;
			case 'v':
				setTileImage(".\\Assets\\images\\tiles\\down.jpg");
				break;
			case '<':
				setTileImage(".\\Assets\\images\\tiles\\left.jpg");
				break;
			case '>':
				setTileImage(".\\Assets\\images\\tiles\\right.jpg");
				break;
			}
		setTileType(form);
	}

	/* (non-Javadoc)
	 * @see roboRallyModel.Tile#tileBehaviour(int[], javafx.scene.layout.GridPane)
	 */
	@Override
	public void tileBehaviour(Player currentPlayer, Tile[][] bTiles) throws IOException {
		ReaderBoard rb = new ReaderBoard();
		int currentPlayerX = currentPlayer.getPlayerRobot().getRobotX();
		int currentPlayerY = currentPlayer.getPlayerRobot().getRobotY();
		
		Tile tile = rb.checkTileType(bTiles[currentPlayerY][currentPlayerX].getTileType());

		switch(tile.getTileType()) {
			case '^':
				if(currentPlayerY != 0) {
					currentPlayer.getPlayerRobot().setRobotY(currentPlayerY-1);
				}
				else {
					currentPlayer.getPlayerRobot().moveRobotToStart(currentPlayer, bTiles);
				}
				break;
			case 'v':
				if(currentPlayerY != bTiles.length-1) {
					currentPlayer.getPlayerRobot().setRobotY(currentPlayerY+1);
				}
				else {
					currentPlayer.getPlayerRobot().moveRobotToStart(currentPlayer, bTiles);
				}
				break;
			case '<':
				if(currentPlayerX != 0) {
					currentPlayer.getPlayerRobot().setRobotX(currentPlayerX-1);
				}
				else {
					currentPlayer.getPlayerRobot().moveRobotToStart(currentPlayer, bTiles);
				}
				break;
			case '>':
				if(currentPlayerX != bTiles[0].length-1) {
					currentPlayer.getPlayerRobot().setRobotX(currentPlayerX+1);
				}
				else {
					currentPlayer.getPlayerRobot().moveRobotToStart(currentPlayer, bTiles);
				}
				break;
		}
		
		bTiles[currentPlayerY][currentPlayerX] = new StraightConveyorBelt();
		bTiles[currentPlayerY][currentPlayerX].setTileForm(tile.getTileType());
		
		currentPlayer.getPlayerRobot().setGotOffSCBelt(true);
	}
}
