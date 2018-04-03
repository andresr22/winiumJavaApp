package main;

import javafx.application.*;
import javafx.fxml.*;
import javafx.stage.*;
import javafx.scene.*;

public class Main extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Parent root = FXMLLoader.load(getClass().getResource("/view/index.fxml"));
		Scene scene = new Scene(root);
		
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
