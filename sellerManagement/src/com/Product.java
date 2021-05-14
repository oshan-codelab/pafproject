package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Product {
	
	
		
		//A common method to connect to the DB
	//A common method to connect to the DB
	private Connection connect(){
		Connection con = null;
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");

			//Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/pafassignment", "root", "");
			
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return con;
	}
			
		
		
		//Insert Project Details
		public String insertProduct(String Pid, String Pname, String Pdesc, String Pcategory, String Pprice){
			String output = "";
			try{
				Connection con = connect();
					if (con == null){
						return "Error while connecting to the database for inserting."; 
				}
				
					
					// create a prepared statement
					String query = " insert into product(`Pid`,`Pname`,`Pdesc`,`Pcategory`,`Pprice`) VALUES (?, ?, ?, ?, ?)";
					PreparedStatement preparedStmt = con.prepareStatement(query);
					
					
					 // binding values
					preparedStmt.setInt(1, 0);
					preparedStmt.setString(2, Pname);
					preparedStmt.setString(3, Pdesc);
					preparedStmt.setString(4,Pcategory);
					preparedStmt.setFloat(5, (float) Double.parseDouble(Pprice));
					 
					 // execute the statement
					 preparedStmt.execute();
					 con.close();
					 
					 String newProduct = readProduct(); 
					 output = "{\"status\":\"success\", \"data\": \"" + newProduct + "\"}";
					 
					 }catch (Exception e)
					 {
						 
						 output = "{\"status\":\"error\", \"data\":\"Error while inserting the  Product.\"}"; 
						 System.err.println(e.getMessage());
					 }
			 return output;
		 }
		
		
		
		
		public String readProduct(){
			String output = "";
			try{
				Connection con = connect();
					if (con == null){
						return "Error while connecting to the database for reading."; 
			}
					
				// Prepare the html table to be displayed
					output = "<table border='1'><tr><th>Pname</th><th>Pdesc</th>" + "<th>Pprice</th>"
							+ "<th>Pcategory</th>" + "<th>Update</th><th>Remove</th></tr>";
	
				String query = "select * from `product`";
				 Statement stmt = con.createStatement();
				 ResultSet rs = stmt.executeQuery(query);
				 
				 
				 // iterate through the rows in the result set
				 while (rs.next()){
					 
					 String Pid = Integer.toString(rs.getInt("Pid"));
						String Pname = rs.getString("Pname");
						String Pdesc = rs.getString("Pdesc");
						String Pcategory = rs.getString("Pcategory");
						String Pprice = Float.toString(rs.getFloat("Pprice"));
					 
					 // Add into the html table
					 
					 
						
						output += "<td>" + Pname + "</td>";
						output += "<td>" + Pdesc + "</td>";
						output += "<td>" + Pcategory + "</td>";
						output += "<td>" + Pprice + "</td>";
					 
					 
					 // buttons
					
					 output += "<td><input name='btnUpdate' type='button' value='Update' "
								+ "class='btnUpdate btn btn-secondary' data-userid='" + Pid + "'></td>"
								+ "<td><input name='btnRemove' type='button' value='Remove' "
								+ "class='btnRemove btn btn-danger' data-userid='" + Pid + "'></td></tr>"; 
				 }
			 con.close();
			 
			 // Complete the html table
			 output += "</table>";
			 
			 
			 }catch (Exception e){
				 
				 output = "Error while reading the product.";
				 System.err.println(e.getMessage());
			 }
			 return output;
			 
		}
		
		
		
		public String updateProduct(String Pid, String Pname, String Pdesc, String Pcategory, String Pprice){ 
			String output = ""; 
			try{
				Connection con = connect();
				if (con == null){
					return "Error while connecting to the database for updating."; 
				} 
				
				 // create a prepared statement
				String query = "UPDATE `product` SET `Pname`=?,`Pdesc`=?,`Pcategory`=?,`Pprice`=? WHERE `Pid`=?";
				 PreparedStatement preparedStmt = con.prepareStatement(query); 
				 
				 // binding values
				  
				
					preparedStmt.setString(1, Pname);
					preparedStmt.setString(2, Pdesc);
					preparedStmt.setString(3, Pcategory);
					preparedStmt.setFloat(4, (float) Double.parseDouble(Pprice));
					
					preparedStmt.setInt(5, Integer.parseInt(Pid));
				 
				// preparedStmt.setString(4, sector);
				
				 
 
				 
				 // execute the statement
				 preparedStmt.execute(); 
				 con.close(); 
				 
				 String newProduct = readProduct(); 
				 output = "{\"status\":\"success\", \"data\": \"" + newProduct + "\"}";
				 
		
				 } catch (Exception e) {
					 
					 output = "{\"status\":\"error\", \"data\": \"Error while updating the product.\"}";
					 System.err.println(e.getMessage()); 
				 } 
				 return output; 
		 }
		
		

		public String deleteProduct(String Pid) { 
			String output = ""; 
			try{ 
				Connection con = connect();
				if (con == null) { 
					return "Error while connecting to the database for deleting."; 
				} 
					// create a prepared statement
				    String query ="DELETE FROM `product` WHERE Pid=?";
					PreparedStatement preparedStmt = con.prepareStatement(query); 
					
					// binding values
					preparedStmt.setInt(1, Integer.parseInt(Pid )); 
					
					// execute the statement
					preparedStmt.execute(); 
					con.close(); 
					
					String newProduct = readProduct(); 
					output = "{\"status\":\"success\", \"data\": \"" + newProduct + "\"}"; 
					
			} catch (Exception e) { 
				output = "{\"status\":\"error\", \"data\": \"Error while deleting the product.\"}"; 
				System.err.println(e.getMessage()); 
			} 
			return output; 
		}
		
}