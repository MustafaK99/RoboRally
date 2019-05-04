package roboRallyModel;

/**
 * @author 180034882
 *
 */
public class Start extends Tile{
	/* (non-Javadoc)
	 * @see roboRallyModel.Tile#setTileForm(java.lang.String)
	 */
	@Override
	public void setTileForm(char form) {
		switch(form) {
		case 'A':
			setTileImage(".\\Assets\\images\\tiles\\a.jpg");
			break;
		case 'B':
			setTileImage(".\\Assets\\images\\tiles\\b.jpg");
			break;
		case 'C':
			setTileImage(".\\Assets\\images\\tiles\\c.jpg");
			break;
		case 'D':
			setTileImage(".\\Assets\\images\\tiles\\d.jpg");
			break;
		}
		setTileType(form);
	}

	/* (non-Javadoc)
	 * @see roboRallyModel.Tile#tileBehaviour(int[], javafx.scene.layout.GridPane)
	 */
	@Override
	public void tileBehaviour(Player currentPlayer, Tile[][] boardTiles) {
	}
}
