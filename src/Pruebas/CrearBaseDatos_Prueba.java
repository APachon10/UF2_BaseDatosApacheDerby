package Pruebas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CrearBaseDatos_Prueba {
	public static void main(String[] args) {
		CrearBaseDatos_Prueba pv = new CrearBaseDatos_Prueba();
		
		String driver = "org.apache.derby.jdbc.EmbeddedDriver";
		String dbName = "BaseDatosApacheDerby\\ForHonor";
		String dbParam = "create=true"; //la base de datos se creará si no existe todavía
		
		pv.crearBaseDatos(dbName,dbParam);
	}
	public void crearBaseDatos(String dbName,String dbParam) {
		String connectionURL = "jdbc:derby:" + dbName + ";" + dbParam;
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(connectionURL);
			System.out.println("Conexion Hecha ");

			Statement st = conn.createStatement();
			String crearTablaFacciones ="CREATE TABLE Faccion("+
					"faccion_id INTEGER NOT NULL CONSTRAINT faccion_id PRIMARY KEY,"+
					"nombre_faccion VARCHAR(15),"+ 
					"lore VARCHAR(20)"+
				    ")";
			// la sentencia SQL crea una tabla con 3 campos
			st.execute(crearTablaFacciones);

			System.out.println("Tabla Facciones Creada");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void Menu() {

	}
	public void realizarConsulta() {
		String driver = "org.apache.derby.jdbc.EmbeddedDriver";
		String dbName = "/DerbyDB/ExampleDB";
		String connectionURL = "jdbc:derby:" + dbName;
		Connection conn = null;

		try{
		  Class.forName(driver);
		} catch(java.lang.ClassNotFoundException e) {
		  e.printStackTrace();
		}

		try {
		  conn = DriverManager.getConnection(connectionURL);

		  Statement st = conn.createStatement();
		  st.executeUpdate("INSERT INTO users VALUES('Jose', 'Olmedo', 1)");
		  st.executeUpdate("INSERT INTO users VALUES('Maria', 'Hoz', 2)");

		  System.out.println("Se han insertado dos registros");
		} catch (Throwable e)  {
		  System.out.println("Ha fallado la insercion de dos registros");
		  e.printStackTrace();
		} finally {
		  try { conn.close(); }
		  catch (Throwable t){}
		}
	}
}
