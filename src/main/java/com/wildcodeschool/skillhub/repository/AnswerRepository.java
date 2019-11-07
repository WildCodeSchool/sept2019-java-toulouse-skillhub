package com.wildcodeschool.skillhub.repository;

import com.wildcodeschool.skillhub.entity.Answer;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

    public Answer saveAnswer(Long questionId, String answerArea, Date currentDate) {

        try {
            Connection connection = DriverManager.getConnection(
                    DB_URL, DB_USER, DB_PASSWORD
            );
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO answer (id_question, body, date) VALUES (?,?,?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            statement.setString(1, answerArea);

            if (statement.executeUpdate() != 1) {
                throw new SQLException("failed to insert data");
            }

            ResultSet generatedKeys = statement.getGeneratedKeys();

            if (generatedKeys.next()) {
                Long id = generatedKeys.getLong(1);
                return new Answer(questionId, answerArea, currentDate);
            } else {
                throw new SQLException("failed to get inserted id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
