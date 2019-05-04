package roboRallyController;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Region;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * @author 180034882
 *
 */
public class GameController {
	@FXML private AnchorPane rootPane;
	
	@FXML
	public void initialize() {
		BackgroundImage myBI= new BackgroundImage(new Image("file:.\\Assets\\images\\bg_brickwall.jpg",Region.USE_COMPUTED_SIZE,Region.USE_COMPUTED_SIZE,false,true),
		        BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,
		          BackgroundSize.DEFAULT);
		rootPane.setBackground(new Background(myBI));
	}
	
	/**
	 * @throws IOException
	 */
	@FXML
	public void startPressed() throws IOException {
		final FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("OptionsScene.fxml"));
		final OptionsController controller = new OptionsController();
		loader.setController(controller);
		try {
			final Parent parent = (Parent) loader.load();
			final Stage optionsStage = new Stage();
			optionsStage.setTitle("RoboRally");
			optionsStage.initModality(Modality.APPLICATION_MODAL);
			optionsStage.setScene(new Scene(parent, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE));
			optionsStage.setResizable(false);
			optionsStage.show();
		} catch(IOException ex) {
			ex.printStackTrace();
		}
	}

	@FXML
	public void exitPressed() {
		Platform.exit();
        System.exit(0);
	}
}
