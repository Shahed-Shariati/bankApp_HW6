package repository;

import database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AccountCustomerRepository {
    private Connection connection = DatabaseConnection.getInstance().getConnection();
    private PreparedStatement preparedStatement;

    public void add(int accountId,int customerId){
        String query = "INSERT INTO accoutncustoer (account_id,customer_id) VALUES (?,?)";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,accountId);
            preparedStatement.setInt(2,customerId);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
