import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class URLdetails {
	private	StringProperty url;
	private	StringProperty status;
	private	StringProperty time;
	private	StringProperty date;
	private	StringProperty email;
		public URLdetails(String url , String status,String time, String date ,String email){
			this.url = new SimpleStringProperty(url);
			this.status = new SimpleStringProperty(status);
			this.time = new SimpleStringProperty(time);
			this.date = new SimpleStringProperty(date);
			this.email = new SimpleStringProperty(email);
		}
		public String getUrl() {
			return url.get();
		}
		public String getDate() {
			return date.get();
		}
		public String getEmail() {
			return email.get();
		}
		
		public String getStatus() {
			return status.get();
		}
		public String getTime() {
			return time.get();
		}
		public void setStatus(String status){
			this.status = new SimpleStringProperty(status);
		}
		public void setTime(String time){
			this.time = new SimpleStringProperty(time);
		}
		public void setDate(String date){
			this.date = new SimpleStringProperty(date);
		}
		@Override
		public String toString(){
			return this.url.toString();
		}
	}