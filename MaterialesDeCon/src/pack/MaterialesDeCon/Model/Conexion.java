package pack.MaterialesDeCon.Model;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import javax.swing.JOptionPane;

public class Conexion {
	
	private static final String URL = "jdbc:sqlserver://localhost;databaseName=MaterialesDeCon;";
	private static final String USER = "salva";
	private static final String PASSWORD = "123";
	
	public static Connection getConection() {
		Connection con = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = (Connection) DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("No se pudo establecer la conexion "+e);
		}
		return con;
	}

}
