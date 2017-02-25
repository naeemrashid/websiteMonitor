
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
public class HTTPconThread extends Thread{
	private String url=null;
	private int time =0;
	private String mail ;
	private URLdetails details;
	public HTTPconThread(String url, int time,String mail,URLdetails obj){
		this.setProxy();
		this.url=url;
		this.time = time;
		this.mail=mail;
		this.details = details;
		this.start();
	}
	@Override
	public void run(){
		while(true){
			this.testIt(url);
			try {
				sleep(time); // sleep thread to get 5 mins interval.
			} catch (InterruptedException e) {
				System.err.println("Thread sleep interupted...");
				e.printStackTrace();
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
				System.out.println("Establishing https URL connection. . .");
				 connection = (HttpsURLConnection) url.openConnection();
			}

			((HttpURLConnection) connection).setRequestMethod("HEAD");
			connection.setConnectTimeout(50000);
			connection.connect();
			int responseCode = connection.getResponseCode();
			System.out.println("The site is up. Response Code : " + responseCode);
//			System.out.println(sdf.format(date));
//			System.out.println(sdf2.format(date));
			details.setStatus(new SimpleStringProperty(""+responseCode));
			details.setTime(new SimpleStringProperty(TimeAndDate.getTime()));
			details.setDate(new SimpleStringProperty(TimeAndDate.getDate()));
			connection.disconnect();
		} catch (MalformedURLException e) {
			System.out.println("Invalid URL.");
//			System.exit(1);
			e.printStackTrace();
		}catch(UnknownHostException e){
			System.out.println("Cannot acess the website.");
//			System.exit(1);
		} catch (IOException e) {
			System.out.println("error connecting with website.");
//			System.exit(1);
			// here would be the e-mail code..
			e.printStackTrace();
		}
		catch(Exception e){
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


}
