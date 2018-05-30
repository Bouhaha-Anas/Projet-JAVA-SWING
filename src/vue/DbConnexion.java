package vue;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class DbConnexion 
{
	public static Statement st;
	public static Connection con;
	
	public static void loadDriver()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
		}
		
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void connect()
	{
		try
		{
			con= DriverManager.getConnection("jdbc:mysql://localhost:3306/gestion-scolarite","root","");
			st=con.createStatement();
		}
		
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}
	
	public static void deconnexion()
	{
		try
		{
			st.close();
			con.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}
}