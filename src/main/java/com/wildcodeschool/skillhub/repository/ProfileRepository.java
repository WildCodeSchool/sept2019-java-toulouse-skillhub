package com.wildcodeschool.skillhub.repository;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProfileRepository {

    private final static String DB_URL = "jdbc:mysql://localhost:3306/skillhub?serverTimezone=GMT";
    private final static String DB_USER = "skillhub";
    private final static String DB_PASSWORD = "5ki!!huB31";

    public Map<Long, String> findAllAvatars() {

        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT id_picture, url FROM picture;"
            );

            Map<Long, String> avatars = new HashMap<>();
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Long id = resultSet.getLong("id_picture");
                String url = resultSet.getString("url");
                avatars.put(id, url);
            }
            return avatars;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Map<Long, String> findAllSkills() {

        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT id_skill, name FROM skill;"
            );

            Map<Long, String> skills = new HashMap<>();
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Long id = resultSet.getLong("id_skill");
                String name = resultSet.getString("name");
                skills.put(id, name);
            }
            return skills;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

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

                if (skillsId.get(0) != - 1) {

                    for (Long skillId : skillsId) {
                        statement = connection.prepareStatement(
                                "INSERT INTO user_skill (id_skill, id_user) VALUES (?, ?)");
                        statement.setLong(1, skillId);
                        statement.setLong(2, userId);
                        if (statement.executeUpdate() != 1) {
                            throw new SQLException("failed to insert data");
                        }
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
