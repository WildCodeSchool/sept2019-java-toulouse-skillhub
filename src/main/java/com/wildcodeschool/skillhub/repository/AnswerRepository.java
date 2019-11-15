package com.wildcodeschool.skillhub.repository;

import com.wildcodeschool.skillhub.entity.Answer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnswerRepository {

    private final static String DB_URL = "jdbc:mysql://localhost:3306/skillhub?serverTimezone=GMT";
    private final static String DB_USER = "skillhub";
    private final static String DB_PASSWORD = "5ki!!huB31";

    private static Connection connection = null;
    public static void setConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Answer> findAnswers(Long questionId) {

        try {
            setConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM answer\n" +
                            "JOIN user ON answer.id_user = user.id_user\n" +
                            "JOIN picture ON user.id_picture = picture.id_picture\n" +
                            "WHERE answer.id_question = ? ORDER BY id_answer DESC;"
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

    public Answer saveAnswer(Long questionId, String answerArea, Date currentDate, Long userId) {

        try {
            setConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO answer (id_question, body, `date`, id_user) VALUES (?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            statement.setLong(1, questionId);
            statement.setString(2, answerArea);
            statement.setDate(3, currentDate);
            statement.setLong(4, userId);

            if (statement.executeUpdate() != 1) {
                throw new SQLException("failed to insert data");
            }
            ResultSet generatedKeys = statement.getGeneratedKeys();

            if (generatedKeys.next()) {
                Long id = generatedKeys.getLong(1);
                return new Answer(id, questionId, answerArea, currentDate, userId);
            } else {
                throw new SQLException("failed to get inserted id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setResolved(Long questionId) {

        try {
            setConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE question SET resolved = 1 WHERE id_question=?"
            );
            statement.setLong(1, questionId);

            if (statement.executeUpdate() != 1) {
                throw new SQLException("failed to update data");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
