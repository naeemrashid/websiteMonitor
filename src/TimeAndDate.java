import java.text.SimpleDateFormat;

public class TimeAndDate {
	
	public static String getTime(){
		java.util.Date date = new java.util.Date();
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
//		SimpleDateFormat dateFormat = new SimpleDateFormat("dd:MM:YYYY");
		return(timeFormat.format(date));
//		System.out.println(sdf2.format(date));
	}
	
	public static String getDate(){
		java.util.Date date = new java.util.Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd:MM:YYYY");
		return(dateFormat.format(date));
	}

}
