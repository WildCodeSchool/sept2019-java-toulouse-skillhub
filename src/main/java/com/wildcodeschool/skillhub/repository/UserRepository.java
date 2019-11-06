package com.wildcodeschool.skillhub.repository;

import com.wildcodeschool.skillhub.entity.Question;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
            List<Question> questions = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();

            }
            return questions;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
