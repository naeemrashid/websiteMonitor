
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
	public class DataBase
	{
		private static Connection c = null;
		private static PreparedStatement perp=null;
		
		public DataBase(){
			CreateDataBase();
			createLog();
		}
		private static Connection getConnection() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		      return DriverManager.getConnection("jdbc:mysql://localhost:3306/pdrg","root","");
		}

	//------------------------------------------------------Creating SQLITE DATABASE and TABLE--------------------------------------//
		public static void CreateDataBase()
		{		
			try
			{
				c = getConnection();
			      Statement stat = c.createStatement();
			      perp=c.prepareStatement("CREATE TABLE if not exists url( url_id INTEGER PRIMARY KEY AUTO_INCREMENT"
			      		+ ",url text,"
//			      		+ "status varchar(20)"
			      		+ "time INTEGER,"
//			      		+ "Date DEFAULT CURRENT_DATE,"
			      		+ "email varchar (40) );");
			      perp.executeUpdate();
			     
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
				c = getConnection();
			      perp=c.prepareStatement("CREATE TABLE if not exists log( url_id INTEGER , "
			      		+ "status varchar(20)"
			      		+ ",time varchar (10),"
			      		+ "Date TIMESTAMP DEFAULT CURRENT_TIMESTAMP );");
			      perp.executeUpdate();
			     
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
				c = getConnection();
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
				c = getConnection();
				  String insert="insert into url(url,time,email)"+"values(?,?,?)";
				  String time= TimeAndDate.getTime();
			      perp=c.prepareStatement(insert);
			      perp.setString(1, url);
			      perp.setInt(2,interval);
			      perp.setString(3,email);
			      perp.executeUpdate();
//			      System.out.println("data has been inserted");
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
			c = getConnection();
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
			c = getConnection();
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
			c = getConnection();
			  String delete="delete from  url where url_id = ?";
		      perp=c.prepareStatement(delete);
		      perp.setInt(1, id);
		      perp.execute();
		      System.out.println("url deleted.");
		}
		catch(Exception e)
		{
			 System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
	
		
	}public static void closeConnection(){
		try {
			perp.close();
			c.close();
		} catch (SQLException e) {
			System.err.println("Unable to close Connection");
		}
		
	}
//	public static void main(String[] args){
//		DataBase.CreateDataBase();
//		DataBase.createLog();
//		ResultSet result = DataBase.getLog(0);
//		try {
//			while(result.next()){
//				System.out.println(result.getString(2));
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//	}
	}

