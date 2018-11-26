package Pruebas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CrearBaseDatos_Prueba {
	public static void main(String[] args) {
		CrearBaseDatos_Prueba pv = new CrearBaseDatos_Prueba();
		pv.crearBaseDatos();
	}
	public void crearBaseDatos() {
		String driver = "org.apache.derby.jdbc.EmbeddedDriver";

		String dbName = "BaseDatosApacheDerby\\ForHonor";
		String dbParam = "create=true"; //la base de datos se creará si no existe todavía

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

	}
}
