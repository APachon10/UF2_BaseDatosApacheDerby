package Pruebas;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.derby.iapi.sql.PreparedStatement;

public class CrearBaseDatos_Prueba {
	public static void main(String[] args) {
		CrearBaseDatos_Prueba pv = new CrearBaseDatos_Prueba();
		
		String driver = "org.apache.derby.jdbc.EmbeddedDriver";
		String dbName = "BaseDatosApacheDerby\\ForHonor";
		String dbParam = "create=true"; //la base de datos se creará si no existe todavía
		
		//pv.crearBaseDatos(dbName,dbParam);
		//pv.insertarDatos(dbName, dbParam);
		pv.realizarConsultas(dbName, dbParam);
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
					"lore VARCHAR(100)"+
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
		  st.executeUpdate("INSERT INTO Faccion(faccion_id,nombre_faccion, lore) VALUES(1,'Caballeros','son paradigmas del poder.La Legion de Hierro los envio para llevar la paz a esas tierras')");
		  
		  st.executeUpdate("INSERT INTO Faccion(faccion_id,nombre_faccion, lore) VALUES(2,'Vikingos',"
		  + "'Los vikingos desaparecieron hace siglos, tras escapar de sus destrozadas tierras natales en pos de costas desconocidas.");
		  
		  st.executeUpdate("INSERT INTO Faccion(faccion_id,nombre_faccion, lore) VALUES(3,'Samurais',"
		  + "'Originarios de una tienda allende los mares,cuentan la historia de un emperador y una patria que desaparecieron entre el mar y el fuego.')");
		  
		  System.out.println("Se han insertado todos  los registros dentro de la tabla Facciones ");
		  
		  //Insertamos los valores dentro del campo Personajes 
		  st.executeUpdate("INSERT INTO Personajes VALUES(1,'Guardianes',4000,5000,1)");
		  st.executeUpdate("INSERT INTO Personajes VALUES(2,'Pacificadoras',1500,1000,1)");
		  st.executeUpdate("INSERT INTO Personajes VALUES(3,'Justicieros',5000,6000,1)");
		  st.executeUpdate("INSERT INTO Personajes VALUES(4,'Berserkers',6500,2000,2)");
		  st.executeUpdate("INSERT INTO Personajes VALUES(5,'Invasores',7000,2500,2)");
		  st.executeUpdate("INSERT INTO Personajes VALUES(6,'Valquirias',4000,4000,2)");
		  st.executeUpdate("INSERT INTO Personajes VALUES(7,'Kensei',3000,2000,3)");
		  st.executeUpdate("INSERT INTO Personajes VALUES(8,'Orochi',3500,2500,3)");
		  st.executeUpdate("INSERT INTO Personajes VALUES(9,'Nobushi',2500,1000,3)");*/
		  
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
		  
		  /*Consultas para la tabla Faccion*/
		  ResultSet rs=st.executeQuery("SELECT * FROM Faccion");
		  
		  
		  while (rs.next()){
		    Integer faccion_id = rs.getInt("faccion_id");
		    String name_faction = rs.getString("nombre_faccion");
		    String history = rs.getString("lore");
		    
		    System.out.println("faccion_id:" +faccion_id+"\nNombre Faccion: "+name_faction+"\nLore: " +history); 
		  }
		  ResultSet rs2=st.executeQuery("SELECT * FROM Personajes");
		  /*Consultas para la tabla Personajes */
		  while (rs2.next()){
		    Integer pj_id = rs.getInt("personaje_id");
		    String name_pj = rs.getString("nombre_personaje");
		    Integer atack = rs.getInt("Ataque");
		    Integer def = rs.getInt("Defensa");
		    Integer faction_id2 = rs.getInt("faccion_id");
		    
		    System.out.println("Personaje_id:"+pj_id+"\n Nombre Personaje:"+name_pj+"\n Ataque:" +atack + "\nDefensa:"+def+"\nfaction_id "+faction_id2); 
		  }
		  
		  //Cerramos los 2 resulSet
		  rs.close();
		  rs2.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
