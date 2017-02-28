import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

public class logFile {
	private static final String FILE_HEADER = "Status,Time,Date";
	
	public static void createLog(int id){
		
		File logFile = new File("Files/"+id+".csv");
		ResultSet result =  DataBase.getLog(id);
		try {
			if(!logFile.exists()){
				logFile.createNewFile();
			}
			FileWriter writer = new FileWriter(logFile);
			writer.append(FILE_HEADER.toString());
			writer.append("\n");
			while(result.next()){
				writer.append(result.getString(1)+","+result.getString(2)+","+result.getString(3)+"\n");
			}
			writer.close();
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		System.out.println("Log file created.");
		
		
	}
	
	
}
