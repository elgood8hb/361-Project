/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exam;

import java.sql.*;

public class ResultSetProcessing extends ClientServer{

    public ResultSetProcessing() {

    }

    public void format(ResultSet rs) {
        
        try {

            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();

            for (int x = 1; x <= columnCount; x++) {
                String columnName = rsmd.getColumnName(x);
                
                // writeToSocket(columnName + "\t"); not sure how to get the data from here to calling class.
            }
            

            while (rs.next()) {
                for (int x = 1; x <= columnCount; x++) {
                    String resultStr = rs.getString(x);
                    
                    //return resultStr + "\t";
                }
                System.out.println("");
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }
} //comment