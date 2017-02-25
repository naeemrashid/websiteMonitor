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
	private static String dateFormat;
	private static  String timeFormat;
	private static URLdetails details;
	private static ObservableList<URLdetails> urldetailList;

	public URLfile(File file){
		this.urlFile = file;
	}

	public   static void readUrlFile() throws FileNotFoundException{
		Scanner sc = new Scanner(urlFile);
		while(sc.hasNextLine()){
			String line = sc.nextLine();
			String[] arr = line.split(",");
			timeFormat = TimeAndDate.getTime();
			dateFormat = TimeAndDate.getDate();
			String email = arr[0];
			String url = arr[1];
			System.out.println(arr[0]+arr[1]+arr[2]);
			int time = Integer.parseInt(arr[2]);
//			System.out.println(arr[0]+"?"+timeFormat.format(date)+dateFormat.format(date)+arr[1]);
			details = new URLdetails(email,"checking",timeFormat,dateFormat,url);
		    urldetailList = HTTPcontroller.getURLdetails();
			urldetailList.add(details);
			System.out.println("connecting to : "+details.getUrl());
			try{
			HTTPconThread thread = new HTTPconThread(url,time,email, details);
			}catch(Exception e){
				System.err.println("Error while connecting");
			}
		}
		sc.close();

	}
	public static  void writeUrlFile(String url , String email, int time) throws FileNotFoundException{
		PrintWriter writer = new PrintWriter(urlFile);
		writer.println(url+","+email+","+time);
		details = new URLdetails(email,"checking",timeFormat,dateFormat,url);
		urldetailList.add(details);
		System.out.println("connection to : "+details.getUrl());
//		HTTPconThread thread = new HTTPconThread(url,time,email, details);
		try{
			HTTPconThread thread = new HTTPconThread(url,time,email, details);
			}catch(Exception e){
				System.err.println("Error while connecting");
			}


	}

}
