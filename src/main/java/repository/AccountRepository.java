package repository;

import database.DatabaseConnection;
import model.Account;
import model.CreditCard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

   public void upDate(Account account){
        String query = "UPDATE account set account_number = ? ,balance = ? " +
                "WHERE id = ?;";
       try {
           preparedStatement = connection.prepareStatement(query);
           preparedStatement.setString(1,account.getAccountNumber());
           preparedStatement.setDouble(2,account.getBalance());
         //  preparedStatement.setInt(3,account.getCreditCard().getId());
       //    preparedStatement.setInt(3,account.getTypeId());
           preparedStatement.setInt(3,account.getId());
           preparedStatement.executeUpdate();
           preparedStatement.close();
       } catch (SQLException e) {
           e.printStackTrace();
       }
   }
    @Override
    public void delete(int id) {
        String query = "DELETE FROM account WHERE id = ?;";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

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

    public Account findByCustomerId(int id)
    {
        String query = "select a2.id,a2.account_number,a2.balance from customer c inner join accoutncustoer a on c.id = a.customer_id " +
                "inner join account a2 on a2.id = a.account_id " +
                "where c.id =?;";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                return new Account(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getDouble(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Account findByCardNumber(String cardNumber){
        String query = "SELECT * FROM account a INNER JOIN creditcard c on c.id = a.credit_card_id\n" +
                "WHERE c.number LIKE ?;";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,cardNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                return new Account(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getDouble(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<Account> findByCustomerIdList(int id){
        String query = "select a2.id,a2.account_number,a2.balance from customer c inner join accoutncustoer a on c.id = a.customer_id\n" +
                "inner join account a2 on a2.id = a.account_id " +
                "where c.id =?;";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Account> accounts = new ArrayList<>();
            while (resultSet.next())
            {
                accounts.add(new Account(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getDouble(3)));
            }
            return accounts;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Account> showCustomerAccount(int customerId){
        String query = "select a2.id,a2.account_number,a2.balance,c2.number,c2.expirdate,c2.cvv,c2.password,c2.password2,c2.isactive,c2.id from customer c inner join accoutncustoer a on c.id = a.customer_id\n" +
                "inner join account a2 on a2.id = a.account_id\n" +
                "inner join users u on u.id = c.user_id\n" +
                "inner join creditcard c2 on c2.id = a2.credit_card_id\n" +
                "where c.id = ?;";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,customerId);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Account> accounts = new ArrayList<>();
            while (resultSet.next()){
                CreditCard creditCard = new CreditCard(resultSet.getInt(10),resultSet.getString(4), resultSet.getString(5),
                        resultSet.getInt(6),
                        resultSet.getString(7),
                        resultSet.getString(8),
                        resultSet.getInt(9));
                accounts.add(new Account(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getDouble(3),
                        creditCard));
            }
            return accounts;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
