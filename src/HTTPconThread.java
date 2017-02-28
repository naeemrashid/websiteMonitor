
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.security.cert.Certificate;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.Scanner;
import java.io.*;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
public class HTTPconThread extends Thread{
	private URLdetails obj;
	private int time;
	private int id;
	private boolean stop=false;
	
//	public HTTPconThread(String url){
//		this.testIt(url);
//	}
	public HTTPconThread(int id,URLdetails obj,int time){
		this.obj=obj;
		this.id = id;
//		this.setProxy();
//		logFile = new LogFiles();
//		logFile.createFile("http://dldir1.qq.com/qqfile/qq/QQ2013/QQ2013Beta2.exe");
	}
	@Override
	public void run(){
		while(true){
			if(Controller.getList().indexOf(obj)==-1){
				System.out.println("break it");
				stop = true;
				//update database here.
				break;
			}
			if(!stop){
			this.testIt(obj.getUrl());
			try {
				sleep(time); // sleep thread .
			} catch (InterruptedException e) {
				System.err.println("Thread sleep interupted...");
				e.printStackTrace();
			}
			}
			//			this.testIt(url);
		}

	}
	private void testIt(String https_url){

		URL url;
		try {
			
			 url = new URL(https_url); // create url object for the given string
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			if(https_url.startsWith("https")){
				 connection = (HttpsURLConnection) url.openConnection();
			}

			((HttpURLConnection) connection).setRequestMethod("HEAD");
			connection.setConnectTimeout(50000);
			connection.connect();
			String responseMessage = connection.getResponseMessage();
			System.out.println(obj.getUrl()+" is up. Response Code : " + responseMessage);
			connection.disconnect();
//			System.out.println(sdf.format(date));
//			System.out.println(sdf2.format(date));
			int index = Controller.getList().indexOf(obj);
			Controller.getList().get(index).setStatus(responseMessage);
			Controller.getList().get(index).setTime(TimeAndDate.getTime());
			DataBase.addLog(id,responseMessage);
	
		} catch (MalformedURLException e) {
			System.out.println(obj.getUrl()+"Invalid URL.");
//			System.exit(1);
			e.printStackTrace();
		}catch(UnknownHostException e){
			System.out.println(obj.getUrl()+" Cannot acess the website.");
//			System.exit(1);
		} catch (IOException e) {
			System.out.println(obj.getUrl()+" Error connecting with website.");
//			System.exit(1);
			// here would be the e-mail code..
			e.printStackTrace();
		}
		catch(Exception e){
			System.out.println("hellow main");
			e.printStackTrace();
//			System.exit(1);
		}
	}
	private void setProxy(){
		Properties systemProperties = System.getProperties();
		systemProperties.setProperty("http.proxyHost","172.16.0.2");
		systemProperties.setProperty("http.proxyPort","8080");
		systemProperties.setProperty("https.proxyHost","172.16.0.2");
		systemProperties.setProperty("https.proxyPort","8080");
	}
//	public static void main (String[] args){
//		HTTPconThread thread =  new HTTPconThread("https://www.namal.edu.pk/");
//		thread.start();
//	}


}
