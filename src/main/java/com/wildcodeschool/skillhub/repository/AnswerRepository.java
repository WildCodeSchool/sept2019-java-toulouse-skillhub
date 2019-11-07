package com.wildcodeschool.skillhub.repository;

import com.wildcodeschool.skillhub.entity.Answer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnswerRepository {

    private final static String DB_URL = "jdbc:mysql://localhost:3306/skillhub?serverTimezone=GMT";
    private final static String DB_USER = "skillhub";
    private final static String DB_PASSWORD = "5ki!!huB31";

    public List<Answer> findAnswers(Long questionId) {

        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM answer\n" +
                            "JOIN user ON answer.id_user = user.id_user\n" +
                            "JOIN picture ON user.id_picture = picture.id_picture\n" +
                            "WHERE answer.id_question = ?;"
            );
            statement.setLong(1, questionId);
            List<Answer> answers = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String nickname = resultSet.getString("nickname");
                String body = resultSet.getString("body");
                String url = resultSet.getString("url");
                Date date = resultSet.getDate("date");
                Long idUser = resultSet.getLong("id_user");
                answers.add(new Answer(url, nickname, body, date, idUser, questionId));
            }
            return answers;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
