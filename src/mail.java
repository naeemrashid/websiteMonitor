import java.util.Properties;

public class mail {
	//download javax.mail-1.4.4.4.jar
	  String username;//sender email address
	  String password; // sender password of the gmail.
	public static void main (String[] args){
		 String host = "smtp.gmail.com";
		 String user = "email@user.com";//user email address
		 String to = "example@gmail.com";
		 String from = "example@gmail.com";
		 String password = "user password";
		 String subject = "website notification email";
		 String messageText = "website is down .";
		 boolean sessionDebug = false;
		 
		Properties prop = new Properties();
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.host", host);
		prop.put("mail.smtp.port", "587");
//		java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
		
	}

}
