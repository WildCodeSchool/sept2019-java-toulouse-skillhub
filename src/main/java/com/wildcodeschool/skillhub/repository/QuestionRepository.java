package com.wildcodeschool.skillhub.repository;

import com.wildcodeschool.skillhub.entity.Answer;
import com.wildcodeschool.skillhub.entity.Question;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionRepository {

    private final static String DB_URL = "jdbc:mysql://localhost:3306/skillhub?serverTimezone=GMT";
    private final static String DB_USER = "skillhub";
    private final static String DB_PASSWORD = "5ki!!huB31";

    public List<Question> findAllOwn(Long userId) {

        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM question\n" +
                            "JOIN user ON question.id_user = user.id_user\n" +
                            "JOIN picture ON user.id_picture = picture.id_picture\n" +
                            "JOIN question_skill ON question.id_question = question_skill.id_question\n" +
                            "JOIN skill ON question_skill.id_skill = skill.id_skill\n" +
                            "WHERE question.id_user = ?;"
            );
            statement.setLong(1, userId);
            List<Question> questions = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String title = resultSet.getString("title");
                String body = resultSet.getString("body");
                Date date = resultSet.getDate("date");
                boolean resolved = resultSet.getBoolean("resolved");
                String author = resultSet.getString("nickname");
                String authorAvatarUrl = resultSet.getString("url");
                String skill = resultSet.getString("name");
                questions.add(new Question(userId, title, body, date, resolved, author, authorAvatarUrl, skill));
            }
            return questions;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Answer save(String answerArea) {

        try {
            Connection connection = DriverManager.getConnection(
                    DB_URL, DB_USER, DB_PASSWORD
            );
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO answer (body) VALUES (?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            statement.setString(1, answerArea);


            if (statement.executeUpdate() != 1) {
                throw new SQLException("failed to insert data");
            }

            ResultSet generatedKeys = statement.getGeneratedKeys();

            if (generatedKeys.next()) {
                Long id = generatedKeys.getLong(1);
                return new Answer(answerArea);
            } else {
                throw new SQLException("failed to get inserted id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
