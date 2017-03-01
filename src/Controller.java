

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
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

public class Controller implements Initializable{
	

    @FXML
    private JFXButton createLogButton;
	   @FXML
	    private BorderPane borderPane;

	    @FXML
	    private GridPane gridPane;

	    @FXML
	    private JFXTextField urlBar;

	    @FXML
	    private JFXTextField timeBar;

	    @FXML
	    private JFXButton addButton;

	    @FXML
	    private JFXTextField mailBar;

	    @FXML
	    private HBox hBox;

	    @FXML
	    private JFXButton deleteButton;

	    @FXML
	    private JFXButton refreshButton;

	    @FXML
	    private TableColumn<URLdetails, String> url;

	    @FXML
	    private TableColumn<URLdetails, String> status;

	    @FXML
	    private TableColumn<URLdetails, String> time;

	   
		@FXML
	    private TableColumn<URLdetails, String> date;

	    @FXML
	    private TableColumn<URLdetails, String> email;
	    @FXML
	    private TableView<URLdetails> table;
	public static ObservableList<URLdetails> list = FXCollections.observableArrayList();
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tableContent content = new tableContent();
		content.fillData();
		deleteButton.setDisable(true);
		createLogButton.setDisable(true);
		url.setCellValueFactory(new PropertyValueFactory<URLdetails,String>("url"));
		status.setCellValueFactory(new PropertyValueFactory<URLdetails,String>("status"));
		date.setCellValueFactory(new PropertyValueFactory<URLdetails,String>("date"));
		time.setCellValueFactory(new PropertyValueFactory<URLdetails,String>("time"));
		email.setCellValueFactory(new PropertyValueFactory<URLdetails,String>("email"));
		table.setItems(list);
		hBox.setPadding(new Insets(10,10,10,10));
		hBox.setSpacing(10);
		table.addEventHandler(MouseEvent.MOUSE_CLICKED, e ->{
			deleteButton.setDisable(false);
			createLogButton.setDisable(false);
			
			
		});
		table.addEventHandler(MouseEvent.MOUSE_EXITED, e ->{
			if(table.getSelectionModel().isEmpty()){
			deleteButton.setDisable(true);
			createLogButton.setDisable(true);
			}
			
			
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
		addButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e ->{
			FieldValidation validate = new FieldValidation();
			if(validate.validateEmail(mailBar.getText())&& validate.validateTime(timeBar.getText())&& validate.validateUrl(urlBar.getText())){
				DataBase.insertUrl(urlBar.getText(), mailBar.getText(),Integer.parseInt(timeBar.getText())*1000);
				URLdetails newURL =new URLdetails(urlBar.getText(),"?",TimeAndDate.getTime(),TimeAndDate.getDate(),mailBar.getText());
				list.add(newURL);
				HTTPconThread thread = new HTTPconThread(list.size()+1, newURL, Integer.parseInt(timeBar.getText())*1000);
			}else{
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Incorrect Fields Detected !");
				alert.setContentText("Please Enter valid Fields.");
				alert.showAndWait();
				
			}
			
			
		});

		createLogButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e ->{
			logFile.createLog(table.getSelectionModel().getSelectedIndex()+1);
						
		});
		refreshButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e ->{
			table.refresh();
						
		});
//		list.addListener(new ListChangeListener<URLdetails>() {
//
//			@Override
//			public void onChanged(javafx.collections.ListChangeListener.Change<? extends URLdetails> c) {
//				if(c.wasUpdated()){
//					System.out.println("updated");
//					table.refresh();
//				}
//			}
//		});
	}
	public static ObservableList<URLdetails> getList() {
		return list;
	}
	public static void setList(ObservableList<URLdetails> list) {
		Controller.list = list;
	}
	
	 
}
