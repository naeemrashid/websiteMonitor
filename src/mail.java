import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {
	private  String username ;
	private  String password ;
	private String from ;
	private String to ;
	public Mail(String from,String password, String to){
		this.from=from;
		this.to = to;
		this.username = from;
		this.password = password;
		sendMail();
	}

	public void sendMail(){
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username,password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));
			message.setSubject("Website Status Notification.");
			message.setText("website is down .");

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			System.out.println("Error sending email.");
			e.printStackTrace();
		}
	}

}
