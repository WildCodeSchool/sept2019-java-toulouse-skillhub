package com.wildcodeschool.skillhub.repository;

import com.wildcodeschool.skillhub.entity.Answer;
import com.wildcodeschool.skillhub.entity.User;

import java.sql.*;
import java.util.List;

public class RegisterRepository {

    private final static String DB_URL = "jdbc:mysql://localhost:3306/skillhub?serverTimezone=GMT";
    private final static String DB_USER = "skillhub";
    private final static String DB_PASSWORD = "5ki!!huB31";

    public static void saveUser(String nickname, String password, String avatar, List<Long> skillsId) {

        try {
            Connection connection = DriverManager.getConnection(
                    DB_URL, DB_USER, DB_PASSWORD
            );
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO user (nickname, password, id_picture) VALUES (?,?,?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            statement.setString(1, nickname);
            statement.setString(2, password);
            statement.setString(3, avatar);

            if (statement.executeUpdate() != 1) {
                throw new SQLException("failed to insert data");
            }

            ResultSet generatedKeys = statement.getGeneratedKeys();

            if (generatedKeys.next()) {
                Long userId = generatedKeys.getLong(1);
                for (Long skillId : skillsId) {
                    statement = connection.prepareStatement(
                            "INSERT INTO user_skill (id_skill, id_user) VALUES (?, ?)");
                    statement.setLong(1, skillId);
                    statement.setLong(2, userId);
                    if (statement.executeUpdate() != 1) {
                        throw new SQLException("failed to insert data");
                    }
                }
            } else {
                throw new SQLException("failed to get inserted id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
