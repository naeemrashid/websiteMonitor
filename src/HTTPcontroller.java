
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
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

	    private static ObservableList<URLdetails> URLdetails = FXCollections.observableArrayList();
	    /*setter and getters for urlDetails....
	     * 
	     * 
	     * */
	public static ObservableList<URLdetails> getURLdetails() {
			return URLdetails;
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
			URLdetails details = new URLdetails(url,"?", TimeAndDate.getTime(), TimeAndDate.getDate(), email);
			this.getURLdetails().add(details);
			if(!(url.equals("") && timeBar.equals("")&& mailBar.equals(""))){
				System.out.println(url +"    "+ timeBar+"   "+mailBar);
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
		
		final TreeItem<URLdetails> root = new RecursiveTreeItem<URLdetails>(URLdetails, RecursiveTreeObject::getChildren);
		treeView.getColumns().addAll(urlCol,statusCol,timeCol,dateCol,emailCol);
		treeView.setRoot(root);
		
		
		treeView.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
			if(e.getButton()==MouseButton.SECONDARY){
				System.out.println("Right click detected.");
//				popUp.show(JFXPopup.PopupVPosition.TOP, PopupHPosition.RIGHT);
			}
		});
		treeView.setShowRoot(false);
		
		  
		}
	}
	


