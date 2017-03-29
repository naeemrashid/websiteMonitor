

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Controller extends Application{
	private static XYChart.Series set1 = null;
	public static ObservableList<URLdetails> list = FXCollections.observableArrayList();
	private static	TableView<URLdetails> table;
	@Override
	public void start(Stage primaryStage) throws Exception {
		JFXButton createLogButton = new JFXButton("Create Log");
		BorderPane borderPane = new BorderPane();
		GridPane gridPane = new GridPane();
		JFXTextField urlBar = new JFXTextField();
		JFXButton chartBtn = new JFXButton("Chart");
		JFXTextField timeBar = new JFXTextField();
		JFXButton addButton = new JFXButton("Add");
		JFXTextField mailBar = new JFXTextField();
		JFXButton deleteButton = new JFXButton("Delete");
		VBox vBox = new VBox();
		TableColumn<URLdetails, String> url = new TableColumn<>("URL");
		url.setPrefWidth(200);
		TableColumn<URLdetails, String> status = new TableColumn<>("STATUS");
		status.setPrefWidth(100);
		TableColumn<URLdetails, String> time = new TableColumn<>("TIME");
		time.setPrefWidth(100);
		TableColumn<URLdetails, String> date = new TableColumn<>("DATE");
		date.setPrefWidth(100);
		TableColumn<URLdetails, String> email = new TableColumn<>("EMAIL NOTIFICATION");
		email.setPrefWidth(200);
		 table = new TableView<>();
		TableColumn<URLdetails, String> acessTime  = new TableColumn<>("ACESS TIME(ms)");
		acessTime.setPrefWidth(120);

		tableContent content = new tableContent();
		content.fillData();
		deleteButton.setDisable(true);
		createLogButton.setDisable(true);
		chartBtn.setDisable(true);
		url.setCellValueFactory(new PropertyValueFactory<URLdetails,String>("url"));
		status.setCellValueFactory(new PropertyValueFactory<URLdetails,String>("status"));
		date.setCellValueFactory(new PropertyValueFactory<URLdetails,String>("date"));
		time.setCellValueFactory(new PropertyValueFactory<URLdetails,String>("time"));
		email.setCellValueFactory(new PropertyValueFactory<URLdetails,String>("email"));
		acessTime.setCellValueFactory(new PropertyValueFactory<URLdetails,String>("acessTime"));


		CategoryAxis x = new CategoryAxis();
		NumberAxis y = new NumberAxis();
		LineChart<?,?> lineChart = new LineChart<>(x, y);
		 set1 = new XYChart.Series<>();
		lineChart.setTitle("Website Status");
		lineChart.getData().add(set1);
		
		
		table.addEventHandler(MouseEvent.MOUSE_CLICKED, e ->{
			deleteButton.setDisable(false);
			createLogButton.setDisable(false);
			chartBtn.setDisable(false);


		});
		table.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(oldValue!=newValue){
					deleteButton.setDisable(false);
					createLogButton.setDisable(false);
					chartBtn.setDisable(false);
				}
			}
		});
		urlBar.addEventHandler(MouseEvent.MOUSE_CLICKED, e ->{
			deleteButton.setDisable(true);
			createLogButton.setDisable(true);
			chartBtn.setDisable(true);
		});
		mailBar.addEventHandler(MouseEvent.MOUSE_CLICKED, e ->{
			deleteButton.setDisable(true);
			createLogButton.setDisable(true);
			chartBtn.setDisable(true);
		});
		addButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e ->{
			deleteButton.setDisable(true);
			createLogButton.setDisable(true);
			chartBtn.setDisable(true);
		});

		deleteButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e ->{
			ObservableList<URLdetails> URLdetailsSelected , allURLdetails;
			allURLdetails = table.getItems();
			DataBase.deleteUrl(((table.getSelectionModel().getSelectedIndex()+1)));
			URLdetailsSelected = table.getSelectionModel().getSelectedItems();
			URLdetailsSelected.forEach(allURLdetails::remove);
			createLogButton.setDisable(true);
			deleteButton.setDisable(true);

		});
		
		
		chartBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
			deleteButton.setDisable(true);
			createLogButton.setDisable(true);
			if(chartBtn.getText().equals("Chart")){
				chartBtn.setText("Back");
				borderPane.setCenter(lineChart);
				System.out.println("hello world");
			}else{
				borderPane.setCenter(table);
			}
			
		});
		
		addButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e ->{
			FieldValidation validate = new FieldValidation();
			if(validate.validateEmail(mailBar.getText())&& validate.validateTime(timeBar.getText())&& validate.validateUrl(urlBar.getText())){
				DataBase.insertUrl(urlBar.getText(), mailBar.getText(),Integer.parseInt(timeBar.getText()));
				URLdetails newURL =new URLdetails(urlBar.getText(),"?",TimeAndDate.getTime(),TimeAndDate.getDate(),mailBar.getText(),"0");
				list.add(newURL);
				HTTPconThread thread = new HTTPconThread(list.size()+1, newURL, Integer.parseInt(timeBar.getText())*1000);
			}else{
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Incorrect Fields Detected !");
				alert.setContentText("Please Enter valid Fields.");
				alert.showAndWait();
				mailBar.clear(); urlBar.clear(); timeBar.clear();

			}


		});
		list.addListener(new ListChangeListener() {
			@Override
			public void onChanged(ListChangeListener.Change change) {
				while (change.next()) {
					if(change.wasAdded()){
						table.refresh();
					}
				}

			}

		});
		createLogButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e ->{
			logFile.createLog(table.getSelectionModel().getSelectedIndex()+1);

		});


		
		gridPane.setHgap(4);
		gridPane.add(mailBar, 0, 0);
		gridPane.add(urlBar, 1, 0);
		gridPane.add(timeBar, 2, 0);
		gridPane.add(addButton, 3, 0);
		HBox hBox = new HBox(deleteButton,createLogButton,chartBtn);
		hBox.setPadding(new Insets(10,10,10,10));
		hBox.setSpacing(10);

		table.getColumns().addAll(url,status,date,time,email,acessTime);
		table.setItems(list);
		borderPane.setTop(gridPane);
		borderPane.setCenter(table);
		borderPane.setBottom(hBox);
		BorderPane root = new BorderPane();
		root.setCenter(borderPane);
		Scene scene = new Scene(root,809,379);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	public static ObservableList<URLdetails> getList() {
		return list;
	}
	public static void setList(ObservableList<URLdetails> list) {
		Controller.list = list;
	}
	public static void addData(URLdetails obj){
		if(set1.getData().size()==10){
			set1.getData().remove(0);
		}
		if(table!=null && table.getSelectionModel().getSelectedItem().equals(obj)){
			set1.getData().add(new XYChart.Data(obj.getTime(),Double.parseDouble(obj.getAcessTime())));	
		}
		
	}
	public static void main(String[] args){
		launch(args);
		System.exit(1);
	}
	
	
	


}
