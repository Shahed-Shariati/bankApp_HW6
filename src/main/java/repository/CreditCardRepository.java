package repository;

import database.DatabaseConnection;
import model.CreditCard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreditCardRepository {
    private Connection connection = DatabaseConnection.getInstance().getConnection();
    private PreparedStatement preparedStatement ;

    public int add(CreditCard creditCard)
    {
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM");
        Date date;
        java.sql.Date sqlDate;
        String query = "INSERT INTO creditcard (number,expirdate,cvv,password,password2,isactive) " +
                       "VALUES (?,?,?,?,?,?) RETURNING id";
        try {
            date = formatDate.parse(creditCard.getExpireDate());
            sqlDate = new java.sql.Date(date.getTime());
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,creditCard.getNumberCard());
            preparedStatement.setDate(2,sqlDate);
            preparedStatement.setInt(3,creditCard.getCvv());
            preparedStatement.setString(4,creditCard.getPassword());
            preparedStatement.setString(5, creditCard.getPasswordOnline());
            preparedStatement.setInt(6,1);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                return resultSet.getInt(1);
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public CreditCard findByAccountId(int id)
    {
        String query = "select c.id,c.number,c.expirdate,c.cvv,c.password,c.password2,c.isactive from account a inner join creditcard c on c.id = a.credit_card_id\n" +
                "where a.id = ?;";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                return new CreditCard(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getInt(4),
                        resultSet.getString(6),
                          resultSet.getInt(7));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
