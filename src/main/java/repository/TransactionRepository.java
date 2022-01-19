package repository;

import database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class TransactionRepository implements Repository{
    private Connection connection = DatabaseConnection.getInstance().getConnection();
    private PreparedStatement preparedStatement;
     public void findByDate()
     {

     }

    @Override
    public void delete(int id) {

    }
}
