import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

public class mail {
	//download javax.mail-1.4.4.4.jar
	  String username;//sender email address
	  String password; // sender password of the gmail.
	public static void main (String[] args){
//		Properties systemProperties = System.getProperties();
//		systemProperties.setProperty("http.proxyHost","172.16.0.2");
//		systemProperties.setProperty("http.proxyPort","8080");
//		systemProperties.setProperty("https.proxyHost","172.16.0.2");
//		systemProperties.setProperty("https.proxyPort","8080");
		
		
		 String host = "smtp.gmail.com";
		 String user = "naeemr2014@namal.edu.pk";//user email address
		 String to = "naeemr2014@namal.edu.pk";
		 String from = "naeemr2014@namal.edu.pk";
		 String password = "PRliePR9994";
		 String subject = "website notification email";
		 String messageText = "website is down .";
		 boolean sessionDebug = false;
		 
		Properties prop = new Properties();
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.host", host);
		prop.put("mail.smtp.port", "587");
		java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
		Session mailSession = Session.getDefaultInstance(prop,null);
		mailSession.setDebug(sessionDebug);
		Message msg = new MimeMessage(mailSession);
		try {
			msg.setFrom(new InternetAddress(from));
			InternetAddress[] address = {new InternetAddress(to)};
			msg.setRecipients(Message.RecipientType.TO, address);
			msg.setSubject(subject);
			msg.setText(messageText);
			
			Transport transport = mailSession.getTransport("smtp");
			transport.connect(host,user,password);
			transport.sendMessage(msg, msg.getAllRecipients());
			transport.close();
			System.out.println("Message Sent Sucessfully.");
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		
		
		
	}

}
