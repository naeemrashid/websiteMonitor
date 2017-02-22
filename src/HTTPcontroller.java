
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.scene.input.KeyCode;
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
	    private JFXTextField time;

	    @FXML
	    private JFXButton addButton;

	    @FXML
	    private JFXTreeTableView<Details> treeView;

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
			if(urlBar.getText().equals("") || time.equals("")){
				System.out.println("field required.");
			}
//			System.out.println(urlBar.getText());
//			System.out.println(time.getText());
		});
		
		JFXTreeTableColumn<Details, String> urlCol = new JFXTreeTableColumn<>("URL");
		urlCol.setPrefWidth(200);
		urlCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Details,String>, ObservableValue<String>>() {
			
			@Override
			public ObservableValue<String> call(CellDataFeatures<Details, String> param) {
				return param.getValue().getValue().url;
			}
		});
		JFXTreeTableColumn<Details, String> statusCol = new JFXTreeTableColumn<>("STATUS");
		statusCol.setPrefWidth(100);
		statusCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Details,String>, ObservableValue<String>>() {
			
			@Override
			public ObservableValue<String> call(CellDataFeatures<Details, String> param) {
				return param.getValue().getValue().status;
			}
		});
		JFXTreeTableColumn<Details, String> timeCol = new JFXTreeTableColumn<>("Time");
		timeCol.setPrefWidth(100);
		timeCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Details,String>, ObservableValue<String>>() {
			
			@Override
			public ObservableValue<String> call(CellDataFeatures<Details, String> param) {
				return param.getValue().getValue().time;
				
			}
		});
		JFXTreeTableColumn<Details, String> dateCol = new JFXTreeTableColumn<>("Date");
		dateCol.setPrefWidth(100);
		dateCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Details,String>, ObservableValue<String>>() {
			
			@Override
			public ObservableValue<String> call(CellDataFeatures<Details, String> param) {
				return param.getValue().getValue().date;
			}
		});
		JFXTreeTableColumn<Details, String> emailCol = new JFXTreeTableColumn<>("Email");
		emailCol.setPrefWidth(200);
		emailCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Details,String>, ObservableValue<String>>() {
			
			@Override
			public ObservableValue<String> call(CellDataFeatures<Details, String> param) {
				return param.getValue().getValue().email;
			}
		});
		ObservableList<Details> Details = FXCollections.observableArrayList();
		Details.add(new Details("https://www.example.com.pk/","OK","12:30","12-3-2014","example@gmial.com"));
		Details.add(new Details("https://www.example.com.pk/","OK","11:30","12-2-2014","example@gmial.com"));
		Details.add(new Details("https://www.example.com.pk/","OK","2:30","12-1-2014","example@gmial.com"));
		Details.add(new Details("https://www.example.com.pk/","OK","1:30","18-3-2014","example@gmial.com"));
		Details.add(new Details("https://www.example.com.pk/","OK","1:30","18-3-2014","example@gmial.com"));
		Details.add(new Details("https://www.example.com.pk/","OK","1:30","18-3-2014","example@gmial.com"));
		final TreeItem<Details> root = new RecursiveTreeItem<Details>(Details, RecursiveTreeObject::getChildren);
		treeView.getColumns().addAll(urlCol,statusCol,timeCol,dateCol,emailCol);
		treeView.setRoot(root);
		treeView.setShowRoot(false);
		};
	}
	class Details extends RecursiveTreeObject<Details>{
		StringProperty url;
		StringProperty status;
		StringProperty time;
		StringProperty date;
		StringProperty email;
		public Details(String url , String status,String time, String date ,String email){
			this.url = new SimpleStringProperty(url);
			this.status = new SimpleStringProperty(status);
			this.time = new SimpleStringProperty(time);
			this.date = new SimpleStringProperty(date);
			this.email = new SimpleStringProperty(email);
		}
	}


