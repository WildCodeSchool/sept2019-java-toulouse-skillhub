package com.wildcodeschool.skillhub.repository;

import com.wildcodeschool.skillhub.entity.Question;

import java.sql.*;

public class QuestionRepository {

    private final static String DB_URL = "jdbc:mysql://localhost:3306/skillhub?serverTimezone=GMT";
    private final static String DB_USER = "nabil";
    private final static String DB_PASSWORD = "Monmdppaypal7;";

    public Question findQuestion(Long id) {

        try {
            Connection connection = DriverManager.getConnection(
                    DB_URL, DB_USER, DB_PASSWORD
            );
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM question\n" +
                            "JOIN user ON question.id_user = user.id_user\n" +
                            "JOIN picture ON user.id_picture = picture.id_picture\n" +
                            "JOIN question_skill ON question.id_question = question_skill.id_question\n" +
                            "JOIN skill ON question_skill.id_skill = skill.id_skill\n" +
                            "WHERE question.id_question = ?;"
            );
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String title = resultSet.getString("title");
                String body = resultSet.getString("body");
                Date date = resultSet.getDate("date");
                Long idUser = resultSet.getLong("id_user");
                boolean resolved = resultSet.getBoolean("resolved");
                return new Question(id, title, body, date, resolved, idUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

/*    public User findUser(Long id) {

        try {
            Connection connection = DriverManager.getConnection(
                    DB_URL, DB_USER, DB_PASSWORD
            );
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT user.id_user, user.nickname, user.password, user.id_picture, picture.url \n" +
                            "from user\n" +
                            "join picture on picture.id_picture = `user`.id_picture\n" +
                            "WHERE user.id_user = ? ;"
            );
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String nickname = resultSet.getString("nickname");
                String password = resultSet.getString("password");
                Long idPicture = resultSet.getLong("id_picture");
                String url = resultSet.getString("url");
                return new User(id, nickname, password, idPicture, url);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }*/
}