package com.wildcodeschool.skillhub.repository;

import com.wildcodeschool.skillhub.entity.Question;
import com.wildcodeschool.skillhub.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private final static String DB_URL = "jdbc:mysql://localhost:3306/skillhub?serverTimezone=GMT";
    private final static String DB_USER = "skillhub";
    private final static String DB_PASSWORD = "5ki!!huB31";

    public User getUserById(Long userId) {

        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM user JOIN picture ON picture.id_picture = user.id_picture " +
                            "WHERE id_user = ?;"
            );
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            String username = resultSet.getString("nickname");
            String avatarUrl = resultSet.getString("url");
            String password = resultSet.getString("password");


            statement = connection.prepareStatement(
                    "SELECT user_skill.id_skill FROM skillhub.user\n" +
                            "JOIN user_skill ON user.id_user = user_skill.id_user\n" +
                            "WHERE user.id_user = ?;"
            );
            statement.setLong(1, userId);

            List<Long> skillsId = new ArrayList<>();
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                skillsId.add(resultSet.getLong("id_skill"));
            }

            return new User(userId, username, password, avatarUrl, skillsId);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

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

    public static User updateUser(Long userId, String nickname, String password, String avatar, List<Long> skillsId) {

        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE SET nickname=?, password=?, id_picture=?, id_skill=? FROM user WHERE id_user = ?;"
            );
            statement.setString(1, nickname);
            statement.setString(2, password);
            statement.setString(3, avatar);
            statement.setLong(4, userId);

            if (statement.executeUpdate() != 1) {
                throw new SQLException("failed to update data");
            }

            statement = connection.prepareStatement(
                    "DELETE FROM user_skill WHERE id_user=?"
            );
            statement.setLong(1, userId);

            for (Long skillId : skillsId) {
                statement = connection.prepareStatement(
                        "INSERT INTO user_skill (id_skill, id_user) VALUES (?, ?)");
                statement.setLong(1, skillId);
                statement.setLong(2, userId);
                if (statement.executeUpdate() != 1) {
                    throw new SQLException("failed to insert data");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }
}
