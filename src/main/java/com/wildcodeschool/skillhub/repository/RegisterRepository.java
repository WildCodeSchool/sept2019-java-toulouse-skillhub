package com.wildcodeschool.skillhub.repository;

import com.wildcodeschool.skillhub.entity.Answer;
import com.wildcodeschool.skillhub.entity.User;

import java.sql.*;
import java.util.List;

public class RegisterRepository {

    private final static String DB_URL = "jdbc:mysql://localhost:3306/skillhub?serverTimezone=GMT";
    private final static String DB_USER = "skillhub";
    private final static String DB_PASSWORD = "5ki!!huB31";

    public static User saveUser(String nickname, String password, String avatarUrl, List<Long> skillsId) {

        try {
            for (Long skillId : skillsId) {
                Connection connection = DriverManager.getConnection(
                        DB_URL, DB_USER, DB_PASSWORD
                );
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO user (id_user, nickname, password, id_picture, id_skill) JOIN user_skill ON user.id_user = user_skill.id_user VALUES (?,?,?,?,?)",
                        Statement.RETURN_GENERATED_KEYS
                );
                statement.setString(2, nickname);
                statement.setString(3, password);
                statement.setString(4, avatarUrl);
                statement.setLong(4, skillId);

                if (statement.executeUpdate() != 1) {
                    throw new SQLException("failed to insert data");
                }

                ResultSet generatedKeys = statement.getGeneratedKeys();

                if (generatedKeys.next()) {
                    Long userId = generatedKeys.getLong(1);
                    return new User(userId, nickname, password, avatarUrl, skillsId);
                } else {
                    throw new SQLException("failed to get inserted id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
