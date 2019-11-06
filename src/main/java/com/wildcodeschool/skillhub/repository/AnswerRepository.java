package com.wildcodeschool.skillhub.repository;

import com.wildcodeschool.skillhub.entity.Answer;

import java.sql.*;

public class AnswerRepository {

    private final static String DB_URL = "jdbc:mysql://localhost:3306/skillhub?serverTimezone=GMT";
    private final static String DB_USER = "nabil";
    private final static String DB_PASSWORD = "Monmdppaypal7;";

    public Answer findAnswers(Long id) {

        try {
            Connection connection = DriverManager.getConnection(
                    DB_URL, DB_USER, DB_PASSWORD
            );
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM answer\n" +
                            "JOIN user ON answer.id_user = user.id_user\n" +
                            "JOIN picture ON user.id_picture = picture.id_picture\n" +
                            "WHERE answer.id_question = ?;"
            );
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String nickname = resultSet.getString("nickname");
                String body = resultSet.getString("body");
                String url = resultSet.getString("url");
                Date date = resultSet.getDate("date");
                Long idUser = resultSet.getLong("id_user");
                return new Answer(url, nickname, body, date, idUser, id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
