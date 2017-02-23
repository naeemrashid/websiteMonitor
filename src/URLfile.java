import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import javafx.beans.InvalidationListener;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;

public class URLfile {

	private static  FileInputStream input;
	private static FileOutputStream output;
	private static File urlFile;
	private static java.util.Date date = new java.util.Date();
	private static SimpleDateFormat dateFormat;
	private static  SimpleDateFormat timeFormat;
	private static URLdetails details;

	public URLfile(File file){
		this.urlFile = file;
	}

	public   static void readUrlFile() throws FileNotFoundException{
		Scanner sc = new Scanner(urlFile);
		while(sc.hasNextLine()){
			String line = sc.nextLine();
			String[] arr = line.split(",");
			date = new java.util.Date();
			timeFormat = new SimpleDateFormat("HH:mm:ss");
			dateFormat = new SimpleDateFormat("dd:MM:YYYY");
			System.out.println(arr[0]+"?"+timeFormat.format(date)+dateFormat.format(date)+arr[1]);
			details = new URLdetails(arr[0],"?",timeFormat.format(date),dateFormat.format(date),arr[1]);
			ObservableList<URLdetails> urldetails = HTTPcontroller.getURLdetails();
			urldetails.add(details);
			//			details.setStatus(new SimpleStringProperty("OK"));
		}
		sc.close();

	}
	public static  void writeUrlFile(String url , String email, int time) throws FileNotFoundException{
		PrintWriter writer = new PrintWriter(urlFile);
		Scanner sc = new Scanner(urlFile);
		while(sc.hasNextLine()){

		}
		writer.println(url+","+email+","+time);


	}

}
