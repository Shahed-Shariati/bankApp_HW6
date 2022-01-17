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
        String query = "INSERT INTO creditcard (number,expirdate,cvv,password,password2) " +
                       "VALUES (?,?,?,?,?) RETURNING id";
        try {
            date = formatDate.parse(creditCard.getExpireDate());
            sqlDate = new java.sql.Date(date.getTime());
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,creditCard.getNumberCard());
            preparedStatement.setDate(2,sqlDate);
            preparedStatement.setInt(3,creditCard.getCvv());
            preparedStatement.setString(4,creditCard.getPassword());
            preparedStatement.setString(5, creditCard.getPasswordOnline());
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
}
