package com;

import java.*;
import java.sql.*;


public class Powercut {
	
	public Connection connect() {
		Connection con = null;

		try {
			
			String url = String.format("jdbc:mysql://127.0.0.1:3306/powercut_schedule");
			String username = "root";
			String password = "root";
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,username,password);
			// For testing
			System.out.print("Successfully connected");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}
	
	
	//retrieve method
			public String readPowercuts() {
				String output = "";
				try {
					Connection con = connect();
					if (con == null) {
						return "Error while connecting to the database for reading Customers.";
					}
					// Prepare the html table to be displayed
					output = "<table border='1'><tr><th>Code</th><th>Name</th><th>Group</th><th>day Start Time</th>"
							+ "<th>day End Time</th><th>night Start Time</th><th>night End Time</th><th>Update</th><th>Remove</th></tr>";

					String query = "select * from powercutschedule";
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(query);
					// iterate through the rows in the result set
					while (rs.next()) {
						String id = Integer.toString(rs.getInt("id"));
						String powercutCode = rs.getString("powercutCode");
						String name = rs.getString("name");
						String group = rs.getString("group");
						String dayStartTime = rs.getString("dayStartTime");
						String dayEndTime = rs.getString("dayEndTime");
						String nightStartTime = rs.getString("nightStartTime");
						String nightEndTime = rs.getString("nightEndTime");

						// Add into the html table
						output += "<tr><td>" + powercutCode + "</td>";
						output += "<td>" + name + "</td>";
						output += "<td>" + group + "</td>";
						output += "<td>" + dayStartTime + "</td>";
						output += "<td>" + dayEndTime + "</td>";
						output += "<td>" + nightStartTime + "</td>";
						output += "<td>" + nightEndTime + "</td>";
						
						// buttons
						output += "<td><input name='btnUpdate' type='button' value='Update' "
								+ "class='btnUpdate btn btn-secondary' data-powercutid='" + id + "'></td>"
								+ "<td><input name='btnRemove' type='button' value='Remove' "
								+ "class='btnRemove btn btn-danger' data-powercutid='" + id + "'></td></tr>";
					}
					con.close();

					output += "</table>";
				}

				catch (Exception e) {
					output = "Error while reading the powercut records.";
					System.err.println(e.getMessage());
				}
				return output;
			}
	
	
	//create method
		public String insertPowercut(String id, String name, String group, String dayStartTime, String dayEndTime, String nightStartTime, String nightEndTime) {
			Connection con = connect();
			String output = "";
			if (con == null) {
				return "Error while connecting to the database";
			}

			// create a prepared statement
			String query = " insert into powercutschedule (`id`,`powercutCode`,`name`,`group`,`dayStartTime`,`dayEndTime`,`nightStartTime`,`nightEndTime`)"
							+ "values (?,?,?,?,?,?,?,?)";
			PreparedStatement preparedStmt;
			try {
				preparedStmt = con.prepareStatement(query);

				preparedStmt.setInt(1, 0);
				preparedStmt.setString(2, id);
				preparedStmt.setString(3, name);
				preparedStmt.setString(4, group);
				preparedStmt.setString(5, dayStartTime);
				preparedStmt.setString(6, dayEndTime);
				preparedStmt.setString(7, nightStartTime);
				preparedStmt.setString(8, nightEndTime);

				preparedStmt.execute();
				con.close();
				
				String newRecord = readPowercuts();
				output = "{\"status\":\"successs\", \"data\": \" "+newRecord+"\"}";
			} catch (SQLException e) {
				output = "{\"status\":\"error\", \"data\": \"Error while inserting the item.\"}";
				System.err.println(e.getMessage());
			}

			return output;
		}
		
		
		
		//update method
		public String updatePowercut(String id, String powercutCode,String name, String group, String dayStartTime, String dayEndTime, String nightStartTime, String nightEndTime)

		{
			String output = "";
			try {
				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for updating.";
				}
				// create a prepared statement

				String query = "UPDATE powercutschedule SET powercutCode=? name=?, group=?, dayStartTime=?,"
						+ " dayEndTime=?, nightStartTime=?,"
						+ " nightEndTime=? WHERE id=?";

				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setString(1,id);
				preparedStmt.setString(2, powercutCode);
				preparedStmt.setString(3, name);
				preparedStmt.setString(4, group);
				preparedStmt.setString(5, dayStartTime);
				preparedStmt.setString(6, dayEndTime);
				preparedStmt.setString(7, nightStartTime);
				preparedStmt.setString(8, nightEndTime);

				
				// execute the statement
				preparedStmt.execute();
				con.close();
				
				String newRecord = readPowercuts();
				output = "{\"status\":\"successs\", \"data\": \" "+newRecord+"\"}";
				
			} catch (Exception e) {
				output = "{\"status\":\"error\", \"data\": \"Error while Updating the item.\"}";
				System.err.println(e.getMessage());
			}
			return output;
		}
		
		//delete method
		public String deletePowercut(String id) {
			String output = "";
			try {
				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for deleting.";
				}
				// create a prepared statement
				String query = "delete from powercutschedule where id=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setInt(1, Integer.parseInt(id));
				// execute the statement
				preparedStmt.execute();
				con.close();
				
				String newRecord = readPowercuts();
				output = "{\"status\":\"successs\", \"data\": \" "+newRecord+"\"}";
				
			} catch (Exception e) {
				output = "{\"status\":\"error\", \"data\": \"Error while Updating the item.\"}";
				System.err.println(e.getMessage());
			}
			return output;
		}

}
