package com.wildcodeschool.skillhub.repository;

import com.wildcodeschool.skillhub.entity.Question;

import java.sql.*;

public class UserRepository {

    private final static String DB_URL = "jdbc:mysql://localhost:3306/skillhub?serverTimezone=GMT";
    private final static String DB_USER = "skillhub";
    private final static String DB_PASSWORD = "5ki!!huB31";

    public Long checkUser(String username, String password) {

        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM user WHERE nickname = ? AND password = ?;"
            );
            statement.setString(1, username);
            statement.setString(2, password);
            Long userId = 0l;
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                userId = resultSet.getLong("id_user");
            }
            return userId;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
