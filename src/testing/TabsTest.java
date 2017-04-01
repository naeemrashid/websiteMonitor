package testing;

import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
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
	public XYChart.Series set2;
	public LineChart<?,?> lineChart;
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
		lineChart = new LineChart<>(x, y);
		set2 = new XYChart.Series<>();
		set2.getData().add(new XYChart.Data("Ramzan",62));
//		set2.getData().add(new XYChart.Data("Hussan",421));
//		set2.getData().add(new XYChart.Data("Shoaib",92));
//		set2.getData().add(new XYChart.Data("Sudo",42));
		
		lineChart.setTitle("History Statistics");
		
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
		
		final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
	    executorService.scheduleWithFixedDelay(new Runnable() {
			@Override
			public void run() {
				Random rand = new Random();
				System.out.println(set2.getData().size());
				java.util.Date date = new java.util.Date();
				SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
				set2.getData().add(new XYChart.Data(timeFormat.format(date),rand.nextInt(20)));	
			}
		}, 0, 1, TimeUnit.SECONDS);
	}
	
	
	public static void main(String[] args){
		launch(args);
		System.exit(1);
	}

}
