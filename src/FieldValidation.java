import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.validator.UrlValidator;


public class FieldValidation {
	private Pattern p;
	private Matcher m;
	
	public boolean validateEmail(String email){
		p = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		m = p.matcher(email);
		if(m.find() && m.group().equals(email)){
			return true;
		}else {
			System.out.println("false");
			return false;
		}
	}
	public boolean validateUrl(String url){
		String[] schemes = {"http","https"}; 
		UrlValidator urlValidator = new UrlValidator(schemes);
		if(urlValidator.isValid(url)){
			System.out.println("is valid");
			return true;
		}
		else{
		return false;
		}
	}
	public boolean validateTime(String time){
		p = Pattern.compile("[0-9]+");
		m = p.matcher(time);
		if(m.find() && m.group().equals(time)){
			return true;
		}else {
			System.out.println("false");
			return false;
		}
		
	}
}
