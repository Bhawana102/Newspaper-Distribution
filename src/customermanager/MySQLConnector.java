package customermanager;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLConnector 
{
	static Connection con;

	public static Connection doConnect()
	{
	
		try{
			//Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost/newspaper","root","Aggarwal@03");
			System.out.println("Connected..");
		}
		catch(Exception exp)
		{
			System.out.println(exp);
		}
		return con;
	}
	
	public static void main(String[] args) 
	{
		doConnect();

	}

}
