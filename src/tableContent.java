import java.sql.ResultSet;

import javafx.collections.ObservableList;

public class tableContent {

	
	
	public void fillData(){
		try{
			ResultSet rs = DataBase.showURLS();
			while (rs.next()){
				 URLdetails obj = new URLdetails( rs.getString(2),"?",TimeAndDate.getTime(),TimeAndDate.getDate(),rs.getString(4));
				 System.out.println(obj.getEmail());
				 Controller.list.add(obj);
				HTTPconThread thread =  new HTTPconThread(rs.getInt(1), obj,rs.getInt(3));
				thread.start();
			}
		}catch(Exception e){
			
		}
		
	}
}
