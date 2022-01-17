package repository;

import database.DatabaseConnection;
import model.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountRepository implements Repository {
    private Connection connection;
    private PreparedStatement preparedStatement;
    public AccountRepository()
    {
        connection = DatabaseConnection.getInstance().getConnection();
    }

    public void add(Account account,int creditCardId){
        String query = "INSERT INTO account (account_number, balance, type_id) VALUES (?,?,?)";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public int add(Account account){
        String query = "INSERT INTO account (account_number, balance) VALUES (?,?) RETURNING id";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,account.getAccountNumber());
            preparedStatement.setDouble(2,account.getBalance());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                return resultSet.getInt(1);
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void delete(int id) {

    }

    public void setCreditCardId(int creditCardId,int accountId){
        String query = "UPDATE account set credit_card_id = ? WHERE id = ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,creditCardId);
            preparedStatement.setInt(2,accountId);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
