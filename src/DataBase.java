
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
	public class DataBase
	{
		private static Connection c = null;
		private static PreparedStatement perp=null;
		private static java.util.Date date = new java.util.Date();
		private static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		
		public DataBase(){
			CreateDataBase();
			createLog();
		}

	//------------------------------------------------------Creating SQLITE DATABASE and TABLE--------------------------------------//
		public static void CreateDataBase()
		{		
			try
			{
				  Class.forName("org.sqlite.JDBC");
			      c = DriverManager.getConnection("jdbc:sqlite:database.db");
//			      System.out.println("Opened database successfully");
			      Statement stat = c.createStatement();
					ResultSet res = stat.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='url'");
					if(!res.next()){
			      perp=c.prepareStatement("CREATE TABLE if not exists url( url_id INTEGER PRIMARY KEY AUTOINCREMENT"
			      		+ ",url text,"
//			      		+ "status varchar(20)"
			      		+ "time INTEGER,"
//			      		+ "Date DEFAULT CURRENT_DATE,"
			      		+ "email varchar (40) );");
			      perp.executeUpdate();
//			      System.out.println("table created");
			      perp.close();
					}
					c.close();
			     
			}
			catch(Exception e)
			{
				e.printStackTrace();
		    	System.out.println("issues");
		    }
			}
		
		public static void createLog(){
			try
			{
				  Class.forName("org.sqlite.JDBC");
			      c = DriverManager.getConnection("jdbc:sqlite:database.db");
//			      System.out.println("Opened database successfully for Log");
			      Statement stat = c.createStatement();
					ResultSet res = stat.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='log'");
					System.out.println("hello");
					if(!res.next()){
						System.out.println("hello");
			      perp=c.prepareStatement("CREATE TABLE if not exists log( url_id INTEGER , "
//			      		+ " FOREIGN KEY(url_id) REFERENCES url(url_id),"
			      		+ "status varchar(20)"
			      		+ ",time varchar (10),"
			      		+ "Date DEFAULT CURRENT_DATE );");
			      perp.executeUpdate();
//			      System.out.println("Log table created.");
			      perp.close();
					}
					c.close();
			     
			}
			catch(Exception e)
			{
				e.printStackTrace();
		    	System.out.println("issues");
		    }
			
		}
		public static void addLog(int url_id , String status){
			try
			{
				  Class.forName("org.sqlite.JDBC");
			      c = DriverManager.getConnection("jdbc:sqlite:database.db");
				  String insert="insert into log(url_id,status,time)"+"values(?,?,?)";
				  String time= TimeAndDate.getTime();
			      perp=c.prepareStatement(insert);
			      perp.setInt(1, url_id);
			      perp.setString(2,status);
			      perp.setString(3,time);
			      perp.executeUpdate();
//			      System.out.println("Log data inserted.");
			}
			catch(Exception e)
			{
				 System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			}
		}
	//------------------------------------ULR INSERTION IN DATABASE----------------------------------------------------------------//
		
		public static void insertUrl(String url,String email ,int interval)
		{
			
			
			try
			{
				  Class.forName("org.sqlite.JDBC");
			      c = DriverManager.getConnection("jdbc:sqlite:database.db");
				  String insert="insert or replace into url(url,time,email)"+"values(?,?,?)";
				  String time= TimeAndDate.getTime();
			      perp=c.prepareStatement(insert);
			      perp.setString(1, url);
			      perp.setInt(2,interval);
			      perp.setString(3,email);
			      perp.executeUpdate();
//			      System.out.println("data has been inserted");
			      c.close();
			}
			catch(Exception e)
			{
				 System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			}
		
		}
	//---------------------------------VIEWING THE HISTORY--------------------------------------------------------------------------//

	public static ResultSet showURLS()
	{
		ResultSet rs=null;
		try
		{
			 Class.forName("org.sqlite.JDBC");
		     c = DriverManager.getConnection("jdbc:sqlite:database.db");
			 String str="select * from url ";
		     perp=c.prepareStatement(str);
		     rs=perp.executeQuery();
		}
		catch(Exception e)
		{
			System.out.println("There are issues while showing the history");
		}
		return rs;
		}
	public static ResultSet getLog(int id){
		ResultSet rs=null;
		try
		{
			 Class.forName("org.sqlite.JDBC");
		     c = DriverManager.getConnection("jdbc:sqlite:database.db");
			 String str="select status,time,Date from log where url_id ="+id;
		     perp=c.prepareStatement(str);
		     rs=perp.executeQuery();
		}
		catch(Exception e)
		{
			System.out.println("There are issues while showing the history");
			e.printStackTrace();
		}
		return rs;
		
	}
	public static void deleteUrl(int id){

		try
		{
			  Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection("jdbc:sqlite:database.db");
			  String delete="delete from  url where url_id = ?";
		      perp=c.prepareStatement(delete);
		      perp.setInt(1, id);
		      perp.execute();
		      c.close();
		      System.out.println("url deleted.");
		}
		catch(Exception e)
		{
			 System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
	
		
	}
//	public static void main (String[] args) throws SQLException{
//		DataBase.CreateDataBase();
//		DataBase.insertUrl("https://www.namal.edu.pk/", "naeeemb7070@gmail.com",120*1000);
//		DataBase.insertUrl("https://www.facebook.com/", "naeeemb7070@gmail.com",120*1000);
//		DataBase.insertUrl("https://www.twitter.com/","naeeemb7070@gmail.com",120*1000);
//		DataBase.insertUrl("https://www.google.com/","naeeemb7070@gmail.com",120*1000);
//		DataBase.createLog();
//		DataBase.addLog(1, "Ok");
//		try{
//		ResultSet set = DataBase.showURLS();
//		while(set.next()){
//			System.out.println(set.getString(1)+"  "+set.getString(2)+"  "+set.getString(3));
//		}
//		}catch(Exception e){
//			System.out.println("exceptions");
//			
//		}
//			
//		
//		
//	}
	}

