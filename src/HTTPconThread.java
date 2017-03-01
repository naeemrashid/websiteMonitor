
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.security.cert.Certificate;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.io.*;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
public class HTTPconThread {
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
		final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
	    executorService.scheduleAtFixedRate(new Runnable() {
			
			@Override
			public void run() {
				if(Controller.getList().indexOf(obj)==-1){
					executorService.shutdown();
				}
				testIt(obj.getUrl());
				
			}
		}, 0, 1, TimeUnit.SECONDS);
	}
	
	private void testIt(String https_url){

		int responseCode = -1;
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
			 responseCode = connection.getResponseCode();
			System.out.println(obj.getUrl()+" is up. Response Code : " + responseMessage);
			connection.disconnect();
			int index = Controller.getList().indexOf(obj);
			if(index!=-1){
			Controller.getList().get(index).setStatus(responseMessage);
			Controller.getList().get(index).setTime(TimeAndDate.getTime());
			DataBase.addLog(id,responseMessage);
			if(responseCode==HttpsURLConnection.HTTP_UNAVAILABLE){
				Mail mail = new Mail("naeemb7070@gmail.com","9994naeemb",obj.getEmail());
				System.out.println(obj.getUrl()+" website is down.Mail sent.");
			}
			}
	
		} catch (MalformedURLException e) {
			System.out.println(obj.getUrl()+"Invalid URL.");
//			System.exit(1);
			e.printStackTrace();
		}catch(UnknownHostException e){
			System.out.println(obj.getUrl()+" Cannot acess the website.\n Check your internet connection.");
//			System.exit(1);
		}catch(ConnectException e){
			System.out.println("Connection TimeOut Occured.");
		}
		catch (IOException e) {
			System.out.println(obj.getUrl()+" Error connecting with website.");
			e.printStackTrace();
		}
		catch(Exception e){
			System.out.println("hellow main");
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
