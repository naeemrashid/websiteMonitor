
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

public class HTTPcontroller implements Initializable{
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
	private JFXTreeTableView<URLdetails> treeView;
	@FXML
	private JFXPopup popUp;

	@FXML
	private JFXTextField mailBar;
	@FXML
	private JFXButton deleteButton;

	@FXML
	private JFXButton refreshButton;
	private static ObservableList<URLdetails> URLdetailList = FXCollections.observableArrayList();

	/*setter and getters for urlDetails....
	 * 
	 * 
	 * */
	 public static ObservableList<URLdetails> getURLdetails() {
		 return URLdetailList;
	 }

	 /* 
	  * 
	  * */
	 @Override
	 public void initialize(URL location, ResourceBundle resources) {
		 //		urlBar.setOnKeyPressed(event -> {
		 //			if (event.getCode() == KeyCode.ENTER) {
		 //				System.out.println(urlBar.getText()); //method call
		 //			}
		 //		});
		 //		
		 //		time.setOnKeyPressed(event -> {
		 //			if (event.getCode() == KeyCode.ENTER) {
		 //				System.out.println(time.getText()); //method call
		 //			}
		 //		});
		 //		
		 addButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
			 String url = urlBar.getText();
			 String timeText = timeBar.getText();
			 String email = mailBar.getText();
			 int miliSeconds = Integer.parseInt(timeText)*1000;
			 try {
				 URLfile.writeUrlFile(url, email,miliSeconds);
			 } catch (Exception e1) {
				 System.out.println("Error while parsing timeText to int.");
			 }
			 //			HTTPconThread thread = new HTTPconThread(url,time);
			 //			if(e.getButton()==MouseButton.SECONDARY){
			 //				System.out.println("Right click detected.");
			 //			}
			 //			if(urlBar.getText().equals(" || time.equals("")){
			 //				System.out.println("field required.");
			 //			}
			 //			System.out.println(urlBar.getText());
			 //			System.out.println(time.getText());
		 });

		 JFXTreeTableColumn<URLdetails, String> urlCol = new JFXTreeTableColumn<>("URL");
		 urlCol.setPrefWidth(200);
		 urlCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<URLdetails,String>, ObservableValue<String>>() {

			 @Override
			 public ObservableValue<String> call(CellDataFeatures<URLdetails, String> param) {
				 return param.getValue().getValue().url;
			 }
		 });
		 JFXTreeTableColumn<URLdetails, String> statusCol = new JFXTreeTableColumn<>("STATUS");
		 statusCol.setPrefWidth(100);
		 statusCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<URLdetails,String>, ObservableValue<String>>() {

			 @Override
			 public ObservableValue<String> call(CellDataFeatures<URLdetails, String> param) {
				 return param.getValue().getValue().status;
			 }
		 });
		 JFXTreeTableColumn<URLdetails, String> timeCol = new JFXTreeTableColumn<>("Time");
		 timeCol.setPrefWidth(100);
		 timeCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<URLdetails,String>, ObservableValue<String>>() {

			 @Override
			 public ObservableValue<String> call(CellDataFeatures<URLdetails, String> param) {
				 return param.getValue().getValue().time;

			 }
		 });
		 JFXTreeTableColumn<URLdetails, String> dateCol = new JFXTreeTableColumn<>("Date");
		 dateCol.setPrefWidth(100);
		 dateCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<URLdetails,String>, ObservableValue<String>>() {

			 @Override
			 public ObservableValue<String> call(CellDataFeatures<URLdetails, String> param) {
				 return param.getValue().getValue().date;
			 }
		 });
		 JFXTreeTableColumn<URLdetails, String> emailCol = new JFXTreeTableColumn<>("Email");
		 emailCol.setPrefWidth(200);
		 emailCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<URLdetails,String>, ObservableValue<String>>() {

			 @Override
			 public ObservableValue<String> call(CellDataFeatures<URLdetails, String> param) {
				 return param.getValue().getValue().email;
			 }
		 });

		 final TreeItem<URLdetails> root = new RecursiveTreeItem<URLdetails>(URLdetailList, RecursiveTreeObject::getChildren);
		 treeView.getColumns().addAll(urlCol,statusCol,timeCol,dateCol,emailCol);
		 treeView.setRoot(root);
		 deleteButton.setDisable(true);

		 treeView.getColumns().get(0).addEventHandler(MouseEvent.MOUSE_ENTERED, e -> {
			 deleteButton.setDisable(false);
			 
			 treeView.setPredicate(new Predicate<TreeItem<URLdetails>>() {

				 @Override
				 public boolean test(TreeItem<URLdetails> urlDetails) {
					 urlDetails.setValue(new URLdetails("this", "this", "this", "this", "this"));
					 return true;
				 }
			 });
			 //			TreeTableColumn<URLdetails, ?> id = treeView.getColumns().get(0);
			 //			System.out.println(id.getCellData(1));
		 });

		 treeView.setShowRoot(false);


	 }
}



