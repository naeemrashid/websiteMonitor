package testing;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class TabsTest extends Application{

	@Override
	public void start(Stage stage) throws Exception {
		BorderPane root = new BorderPane();
		JFXTabPane tabPane = new JFXTabPane();
		Tab tab1 = new Tab();
		tab1.setText("Graph Tab");
		Tab tab2 = new Tab();
		tab2.setText("Empty Tab");
		
		BorderPane gPane = new BorderPane();
		CategoryAxis x = new CategoryAxis();
		x.setLabel("Visited Sites");
		NumberAxis y = new NumberAxis();
		y.setLabel("Hits");
		LineChart<?,?> lineChart = new LineChart<>(x, y);
		XYChart.Series set1 = new XYChart.Series<>();
		set1.getData().add(new XYChart.Data("Jadi",302));
		set1.getData().add(new XYChart.Data("Sana",242));
		set1.getData().add(new XYChart.Data("Badr",32));
		set1.getData().add(new XYChart.Data("Naeem",102));
		XYChart.Series set2 = new XYChart.Series<>();
		set2.getData().add(new XYChart.Data("Ramzan",62));
		set2.getData().add(new XYChart.Data("Hussan",421));
		set2.getData().add(new XYChart.Data("Shoaib",92));
		set2.getData().add(new XYChart.Data("Sudo",42));
		
		lineChart.setTitle("History Statistics");
		
		lineChart.getData().add(set1);
		lineChart.getData().add(set2);
		JFXButton button = new JFXButton("add");
		button.addEventHandler(MouseEvent.MOUSE_CLICKED, e ->{
			set2.getData().remove(0);
			set2.getData().add(new XYChart.Data("chhala",45));
		});
		gPane.setCenter(lineChart);
		gPane.setLeft(button);
		
		tab1.setContent(gPane);
		tabPane.getTabs().addAll(tab1,tab2);
		
		root.setCenter(tabPane);
		Scene scene = new Scene(root,800,600);
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String[] args){
		launch(args);
	}

}
