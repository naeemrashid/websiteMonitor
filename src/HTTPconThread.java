
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.security.cert.Certificate;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
public class HTTPconThread {
	private URLdetails obj;
	private double responseTime = 0.0;
	private int id;
	private boolean stop=false;
	int index = 0;
	
//	public HTTPconThread(String url){
//		this.testIt(url);
//	}
	public HTTPconThread(int id,URLdetails obj,int time){
		this.obj=obj;
		this.id = id;
		this.responseTime=time;
		this.index = Controller.getList().indexOf(obj);
		final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
	    executorService.scheduleWithFixedDelay(new Runnable() {
			@Override
			public void run() {
				if(Controller.getList().indexOf(obj)==-1){
					executorService.shutdown();
				}
				testIt(obj.getUrl());
				System.out.println(time);
			}
		}, 0, time, TimeUnit.SECONDS);
	}
	
	private void testIt(String https_url){

		int responseCode = -1;
		URL url;
		try {
			
			url = new URL(https_url); // create url object for the given string	
			long millisStart = Calendar.getInstance().getTimeInMillis();
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
			long millisEnd = Calendar.getInstance().getTimeInMillis();
			responseTime = millisEnd-millisStart;
			System.out.println("Response Time : "+(millisEnd-millisStart));
//			 index = Controller.getList().indexOf(obj);
			if(index!=-1){
			Controller.getList().get(index).setStatus(responseMessage);
			Controller.getList().get(index).setTime(TimeAndDate.getTime());
			Controller.getList().get(index).setAcessTime(""+(responseTime));
			Controller.getList().remove(index);
			Controller.getList().add(index, obj);
			Controller.addData(obj);
			DataBase.addLog(id,responseMessage);
			if(responseCode==HttpsURLConnection.HTTP_UNAVAILABLE){
				Mail mail = new Mail("naeemb7070@gmail.com","9994naeemb",obj.getEmail());
				System.out.println(obj.getUrl()+" website is down.Mail sent.");
			}
			}
	
		} catch (MalformedURLException e) {
			System.out.println("Invalid URL.");
			Controller.getList().get(index).setStatus("Invalid URL.");
		}catch(UnknownHostException e){
			System.out.println("Unknown Host");
			Controller.getList().get(index).setStatus("Unknown Host");
		}catch(ConnectException e){
			System.out.println("Connection TimeOut");
			Controller.getList().get(index).setStatus("Connection Timeout");
		}
		catch (IOException e) {
			System.out.println("Internet Unavilabe");
			Controller.getList().get(index).setStatus("Internet Unavilable");
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
