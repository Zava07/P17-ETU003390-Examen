package main.Java.Model;

import main.Java.utils.DB;
import java.sql.*;
public class Operation {
    
    public int getLastIdOnDept(){
        int id = 0;
        try{
            String query = "SELECT MAX(id) as id FROM dept";
            try(Connection connection = DB.connect()){
                try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
                    try(ResultSet resultSet = preparedStatement.executeQuery()){
                        if(resultSet.next()){
                            id = resultSet.getInt("id");
                        }
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return id;
    }
}
