package org.fakehospital.itdb;

import java.sql.*;
import javax.swing.*;

public class ConnectToDB {
	Connection conn = null;
	public static Connection connect() {
		//connects to db on mysqlite
		String url="jdbc:sqlite:c:\\sqlite\\hospital.db";
		
		try {//tries to connect to database using getConnection
			Connection conn = DriverManager.getConnection(url);
			JOptionPane.showMessageDialog(null, "Connection Successful, welcome to the Fake Hospital DB");
			return conn;
		}catch (SQLException e) {//if it fails it returns an error
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}	
}
	//SELECT patientNumber, patientName, sex, room, bloodType, height, weight, birthDate, address, insuranceProvider FROM hospitaldb 
	/*public void selectPerson(){
        String sql = "SELECT patientNumber, patientName FROM hospitaldb";
        
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("patientNumber") +  "\t" + 
                                   rs.getString("patientName") + "\t"// +
                               //    rs.getDouble("capacity")
                                   );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }*/
    
   
    /**
     * @param args the command line arguments
     */
   /* public static void main(String[] args) {
        ConnectToDB app = new ConnectToDB();

    }*/