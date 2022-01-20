package repository;

import database.DatabaseConnection;
import model.Transaction;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionRepository implements Repository{
    private Connection connection = DatabaseConnection.getInstance().getConnection();
    private PreparedStatement preparedStatement;
     public void findByDate()
     {

     }
     public void addTransaction(Transaction transaction){
         String query = "INSERT INTO transactions (accountid, amount, status, types, transaction_date, account_destination) \n" +
                 "VALUES (?,?,?,?,?,?)";
         java.util.Date date = transaction.getDate();
         Date dateSql = new Date(date.getTime());

         try {

             preparedStatement = connection.prepareStatement(query);
             preparedStatement.setInt(1,transaction.getAccountSource().getId());
             preparedStatement.setDouble(2,transaction.getAmount());
             preparedStatement.setString(3,transaction.getStatus());
             preparedStatement.setString(4,transaction.getType());
             preparedStatement.setDate(5, dateSql);
             preparedStatement.setInt(6,transaction.getAccountDestination().getId());
             preparedStatement.execute();
         } catch (SQLException e) {
             e.printStackTrace();
         }
     }
    @Override
    public void delete(int id) {
        String query = "DELETE FROM transactions WHERE id = ?;";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
