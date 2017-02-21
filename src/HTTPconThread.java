
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.security.cert.Certificate;
import java.util.Properties;
import java.util.Scanner;
import java.io.*;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
public class HTTPconThread extends Thread{
	private String url=null;
	public HTTPconThread(String url){
		this.setProxy();
		this.url=url;
		this.start();
	}
	@Override
	public void run(){
		while(true){
			this.testIt(url);
			try {
				sleep(5*60*1000); // sleep thread to get 5 mins interval.
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

			url = new URL(https_url);
			URLConnection con;
			if(https_url.startsWith("http")){
				con = (HttpURLConnection)url.openConnection();
			}else{
				con = (HttpsURLConnection)url.openConnection();
			}

			((HttpURLConnection) con).setRequestMethod("HEAD");
			con.setConnectTimeout(50000);
			con.connect();
			System.out.println("The site is up. Response Code : " + ((HttpURLConnection) con).getResponseCode());
		} catch (MalformedURLException e) {
			System.out.println("Invalid URL.");
			e.printStackTrace();
		}catch(UnknownHostException e){
			System.out.println("Cannot acess the website.");
		} catch (IOException e) {
			System.out.println("error connecting with website.");
			// here would be the e-mail code..
			e.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
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
