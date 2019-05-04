package roboRallyModel;

import java.io.IOException;

/**
 * @author 180034882
 *
 */
public class Pit extends Tile{	
	/* (non-Javadoc)
	 * @see roboRallyModel.Tile#setTileForm(java.lang.String)
	 */
	@Override
	public void setTileForm(char form) {
		setTileImage(".\\Assets\\images\\tiles\\pit.jpg");
		setTileType(form);
	}

	/* (non-Javadoc)
	 * @see roboRallyModel.Tile#tileBehaviour(int[], javafx.scene.layout.GridPane)
	 */
	@Override
	public void tileBehaviour(Player currentPlayer, Tile[][] bTiles) throws IOException {
		int currentPlayerX = currentPlayer.getPlayerRobot().getRobotX();
		int currentPlayerY = currentPlayer.getPlayerRobot().getRobotY();
		char currentPlayerStartPos = currentPlayer.getPlayerRobot().getStartPos();
		
		bTiles[currentPlayerY][currentPlayerX] = new Pit();
		bTiles[currentPlayerY][currentPlayerX].setTileForm('x');

		currentPlayer.getPlayerRobot().setStartPos(currentPlayer.getNoOfStarts(), currentPlayerStartPos, bTiles);
		currentPlayerX = currentPlayer.getPlayerRobot().getRobotX();
		currentPlayerY = currentPlayer.getPlayerRobot().getRobotY();
	}
}
