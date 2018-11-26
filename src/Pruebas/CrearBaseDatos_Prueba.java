package Pruebas;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CrearBaseDatos_Prueba {
	public static void main(String[] args) {
		CrearBaseDatos_Prueba pv = new CrearBaseDatos_Prueba();
		
		String driver = "org.apache.derby.jdbc.EmbeddedDriver";
		String dbName = "BaseDatosApacheDerby\\ForHonor";
		String dbParam = "create=true"; //la base de datos se creará si no existe todavía
		
		pv.crearBaseDatos(dbName,dbParam);
		//pv.insertarDatos(dbName, dbParam);
		//pv.realizarConsultas(dbName, dbParam);
	}
	public void crearBaseDatos(String dbName,String dbParam) {
		String connectionURL = "jdbc:derby:" + dbName + ";" + dbParam;
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(connectionURL);
			System.out.println("Conexion Hecha ");

			Statement st = conn.createStatement();
			String crearTablaFacciones ="CREATE TABLE Faccion("+
					"faccion_id  INTEGER NOT NULL CONSTRAINT faccion_id PRIMARY KEY,"+
					"nombre_faccion VARCHAR(15),"+ 
					"lore VARCHAR(20)"+
				    ")";
			st.execute(crearTablaFacciones);
			System.out.println("Tabla Facciones Creada");
			
			String crearTablaPersonajes ="CREATE TABLE Personajes("+
					"personaje_id INTEGER NOT NULL CONSTRAINT personaje_id PRIMARY KEY,"+
					"nombre_personaje VARCHAR(15),"+ 
					"Ataque INTEGER,"+ 
					"Defensa INTEGER,"+ 
					"faccion_id INTEGER,"+ 
					"FOREIGN KEY(faccion_id) REFERENCES Faccion(faccion_id)"+
				    ")";
			st.execute(crearTablaPersonajes);
			System.out.println("Tabla Personajes creada ");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void insertarDatos(String dbName,String dbParam) {
		String connectionURL = "jdbc:derby:" + dbName + ";" + dbParam;
		Connection conn = null;

		try {
		  conn = DriverManager.getConnection(connectionURL);

		  Statement st = conn.createStatement();
		  
		  //Insertamos los valores dentro del campo Facciones 
		  st.executeUpdate("INSERT INTO Faccion VALUES (1,'Caballeros','Los caballeros de Ashfeld son paradigmas del poder. La Legión de Hierro los envió para llevar la paz a esas tierras y, desde entonces, disfrutan de la libertad y han hecho de Ashfeld su hogar')");
		  
		  st.executeUpdate("INSERT INTO Faccion VALUES (2,'Vikingos',"
		  + "'Los vikingos desaparecieron hace siglos, tras escapar de sus destrozadas tierras natales en pos de costas desconocidas. "+
		  "Los caballeros conquistaron a aquellos que se quedaron atrás e hicieron que se incorporaran a su cultura."+ 
		  "Los vikingos volvieron en masa hace unos pocos siglos. Vinieron de más allá del mar, del lugar en el que habían establecido su nuevo hogar. Regresaron por muchas razones, pero principalmente para reclamar su antiguo hogar en el norte: Valkeinheim')");
		  
		  st.executeUpdate("INSERT INTO Faccion VALUES (3,'Samuráis',"
		  + "'Originarios de una tienda allende los mares, "
		  + "cuentan la historia de un emperador y una patria que desaparecieron entre el mar y el fuego. "
		  + "Casi un milenio después, la nación nómada ha dejado de errar y "
		  + "ha construido un nuevo imperio cerca de las tierras reclamadas por los vikingos y por las que disputan los caballeros"
		  +"Tras pasar las útlimas décadas aclimatándose a las colinas pantanosas conocidas como el Myre, "
		  + "han prosperado, pero siguen siendo muy inferiores en número respecto a sus vecinos. "
		  + "Para sobrevivir, dependen de sus habilidades marciales, su astucia y la devoción por su cultura')");
		  
		  System.out.println("Se han insertado todos  los registros dentro de la tabla Facciones ");
		  
		  //Insertamos los valores dentro del campo Personajes 
		  st.executeUpdate("INSERT INTO Personaje VALUES (1,'Guardianes',4000,5000,1)");
		  st.executeUpdate("INSERT INTO Personaje VALUES (2,'Pacificadoras',1500,1000,1)");
		  st.executeUpdate("INSERT INTO Personaje VALUES (3,'Justicieros',5000,6000,1)");
		  st.executeUpdate("INSERT INTO Personaje VALUES (4,'Berserkers',6500,2000,2)");
		  st.executeUpdate("INSERT INTO Personaje VALUES (5,'Invasores',7000,2500,2)");
		  st.executeUpdate("INSERT INTO Personaje VALUES (6,'Valquirias',4000,4000,2)");
		  st.executeUpdate("INSERT INTO Personaje VALUES (7,'Kensei',3000,2000,3)");
		  st.executeUpdate("INSERT INTO Personaje VALUES (8,'Orochi',3500,2500,3)");
		  st.executeUpdate("INSERT INTO Personaje VALUES (9,'Nobushi',2500,1000,3)");
		  
		  System.out.println("Se han insertado todos  los registros dentro de la tabla Personajes  ");
		  
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void realizarConsultas(String dbName,String dbParam) {
		String connectionURL = "jdbc:derby:" + dbName;
		Connection conn = null;

		try {
		  conn = DriverManager.getConnection(connectionURL);

		  Statement st = conn.createStatement();
		  ResultSet rs=st.executeQuery("SELECT * FROM Facciones");
		  while (rs.next()){
		    Integer idUser = rs.getInt("faccion_id");
		    String first = rs.getString("nombre_faccion");
		    String last = rs.getString("lore");
		    System.out.println(idUser + " se llama: " + first + " " + last);
		  }
		  rs.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
