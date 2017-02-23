
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
//		scene.getStylesheets().add(getClass().getResource("/stylesheet.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
		
	}
	public static void main(String[] args){
//		java.util.Date date = new java.util.Date();
//		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
//		SimpleDateFormat sdf2 = new SimpleDateFormat("dd:MM:YYYY");
//		System.out.println(sdf.format(date));
//		System.out.println(sdf2.format(date));
		File urlFile = new File("E:/workspace/PDRG_webtrack/src/urls");
		try {
			URLfile url = new URLfile(urlFile);
			url.readUrlFile();
//			url.writeUrlFile("https://www.w3c.org", "emailme@yahoo.com", 50);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		};
		launch(args);
		
	}

}
