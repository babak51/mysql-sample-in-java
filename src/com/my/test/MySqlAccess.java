package com.my.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 * The aim was to see if I can serve a DB on mySQL server and interact with
 * it from a Java project.  Checking a CD Collection DB with a few CDs.
 * @author babak51
 * @date 06/15/2017
 */
public class MySqlAccess {
	private Connection connect = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
	
	public void readDataBase() throws Exception{
		// This will load the MySQL driver, each DB has its own driver
		// connector is at ~/Downloads/sql/mysql/mysql-connector-java-5.1.42 folder
		// You need to start the XAMPP's servers (includes the mySQL server on the Mac)
		// you need to select the project then use properties->java build path->libraries->add the connector as an external jar
		// the location of the jar is given above on my PC.  So two things you need to do:
		// 1) add the external jar to library of the project.
		// 2) make sure the XAMPP's servers are all running (all 3 of them).  The Database is on the mySQL server.
		// When all this is done then you can interact with the cdcol (CD Collection) DB.
		Class.forName("com.mysql.jdbc.Driver"); 
		
		// Setup the connection with the DB
		connect = DriverManager.getConnection("jdbc:mysql://localhost/cdcol?user=root&password=");
		
		// Statements allow to issue SQL queries to the database
		statement = connect.createStatement();
		
		System.out.println("\n1) --------DBs--------");
		// Result set get the result of the SQL query
		resultSet = statement.executeQuery("SHOW DATABASES");
		
		// ResultSet is initially before the first data set
		while(resultSet.next()){
			System.out.println(resultSet.getString(1));
		}
		
		System.out.println("\n2) --------Tables--------");
		// Result set get the result of the SQL query
		resultSet = statement.executeQuery("SHOW TABLES");
			
		// ResultSet is initially before the first data set
		while(resultSet.next()){
		    System.out.println(resultSet.getString(1));
		}
		
		System.out.println("\n3) --------DESC--------");
		// Result set get the result of the SQL query
		resultSet = statement.executeQuery("DESC CDS");
			
		// ResultSet is initially before the first data set
		while(resultSet.next()){
		    System.out.println(resultSet.getString(1));
		}
		
		System.out.println("\n4) --------SELECT * FROM CDS--------");
		// Result set get the result of the SQL query
		resultSet = statement.executeQuery("SELECT * FROM CDS");
			
		// ResultSet is initially before the first data set
		while(resultSet.next()){
		    System.out.println(resultSet.getString(1) 
		    		 + " - " + resultSet.getString(2) 
		    		 + " - " + resultSet.getString(3) 
		    		 + " - " + resultSet.getString(4));
		}
		
		System.out.println("\n5) --------SELECT * FROM CDS--------");
		// Result set get the result of the SQL query
		resultSet = statement.executeQuery("SELECT * FROM CDS");
			
		// ResultSet is initially before the first data set
		while(resultSet.next()){
		    System.out.println(resultSet.getString("titel") 
		    		 + " - " + resultSet.getString("interpret") 
		    		 + " - " + resultSet.getString("jahr") 
		    		 + " - " + resultSet.getString("id"));
		}
		
	}
 
	public static void main(String[] args) throws Exception{
		MySqlAccess mySqlAccess = new MySqlAccess();
		mySqlAccess.readDataBase();
	}
}

