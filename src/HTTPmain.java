
import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HTTPmain extends Application{
	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("gui.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}
	public static void main(String[] args){
		
		File urlFile = new File("Files/urls");
		try {
			URLfile url = new URLfile(urlFile);
			url.readUrlFile();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		};
//		
		launch(args);
		
	}

}
