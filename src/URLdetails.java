import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

class URLdetails extends RecursiveTreeObject<URLdetails>{
		StringProperty url;
		StringProperty status;
		StringProperty time;
		StringProperty date;
		StringProperty email;
		public URLdetails(String email , String status,String time, String date ,String url){
			this.url = new SimpleStringProperty(url);
			this.status = new SimpleStringProperty(status);
			this.time = new SimpleStringProperty(time);
			this.date = new SimpleStringProperty(date);
			this.email = new SimpleStringProperty(email);
		}
		public StringProperty getUrl() {
			return url;
		}
		public void setUrl(StringProperty url) {
			this.url = url;
		}
		public StringProperty getDate() {
			return date;
		}
		public void setDate(StringProperty date) {
			this.date = date;
		}
		public StringProperty getEmail() {
			return email;
		}
		public void setEmail(StringProperty email) {
			this.email = email;
		}
		public StringProperty getStatus() {
			return status;
		}
		public void setStatus(StringProperty status) {
			this.status = status;
		}
		public StringProperty getTime() {
			return time;
		}
		public void setTime(StringProperty time) {
			this.time = time;
		}
		@Override
		public String toString(){
			return this.url.toString();
		}
	}