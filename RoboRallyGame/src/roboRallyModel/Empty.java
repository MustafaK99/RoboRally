package roboRallyModel;

/**
 * @author 180034882
 *
 */
public class Empty extends Tile{
	
	/* (non-Javadoc)
	 * @see roboRallyModel.Tile#setTileForm(java.lang.String)
	 */
	@Override
	public void setTileForm(char form) {
		setTileImage(".\\Assets\\images\\tiles\\empty.jpg");
		setTileType(form);
	}

	/* (non-Javadoc)
	 * @see roboRallyModel.Tile#tileBehaviour(int[], javafx.scene.layout.GridPane)
	 */
	@Override
	public void tileBehaviour(Player currentPlayer, Tile[][] boardTiles) {
	}

}
