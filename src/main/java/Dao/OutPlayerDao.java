package Dao;

import Model.OutPlayer;
import lombok.Getter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Getter
public class OutPlayerDao {
    private Connection connection;

    public void OurplayerDao(Connection connection) {
        this.connection = connection;
    }


    public void createAccount(int accountNumber, String accountPassword, int accountBalance) throws SQLException {
        String query = "INSERT INTO account_tb (account_number, account_password, account_balance, account_created_at) VALUES (?, ?, ?, now())";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, accountNumber);
            statement.setString(2, accountPassword);
            statement.setInt(3, accountBalance);
            statement.executeUpdate();
        }
    }


    public OutPlayer getAccountByNumber(int accountNumber) throws SQLException {
        String query = "SELECT * FROM account_tb WHERE account_number = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, accountNumber);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return buildAccountFromResultSet(resultSet);
                }
            }
        }
        return null; // Account not found
    }


    public List<OutPlayer> getAllAccounts() throws SQLException {
        List<OutPlayer> accounts = new ArrayList<>();
        String query = "SELECT * FROM account_tb";
        try (PreparedStatement statement= connection.prepareStatement(query)) {
            try(ResultSet resultSet = statement.executeQuery(query)){
                while (resultSet.next()) {
                    OutPlayer outPlayer = buildAccountFromResultSet(resultSet);
                    accounts.add(outPlayer);
                }
            }
        }
        return accounts;
    }

    public void updateAccount(int accountBalance, int accountNumber) throws SQLException {
        String query = "UPDATE account_tb SET account_balance = ?  WHERE account_number = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, accountBalance);
            statement.setInt(2, accountNumber);
            statement.executeUpdate();
        }
    }

    public void deleteAccount(int accountNumber) throws SQLException {
        String query = "DELETE FROM account_tb WHERE account_number = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, accountNumber);
            statement.executeUpdate();
        }
    }

    private OutPlayer buildAccountFromResultSet(ResultSet resultSet) throws SQLException {
        int accountNumber = resultSet.getInt("account_number");
        String accountPassword = resultSet.getString("account_password");
        int accountBalance = resultSet.getInt("account_balance");
        Timestamp accountCreatedAt = resultSet.getTimestamp("account_created_at");

        return OutPlayer.builder()
                .accountNumber(accountNumber)
                .accountPassword(accountPassword)
                .accountBalance(accountBalance)
                .accountCreatedAt(accountCreatedAt)
                .build();
    }
}