package conectionFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectionFactory {

	
	  private Connection con;
	  
	  public ConectionFactory() { 
		  this.con = this.establecerConexion();
		  }
	 

	public Connection establecerConexion() {
		 Connection con;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost/hotel_alura?useTimeZone=true&serverTimeZone=UTC";
			String user ="root";
			String pass = "21306336.Ff,";
			
			  con = DriverManager.getConnection( url , user , pass);
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Bloque catch generado automáticamente
			throw new RuntimeException(e);
		}
		return con;
	}
	
	
	

}
