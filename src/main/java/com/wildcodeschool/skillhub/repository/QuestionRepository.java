package com.wildcodeschool.skillhub.repository;

import com.wildcodeschool.skillhub.entity.Question;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionRepository {

    private final static String DB_URL = "jdbc:mysql://localhost:3306/skillhub?serverTimezone=GMT";
    private final static String DB_USER = "skillhub";
    private final static String DB_PASSWORD = "5ki!!huB31";

    public List<Question> findOwn(Long userId) {

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
                String date = resultSet.getString("date");
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
}
