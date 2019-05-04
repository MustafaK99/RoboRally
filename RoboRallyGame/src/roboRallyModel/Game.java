package roboRallyModel;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import roboRallyController.GameController;

/**
 * @author 180034882
 *
 */
public class Game extends Application{
	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	  public void start(Stage primaryStage) {
	    try {
	        final FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(getClass().getResource("GameScene.fxml"));
	        loader.setController(new GameController());
	        final Parent root = loader.load();
	        final Scene scene = new Scene(root, Region.USE_COMPUTED_SIZE,  Region.USE_COMPUTED_SIZE);
	        primaryStage.setTitle("RoboRally");
	        primaryStage.setScene(scene);
	        primaryStage.setResizable(false);
	        primaryStage.show();
	    } catch(Exception e) {
	      e.printStackTrace();
	    }
	  }

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		   launch(args);
	}

}
