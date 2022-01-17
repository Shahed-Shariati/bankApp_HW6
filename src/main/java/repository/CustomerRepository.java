package repository;

import database.DatabaseConnection;
import model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerRepository implements Repository {
    private Connection connection;
    private PreparedStatement preparedStatement;
    public CustomerRepository()
     {
         connection = DatabaseConnection.getInstance().getConnection();
     }



     public int add(int userId){
         String query = "INSERT INTO customer (user_id) VALUES (?) RETURNING id";
         try {
             preparedStatement = connection.prepareStatement(query);
             preparedStatement.setInt(1,userId);
             ResultSet resultSet = preparedStatement.executeQuery();
             while (resultSet.next())
             {
                 return resultSet.getInt(1);
             }
         } catch (SQLException e) {
             e.printStackTrace();
         }
         return 0;
     }



    @Override
    public void delete(int id) {

    }
}
